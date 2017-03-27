package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Transportadora;

public interface TransportadoraService {
	public Transportadora salva(Transportadora transportadora);
	public List<Transportadora> listaTransportadora();
	public void remove(Transportadora transportadora);
	public List<Transportadora> pesquisa(String nome);
	public Transportadora buscaId(Long id);
}
