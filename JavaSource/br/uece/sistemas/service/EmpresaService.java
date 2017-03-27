package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Empresa;

public interface EmpresaService {
	
	public Empresa salva(Empresa empresa);
	public List<Empresa> listaEmpresa();
	public void remove(Empresa empresa);
	public List<Empresa> pesquisa(String nome);
	public Empresa buscaId(Long id);
}
