package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Totalizadores;

public interface TotalizadoresService {
	public Totalizadores salva(Totalizadores totalizadores);
	public List<Totalizadores> listaTotalizadores();
	public void remove(Totalizadores totalizadores);
	public List<Totalizadores> pesquisa(String nome);
	public Totalizadores buscaId(Long id);
}
