package br.uece.sistemas.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.sistemas.enumeration.Pagamento;
import br.com.sistemas.enumeration.Parcela;
import br.com.sistemas.enumeration.TipoDeDesconto;
import br.com.sistemas.enumeration.TipoDeEntrada;

@Entity
public class FormaDePagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Pagamento pagamento;
	@Enumerated(EnumType.STRING)
	private Parcela parcela;
	@Enumerated(EnumType.STRING)
	private TipoDeDesconto tipoDeDesconto;
	@Enumerated(EnumType.STRING)
	private TipoDeEntrada tipoDeEntrada;
	private BigDecimal desconto;
	private BigDecimal valorEntrada;
	private boolean juros;
	private boolean taxaFixa;
	
	public FormaDePagamento(Long id, Pagamento pagamento, Parcela parcela,
			TipoDeDesconto tipoDeDesconto, TipoDeEntrada tipoDeEntrada,
			BigDecimal desconto, BigDecimal valorEntrada, boolean juros,
			boolean taxaFixa) {
		this.id = id;
		this.pagamento = pagamento;
		this.parcela = parcela;
		this.tipoDeDesconto = tipoDeDesconto;
		this.tipoDeEntrada = tipoDeEntrada;
		this.desconto = desconto;
		this.valorEntrada = valorEntrada;
		this.juros = juros;
		this.taxaFixa = taxaFixa;
	}
	
	public FormaDePagamento() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public Parcela getParcela() {
		return parcela;
	}
	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}
	public TipoDeDesconto getTipoDeDesconto() {
		return tipoDeDesconto;
	}
	public void setTipoDeDesconto(TipoDeDesconto tipoDeDesconto) {
		this.tipoDeDesconto = tipoDeDesconto;
	}
	public TipoDeEntrada getTipoDeEntrada() {
		return tipoDeEntrada;
	}
	public void setTipoDeEntrada(TipoDeEntrada tipoDeEntrada) {
		this.tipoDeEntrada = tipoDeEntrada;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public boolean isJuros() {
		return juros;
	}
	public void setJuros(boolean juros) {
		this.juros = juros;
	}
	public boolean isTaxaFixa() {
		return taxaFixa;
	}
	public void setTaxaFixa(boolean taxaFixa) {
		this.taxaFixa = taxaFixa;
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
		FormaDePagamento other = (FormaDePagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
