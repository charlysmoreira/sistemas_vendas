package br.uece.sistemas.controleBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sistemas.enumeration.Estado;
import br.uece.sistemas.model.Cliente;
import br.uece.sistemas.serviceImpl.ClienteServiceImpl;

public class ClienteControlerBean {
	
	private Cliente cliente = new Cliente();
	private ClienteServiceImpl clienteServiceImpl = new ClienteServiceImpl();
	private List<Cliente> listaDeClientes;
	private List<SelectItem> listaDeEstados;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ClienteServiceImpl getClienteServiceImpl() {
		return clienteServiceImpl;
	}
	public void setClienteServiceImpl(ClienteServiceImpl clienteServiceImpl) {
		this.clienteServiceImpl = clienteServiceImpl;
	}
	public List<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}
	public void setListaDeClientes(List<Cliente> listaDeClientes) {
		this.listaDeClientes = listaDeClientes;
	}
	public void setListaDeEstados(List<SelectItem> listaDeEstados) {
		this.listaDeEstados = listaDeEstados;
	}

	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
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
	
	public void buscaClientePorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		cliente = clienteServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(cliente.getId() != null){
				clienteServiceImpl.salva(cliente);
				addMessage("Cliente Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				clienteServiceImpl.salva(cliente);
				addMessage("Cliente Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		cliente = new Cliente();
	}
	
	public List<Cliente> getListaClientes() {
		return clienteServiceImpl.listaCliente();
	}
	
	public void listaClientes() {
		if(cliente.getNome()== null || cliente.getNome().trim().isEmpty()){
			listaDeClientes = getListaClientes();
		}else{
			listaDeClientes = clienteServiceImpl.pesquisa(cliente.getNome());
		}
	}
	
	public String novo(){
		cliente = new Cliente();
		return "cadastroCliente";
	}
	
	public String editar() {
		buscaClientePorId();
		return "cadastroCliente";
	}
	
	public String remover() {
		buscaClientePorId();
		clienteServiceImpl.remove(cliente);
		addMessage("Cliente Removido com sucesso", FacesMessage.SEVERITY_INFO);
		cliente = new Cliente();
		listaDeClientes = null;
		return "listaCliente";
	}
	
}
