package br.uece.sistemas.controleBean;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.uece.sistemas.model.Usuario;
import br.uece.sistemas.serviceImpl.UsuarioServiceImpl;

public class UsuarioControleBean {
	
	private String nome;
	private String senha;
	
	private Usuario usuario = new Usuario();
	private UsuarioServiceImpl usuarioServiceImpl = new UsuarioServiceImpl();
	private List<Usuario> listaDeUsuarios;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public UsuarioServiceImpl getUsuarioServiceImpl() {
		return usuarioServiceImpl;
	}
	public void setUsuarioServiceImpl(UsuarioServiceImpl usuarioServiceImpl) {
		this.usuarioServiceImpl = usuarioServiceImpl;
	}
	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}
	public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void salvar() {
		try {
			if(usuario.getId() != null){
				usuarioServiceImpl.salva(usuario);
				addMessage("Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				usuarioServiceImpl.salva(usuario);
				addMessage("Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		usuario = new Usuario();
	}
	
	public List<Usuario> getListaUsuarios() {
		return usuarioServiceImpl.listaUser();
	}
	
	public void listaUsuarios() {
		if(usuario.getNome()== null || usuario.getNome().trim().isEmpty()){
			listaDeUsuarios = getListaUsuarios();
		}else{
			listaDeUsuarios = usuarioServiceImpl.pesquisa(usuario.getNome());
		}
	}
	
	public String novo(){
		usuario = new Usuario();
		return "cadastroUser";
	}
	
	public String editar() {
		buscaUsuarioPorId();
		return "cadastroUser";
	}
	
	public String remover() {
		buscaUsuarioPorId();
		usuarioServiceImpl.remove(usuario);
		addMessage("Usário Removido com sucesso", FacesMessage.SEVERITY_INFO);
		usuario = new Usuario();
		listaDeUsuarios = null;
		return "listaUsuario";
	}
	
	public void buscaUsuarioPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		usuario = usuarioServiceImpl.buscaId(id);
	}
	
	//parte de login
	public String logar(){
		Usuario usuario = usuarioServiceImpl.verificaUsuario(nome, senha);
		if(usuario != null){
		  return "listaUsuario";
		}else{
			addMessage("Usuário ou Senha Inválidos", FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}
	
	public String sair(){
		usuario = new Usuario();
		return "login";
	}
	
}
