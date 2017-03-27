package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Fornecedor;

public interface FornecedorService {
	public Fornecedor salva(Fornecedor fornecedor);
	public List<Fornecedor> listaFornecedor();
	public void remove(Fornecedor fornecedor);
	public List<Fornecedor> pesquisa(String nome);
	public Fornecedor buscaId(Long id);
}
