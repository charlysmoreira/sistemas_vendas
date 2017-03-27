package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Tecnico;

public interface TecnicoService {
	public Tecnico salva(Tecnico tecnico);
	public List<Tecnico> listaTecnico();
	public void remove(Tecnico tecnico);
	public List<Tecnico> pesquisa(String nome);
	public Tecnico buscaId(Long id);
}
