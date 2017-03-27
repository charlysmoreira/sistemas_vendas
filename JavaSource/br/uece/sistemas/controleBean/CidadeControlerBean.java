package br.uece.sistemas.controleBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sistemas.enumeration.Estado;
import br.uece.sistemas.model.Cidade;
import br.uece.sistemas.serviceImpl.CidadeServiceImpl;

public class CidadeControlerBean {
	
	private Cidade cidade = new Cidade();
	private CidadeServiceImpl cidadeServiceImpl = new CidadeServiceImpl();
	private List<Cidade> listaDeCidades;
	private List<SelectItem> listaDeEstados;
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public CidadeServiceImpl getCidadeServiceImpl() {
		return cidadeServiceImpl;
	}
	public void setCidadeServiceImpl(CidadeServiceImpl cidadeServiceImpl) {
		this.cidadeServiceImpl = cidadeServiceImpl;
	}
	public List<Cidade> getListaDeCidades() {
		return listaDeCidades;
	}
	public void setListaDeCidades(List<Cidade> listaDeCidades) {
		this.listaDeCidades = listaDeCidades;
	}
	
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaCidadePorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		cidade = cidadeServiceImpl.buscaId(id);
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
	
	public void salvar(){
		try {
			if(cidade.getId() != null){
				cidadeServiceImpl.salva(cidade);
				addMessage("Categoria Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				cidadeServiceImpl.salva(cidade);
				addMessage("Categoria Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		cidade = new Cidade();
	}
	
	public List<Cidade> getListaCidades() {
		return cidadeServiceImpl.listaCidade();
	}
	
	public void listaCidades() {
		if(cidade.getNome()== null || cidade.getNome().trim().isEmpty()){
			listaDeCidades = getListaCidades();
		}else{
			listaDeCidades = cidadeServiceImpl.pesquisa(cidade.getNome());
		}
	}
	
	public String novo(){
		cidade = new Cidade();
		return "cadastroCidade";
	}
	
	public String editar() {
		buscaCidadePorId();
		return "cadastroCidade";
	}
	
}
