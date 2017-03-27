package br.uece.sistemas.controleBean;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.uece.sistemas.model.Categoria;
import br.uece.sistemas.serviceImpl.CategoriaServiceImpl;

public class CategoriaControlerBean {
	
	private Categoria categoria = new Categoria();
	private CategoriaServiceImpl categoriaServiceImpl = new CategoriaServiceImpl();
	private List<Categoria> listaDeCategorias;
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public CategoriaServiceImpl getCategoriaServiceImpl() {
		return categoriaServiceImpl;
	}
	public void setCategoriaServiceImpl(CategoriaServiceImpl categoriaServiceImpl) {
		this.categoriaServiceImpl = categoriaServiceImpl;
	}
	public List<Categoria> getListaDeCategorias() {
		return listaDeCategorias;
	}
	public void setListaDeCategorias(List<Categoria> listaDeCategorias) {
		this.listaDeCategorias = listaDeCategorias;
	}
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaCategoriaPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		categoria = categoriaServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(categoria.getId() != null){
				categoriaServiceImpl.salva(categoria);
				addMessage("Categoria Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				categoriaServiceImpl.salva(categoria);
				addMessage("Categoria Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		categoria = new Categoria();
	}
	
	public List<Categoria> getListaCategorias() {
		return categoriaServiceImpl.listaCategoria();
	}
	
	public void listaCategorias() {
		if(categoria.getNome()== null || categoria.getNome().trim().isEmpty()){
			listaDeCategorias = getListaCategorias();
		}else{
			listaDeCategorias = categoriaServiceImpl.pesquisa(categoria.getNome());
		}
	}
	
	public String novo(){
		categoria = new Categoria();
		return "cadastroCategoria";
	}
	
	public String editar() {
		buscaCategoriaPorId();
		return "cadastroCategoria";
	}
	
	public String remover() {
		buscaCategoriaPorId();
		categoriaServiceImpl.remove(categoria);
		addMessage("Categoria Removido com sucesso", FacesMessage.SEVERITY_INFO);
		categoria = new Categoria();
		listaDeCategorias = null;
		return "listaCategoria";
	}
	
}
