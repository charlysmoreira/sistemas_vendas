package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Servico;

public interface ServicoService {
	public Servico salva(Servico servico);
	public List<Servico> listaServico();
	public void remove(Servico servico);
	public List<Servico> pesquisa(String nome);
	public Servico buscaId(Long id);
}
