package br.uece.sistemas.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String referencia;
	private String despesa;
	@ManyToOne
	private Cliente cliente;
	@ManyToOne
	private Vendedor vendedor;
	private Date dataVenda;
	private Date dataEntrega;
	private double frete;
	private String seguro;
	private int numero;
	private String observacao;
	@ManyToOne
	private FormaDePagamento formaDePagamento;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_pedido")
	private List<ItemPedido> listaItemPedidos;

	public Pedido(Long id, String referencia, String despesa, Cliente cliente,
			Vendedor vendedor, Date dataVenda, Date dataEntrega, double frete, int numero,
			String seguro, String observacao,
			FormaDePagamento formaDePagamento, List<ItemPedido> listaItemPedidos) {
		super();
		this.id = id;
		this.referencia = referencia;
		this.despesa = despesa;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.dataVenda = dataVenda;
		this.dataEntrega = dataEntrega;
		this.frete = frete;
		this.seguro = seguro;
		this.observacao = observacao;
		this.formaDePagamento = formaDePagamento;
		this.listaItemPedidos = listaItemPedidos;
		this.numero = numero;
	}

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getDespesa() {
		return despesa;
	}
	public void setDespesa(String despesa) {
		this.despesa = despesa;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public double getFrete() {
		return frete;
	}
	public void setFrete(double frete) {
		this.frete = frete;
	}
	public String getSeguro() {
		return seguro;
	}
	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	public List<ItemPedido> getListaItemPedidos() {
		return listaItemPedidos;
	}
	public void setListaItemPedidos(List<ItemPedido> listaItemPedidos) {
		this.listaItemPedidos = listaItemPedidos;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
