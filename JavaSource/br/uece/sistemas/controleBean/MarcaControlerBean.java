package br.uece.sistemas.controleBean;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.uece.sistemas.model.Categoria;
import br.uece.sistemas.model.Marca;
import br.uece.sistemas.serviceImpl.MarcaServiceImpl;

public class MarcaControlerBean {
	
	private Marca marca = new Marca();
	private MarcaServiceImpl marcaServiceImpl = new MarcaServiceImpl();
	private List<Marca> listaDeMarcas;
	 
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public MarcaServiceImpl getMarcaServiceImpl() {
		return marcaServiceImpl;
	}
	public void setMarcaServiceImpl(MarcaServiceImpl marcaServiceImpl) {
		this.marcaServiceImpl = marcaServiceImpl;
	}
	public List<Marca> getListaDeMarcas() {
		return listaDeMarcas;
	}
	public void setListaDeMarcas(List<Marca> listaDeMarcas) {
		this.listaDeMarcas = listaDeMarcas;
	}
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaMarcaPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		marca = marcaServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(marca.getId() != null){
				marcaServiceImpl.salva(marca);
				addMessage("Marca Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				marcaServiceImpl.salva(marca);
				addMessage("Marca Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		marca = new Marca();
	}
	
	public List<Marca> getListaMarcas() {
		return marcaServiceImpl.listaMarca();
	}
	
	public void listaMarcas() {
		if(marca.getNome()== null || marca.getNome().trim().isEmpty()){
			listaDeMarcas = getListaMarcas();
		}else{
			listaDeMarcas = marcaServiceImpl.pesquisa(marca.getNome());
		}
	}
	
	public String novo(){
		marca = new Marca();
		return "cadastroMarca";
	}
	
	public String editar() {
		buscaMarcaPorId();
		return "cadastroMarca";
	}
	
	public String remover() {
		buscaMarcaPorId();
		marcaServiceImpl.remove(marca);
		addMessage("Marca Removido com sucesso", FacesMessage.SEVERITY_INFO);
		marca = new Marca();
		listaDeMarcas = null;
		return "listaMarca";
	}
	
}
