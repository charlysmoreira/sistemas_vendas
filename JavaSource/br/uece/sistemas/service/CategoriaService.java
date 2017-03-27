package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Categoria;

public interface CategoriaService {
	public Categoria salva(Categoria categoria);
	public List<Categoria> listaCategoria();
	public void remove(Categoria categoria);
	public List<Categoria> pesquisa(String nome);
	public Categoria buscaId(Long id);
}
