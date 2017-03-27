package br.uece.sistemas.controleBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sistemas.enumeration.Estado;
import br.com.sistemas.enumeration.FinalidadeEmissao;
import br.com.sistemas.enumeration.ModalidadeBC;
import br.com.sistemas.enumeration.NaturezaVenda;
import br.com.sistemas.enumeration.Origem;
import br.com.sistemas.enumeration.Pagamento;
import br.com.sistemas.enumeration.SituacaoTributaria;
import br.com.sistemas.enumeration.TipoOperacao;
import br.uece.sistemas.model.Cidade;
import br.uece.sistemas.model.Empresa;
import br.uece.sistemas.model.NotaFiscalEletronica;
import br.uece.sistemas.model.ProdutoNF;
import br.uece.sistemas.model.Totalizadores;
import br.uece.sistemas.serviceImpl.CidadeServiceImpl;
import br.uece.sistemas.serviceImpl.EmpresaServiceImpl;
import br.uece.sistemas.serviceImpl.TotalizadoresServiceImpl;

public class NotaFiscalEletronicaControlerBean {
	
	private NotaFiscalEletronica notaFiscalEletronica = new NotaFiscalEletronica();
	private EmpresaServiceImpl empresaServiceImpl = new EmpresaServiceImpl();
	private CidadeServiceImpl cidadeServiceImpl = new CidadeServiceImpl();
	private List<SelectItem> listaDeEstados;
	private List<SelectItem> listaNaturezaVena;
	private List<SelectItem> listaTipoVenda;
	private List<SelectItem> listaFormDePag;
	private List<SelectItem> listaTipoOperacao;
	private List<SelectItem> listaFinalidadeMissao;
	private Empresa empresa = new Empresa();
	private List<ProdutoNF> listaDeProdutoTONf;
	
	private ProdutoNF produtoNF = new ProdutoNF();
	private List<SelectItem> listaDeCidades;
	private List<SelectItem> listaSituacaoTributaria;
	private List<SelectItem> listaSituacaoTributariaIcms;
	private List<SelectItem> listaSituacaoTributariaPis;
	private List<SelectItem> listaOrigens;
	private List<SelectItem> listaModalidadeBC;
	private List<SelectItem> listaNaturezas;
	private List<SelectItem> listaSituacaoTributariaCofins;
	
	private Totalizadores totalizadores = new Totalizadores();
	private TotalizadoresServiceImpl totalizadoresServiceImpl = new TotalizadoresServiceImpl();
	
	public List<SelectItem> getListaDeEstados() {
		buscarEmpresa();
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
	
	public List<SelectItem> getListaNaturezaVena() {
		if (listaNaturezaVena == null) {
			listaNaturezaVena = new ArrayList<SelectItem>();
			NaturezaVenda[] vendas = NaturezaVenda.values();
			for (int j = 0; j < vendas.length; j++) {
				listaNaturezaVena.add(new SelectItem(vendas[j],
						vendas[j].name()));
			}
		}
		return listaNaturezaVena;
	}

	public void setListaNaturezaVena(List<SelectItem> listaNaturezaVena) {
		this.listaNaturezaVena = listaNaturezaVena;
	}

	public List<SelectItem> getListaTipoVenda() {
		if (listaTipoVenda == null) {
			listaTipoVenda = new ArrayList<SelectItem>();
			TipoOperacao[] tipoOperacaos = TipoOperacao.values();
			for (int j = 0; j < tipoOperacaos.length; j++) {
				listaDeEstados.add(new SelectItem(tipoOperacaos[j],
						tipoOperacaos[j].name()));
			}
		}
		return listaTipoVenda;
	}

	public void setListaTipoVenda(List<SelectItem> listaTipoVenda) {
		this.listaTipoVenda = listaTipoVenda;
	}
	public List<ProdutoNF> getListaDeProdutoTONf() {
		return listaDeProdutoTONf;
	}
	public void setListaDeProdutoTONf(List<ProdutoNF> listaDeProdutoTONf) {
		this.listaDeProdutoTONf = listaDeProdutoTONf;
	}
	
	public List<SelectItem> getListaFormDePag() {
		pegarApenasHora();
		addTotalizadores();
		if (listaFormDePag == null) {
			listaFormDePag = new ArrayList<SelectItem>();
			Pagamento[] pagamentos = Pagamento.values();
			for (int j = 0; j < pagamentos.length; j++) {
				listaFormDePag.add(new SelectItem(pagamentos[j],
						pagamentos[j].name()));
			}
		}
		return listaFormDePag;
	}

	public void setListaFormDePag(List<SelectItem> listaFormDePag) {
		this.listaFormDePag = listaFormDePag;
	}

	public List<SelectItem> getListaTipoOperacao() {
		if (listaTipoOperacao == null) {
			listaTipoOperacao = new ArrayList<SelectItem>();
			TipoOperacao[] tipoOperacaos = TipoOperacao.values();
			for (int j = 0; j < tipoOperacaos.length; j++) {
				listaTipoOperacao.add(new SelectItem(tipoOperacaos[j],
						tipoOperacaos[j].name()));
			}
		}
		return listaTipoOperacao;
	}

	public void setListaTipoOperacao(List<SelectItem> listaTipoOperacao) {
		this.listaTipoOperacao = listaTipoOperacao;
	}

	public List<SelectItem> getListaFinalidadeMissao() {
		if (listaFinalidadeMissao == null) {
			listaFinalidadeMissao = new ArrayList<SelectItem>();
			FinalidadeEmissao[] finalidadeEmissaos = FinalidadeEmissao.values();
			for (int j = 0; j < finalidadeEmissaos.length; j++) {
				listaFinalidadeMissao.add(new SelectItem(finalidadeEmissaos[j],
						finalidadeEmissaos[j].name()));
			}
		}
		return listaFinalidadeMissao;
	}

	public void setListaFinalidadeMissao(List<SelectItem> listaFinalidadeMissao) {
		this.listaFinalidadeMissao = listaFinalidadeMissao;
	}

	public List<SelectItem> getListaSituacaoTributaria() {
		if (listaSituacaoTributaria == null) {
			listaSituacaoTributaria = new ArrayList<SelectItem>();
			SituacaoTributaria[] situacaoTributarias = SituacaoTributaria.values();
			for (int j = 0; j < situacaoTributarias.length; j++) {
				listaSituacaoTributaria.add(new SelectItem(situacaoTributarias[j],
						situacaoTributarias[j].name()));
			}
		}
		return listaSituacaoTributaria;
	}

	public void setListaSituacaoTributaria(List<SelectItem> listaSituacaoTributaria) {
		this.listaSituacaoTributaria = listaSituacaoTributaria;
	}

	public List<SelectItem> getListaSituacaoTributariaIcms() {
		if (listaSituacaoTributariaIcms == null) {
			listaSituacaoTributariaIcms = new ArrayList<SelectItem>();
			SituacaoTributaria[] situacaoTributarias = SituacaoTributaria.values();
			for (int j = 0; j < situacaoTributarias.length; j++) {
				listaSituacaoTributariaIcms.add(new SelectItem(situacaoTributarias[j],
						situacaoTributarias[j].name()));
			}
		}
		return listaSituacaoTributariaIcms;
	}

	public void setListaSituacaoTributariaIcms(
			List<SelectItem> listaSituacaoTributariaIcms) {
		this.listaSituacaoTributariaIcms = listaSituacaoTributariaIcms;
	}

	public List<SelectItem> getListaSituacaoTributariaPis() {
		if (listaSituacaoTributariaPis == null) {
			listaSituacaoTributariaPis = new ArrayList<SelectItem>();
			SituacaoTributaria[] situacaoTributarias = SituacaoTributaria.values();
			for (int j = 0; j < situacaoTributarias.length; j++) {
				listaSituacaoTributariaPis.add(new SelectItem(situacaoTributarias[j],
						situacaoTributarias[j].name()));
			}
		}
		return listaSituacaoTributariaPis;
	}

	public void setListaSituacaoTributariaPis(
			List<SelectItem> listaSituacaoTributariaPis) {
		this.listaSituacaoTributariaPis = listaSituacaoTributariaPis;
	}

	public List<SelectItem> getListaSituacaoTributariaCofins() {
		if (listaSituacaoTributariaCofins == null) {
			listaSituacaoTributariaCofins = new ArrayList<SelectItem>();
			SituacaoTributaria[] situacaoTributarias = SituacaoTributaria.values();
			for (int j = 0; j < situacaoTributarias.length; j++) {
				listaSituacaoTributariaCofins.add(new SelectItem(situacaoTributarias[j],
						situacaoTributarias[j].name()));
			}
		}
		return listaSituacaoTributariaCofins;
	}

	public void setListaSituacaoTributariaCofins(
			List<SelectItem> listaSituacaoTributariaCofins) {
		this.listaSituacaoTributariaCofins = listaSituacaoTributariaCofins;
	}

	public List<SelectItem> getListaOrigens() {
		if (listaOrigens == null) {
			listaOrigens = new ArrayList<SelectItem>();
			Origem[] origems = Origem.values();
			for (int j = 0; j < origems.length; j++) {
				listaOrigens.add(new SelectItem(origems[j],
						origems[j].name()));
			}
		}
		return listaOrigens;
	}

	public void setListaOrigens(List<SelectItem> listaOrigens) {
		this.listaOrigens = listaOrigens;
	}

	public List<SelectItem> getListaModalidadeBC() {
		if (listaModalidadeBC == null) {
			listaModalidadeBC = new ArrayList<SelectItem>();
			ModalidadeBC[] modalidadeBCs = ModalidadeBC.values();
			for (int j = 0; j < modalidadeBCs.length; j++) {
				listaModalidadeBC.add(new SelectItem(modalidadeBCs[j],
						modalidadeBCs[j].name()));
			}
		}
		return listaModalidadeBC;
	}

	public void setListaModalidadeBC(List<SelectItem> listaModalidadeBC) {
		this.listaModalidadeBC = listaModalidadeBC;
	}

	public List<SelectItem> getListaNaturezas() {
		if (listaNaturezas == null) {
			listaNaturezas = new ArrayList<SelectItem>();
			NaturezaVenda[] naturezaVendas = NaturezaVenda.values();
			for (int j = 0; j < naturezaVendas.length; j++) {
				listaNaturezas.add(new SelectItem(naturezaVendas[j],
						naturezaVendas[j].name()));
			}
		}
		return listaNaturezas;
	}

	public void setListaNaturezas(List<SelectItem> listaNaturezas) {
		this.listaNaturezas = listaNaturezas;
	}

	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void salvarProdutoNF() {
		try {
			if (notaFiscalEletronica.getListaProdutoNFs() == null) {
				notaFiscalEletronica.setListaProdutoNFs(new ArrayList<ProdutoNF>());
				notaFiscalEletronica.getListaProdutoNFs().add(produtoNF);
			}else{
				notaFiscalEletronica.getListaProdutoNFs().add(produtoNF);
			}
			addMessage("Filial Salva com sucesso", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			addMessage("Não pode ser cadastrar", FacesMessage.SEVERITY_ERROR);
		}
		produtoNF = new ProdutoNF();
	}
	
	public Empresa buscarEmpresa() {
		empresa = empresaServiceImpl.listaEmpresa().get(0);
		return empresa;
	}

	public NotaFiscalEletronica getNotaFiscalEletronica() {
		return notaFiscalEletronica;
	}

	public void setNotaFiscalEletronica(NotaFiscalEletronica notaFiscalEletronica) {
		this.notaFiscalEletronica = notaFiscalEletronica;
	}

	public EmpresaServiceImpl getEmpresaServiceImpl() {
		return empresaServiceImpl;
	}

	public void setEmpresaServiceImpl(EmpresaServiceImpl empresaServiceImpl) {
		this.empresaServiceImpl = empresaServiceImpl;
	}

	public CidadeServiceImpl getCidadeServiceImpl() {
		return cidadeServiceImpl;
	}

	public void setCidadeServiceImpl(CidadeServiceImpl cidadeServiceImpl) {
		this.cidadeServiceImpl = cidadeServiceImpl;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public ProdutoNF getProdutoNF() {
		return produtoNF;
	}

	public void setProdutoNF(ProdutoNF produtoNF) {
		this.produtoNF = produtoNF;
	}
	 
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	String datafor = "dd/MM/yyyy";
    String horaf = "hh:mm:ss - a";
	String hora;
	String data;
	public void pegarApenasHora() {
	  java.util.Date agora = new java.util.Date();;
	  SimpleDateFormat formata = new SimpleDateFormat(datafor);
	  data = formata.format(agora);
	  formata = new SimpleDateFormat(horaf);
	  hora = formata.format(agora);

	}
	public List<SelectItem> getListaDeCidades() {
		if (listaDeCidades == null) {
			listaDeCidades = new ArrayList<SelectItem>();
			List<Cidade> cidades = cidadeServiceImpl.listaCidade();
			for (Cidade cidade : cidades) {
				listaDeCidades.add(new SelectItem(cidade, cidade.getNome()));
			}
		}
		return listaDeCidades;
	}

	public void setListaDeCidades(List<SelectItem> listaDeCidades) {
		this.listaDeCidades = listaDeCidades;
	}
	
	public void addProduto() {
		try {
			 if (listaDeProdutoTONf == null) {
				 listaDeProdutoTONf = new ArrayList<ProdutoNF>();
				 listaDeProdutoTONf.add(produtoNF);
			  }else{
				  listaDeProdutoTONf.add(produtoNF);
			 }
		} catch (Exception e) {
			addMessage("Não pode ser adicionado!", FacesMessage.SEVERITY_ERROR);
		}
		produtoNF = new ProdutoNF();
	}
	
	public void removerProdutoNF() {
		if (listaDeProdutoTONf.size() > 0) {   
			listaDeProdutoTONf.remove(produtoNF);
		}
	}
	public Totalizadores getTotalizadores() {
		return totalizadores;
	}
	public void setTotalizadores(Totalizadores totalizadores) {
		this.totalizadores = totalizadores;
	}
	public TotalizadoresServiceImpl getTotalizadoresServiceImpl() {
		return totalizadoresServiceImpl;
	}
	public void setTotalizadoresServiceImpl(
			TotalizadoresServiceImpl totalizadoresServiceImpl) {
		this.totalizadoresServiceImpl = totalizadoresServiceImpl;
	}

	public void addTotalizadores() {
		List<Totalizadores> ListaTotalizadores = totalizadoresServiceImpl.listaTotalizadores();
		for (int i = 0; i < ListaTotalizadores.size(); i++) {
			notaFiscalEletronica.setTotalBcIcms(ListaTotalizadores.get(i).getTotalBcIcms());
			notaFiscalEletronica.setTotalBcIcmsSt(ListaTotalizadores.get(i).getTotalBcIcmsSt());
			notaFiscalEletronica.setTotalIcms(ListaTotalizadores.get(i).getTotalIcms());
			notaFiscalEletronica.setTotalIcmsSt(ListaTotalizadores.get(i).getTotalIcmsSt());
			notaFiscalEletronica.setTotalBrutoProduto(ListaTotalizadores.get(i).getTotalBrutoProduto());
			notaFiscalEletronica.setTotalFrete(ListaTotalizadores.get(i).getTotalFrete());
			notaFiscalEletronica.setTotalSeguro(ListaTotalizadores.get(i).getTotalSeguro());
			notaFiscalEletronica.setTotalDesconto(ListaTotalizadores.get(i).getTotalDesconto());
			notaFiscalEletronica.setTotalIi(ListaTotalizadores.get(i).getTotalIi());
			notaFiscalEletronica.setTotalIpi(ListaTotalizadores.get(i).getTotalIpi());
			notaFiscalEletronica.setTotalPis(ListaTotalizadores.get(i).getTotalPis());
			notaFiscalEletronica.setTotalPisSt(ListaTotalizadores.get(i).getTotalPisSt());
			notaFiscalEletronica.setTotalCofins(ListaTotalizadores.get(i).getTotalCofins());
			notaFiscalEletronica.setTotalCofinsSt(ListaTotalizadores.get(i).getTotalCofinsSt());
			notaFiscalEletronica.setTotalOutrasDespesas(ListaTotalizadores.get(i).getTotalOutrasDespesas());
			notaFiscalEletronica.setTotalNf(ListaTotalizadores.get(i).getTotalNf());
			notaFiscalEletronica.setRetencaoDeImposto(ListaTotalizadores.get(i).isRetencaoDeImposto());
		}
	}
	
}
