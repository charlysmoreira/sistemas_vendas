package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Produto;

public interface ProdutoService {
	public Produto salva(Produto produto);
	public List<Produto> listaProduto();
	public void remove(Produto produto);
	public List<Produto> pesquisa(String nome);
	public Produto buscaId(Long id);
}
