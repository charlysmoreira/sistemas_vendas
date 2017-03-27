package br.uece.sistemas.controleBean;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.uece.sistemas.model.Caixa;
import br.uece.sistemas.model.ItemCaixa;
import br.uece.sistemas.model.ItemPedido;
import br.uece.sistemas.model.Produto;
import br.uece.sistemas.model.ProdutoCaixa;
import br.uece.sistemas.model.Vendedor;
import br.uece.sistemas.serviceImpl.CaixaServiceImpl;
import br.uece.sistemas.serviceImpl.VendedorServiceImpl;

public class CaixaControlerBean {
	
	private Caixa caixa = new Caixa();
	private CaixaServiceImpl caixaServiceImpl = new CaixaServiceImpl();
	private VendedorServiceImpl vendedorServiceImpl = new VendedorServiceImpl();
	private List<SelectItem> funcinarios;
	private List<SelectItem> caixas;
	private List<ItemCaixa> listaDePedidoCaixa;
	private List<ProdutoCaixa> listaPesquisaPorNumero;
	private ItemCaixa itemCaixa = new ItemCaixa();
	private String valorPago;
	private double troco;
	private double total;
	private double totalParcial;
	private int numeroPedido;
	private ProdutoCaixa produtoCaixa = new ProdutoCaixa();
	private Produto produto = new Produto();
	
	public Caixa getCaixa() {
		return caixa;
	}
	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
	public CaixaServiceImpl getCaixaServiceImpl() {
		return caixaServiceImpl;
	}
	public void setCaixaServiceImpl(CaixaServiceImpl caixaServiceImpl) {
		this.caixaServiceImpl = caixaServiceImpl;
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
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<SelectItem> getCaixas() {
		if (caixas == null) {
			caixas = new ArrayList<SelectItem>();
			List<Vendedor> calixaList = vendedorServiceImpl.listaVendedor();
			for (Vendedor caixaVendedor : calixaList) {
				caixas.add(new SelectItem(caixaVendedor, caixaVendedor.getNome()));
			}
		}
		return caixas;
	}
	public void setCaixas(List<SelectItem> caixas) {
		this.caixas = caixas;
	}
	public VendedorServiceImpl getVendedorServiceImpl() {
		return vendedorServiceImpl;
	}
	public void setVendedorServiceImpl(VendedorServiceImpl vendedorServiceImpl) {
		this.vendedorServiceImpl = vendedorServiceImpl;
	}
	public List<ItemCaixa> getListaDePedidoCaixa() {
		return listaDePedidoCaixa;
	}
	public void setListaDePedidoCaixa(List<ItemCaixa> listaDePedidoCaixa) {
		this.listaDePedidoCaixa = listaDePedidoCaixa;
	}
	public List<ProdutoCaixa> getListaPesquisaPorNumero() {
		return listaPesquisaPorNumero;
	}
	public void setListaPesquisaPorNumero(List<ProdutoCaixa> listaPesquisaPorNumero) {
		this.listaPesquisaPorNumero = listaPesquisaPorNumero;
	}
	public ProdutoCaixa getProdutoCaixa() {
		return produtoCaixa;
	}
	public void setProdutoCaixa(ProdutoCaixa produtoCaixa) {
		this.produtoCaixa = produtoCaixa;
	}
	public String getValorPago() {
		return valorPago;
	}
	public void setValorPago(String valorPago) {
		this.valorPago = valorPago;
	}
	public double getTroco() {
		return troco;
	}
	public void setTroco(double troco) {
		this.troco = troco;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public ItemCaixa getItemCaixa() {
		return itemCaixa;
	}
	public void setItemCaixa(ItemCaixa itemCaixa) {
		this.itemCaixa = itemCaixa;
	}
	
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void salvar(){
		try { 
			  caixa.setTotalVenda(total);
			  caixa.setListaItemCaixa(new ArrayList<ItemCaixa>());
			  listaDePedidoCaixa = new ArrayList<ItemCaixa>();
			  for (int i = 0; i < listaPesquisaPorNumero.size(); i++) {
				  itemCaixa.setProdutoCaixa(listaPesquisaPorNumero.get(i).getNomeProduto());
				  itemCaixa.setValorProduto(listaPesquisaPorNumero.get(i).getValorProduto());
				  itemCaixa.setQuantidadeCaixa(listaPesquisaPorNumero.get(i).getQuantidadeProduto());
				  listaDePedidoCaixa.add(itemCaixa);
				  caixa.getListaItemCaixa().add(listaDePedidoCaixa.get(i));
				  itemCaixa = new ItemCaixa();
			  }
			  caixaServiceImpl.salva(caixa);
			  addMessage("Confirmar venda com Sucesso!", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			addMessage("Erro ao Confirmar venda", FacesMessage.SEVERITY_ERROR);
		}
		listaDePedidoCaixa = new ArrayList<ItemCaixa>();
		itemCaixa = new ItemCaixa();
		caixa = new Caixa();
	}
	
	public List<ProdutoCaixa> pesquisaPorNumero() {
		if(listaPesquisaPorNumero == null){
			listaPesquisaPorNumero = new ArrayList<ProdutoCaixa>();
			listaPesquisaPorNumero = caixaServiceImpl.pesquisaPedidoPorNumero(numeroPedido);
		}
		return listaPesquisaPorNumero;

	}
	public void removerProdutoPedido() {
		if (listaPesquisaPorNumero.size() > 0) {   
			listaPesquisaPorNumero.remove(produtoCaixa);
		}
	}
	public int getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public double getTotalParcial() {
		return totalParcial;
	}
	public void setTotalParcial(double totalParcial) {
		this.totalParcial = totalParcial;
	}
	
	public void calcular() {
		total = 0;
		for (int i = 0; i < listaPesquisaPorNumero.size(); i++) {
			listaPesquisaPorNumero.get(i).setQuantidadeProduto(listaPesquisaPorNumero.get(i).getQuantidadeProduto());
			totalParcial = listaPesquisaPorNumero.get(i).getQuantidadeProduto()*
			listaPesquisaPorNumero.get(i).getValorProduto();
			listaPesquisaPorNumero.get(i).setTotalParcial(totalParcial);
			total += totalParcial;
			totalParcial = 0;
		}
		calcularVenda();
	}
	
	DecimalFormat dff = (DecimalFormat) DecimalFormat.getInstance();  
	public void calcularVenda() {
		Double valorPagoFormat;
		if(valorPago!= null){
			try {
				valorPagoFormat = (Double) dff.parse(valorPago);
				troco = valorPagoFormat - total;
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}
	}
}
