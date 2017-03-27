package br.uece.sistemas.model;

public class PedidoFeito {
	
	private Long id;
	private String categoriaNome;
	private String nome;
	private String codigo;
	private double valorUnidade;
	private int quantidade;
	private double totalParcial;
	
	public PedidoFeito() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoriaNome() {
		return categoriaNome;
	}
	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
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
	public double getValorUnidade() {
		return valorUnidade;
	}
	public void setValorUnidade(double valorUnidade) {
		this.valorUnidade = valorUnidade;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getTotalParcial() {
		return totalParcial;
	}
	public void setTotalParcial(double totalParcial) {
		this.totalParcial = totalParcial;
	}
	
	
}
