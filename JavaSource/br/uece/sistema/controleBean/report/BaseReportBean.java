package br.uece.sistema.controleBean.report;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public abstract class BaseReportBean {

	private Map<String, Object> parameters = new HashMap<String, Object>();

	private String reportHtml;

	private String[] pagedReportHtml;

	private PagedReportHtml pagedReport;

	private JasperPrint jasperPrint;

	private boolean notPaged;

	protected boolean closed = false;
	
	protected Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/svendas");
		return ds.getConnection();
	}

	public void setNotPaged(boolean notPaged) {
		this.notPaged = notPaged;
	}

	public final void addParam(String key, Object value) {
		parameters.put(key, value);
	}

	public String getReportHtml() {
		return reportHtml;
	}

	public void setReportHtml(String reportHtml) {
		this.reportHtml = reportHtml;
	}

	public PagedReportHtml getPagedReport() {
		return pagedReport;
	}

	public void setPagedReport(PagedReportHtml pagedReport) {
		this.pagedReport = pagedReport;
	}

	public final void generate() {

		try {
			InputStream is = getServletContext().getResourceAsStream(
					"/WEB-INF/reports/" + getReport());
			JasperReport jasperReport;

			jasperReport = (JasperReport) JasperCompileManager
					.compileReport(is);

			JRAbstractBeanDataSource abstractBeanDataSource = getJRAbstractBeanDataSource();

			if (abstractBeanDataSource == null) {
				Connection connection = getConnection();
				jasperPrint = JasperFillManager.fillReport(jasperReport,
						parameters, connection);
			} else {
				jasperPrint = JasperFillManager.fillReport(jasperReport,
						parameters, abstractBeanDataSource);
			}
			reportGenerateInHtml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void beforeReportGenerate() throws Exception {
	}

	public String reportGenerate() {
		try {
			this.parameters = new HashMap<String, Object>();

			beforeReportGenerate();
			addParam("SUBREPORT_DIR",
					getServletContext().getRealPath("//WEB-INF//reports//"));
			addParam("REPORT_LOCALE", new Locale("pt", "BR"));
			generate();
			if (reportHtml != null)
				closed = true;
		} catch (Exception e) {
			closed = false;
		}
		return null;
	}

	public JRAbstractBeanDataSource getJRAbstractBeanDataSource() {
		return null;
	}

	public abstract String getReport();

	public String reportGenerateInXls() {
		getResponse().setContentType("application/xls");
		getResponse().addHeader("Content-Disposition",
				"attachment; filename=relatorio.xls");
		try {
			JRXlsExporter exporter = new JRXlsExporter();
			jasperPrint.setName("Relatorio");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					getResponse().getOutputStream());
			exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
					Boolean.TRUE);
			exporter.exportReport();
			getFacesContext().getApplication().getStateManager()
					.saveView(getFacesContext());
			getFacesContext().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void reportGenerateInPdf() {
		getResponse().setContentType("application/pdf");
		getResponse().addHeader("Content-Disposition",
				"attachment; filename=relatorio.pdf");
		try {
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					getResponse().getOutputStream());
			exporter.exportReport();
			getFacesContext().getApplication().getStateManager()
					.saveView(getFacesContext());
			getFacesContext().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void reportGenerateInHtml() {
		JRHtmlExporter exporter = new JRHtmlExporter();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(baos);
		getRequest().getSession().setAttribute(
				ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
				jasperPrint);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, writer);

		Map imagesMap = new HashMap();
		getRequest().getSession().setAttribute("IMAGES_MAP", imagesMap);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, getRequest()
				.getContextPath() + "/image?image=");

		try {
			exporter.exportReport();
			reportHtml = new String(baos.toByteArray());
			if (notPaged) {
				pagedReport(reportHtml);
				pagedReport = new PagedReportHtml(pagedReportHtml, this);
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private void pagedReport(String reportHtml2) {
		pagedReportHtml = reportHtml2.split("JR_PAGE_ANCHOR");
		String[] pagedReportHtmlTemp = new String[pagedReportHtml.length - 1];
		for (int i = 1; i < pagedReportHtml.length; i++) {

			int posInitial = pagedReportHtml[i].indexOf("<table", 0);
			int j = posInitial;
			int totalTableInitial = 0;
			while (j > -1) {
				++totalTableInitial;
				j = pagedReportHtml[i].indexOf("<table", j + 1);
			}
			j = posInitial;
			int totalTableFinal = 0;
			while (j > -1) {
				if (totalTableFinal == totalTableInitial) {
					break;
				}
				j = pagedReportHtml[i].indexOf("</table", j + 1);
				++totalTableFinal;
			}
			String reportValue = pagedReportHtml[i].substring(posInitial, j);
			pagedReportHtmlTemp[i - 1] = reportValue + "</table>";
		}
		pagedReportHtml = pagedReportHtmlTemp;
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getFacesContext().getExternalContext()
				.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) getFacesContext().getExternalContext()
				.getResponse();
	}

	public void setPagedReportHtml(String[] pagedReportHtml) {
		this.pagedReportHtml = pagedReportHtml;
	}

	public String[] getPagedReportHtml() {
		return pagedReportHtml;
	}

	protected ServletContext getServletContext() {
		return (ServletContext) getFacesContext().getExternalContext()
				.getContext();
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public boolean isClosed() {
		return closed;
	}

	public JasperPrint getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(JasperPrint jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public int getPicIndex(HSSFWorkbook wb, BufferedImage image) {
		  
		// pode converter para um array de bytes assim:  
		int index = -1;
		try {
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpeg", baos);
			byte[] picData = baos.toByteArray();
			index = wb.addPicture(picData, HSSFWorkbook.PICTURE_TYPE_JPEG);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
	}

}

