package br.uece.sistema.controlerBean.relatorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.uece.sistema.controleBean.report.BaseReportBean;
import br.uece.sistemas.entidade.report.PedidoDoReletorio;
import br.uece.sistemas.serviceImpl.PedidoServiceImpl;

public class ReletorioPedidoControllerBean extends BaseReportBean{
	
	private List<PedidoDoReletorio> listaPedido = new ArrayList<PedidoDoReletorio>();
	private PedidoServiceImpl pedidoServiceImpl = new PedidoServiceImpl();
	@Override
	public String getReport() {
		formataData();
		return "pedido.jrxml";
	}
	
	@Override
	public JRAbstractBeanDataSource getJRAbstractBeanDataSource() {
		return new JRBeanCollectionDataSource(listaPedido);
	}
	
	@Override
	public void beforeReportGenerate() throws Exception {
		listaPedido = pedidoServiceImpl.pesquisaPedidoPorData(dataInicial, dataFinal);
		if(listaPedido.size() == 0){
			addMessage("Pesquisa retornou nenhum registro!", FacesMessage.SEVERITY_INFO);
		}
		String realPath = getServletContext().getRealPath("//images//logo.png");
		addParam("img", realPath);
		super.beforeReportGenerate();
	}
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	private boolean toggle = false;
	public boolean isToggle() {
		return toggle;
	}
	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}
	private Date dataInicial;
	private Date dataFinal;
	static String dataString,dataStringDataFinal;
	
	public void formataData() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		dataString = (sd.format(dataInicial));
		dataStringDataFinal = (sd.format(dataFinal));
		try {
			dataInicial = new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
			dataFinal = new SimpleDateFormat("yyyy-MM-dd").parse(dataStringDataFinal);
			if(!dataFinal.after(dataInicial)){
				setToggle(false);
				addMessage("Data Inicial não pode ser maior que a Final", FacesMessage.SEVERITY_INFO);
			}else{
				setToggle(true);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
}
