package br.uece.sistemas.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import br.com.sistemas.enumeration.Estado;
import br.com.sistemas.enumeration.FinalidadeEmissao;
import br.com.sistemas.enumeration.NaturezaVenda;
import br.com.sistemas.enumeration.Pagamento;
import br.com.sistemas.enumeration.TipoEmissao;
import br.com.sistemas.enumeration.TipoOperacao;

public class NotaFiscalEletronica {
	
	private Long id;
	private int numeroNFe;
	private int serieNotas;
	private Date dataEmissaoNF;
	private Date saidaEntrada;
	private String horaSaidaEntrada;
	@Enumerated(EnumType.STRING)
	private Estado estado;
	@Enumerated(EnumType.STRING)
	private Pagamento pagamento;
	@Enumerated(EnumType.STRING)
	private TipoOperacao tipoOperacao;
	@Enumerated(EnumType.STRING)
	private NaturezaVenda naturezaVenda;
	@Enumerated(EnumType.STRING)
	private TipoEmissao tipoEmissao;
	@Enumerated(EnumType.STRING)
	private FinalidadeEmissao finalidadeEmissao;
	private boolean NFReferenciada;
	private String nomeDestinatario;
	private String cpfCnpjDestinatario;
	private String telefoneDest;
	private String emailDest;
	private String inscrDest;
	private String logradouroDest;
	private int numeroDest;
	private String complementoDest;
	private String bairroDest;
	private String cepDest;
	private String municipio;
	private int inscricaoEstadual;
	private int inscricaoSufruma;
	private boolean endereroDeEntregaDiferente;
	private List<ProdutoNF> listaProdutoNFs;
	private double totalBcIcms;
	private double totalIcms;
	private double totalBcIcmsSt;
	private double totalIcmsSt;
	private double totalBrutoProduto;
	private double totalFrete;
	private double totalSeguro;
	private double totalDesconto;
	private double totalIi;
	private double totalIpi;
	private double totalPis;
	private double totalPisSt;
	private double totalCofins;
	private double totalCofinsSt;
	private double totalOutrasDespesas;
	private double totalNf;
	private boolean retencaoDeImposto;
	
	public NotaFiscalEletronica() {
		// TODO Auto-generated constructor stub
	}

	public int getNumeroNFe() {
		return numeroNFe;
	}
	public void setNumeroNFe(int numeroNFe) {
		this.numeroNFe = numeroNFe;
	}
	public int getSerieNotas() {
		return serieNotas;
	}
	public void setSerieNotas(int serieNotas) {
		this.serieNotas = serieNotas;
	}
	public Date getDataEmissaoNF() {
		return dataEmissaoNF;
	}
	public void setDataEmissaoNF(Date dataEmissaoNF) {
		this.dataEmissaoNF = dataEmissaoNF;
	}
	public Date getSaidaEntrada() {
		return saidaEntrada;
	}
	public void setSaidaEntrada(Date saidaEntrada) {
		this.saidaEntrada = saidaEntrada;
	}
	public String getHoraSaidaEntrada() {
		return horaSaidaEntrada;
	}
	public void setHoraSaidaEntrada(String horaSaidaEntrada) {
		this.horaSaidaEntrada = horaSaidaEntrada;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public NaturezaVenda getNaturezaVenda() {
		return naturezaVenda;
	}
	public void setNaturezaVenda(NaturezaVenda naturezaVenda) {
		this.naturezaVenda = naturezaVenda;
	}
	public TipoEmissao getTipoEmissao() {
		return tipoEmissao;
	}
	public void setTipoEmissao(TipoEmissao tipoEmissao) {
		this.tipoEmissao = tipoEmissao;
	}
	public FinalidadeEmissao getFinalidadeEmissao() {
		return finalidadeEmissao;
	}
	public void setFinalidadeEmissao(FinalidadeEmissao finalidadeEmissao) {
		this.finalidadeEmissao = finalidadeEmissao;
	}
	public boolean isNFReferenciada() {
		return NFReferenciada;
	}
	public void setNFReferenciada(boolean nFReferenciada) {
		NFReferenciada = nFReferenciada;
	}
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	public String getCpfCnpjDestinatario() {
		return cpfCnpjDestinatario;
	}
	public void setCpfCnpjDestinatario(String cpfCnpjDestinatario) {
		this.cpfCnpjDestinatario = cpfCnpjDestinatario;
	}
	public String getTelefoneDest() {
		return telefoneDest;
	}
	public void setTelefoneDest(String telefoneDest) {
		this.telefoneDest = telefoneDest;
	}
	public String getEmailDest() {
		return emailDest;
	}
	public void setEmailDest(String emailDest) {
		this.emailDest = emailDest;
	}
	public String getInscrDest() {
		return inscrDest;
	}
	public void setInscrDest(String inscrDest) {
		this.inscrDest = inscrDest;
	}
	public String getLogradouroDest() {
		return logradouroDest;
	}
	public void setLogradouroDest(String logradouroDest) {
		this.logradouroDest = logradouroDest;
	}
	public int getNumeroDest() {
		return numeroDest;
	}
	public void setNumeroDest(int numeroDest) {
		this.numeroDest = numeroDest;
	}
	public String getComplementoDest() {
		return complementoDest;
	}
	public void setComplementoDest(String complementoDest) {
		this.complementoDest = complementoDest;
	}
	public String getBairroDest() {
		return bairroDest;
	}
	public void setBairroDest(String bairroDest) {
		this.bairroDest = bairroDest;
	}
	public String getCepDest() {
		return cepDest;
	}
	public void setCepDest(String cepDest) {
		this.cepDest = cepDest;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public int getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(int inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public int getInscricaoSufruma() {
		return inscricaoSufruma;
	}
	public void setInscricaoSufruma(int inscricaoSufruma) {
		this.inscricaoSufruma = inscricaoSufruma;
	}
	public boolean isEndereroDeEntregaDiferente() {
		return endereroDeEntregaDiferente;
	}
	public void setEndereroDeEntregaDiferente(boolean endereroDeEntregaDiferente) {
		this.endereroDeEntregaDiferente = endereroDeEntregaDiferente;
	}
	public List<ProdutoNF> getListaProdutoNFs() {
		return listaProdutoNFs;
	}
	public void setListaProdutoNFs(List<ProdutoNF> listaProdutoNFs) {
		this.listaProdutoNFs = listaProdutoNFs;
	}
	public double getTotalBcIcms() {
		return totalBcIcms;
	}
	public void setTotalBcIcms(double totalBcIcms) {
		this.totalBcIcms = totalBcIcms;
	}
	public double getTotalIcms() {
		return totalIcms;
	}
	public void setTotalIcms(double totalIcms) {
		this.totalIcms = totalIcms;
	}
	public double getTotalBcIcmsSt() {
		return totalBcIcmsSt;
	}
	public void setTotalBcIcmsSt(double totalBcIcmsSt) {
		this.totalBcIcmsSt = totalBcIcmsSt;
	}
	public double getTotalIcmsSt() {
		return totalIcmsSt;
	}
	public void setTotalIcmsSt(double totalIcmsSt) {
		this.totalIcmsSt = totalIcmsSt;
	}
	public double getTotalBrutoProduto() {
		return totalBrutoProduto;
	}
	public void setTotalBrutoProduto(double totalBrutoProduto) {
		this.totalBrutoProduto = totalBrutoProduto;
	}
	public double getTotalFrete() {
		return totalFrete;
	}
	public void setTotalFrete(double totalFrete) {
		this.totalFrete = totalFrete;
	}
	public double getTotalSeguro() {
		return totalSeguro;
	}
	public void setTotalSeguro(double totalSeguro) {
		this.totalSeguro = totalSeguro;
	}
	public double getTotalDesconto() {
		return totalDesconto;
	}
	public void setTotalDesconto(double totalDesconto) {
		this.totalDesconto = totalDesconto;
	}
	public double getTotalIi() {
		return totalIi;
	}
	public void setTotalIi(double totalIi) {
		this.totalIi = totalIi;
	}
	public double getTotalIpi() {
		return totalIpi;
	}
	public void setTotalIpi(double totalIpi) {
		this.totalIpi = totalIpi;
	}
	public double getTotalPis() {
		return totalPis;
	}
	public void setTotalPis(double totalPis) {
		this.totalPis = totalPis;
	}
	public double getTotalPisSt() {
		return totalPisSt;
	}
	public void setTotalPisSt(double totalPisSt) {
		this.totalPisSt = totalPisSt;
	}
	public double getTotalCofins() {
		return totalCofins;
	}
	public void setTotalCofins(double totalCofins) {
		this.totalCofins = totalCofins;
	}
	public double getTotalCofinsSt() {
		return totalCofinsSt;
	}
	public void setTotalCofinsSt(double totalCofinsSt) {
		this.totalCofinsSt = totalCofinsSt;
	}
	public double getTotalOutrasDespesas() {
		return totalOutrasDespesas;
	}
	public void setTotalOutrasDespesas(double totalOutrasDespesas) {
		this.totalOutrasDespesas = totalOutrasDespesas;
	}
	public double getTotalNf() {
		return totalNf;
	}
	public void setTotalNf(double totalNf) {
		this.totalNf = totalNf;
	}
	public boolean isRetencaoDeImposto() {
		return retencaoDeImposto;
	}
	public void setRetencaoDeImposto(boolean retencaoDeImposto) {
		this.retencaoDeImposto = retencaoDeImposto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotaFiscalEletronica other = (NotaFiscalEletronica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
