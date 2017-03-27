package br.uece.sistemas.entidade.report;

public class PedidoDoReletorio {
	
	private String nomeCliente;
	private String cnpj;
	
	public PedidoDoReletorio(String nomeCliente, String cnpj) {
		this.nomeCliente = nomeCliente;
		this.cnpj = cnpj;
	}
	
	public PedidoDoReletorio() {
		// TODO Auto-generated constructor stub
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
