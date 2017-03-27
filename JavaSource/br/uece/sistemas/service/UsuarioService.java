package br.uece.sistemas.service;

import java.util.List;

import br.uece.sistemas.model.Usuario;

public interface UsuarioService {
	public Usuario salva(Usuario usuario);
	public List<Usuario> listaUser();
	public void remove(Usuario usuario);
	public List<Usuario> pesquisa(String nome);
	public Usuario buscaId(Long id);
	public Usuario verificaUsuario(String nome, String senha);
}
