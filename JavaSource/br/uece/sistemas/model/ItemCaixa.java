package br.uece.sistemas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemCaixa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String produtoCaixa;
	private double valorProduto;
	private int quantidadeCaixa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProdutoCaixa() {
		return produtoCaixa;
	}
	public void setProdutoCaixa(String produtoCaixa) {
		this.produtoCaixa = produtoCaixa;
	}
	public double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
	public int getQuantidadeCaixa() {
		return quantidadeCaixa;
	}
	public void setQuantidadeCaixa(int quantidadeCaixa) {
		this.quantidadeCaixa = quantidadeCaixa;
	}
	
}
