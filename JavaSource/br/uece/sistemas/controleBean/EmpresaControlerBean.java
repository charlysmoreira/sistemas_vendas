package br.uece.sistemas.controleBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sistemas.enumeration.Estado;
import br.uece.sistemas.model.Empresa;
import br.uece.sistemas.model.Filial;
import br.uece.sistemas.serviceImpl.EmpresaServiceImpl;

public class EmpresaControlerBean {
	
	private Empresa empresa = new Empresa();
	private EmpresaServiceImpl empresaServiceImpl = new EmpresaServiceImpl();
	private List<Empresa> listaDeEmpresas;
	private List<SelectItem> listaDeEstados;
	private Filial filial = new Filial();
	boolean alteraFilaial;
	Integer posicaoFilial;
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public EmpresaServiceImpl getEmpresaServiceImpl() {
		return empresaServiceImpl;
	}
	public void setEmpresaServiceImpl(EmpresaServiceImpl empresaServiceImpl) {
		this.empresaServiceImpl = empresaServiceImpl;
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
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
	
	public void setListaDeEstados(List<SelectItem> listaDeEstados) {
		this.listaDeEstados = listaDeEstados;
	}
	public List<Empresa> getListaDeEmpresas() {
		return listaDeEmpresas;
	}
	public void setListaDeEmpresas(List<Empresa> listaDeEmpresas) {
		this.listaDeEmpresas = listaDeEmpresas;
	}
	
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaEmpresaPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		empresa = empresaServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			empresa = empresaServiceImpl.salva(empresa);
			addMessage("Empresa Salva com Sucesso!!", FacesMessage.SEVERITY_INFO);
			empresa = new Empresa();
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void salvarFilial() {
		try {
			if (empresa.getFiliais() == null) {
				empresa.setFiliais(new ArrayList<Filial>());
				empresa.getFiliais().add(filial);
			}else if (alteraFilaial) {			
				empresa.getFiliais().set(posicaoFilial, filial);
				addMessage("Filial Alterada com sucesso", FacesMessage.SEVERITY_INFO);
			}else{
				empresa.getFiliais().add(filial);
			}
			addMessage("Filial Salva com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			addMessage("Não pode ser cadastrar", FacesMessage.SEVERITY_ERROR);
		}
		alteraFilaial = false;
		filial = new Filial();
	}
	
	public List<Empresa> getListaEmpresas() {
		return empresaServiceImpl.listaEmpresa();
	}
	
	public void listaEmpresas() {
		if(empresa.getNome()== null || empresa.getNome().trim().isEmpty()){
			listaDeEmpresas = getListaEmpresas();
		}else{
			listaDeEmpresas = empresaServiceImpl.pesquisa(empresa.getNome());
		}
	}
	
	public void editarFilial() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> requestMap = context.getExternalContext()
				.getRequestParameterMap();
		posicaoFilial = Integer.parseInt(requestMap.get("posicaoFilial"));	
		filial = empresa.getFiliais().get(posicaoFilial);
		alteraFilaial = true;
	}
	
	public void removerFilial() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> requestMap = context.getExternalContext()
				.getRequestParameterMap();
		posicaoFilial = Integer.parseInt(requestMap.get("posicaoFilial"));
		empresa.getFiliais().remove(posicaoFilial.intValue());
	}
	
	public String editarEmpresa() {
		buscaEmpresaPorId();
		return "cadastroEmpresa";
	}
	
	public String removerEmpresa() {
		buscaEmpresaPorId();
		empresaServiceImpl.remove(empresa);
		addMessage("Empresa Removido com sucesso", FacesMessage.SEVERITY_INFO);
		empresa = new Empresa();
		listaDeEmpresas = null;
		return "listaEmpresa";
	}
	
	public void modalFilial() {
		buscaEmpresaPorId();
	}
	
	public String novo(){
		empresa = new Empresa();
		return "cadastroEmpresa";
	}
}
