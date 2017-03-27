package br.uece.sistemas.controleBean;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.uece.sistemas.model.Tipo;
import br.uece.sistemas.serviceImpl.TipoServiceImpl;

public class TipoControlerBean {
	
	private Tipo tipo = new Tipo();
	private TipoServiceImpl tipoServiceImpl = new TipoServiceImpl();
	private List<Tipo> listaDeTipos;
	 
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public TipoServiceImpl getTipoServiceImpl() {
		return tipoServiceImpl;
	}
	public void setTipoServiceImpl(TipoServiceImpl tipoServiceImpl) {
		this.tipoServiceImpl = tipoServiceImpl;
	}
	public List<Tipo> getListaDeTipos() {
		return listaDeTipos;
	}
	public void setListaDeTipos(List<Tipo> listaDeTipos) {
		this.listaDeTipos = listaDeTipos;
	}

	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaTipoPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		tipo = tipoServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(tipo.getId() != null){
				tipoServiceImpl.salva(tipo);
				addMessage("Tipo Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				tipoServiceImpl.salva(tipo);
				addMessage("Tipo Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		tipo = new Tipo();
	}
	
	public List<Tipo> getListaTipos() {
		return tipoServiceImpl.listaTipo();
	}
	
	public void listaTipos() {
		if(tipo.getNome()== null || tipo.getNome().trim().isEmpty()){
			listaDeTipos = getListaTipos();
		}else{
			listaDeTipos = tipoServiceImpl.pesquisa(tipo.getNome());
		}
	}
	
	public String novo(){
		tipo = new Tipo();
		return "cadastroTipo";
	}
	
	public String editar() {
		buscaTipoPorId();
		return "cadastroTipo";
	}
	
}
