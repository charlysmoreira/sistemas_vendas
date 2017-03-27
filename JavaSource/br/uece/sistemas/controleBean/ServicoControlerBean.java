package br.uece.sistemas.controleBean;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.uece.sistemas.model.Servico;
import br.uece.sistemas.serviceImpl.ServicoServiceImpl;

public class ServicoControlerBean {
	
	private Servico servico = new Servico();
	private ServicoServiceImpl servicoServiceImpl = new ServicoServiceImpl();
	private List<Servico> listaDeServicos;
	
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public ServicoServiceImpl getServicoServiceImpl() {
		return servicoServiceImpl;
	}
	public void setServicoServiceImpl(ServicoServiceImpl servicoServiceImpl) {
		this.servicoServiceImpl = servicoServiceImpl;
	}
	public List<Servico> getListaDeServicos() {
		return listaDeServicos;
	}
	public void setListaDeServicos(List<Servico> listaDeServicos) {
		this.listaDeServicos = listaDeServicos;
	}

	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaServicoPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		servico = servicoServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(servico.getId() != null){
				servicoServiceImpl.salva(servico);
				addMessage("Servico Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				servicoServiceImpl.salva(servico);
				addMessage("Servico Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		servico = new Servico();
	}
	
	public List<Servico> getListaServicos() {
		return servicoServiceImpl.listaServico();
	}
	
	public void listaServicos() {
		if(servico.getNome()== null || servico.getNome().trim().isEmpty()){
			listaDeServicos = getListaServicos();
		}else{
			listaDeServicos = servicoServiceImpl.pesquisa(servico.getNome());
		}
	}
	
	public String novo(){
		servico = new Servico();
		return "cadastroServico";
	}
	
	public String editar() {
		buscaServicoPorId();
		return "cadastroServico";
	}
	
	public String remover() {
		buscaServicoPorId();
		servicoServiceImpl.remove(servico);
		addMessage("Servico Removido com sucesso", FacesMessage.SEVERITY_INFO);
		servico = new Servico();
		listaDeServicos = null;
		return "listaServico";
	}
	
}
