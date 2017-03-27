package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Cliente;

public interface ClienteService {
	public Cliente salva(Cliente cliente);
	public List<Cliente> listaCliente();
	public void remove(Cliente cliente);
	public List<Cliente> pesquisa(String nome);
	public Cliente buscaId(Long id);
}
