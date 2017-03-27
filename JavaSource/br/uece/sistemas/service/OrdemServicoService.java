package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.OrdemServico;

public interface OrdemServicoService {
	
	public OrdemServico salva(OrdemServico ordemServico);
	public List<OrdemServico> listaOrdemServico();
	public void remove(OrdemServico ordemServico);
	public List<OrdemServico> pesquisa(String nome);
	public OrdemServico buscaId(Long id);
}
