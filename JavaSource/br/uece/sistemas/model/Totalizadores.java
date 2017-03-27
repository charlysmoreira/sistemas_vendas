package br.uece.sistemas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Totalizadores {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
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
	
	public Totalizadores() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		Totalizadores other = (Totalizadores) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
