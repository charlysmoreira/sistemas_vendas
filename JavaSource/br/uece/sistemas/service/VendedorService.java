package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Vendedor;

public interface VendedorService {
	public Vendedor salva(Vendedor vendedor);
	public List<Vendedor> listaVendedor();
	public void remove(Vendedor vendedor);
	public List<Vendedor> pesquisa(String nome);
	public Vendedor buscaId(Long id);
}
