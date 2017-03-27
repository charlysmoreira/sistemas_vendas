package br.uece.sistemas.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.sistemas.enumeration.ModalidadeBC;
import br.com.sistemas.enumeration.NaturezaVenda;
import br.com.sistemas.enumeration.Origem;
import br.com.sistemas.enumeration.SituacaoTributaria;
import br.com.sistemas.enumeration.TipoDeCalculo;

public class ProdutoNF {
	private Long id;
	private String nome;
	private String codigo;
	@Enumerated(EnumType.STRING)
	private NaturezaVenda cfop;
	private int unidadeCormecial;
	private int quantidade;
	private double valorUnitario;
	private double valorTotal;
	private String gtin;
	private String ncm;
	private String exececao;
	private double totalFrete;
	private double totalSeguro;
	private double totalDesconto;
	private double outrasDespesas;
	private String obs;
	@Enumerated(EnumType.STRING)
	private SituacaoTributaria situacaoTributaria;
	@Enumerated(EnumType.STRING)
	private SituacaoTributaria situacaoTributariaIcms;
	@Enumerated(EnumType.STRING)
	private SituacaoTributaria situacaoTributariaPis;
	@Enumerated(EnumType.STRING)
	private SituacaoTributaria situacaoTributariaCofins;
	@Enumerated(EnumType.STRING)
	private Origem origem;
	@Enumerated(EnumType.STRING)
	private ModalidadeBC modalidadeBC;
	private double baseDeCalculo;
	private double alicotaIcms;
	private double valorIcms;
	@Enumerated(EnumType.STRING)
	private TipoDeCalculo tipoDeCalculoPis;
	@Enumerated(EnumType.STRING)
	private TipoDeCalculo tipoDeCalculoCofins;
	private double baseDeCalculoPis;
	private double alicotaIcmsPis;
	private double valorIcmsPis;
	private double baseDeCalculoCofins;
	private double alicotaIcmsCofins;
	private double valorIcmsCofins;
	
	public ProdutoNF() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public NaturezaVenda getCfop() {
		return cfop;
	}
	public void setCfop(NaturezaVenda cfop) {
		this.cfop = cfop;
	}
	public int getUnidadeCormecial() {
		return unidadeCormecial;
	}
	public void setUnidadeCormecial(int unidadeCormecial) {
		this.unidadeCormecial = unidadeCormecial;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getGtin() {
		return gtin;
	}
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}
	public String getNcm() {
		return ncm;
	}
	public void setNcm(String ncm) {
		this.ncm = ncm;
	}
	public String getExececao() {
		return exececao;
	}
	public void setExececao(String exececao) {
		this.exececao = exececao;
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
	public double getOutrasDespesas() {
		return outrasDespesas;
	}
	public void setOutrasDespesas(double outrasDespesas) {
		this.outrasDespesas = outrasDespesas;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public SituacaoTributaria getSituacaoTributaria() {
		return situacaoTributaria;
	}
	public void setSituacaoTributaria(SituacaoTributaria situacaoTributaria) {
		this.situacaoTributaria = situacaoTributaria;
	}
	public SituacaoTributaria getSituacaoTributariaIcms() {
		return situacaoTributariaIcms;
	}
	public void setSituacaoTributariaIcms(SituacaoTributaria situacaoTributariaIcms) {
		this.situacaoTributariaIcms = situacaoTributariaIcms;
	}
	public SituacaoTributaria getSituacaoTributariaPis() {
		return situacaoTributariaPis;
	}
	public void setSituacaoTributariaPis(SituacaoTributaria situacaoTributariaPis) {
		this.situacaoTributariaPis = situacaoTributariaPis;
	}
	public SituacaoTributaria getSituacaoTributariaCofins() {
		return situacaoTributariaCofins;
	}
	public void setSituacaoTributariaCofins(
			SituacaoTributaria situacaoTributariaCofins) {
		this.situacaoTributariaCofins = situacaoTributariaCofins;
	}
	public Origem getOrigem() {
		return origem;
	}
	public void setOrigem(Origem origem) {
		this.origem = origem;
	}
	public ModalidadeBC getModalidadeBC() {
		return modalidadeBC;
	}
	public void setModalidadeBC(ModalidadeBC modalidadeBC) {
		this.modalidadeBC = modalidadeBC;
	}
	public double getBaseDeCalculo() {
		return baseDeCalculo;
	}
	public void setBaseDeCalculo(double baseDeCalculo) {
		this.baseDeCalculo = baseDeCalculo;
	}
	public double getAlicotaIcms() {
		return alicotaIcms;
	}
	public void setAlicotaIcms(double alicotaIcms) {
		this.alicotaIcms = alicotaIcms;
	}
	public double getValorIcms() {
		return valorIcms;
	}
	public void setValorIcms(double valorIcms) {
		this.valorIcms = valorIcms;
	}
	public TipoDeCalculo getTipoDeCalculoPis() {
		return tipoDeCalculoPis;
	}
	public void setTipoDeCalculoPis(TipoDeCalculo tipoDeCalculoPis) {
		this.tipoDeCalculoPis = tipoDeCalculoPis;
	}
	public TipoDeCalculo getTipoDeCalculoCofins() {
		return tipoDeCalculoCofins;
	}
	public void setTipoDeCalculoCofins(TipoDeCalculo tipoDeCalculoCofins) {
		this.tipoDeCalculoCofins = tipoDeCalculoCofins;
	}
	public double getBaseDeCalculoPis() {
		return baseDeCalculoPis;
	}
	public void setBaseDeCalculoPis(double baseDeCalculoPis) {
		this.baseDeCalculoPis = baseDeCalculoPis;
	}
	public double getAlicotaIcmsPis() {
		return alicotaIcmsPis;
	}
	public void setAlicotaIcmsPis(double alicotaIcmsPis) {
		this.alicotaIcmsPis = alicotaIcmsPis;
	}
	public double getValorIcmsPis() {
		return valorIcmsPis;
	}
	public void setValorIcmsPis(double valorIcmsPis) {
		this.valorIcmsPis = valorIcmsPis;
	}
	public double getBaseDeCalculoCofins() {
		return baseDeCalculoCofins;
	}
	public void setBaseDeCalculoCofins(double baseDeCalculoCofins) {
		this.baseDeCalculoCofins = baseDeCalculoCofins;
	}
	public double getAlicotaIcmsCofins() {
		return alicotaIcmsCofins;
	}
	public void setAlicotaIcmsCofins(double alicotaIcmsCofins) {
		this.alicotaIcmsCofins = alicotaIcmsCofins;
	}
	public double getValorIcmsCofins() {
		return valorIcmsCofins;
	}
	public void setValorIcmsCofins(double valorIcmsCofins) {
		this.valorIcmsCofins = valorIcmsCofins;
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
		ProdutoNF other = (ProdutoNF) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
