package br.uece.sistemas.controleBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sistemas.enumeration.Estado;
import br.uece.sistemas.model.Fornecedor;
import br.uece.sistemas.serviceImpl.FornecedorServiceImpl;

public class FornecedorControlerBean {
	
	private Fornecedor fornecedor = new Fornecedor();
	private FornecedorServiceImpl fornecedorServiceImpl = new FornecedorServiceImpl();
	private List<Fornecedor> listaDeFornecedores;
	private List<SelectItem> listaDeEstados;
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public FornecedorServiceImpl getFornecedorServiceImpl() {
		return fornecedorServiceImpl;
	}
	public void setFornecedorServiceImpl(FornecedorServiceImpl fornecedorServiceImpl) {
		this.fornecedorServiceImpl = fornecedorServiceImpl;
	}
	public List<Fornecedor> getListaDeFornecedores() {
		return listaDeFornecedores;
	}
	public void setListaDeFornecedores(List<Fornecedor> listaDeFornecedores) {
		this.listaDeFornecedores = listaDeFornecedores;
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
	
	public void buscaFornecedorPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		fornecedor = fornecedorServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(fornecedor.getId() != null){
				fornecedorServiceImpl.salva(fornecedor);
				addMessage("Fornecedor Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				fornecedorServiceImpl.salva(fornecedor);
				addMessage("Fornecedor Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		fornecedor = new Fornecedor();
	}
	
	public List<Fornecedor> getListaFornecedores() {
		return fornecedorServiceImpl.listaFornecedor();
	}
	
	public void listaFornecedores() {
		if(fornecedor.getNome()== null || fornecedor.getNome().trim().isEmpty()){
			listaDeFornecedores = getListaFornecedores();
		}else{
			listaDeFornecedores = fornecedorServiceImpl.pesquisa(fornecedor.getNome());
		}
	}
	
	public String novo(){
		fornecedor = new Fornecedor();
		return "cadastroFornecedor";
	}
	
	public String editar() {
		buscaFornecedorPorId();
		return "cadastroFornecedor";
	}
	
	public String remover() {
		buscaFornecedorPorId();
		fornecedorServiceImpl.remove(fornecedor);
		addMessage("Fornecedor Removido com sucesso", FacesMessage.SEVERITY_INFO);
		fornecedor = new Fornecedor();
		listaDeFornecedores = null;
		return "listaFornecedor";
	}
	
}
