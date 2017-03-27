package br.uece.sistemas.model;

public class ProdutoCaixa {
	
	private String nomeProduto;
	private double valorProduto;
	private int quantidadeProduto;
	private double totalParcial;
	
	public ProdutoCaixa() {
		// TODO Auto-generated constructor stub
	}
	 
	public ProdutoCaixa(String nomeProduto, double valorProduto,
			int quantidadeProduto, double totalParcial) {
		this.nomeProduto = nomeProduto;
		this.valorProduto = valorProduto;
		this.quantidadeProduto = quantidadeProduto;
		this.totalParcial = totalParcial;
	}

	public double getTotalParcial() {
		return totalParcial;
	}
	public void setTotalParcial(double totalParcial) {
		this.totalParcial = totalParcial;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}
	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}
	
	
}
