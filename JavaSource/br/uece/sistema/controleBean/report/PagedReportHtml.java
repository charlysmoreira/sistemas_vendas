package br.uece.sistema.controleBean.report;

public class PagedReportHtml {
	
	private String[] pagedReportHtml;
	private int page;
	private BaseReportBean baseReportBean;

	public PagedReportHtml(String[] pagedReportHtml,
			BaseReportBean baseReportBean) {
		this.pagedReportHtml = pagedReportHtml;
		this.baseReportBean = baseReportBean;
		firstPage();
	}

	public int getTotal() {
		return pagedReportHtml == null ? 0 : pagedReportHtml.length;
	}

	public boolean getFirstPageEnable() {
		return !(pagedReportHtml != null && page > 0);
	}

	public boolean getLastPageEnable() {
		return !(pagedReportHtml != null && page < pagedReportHtml.length - 1);
	}

	public boolean getNextPageEnable() {
		return !(pagedReportHtml != null && page < pagedReportHtml.length - 1);
	}

	public boolean getPreviousPageEnable() {
		return !(pagedReportHtml != null && page > 0);
	}

	public String nextPage() {
		page += 1;
		baseReportBean.setReportHtml(pagedReportHtml[page]);
		return null;
	}

	public String lastPage() {
		page = pagedReportHtml.length - 1;
		baseReportBean.setReportHtml(pagedReportHtml[page]);
		return null;
	}

	public String previousPage() {
		page -= 1;
		baseReportBean.setReportHtml(pagedReportHtml[page]);
		return null;
	}

	public String firstPage() {
		page = 0;
		if (pagedReportHtml != null)
			baseReportBean.setReportHtml(pagedReportHtml[page]);
		return null;
	}

	public int getPage() {
		return page;
	}

}
