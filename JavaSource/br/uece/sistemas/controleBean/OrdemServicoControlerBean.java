package br.uece.sistemas.controleBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.eclipse.jdt.internal.compiler.impl.ITypeRequestor;

import br.com.sistemas.enumeration.SituacaoOS;
import br.uece.sistemas.model.Cliente;
import br.uece.sistemas.model.ItemOrdemServico;
import br.uece.sistemas.model.ItemPedido;
import br.uece.sistemas.model.OrdemServico;
import br.uece.sistemas.model.Produto;
import br.uece.sistemas.model.Servico;
import br.uece.sistemas.model.Vendedor;
import br.uece.sistemas.serviceImpl.ClienteServiceImpl;
import br.uece.sistemas.serviceImpl.ItemOrdemServicoServiceImpl;
import br.uece.sistemas.serviceImpl.OrdemServicoServiceImpl;
import br.uece.sistemas.serviceImpl.ProdutoServiceImpl;
import br.uece.sistemas.serviceImpl.ServicoServiceImpl;
import br.uece.sistemas.serviceImpl.VendedorServiceImpl;

public class OrdemServicoControlerBean {
	
	private OrdemServico ordemServico = new OrdemServico();
	private OrdemServicoServiceImpl ordemServicoServiceImpl = new OrdemServicoServiceImpl();
	private VendedorServiceImpl vendedorServiceImpl = new VendedorServiceImpl();
	private ServicoServiceImpl servicoServiceImpl = new ServicoServiceImpl();
	private ClienteServiceImpl clienteServiceImpl = new ClienteServiceImpl();
	private ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl();
	private ItemOrdemServicoServiceImpl itemOrdemServicoServiceImpl = new ItemOrdemServicoServiceImpl();
	private List<SelectItem> listaDeOprecoes;
	private List<SelectItem> funcinarios;
	private List<Servico> listaDeServicos;
	private List<Produto> listaDeProdutos;
	private List<ItemOrdemServico> listaItensProduto;
	private List<ItemOrdemServico> listaItensServico;
	private List<Cliente> listaDeClientes;
	private Produto produto = new Produto();
	private Servico servico = new Servico();
	private Cliente cliente = new Cliente();
	private ItemOrdemServico itemOrdemServico = new ItemOrdemServico();
	Long posicaoServico;
	Long posicaoProduto;
	int quantidadeServico;
	int quantidadeProduto;
	private String nomeDaPesquisaServico;
	private String nomePesquisaCliente;
	private String nomeDaPesquisaProduto;
	
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	public OrdemServicoServiceImpl getOrdemServicoServiceImpl() {
		return ordemServicoServiceImpl;
	}
	public void setOrdemServicoServiceImpl(
			OrdemServicoServiceImpl ordemServicoServiceImpl) {
		this.ordemServicoServiceImpl = ordemServicoServiceImpl;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public VendedorServiceImpl getVendedorServiceImpl() {
		return vendedorServiceImpl;
	}
	public void setVendedorServiceImpl(VendedorServiceImpl vendedorServiceImpl) {
		this.vendedorServiceImpl = vendedorServiceImpl;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ItemOrdemServico getItemOrdemServico() {
		return itemOrdemServico;
	}
	public void setItemOrdemServico(ItemOrdemServico itemOrdemServico) {
		this.itemOrdemServico = itemOrdemServico;
	}
	public List<ItemOrdemServico> getListaItensProduto() {
		return listaItensProduto;
	}
	public void setListaItensProduto(List<ItemOrdemServico> listaItensProduto) {
		this.listaItensProduto = listaItensProduto;
	}
	public List<ItemOrdemServico> getListaItensServico() {
		return listaItensServico;
	}
	public void setListaItensServico(List<ItemOrdemServico> listaItensServico) {
		this.listaItensServico = listaItensServico;
	}
	public ItemOrdemServicoServiceImpl getItemOrdemServicoServiceImpl() {
		return itemOrdemServicoServiceImpl;
	}
	public void setItemOrdemServicoServiceImpl(
			ItemOrdemServicoServiceImpl itemOrdemServicoServiceImpl) {
		this.itemOrdemServicoServiceImpl = itemOrdemServicoServiceImpl;
	}
	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}
	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
	 
	
	public List<SelectItem> getListaDeOprecoes() {
		buscarDataAtual();
		if (listaDeOprecoes == null) {
			listaDeOprecoes = new ArrayList<SelectItem>();
			SituacaoOS[] situacaoOSs = SituacaoOS.values();
			for (int j = 0; j < situacaoOSs.length; j++) {
				listaDeOprecoes.add(new SelectItem(situacaoOSs[j],
						situacaoOSs[j].name()));
			}
		}
		return listaDeOprecoes;
	}
	public void setListaDeOprecoes(List<SelectItem> listaDeOprecoes) {
		this.listaDeOprecoes = listaDeOprecoes;
	}
	public List<SelectItem> getFuncinarios() {
		if (funcinarios == null) {
			funcinarios = new ArrayList<SelectItem>();
			List<Vendedor> funcinarioList = vendedorServiceImpl.listaVendedor();
			for (Vendedor vendedor : funcinarioList) {
				funcinarios.add(new SelectItem(vendedor, vendedor.getNome()));
			}
		}
		return funcinarios;
	}
	public void setFuncinarios(List<SelectItem> funcinarios) {
		this.funcinarios = funcinarios;
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
	
	public ProdutoServiceImpl getProdutoServiceImpl() {
		return produtoServiceImpl;
	}
	public void setProdutoServiceImpl(ProdutoServiceImpl produtoServiceImpl) {
		this.produtoServiceImpl = produtoServiceImpl;
	}
	public String getNomeDaPesquisaProduto() {
		return nomeDaPesquisaProduto;
	}
	public void setNomeDaPesquisaProduto(String nomeDaPesquisaProduto) {
		this.nomeDaPesquisaProduto = nomeDaPesquisaProduto;
	}
	
	public List<Servico> getListaServicos() {
		return servicoServiceImpl.listaServico();
	}
	
	public void listaServicos() {
		if(servico.getNome()== null || servico.getNome().trim().isEmpty()){
			listaDeServicos = getListaServicos();
		}else{
			listaDeServicos = servicoServiceImpl.pesquisa(getNomeDaPesquisaServico());
		}
	}
	
	public List<Produto> getListaProdutos() {
		return produtoServiceImpl.listaProduto();
	}
	
	public void listaProdutos() {
		if(produto.getNome()== null || produto.getNome().trim().isEmpty()){
			listaDeProdutos = getListaProdutos();
		}else{
			listaDeProdutos = produtoServiceImpl.pesquisa(getNomeDaPesquisaProduto());
		}
	}
	
	public String getNomePesquisaCliente() {
		return nomePesquisaCliente;
	}
	public void setNomePesquisaCliente(String nomePesquisaCliente) {
		this.nomePesquisaCliente = nomePesquisaCliente;
	}
	public void listaClientes() {
		if(cliente.getNome()== null || cliente.getNome().trim().isEmpty()){
			listaDeClientes = getListaClientes();
		}else{
			listaDeClientes = clienteServiceImpl.pesquisa(getNomePesquisaCliente());
		}
	}
	
	public List<Cliente> getListaClientes() {
		return clienteServiceImpl.listaCliente();
	}
	
	public String getNomeDaPesquisaServico() {
		return nomeDaPesquisaServico;
	}
	public void setNomeDaPesquisaServico(String nomeDaPesquisaServico) {
		this.nomeDaPesquisaServico = nomeDaPesquisaServico;
	}
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaOrdemServicoPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		ordemServico = ordemServicoServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			ordemServico.setCliente(cliente);
			ordemServico.setDataInicio(dataAtual);
			ordemServico.setListaItemOrdemServicos(new ArrayList<ItemOrdemServico>());
			if(listaItensProduto != null){
				if(listaItensProduto.size()!= 0){
					for (int k = 0; k < listaItensProduto.size(); k++) {
						ordemServico.getListaItemOrdemServicos().add(listaItensProduto.get(k));
						itemOrdemServico = new ItemOrdemServico();
					}
					}
			}else{
				if(listaItensServico.size()!= 0){
					for (int i = 0; i < listaItensServico.size(); i++) {
						ordemServico.getListaItemOrdemServicos().add(listaItensServico.get(i));
						itemOrdemServico = new ItemOrdemServico();
					}
				}
			}
			ordemServico = ordemServicoServiceImpl.salva(ordemServico);
			addMessage("OrdemServico Salva com Sucesso!!", FacesMessage.SEVERITY_INFO);
			ordemServico = new OrdemServico();
			cliente = new Cliente();
			listaItensServico = new ArrayList<ItemOrdemServico>();
			listaItensProduto = new ArrayList<ItemOrdemServico>();
		} catch (Exception e) {
			addMessage("Erro ao Salvar OS", FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void addProduto() {
		try {
			 if (listaItensProduto == null) {
				 listaItensProduto = new ArrayList<ItemOrdemServico>();
				 itemOrdemServico.setProduto(produto);
				 itemOrdemServico.setQuantidadeProduto(quantidadeProduto);
				 listaItensProduto.add(itemOrdemServico);
			    }else{
			    	itemOrdemServico.setProduto(produto);
			    	itemOrdemServico.setQuantidadeProduto(quantidadeProduto);
			    	listaItensProduto.add(itemOrdemServico);
			 }
		} catch (Exception e) {
			addMessage("Não pode ser adicionado", FacesMessage.SEVERITY_ERROR);
		}
		produto = new Produto();
		itemOrdemServico = new ItemOrdemServico();
		quantidadeProduto = 0;
	}
	
	public List<OrdemServico> getListaOrdemServicos() {
		return ordemServicoServiceImpl.listaOrdemServico();
	}
	
	public void removerServico() {
		if (listaItensServico.size() > 0) {   
			listaItensServico.remove(itemOrdemServico);
		}
	}
	
	public void removerProduto() {
		if (listaItensProduto.size() > 0) {   
			listaItensProduto.remove(itemOrdemServico);
		}
	}
	
	 private static Date dataAtual;

	 public static void buscarDataAtual() {
		 dataAtual =  new Date();
	  }
	public Date getDataAtual() {
		return dataAtual;
	}
	public int getQuantidadeServico() {
		return quantidadeServico;
	}
	public void setQuantidadeServico(int quantidadeServico) {
		this.quantidadeServico = quantidadeServico;
	}
	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}
	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}
	
	public void addServico() {
		try {
			 if (listaItensServico == null) {
				 listaItensServico = new ArrayList<ItemOrdemServico>();
				 itemOrdemServico.setServico(servico);
				 itemOrdemServico.setQuantidade(quantidadeServico);
				 listaItensServico.add(itemOrdemServico);
				}else{
					 itemOrdemServico.setServico(servico);
					 itemOrdemServico.setQuantidade(quantidadeServico);
					 listaItensServico.add(itemOrdemServico);
				}
		} catch (Exception e) {
			addMessage("Não pode ser adicionado", FacesMessage.SEVERITY_ERROR);
		}
		servico = new Servico();
		itemOrdemServico = new ItemOrdemServico();
		quantidadeServico = 0;
	}
	public void icluirServico() {
		buscaServicoPorId();

	}
	public void buscaServicoPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		servico = servicoServiceImpl.buscaId(id);
		
	}
	
	public void icluirProduto() {
		buscaProdutoPorId();

	}
	
	private void buscaProdutoPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		produto = produtoServiceImpl.buscaId(id);
		
	}
	public void icluirCliente() {
		buscaClientePorId();
		
	}
	public void buscaClientePorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		cliente = clienteServiceImpl.buscaId(id);
		
	}
}
