package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Marca;

public interface MarcaService {
	public Marca salva(Marca marca);
	public List<Marca> listaMarca();
	public void remove(Marca marca);
	public List<Marca> pesquisa(String nome);
	public Marca buscaId(Long id);
}
