package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Tipo;

public interface TipoService {
	public Tipo salva(Tipo tipo);
	public List<Tipo> listaTipo();
	public List<Tipo> pesquisa(String nome);
	public Tipo buscaId(Long id);
}
