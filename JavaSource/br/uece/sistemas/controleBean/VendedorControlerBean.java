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
import br.uece.sistemas.model.Filial;
import br.uece.sistemas.model.Tipo;
import br.uece.sistemas.model.Vendedor;
import br.uece.sistemas.serviceImpl.CidadeServiceImpl;
import br.uece.sistemas.serviceImpl.FilialServiceImpl;
import br.uece.sistemas.serviceImpl.TipoServiceImpl;
import br.uece.sistemas.serviceImpl.VendedorServiceImpl;

public class VendedorControlerBean {
	
	private Vendedor vendedor = new Vendedor();
	private VendedorServiceImpl vendedorServiceImpl = new VendedorServiceImpl();
	private FilialServiceImpl filialServiceImpl = new FilialServiceImpl();
	private CidadeServiceImpl cidadeServiceImpl = new CidadeServiceImpl();
	private TipoServiceImpl tipoServiceImpl = new TipoServiceImpl();
	private List<Vendedor> listaDeVendedores;
	private List<SelectItem> listaDeEstados;
	private List<SelectItem> filiais;
	private List<SelectItem> tipos;
	private List<SelectItem> cidades;
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public VendedorServiceImpl getVendedorServiceImpl() {
		return vendedorServiceImpl;
	}
	public void setVendedorServiceImpl(VendedorServiceImpl vendedorServiceImpl) {
		this.vendedorServiceImpl = vendedorServiceImpl;
	}
	public List<Vendedor> getListaDeVendedores() {
		return listaDeVendedores;
	}
	public void setListaDeVendedores(List<Vendedor> listaDeVendedores) {
		this.listaDeVendedores = listaDeVendedores;
	}
	public void setFiliais(List<SelectItem> filiais) {
		this.filiais = filiais;
	}
	public void setTipos(List<SelectItem> tipos) {
		this.tipos = tipos;
	}
	public void setListaDeEstados(List<SelectItem> listaDeEstados) {
		this.listaDeEstados = listaDeEstados;
	}
	public FilialServiceImpl getFilialServiceImpl() {
		return filialServiceImpl;
	}
	public void setFilialServiceImpl(FilialServiceImpl filialServiceImpl) {
		this.filialServiceImpl = filialServiceImpl;
	}
	public TipoServiceImpl getTipoServiceImpl() {
		return tipoServiceImpl;
	}
	public void setTipoServiceImpl(TipoServiceImpl tipoServiceImpl) {
		this.tipoServiceImpl = tipoServiceImpl;
	}
	public CidadeServiceImpl getCidadeServiceImpl() {
		return cidadeServiceImpl;
	}
	public void setCidadeServiceImpl(CidadeServiceImpl cidadeServiceImpl) {
		this.cidadeServiceImpl = cidadeServiceImpl;
	}
	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
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
	
	public List<SelectItem> getTipos() {
		if (tipos == null) {
			tipos = new ArrayList<SelectItem>();
			List<Tipo> tiposList = tipoServiceImpl.listaTipo();
			for (Tipo tipo : tiposList) {
				tipos.add(new SelectItem(tipo, tipo.getNome()));
			}
		}
		return tipos;
	}
	
	public List<SelectItem> getFiliais() {
		if (filiais == null) {
			filiais = new ArrayList<SelectItem>();
			List<Filial> filialList = filialServiceImpl.listaFilial();
			for (Filial filial : filialList) {
				filiais.add(new SelectItem(filial, filial.getNome()));
			}
		}
		return filiais;
	}
	
	public List<SelectItem> getCidades() {
		if (cidades == null) {
			cidades = new ArrayList<SelectItem>();
			List<Cidade> cidadeList = cidadeServiceImpl.listaCidade();
			for (Cidade cidade : cidadeList) {
				cidades.add(new SelectItem(cidade, cidade.getNome()));
			}
		}
		return cidades;
	}
	
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaVendedorPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		vendedor = vendedorServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(vendedor.getId() != null){
				vendedorServiceImpl.salva(vendedor);
				addMessage("Vendedor Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				vendedorServiceImpl.salva(vendedor);
				addMessage("Vendedor Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		vendedor = new Vendedor();
	}
	
	public List<Vendedor> getListaVendedores() {
		return vendedorServiceImpl.listaVendedor();
	}
	
	public void listaVendedores() {
		if(vendedor.getNome()== null || vendedor.getNome().trim().isEmpty()){
			listaDeVendedores = getListaVendedores();
		}else{
			listaDeVendedores = vendedorServiceImpl.pesquisa(vendedor.getNome());
		}
	}
	
	public String novo(){
		vendedor = new Vendedor();
		return "cadastroVendedor";
	}
	
	public String editar() {
		buscaVendedorPorId();
		return "cadastroVendedor";
	}
	
	public String remover() {
		buscaVendedorPorId();
		vendedorServiceImpl.remove(vendedor);
		addMessage("Vendedor Removido com sucesso", FacesMessage.SEVERITY_INFO);
		vendedor = new Vendedor();
		listaDeVendedores = null;
		return "listaVendedor";
	}
	
}
