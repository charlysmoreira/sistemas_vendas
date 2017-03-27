package br.uece.sistemas.controleBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sistemas.enumeration.Pagamento;
import br.com.sistemas.enumeration.Parcela;
import br.com.sistemas.enumeration.TipoDeDesconto;
import br.com.sistemas.enumeration.TipoDeEntrada;
import br.uece.sistemas.model.Cliente;
import br.uece.sistemas.model.FormaDePagamento;
import br.uece.sistemas.model.ItemPedido;
import br.uece.sistemas.model.Pedido;
import br.uece.sistemas.model.PedidoFeito;
import br.uece.sistemas.model.Produto;
import br.uece.sistemas.model.Vendedor;
import br.uece.sistemas.serviceImpl.ClienteServiceImpl;
import br.uece.sistemas.serviceImpl.FormaDePagamentoServiceImpl;
import br.uece.sistemas.serviceImpl.PedidoServiceImpl;
import br.uece.sistemas.serviceImpl.ProdutoServiceImpl;
import br.uece.sistemas.serviceImpl.VendedorServiceImpl;

public class PedidoControlerBean {
	
	private Pedido pedido = new Pedido();
	private PedidoServiceImpl pedidoServiceImpl = new PedidoServiceImpl();
	private ClienteServiceImpl clienteServiceImpl = new ClienteServiceImpl();
	private VendedorServiceImpl vendedorServiceImpl = new VendedorServiceImpl();
	private ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl();
	private FormaDePagamentoServiceImpl formaDePagamentoServiceImpl = new FormaDePagamentoServiceImpl();
	private List<SelectItem> funcinarios;
	private List<Cliente> listaDeClientes;
	private List<Produto> listaDeProdutos;
	private List<ItemPedido> listaItensProduto;
	private List<SelectItem> pagamentoLista;
	private List<SelectItem> parcelaLista;
	private List<SelectItem> tipoDeDescontoLista;
	private List<SelectItem> tipoDeEntradaLista;
	private Produto produto = new Produto();
	private ItemPedido itemPedido = new ItemPedido();
	private Cliente cliente = new Cliente();
	private FormaDePagamento formaDePagamento = new FormaDePagamento();
	Long posicaoProduto;
	int quantidadeProduto;
	private String nomePesquisaCliente;
	private String nomeDaPesquisaProduto;
	double valorTotal;
	private List<PedidoFeito> listaDePedidoFeita;
	private PedidoFeito pedidoFeito = new PedidoFeito();
	private int numeroPedido;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public PedidoServiceImpl getPedidoServiceImpl() {
		return pedidoServiceImpl;
	}
	public void setPedidoServiceImpl(PedidoServiceImpl pedidoServiceImpl) {
		this.pedidoServiceImpl = pedidoServiceImpl;
	}
	public ClienteServiceImpl getClienteServiceImpl() {
		return clienteServiceImpl;
	}
	public void setClienteServiceImpl(ClienteServiceImpl clienteServiceImpl) {
		this.clienteServiceImpl = clienteServiceImpl;
	}
	public VendedorServiceImpl getVendedorServiceImpl() {
		return vendedorServiceImpl;
	}
	public void setVendedorServiceImpl(VendedorServiceImpl vendedorServiceImpl) {
		this.vendedorServiceImpl = vendedorServiceImpl;
	}
	public FormaDePagamentoServiceImpl getFormaDePagamentoServiceImpl() {
		return formaDePagamentoServiceImpl;
	}
	public void setFormaDePagamentoServiceImpl(
			FormaDePagamentoServiceImpl formaDePagamentoServiceImpl) {
		this.formaDePagamentoServiceImpl = formaDePagamentoServiceImpl;
	}
	public double getTotalParcialDePedido() {
		return totalParcialDePedido;
	}
	public void setTotalParcialDePedido(double totalParcialDePedido) {
		this.totalParcialDePedido = totalParcialDePedido;
	}
	
	public List<SelectItem> getFuncinarios() {
		buscarDataAtual();
		if (funcinarios == null) {
			funcinarios = new ArrayList<SelectItem>();
			List<Vendedor> funcinarioList = vendedorServiceImpl.listaVendedor();
			for (Vendedor vendedor : funcinarioList) {
				funcinarios.add(new SelectItem(vendedor, vendedor.getNome()));
			}
		}
		return funcinarios;
	}
	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}
	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
	public void setFuncinarios(List<SelectItem> funcinarios) {
		this.funcinarios = funcinarios;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public ItemPedido getItemPedido() {
		return itemPedido;
	}
	public void setItemPedido(ItemPedido itemPedido) {
		this.itemPedido = itemPedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getNomePesquisaCliente() {
		return nomePesquisaCliente;
	}
	public void setNomePesquisaCliente(String nomePesquisaCliente) {
		this.nomePesquisaCliente = nomePesquisaCliente;
	}
	public String getNomeDaPesquisaProduto() {
		return nomeDaPesquisaProduto;
	}
	public void setNomeDaPesquisaProduto(String nomeDaPesquisaProduto) {
		this.nomeDaPesquisaProduto = nomeDaPesquisaProduto;
	}
	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	public List<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}
	public void setListaDeClientes(List<Cliente> listaDeClientes) {
		this.listaDeClientes = listaDeClientes;
	}
	public List<ItemPedido> getListaItensProduto() {
		return listaItensProduto;
	}
	public void setListaItensProduto(List<ItemPedido> listaItensProduto) {
		this.listaItensProduto = listaItensProduto;
	}
	public ProdutoServiceImpl getProdutoServiceImpl() {
		return produtoServiceImpl;
	}
	public void setProdutoServiceImpl(ProdutoServiceImpl produtoServiceImpl) {
		this.produtoServiceImpl = produtoServiceImpl;
	}
	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}
	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}
	public void setPagamentoLista(List<SelectItem> pagamentoLista) {
		this.pagamentoLista = pagamentoLista;
	}
	public void setParcelaLista(List<SelectItem> parcelaLista) {
		this.parcelaLista = parcelaLista;
	}
	public void setTipoDeDescontoLista(List<SelectItem> tipoDeDescontoLista) {
		this.tipoDeDescontoLista = tipoDeDescontoLista;
	}
	public void setTipoDeEntradaLista(List<SelectItem> tipoDeEntradaLista) {
		this.tipoDeEntradaLista = tipoDeEntradaLista;
	}
	public List<PedidoFeito> getListaDePedidoFeita() {
		return listaDePedidoFeita;
	}
	public void setListaDePedidoFeita(List<PedidoFeito> listaDePedidoFeita) {
		this.listaDePedidoFeita = listaDePedidoFeita;
	}
	public PedidoFeito getPedidoFeito() {
		return pedidoFeito;
	}
	public void setPedidoFeito(PedidoFeito pedidoFeito) {
		this.pedidoFeito = pedidoFeito;
	}
	
	public List<SelectItem> getPagamentoLista() {
		if (pagamentoLista == null) {
			pagamentoLista = new ArrayList<SelectItem>();
			Pagamento[] pagamentos = Pagamento.values();
			for (int j = 0; j < pagamentos.length; j++) {
				pagamentoLista.add(new SelectItem(pagamentos[j],
						pagamentos[j].name()));
			}
		}
		return pagamentoLista;
	}
	public List<SelectItem> getParcelaLista() {
		if (parcelaLista == null) {
			parcelaLista = new ArrayList<SelectItem>();
			Parcela[] parcelas = Parcela.values();
			for (int j = 0; j < parcelas.length; j++) {
				parcelaLista.add(new SelectItem(parcelas[j],
						parcelas[j].name()));
			}
		}
		return parcelaLista;
	}
	public List<SelectItem> getTipoDeDescontoLista() {
		if (tipoDeDescontoLista == null) {
			tipoDeDescontoLista = new ArrayList<SelectItem>();
			TipoDeDesconto[] tipoDeDescontos = TipoDeDesconto.values();
			for (int j = 0; j < tipoDeDescontos.length; j++) {
				tipoDeDescontoLista.add(new SelectItem(tipoDeDescontos[j],
						tipoDeDescontos[j].name()));
			}
		}
		return tipoDeDescontoLista;
	}
	public List<SelectItem> getTipoDeEntradaLista() {
		if (tipoDeEntradaLista == null) {
			tipoDeEntradaLista = new ArrayList<SelectItem>();
			TipoDeEntrada[] tipoDeEntradas = TipoDeEntrada.values();
			for (int j = 0; j < tipoDeEntradas.length; j++) {
				tipoDeEntradaLista.add(new SelectItem(tipoDeEntradas[j],
						tipoDeEntradas[j].name()));
			}
		}
		return tipoDeEntradaLista;
	}
	
	private static Date dataAtual;
	static String dataString;
	public static void buscarDataAtual() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		dataString = (sd.format(new Date()));
		try {
			dataAtual = new SimpleDateFormat("yyyy-MM-dd").parse(dataString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Date getDataAtual() {
		return dataAtual;
	}

	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public List<Cliente> getListaClientes() {
		return clienteServiceImpl.listaCliente();
	}
	
	public void listaClientes() {
		if(cliente.getNome()== null || cliente.getNome().trim().isEmpty()){
			listaDeClientes = getListaClientes();
		}else{
			listaDeClientes = clienteServiceImpl.pesquisa(getNomePesquisaCliente());
		}
	}
	
	public String salvar() {
		 try {
			 pedido.setCliente(cliente);
			 pedido.setDataVenda(dataAtual);
			 pedido.setFormaDePagamento(formaDePagamento);
			 pedido.setListaItemPedidos(new ArrayList<ItemPedido>());
			 if(listaItensProduto.size()!= 0){
				for (int k = 0; k < listaItensProduto.size(); k++) {
					pedido.getListaItemPedidos().add(listaItensProduto.get(k));
					itemPedido = new ItemPedido();
				}
			}
			 pedido = pedidoServiceImpl.salva(pedido);
			 addMessage("Pedido Salva com Sucesso!!", FacesMessage.SEVERITY_INFO);
			 pedido = new Pedido();
			 cliente = new Cliente();
			 listaItensProduto = new ArrayList<ItemPedido>();
			 
		} catch (Exception e) {
			addMessage("Erro ao Salvar Pedido", FacesMessage.SEVERITY_ERROR);
		}
		return "cadastroPedido";
	}
	double totalParcialDePedido;
	
	public String salvarComoVenda() {
		for (int i = 0; i < listaItensProduto.size(); i++) {
			valorTotal += listaItensProduto.get(i).getProduto().getValor()*
			listaItensProduto.get(i).getQuantidade();
		}
		listaPedidoFeito();
		gerarNumeroPedido();
		return "visualizaVenda";
	}
	
	public Integer gerarNumeroPedido() {
		numeroPedido = (int) (Math.random() * 100000) + 1; 
		pedido.setNumero(numeroPedido);
		return numeroPedido;

	}
	
	public List<PedidoFeito> listaPedidoFeito() {
		if(listaDePedidoFeita == null){
			listaDePedidoFeita = new ArrayList<PedidoFeito>();
			for (int i = 0; i < listaItensProduto.size(); i++) {
				pedidoFeito.setCategoriaNome(listaItensProduto.get(i).getProduto().getCategoria().getNome());
				pedidoFeito.setCodigo(listaItensProduto.get(i).getProduto().getCodigo());
				pedidoFeito.setNome(listaItensProduto.get(i).getProduto().getNome());
				pedidoFeito.setValorUnidade(listaItensProduto.get(i).getProduto().getValor());
				pedidoFeito.setQuantidade(listaItensProduto.get(i).getQuantidade());
				totalParcialDePedido = listaItensProduto.get(i).getQuantidade()*listaItensProduto.get(i).getProduto().getValor();
				pedidoFeito.setTotalParcial(totalParcialDePedido);
				
				listaDePedidoFeita.add(pedidoFeito);
				pedidoFeito = new PedidoFeito();
			}
			
		}
		 return listaDePedidoFeita;
	}
	
	public String addFormaDePagamento(){
		formaDePagamento = formaDePagamentoServiceImpl.salva(formaDePagamento);
		return null;
	}
	
	public void addProdutoPedido() {
		try {
			 if (listaItensProduto == null) {
				 listaItensProduto = new ArrayList<ItemPedido>();
				 itemPedido.setQuantidade(quantidadeProduto);
				 itemPedido.setProduto(produto);
				 listaItensProduto.add(itemPedido);
			  }else{
				 itemPedido.setQuantidade(quantidadeProduto);
				 itemPedido.setProduto(produto);
				 listaItensProduto.add(itemPedido);
			 }
		} catch (Exception e) {
			addMessage("Não pode ser adicionado", FacesMessage.SEVERITY_ERROR);
		}
		produto = new Produto();
		itemPedido = new ItemPedido();
		quantidadeProduto = 0;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public void buscaClientePorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		cliente = clienteServiceImpl.buscaId(id);
		
	}
	
	public void icluirClientePedido() {
		buscaClientePorId();
	}
	
	public void icluirProdutoPedido() {
		buscaProdutoPorId();
	}
	
	public void buscaProdutoPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		produto = produtoServiceImpl.buscaId(id);
		
	}
	public void removerProdutoPedido() {
		if (listaItensProduto.size() > 0) {   
            listaItensProduto.remove(itemPedido);
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
	public int getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
 
}
