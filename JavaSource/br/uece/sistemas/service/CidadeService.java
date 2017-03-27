package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Cidade;

public interface CidadeService {
	public Cidade salva(Cidade cidade);
	public List<Cidade> listaCidade();
	public List<Cidade> pesquisa(String nome);
	public Cidade buscaId(Long id);
}
