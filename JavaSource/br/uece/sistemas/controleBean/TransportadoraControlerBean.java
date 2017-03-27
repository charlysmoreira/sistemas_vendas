package br.uece.sistemas.controleBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sistemas.enumeration.Estado;
import br.com.sistemas.enumeration.Pessoa;
import br.uece.sistemas.model.Transportadora;
import br.uece.sistemas.serviceImpl.TransportadoraServiceImpl;

public class TransportadoraControlerBean {
	
	private Transportadora transportadora = new Transportadora();
	private TransportadoraServiceImpl transportadoraServiceImpl = new TransportadoraServiceImpl();
	private List<Transportadora> listaDeTransportadoras;
	private List<SelectItem> listaDeEstados;
	private List<SelectItem> listaDePessoas;
	
	public Transportadora getTransportadora() {
		return transportadora;
	}
	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}
	public TransportadoraServiceImpl getTransportadoraServiceImpl() {
		return transportadoraServiceImpl;
	}
	public void setTransportadoraServiceImpl(
			TransportadoraServiceImpl transportadoraServiceImpl) {
		this.transportadoraServiceImpl = transportadoraServiceImpl;
	}
	public List<Transportadora> getListaDeTransportadoras() {
		return listaDeTransportadoras;
	}
	public void setListaDeTransportadoras(
			List<Transportadora> listaDeTransportadoras) {
		this.listaDeTransportadoras = listaDeTransportadoras;
	}
	
	public List<SelectItem> getListaDeEstados() {
		if (listaDeEstados == null) {
			listaDeEstados = new ArrayList<SelectItem>();
			Estado[] estados = Estado.values();
			for (int j = 0; j < estados.length; j++) {
				listaDeEstados.add(new SelectItem(estados[j],
						estados[j].name()));
			}
		}
		return listaDeEstados;
	}
	
	public List<SelectItem> getListaDePessoas() {
		if (listaDePessoas == null) {
			listaDePessoas = new ArrayList<SelectItem>();
			Pessoa[] pessoas = Pessoa.values();
			for (int j = 0; j < pessoas.length; j++) {
				listaDePessoas.add(new SelectItem(pessoas[j],
						pessoas[j].name()));
			}
		}
		return listaDePessoas;
	}
	
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaTransportadoraPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		transportadora = transportadoraServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(transportadora.getId() != null){
				transportadoraServiceImpl.salva(transportadora);
				addMessage("Transportadora Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				transportadoraServiceImpl.salva(transportadora);
				addMessage("Transportadora Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		transportadora = new Transportadora();
	}
	
	public List<Transportadora> getListaTransportadoras() {
		return transportadoraServiceImpl.listaTransportadora();
	}
	
	public void listaTransportadoras() {
		if(transportadora.getNome()== null || transportadora.getNome().trim().isEmpty()){
			listaDeTransportadoras = getListaTransportadoras();
		}else{
			listaDeTransportadoras = transportadoraServiceImpl.pesquisa(transportadora.getNome());
		}
	}
	
	public String novo(){
		transportadora = new Transportadora();
		return "cadastroTransportadora";
	}
	
	public String editar() {
		buscaTransportadoraPorId();
		return "cadastroTransportadora";
	}
	
	public String remover() {
		buscaTransportadoraPorId();
		transportadoraServiceImpl.remove(transportadora);
		addMessage("Transportadora Removido com sucesso", FacesMessage.SEVERITY_INFO);
		transportadora = new Transportadora();
		listaDeTransportadoras = null;
		return "listaTransportadora";
	}
	
}
