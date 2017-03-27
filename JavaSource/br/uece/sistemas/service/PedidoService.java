package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Pedido;

public interface PedidoService {
	public Pedido salva(Pedido pedido);
	public List<Pedido> listaPedido();
	public void remove(Pedido pedido);
	public List<Pedido> pesquisa(String nome);
	public Pedido buscaId(Long id);
}
