package br.uece.sistemas.controleBean;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.uece.sistemas.model.Tecnico;
import br.uece.sistemas.serviceImpl.TecnicoServiceImpl;

public class TecnicoControlerBean {
	
	private Tecnico tecnico = new Tecnico();
	private TecnicoServiceImpl tecnicoServiceImpl = new TecnicoServiceImpl();
	private List<Tecnico> listaDeTecnicos;
	
	public Tecnico getTecnico() {
		return tecnico;
	}
	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	public TecnicoServiceImpl getTecnicoServiceImpl() {
		return tecnicoServiceImpl;
	}
	public void setTecnicoServiceImpl(TecnicoServiceImpl tecnicoServiceImpl) {
		this.tecnicoServiceImpl = tecnicoServiceImpl;
	}
	public List<Tecnico> getListaDeTecnicos() {
		return listaDeTecnicos;
	}
	public void setListaDeTecnicos(List<Tecnico> listaDeTecnicos) {
		this.listaDeTecnicos = listaDeTecnicos;
	}

	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaTecnicoPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		tecnico = tecnicoServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(tecnico.getId() != null){
				tecnicoServiceImpl.salva(tecnico);
				addMessage("Tecnico Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				tecnicoServiceImpl.salva(tecnico);
				addMessage("Tecnico Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		tecnico = new Tecnico();
	}
	
	public List<Tecnico> getListaTecnicos() {
		return tecnicoServiceImpl.listaTecnico();
	}
	
	public void listaTecnicos() {
		if(tecnico.getNome()== null || tecnico.getNome().trim().isEmpty()){
			listaDeTecnicos = getListaTecnicos();
		}else{
			listaDeTecnicos = tecnicoServiceImpl.pesquisa(tecnico.getNome());
		}
	}
	
	public String novo(){
		tecnico = new Tecnico();
		return "cadastroTecnico";
	}
	
	public String editar() {
		buscaTecnicoPorId();
		return "cadastroTecnico";
	}
	
	public String remover() {
		buscaTecnicoPorId();
		tecnicoServiceImpl.remove(tecnico);
		addMessage("Tecnico Removido com sucesso", FacesMessage.SEVERITY_INFO);
		tecnico = new Tecnico();
		listaDeTecnicos = null;
		return "listaTecnico";
	}
	
}
