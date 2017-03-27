package br.uece.sistemas.controleBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.uece.sistemas.model.Totalizadores;
import br.uece.sistemas.serviceImpl.TotalizadoresServiceImpl;

public class TotalizadoresControlerBean {
	
	private Totalizadores totalizadores = new Totalizadores();
	private TotalizadoresServiceImpl totalizadoresServiceImpl = new TotalizadoresServiceImpl();
	private List<Totalizadores> listaDeTotalizadores;
	
	public Totalizadores getTotalizadores() {
		return totalizadores;
	}
	public void setTotalizadores(Totalizadores totalizadores) {
		this.totalizadores = totalizadores;
	}
	public TotalizadoresServiceImpl getTotalizadoresServiceImpl() {
		return totalizadoresServiceImpl;
	}
	public void setTotalizadoresServiceImpl(
			TotalizadoresServiceImpl totalizadoresServiceImpl) {
		this.totalizadoresServiceImpl = totalizadoresServiceImpl;
	}
	public List<Totalizadores> getListaDeTotalizadores() {
		return listaDeTotalizadores;
	}
	public void setListaDeTotalizadores(List<Totalizadores> listaDeTotalizadores) {
		this.listaDeTotalizadores = listaDeTotalizadores;
	}

	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void salvar(){
		try {
			if(totalizadores.getId() != null){
				totalizadoresServiceImpl.salva(totalizadores);
				addMessage("Totalizadores Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				totalizadoresServiceImpl.salva(totalizadores);
				addMessage("Totalizadores Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		totalizadores = new Totalizadores();
	}
	
	public List<Totalizadores> getListaTotalizadores() {
		return totalizadoresServiceImpl.listaTotalizadores();
	}
	
	public void listaTotalizadores() {
		 listaDeTotalizadores = getListaTotalizadores();
	}
	
	public String novo(){
		totalizadores = new Totalizadores();
		return "cadastroTotalizadores";
	}
	
	public String editar() {
		return "cadastroTotalizadores";
	}
}
