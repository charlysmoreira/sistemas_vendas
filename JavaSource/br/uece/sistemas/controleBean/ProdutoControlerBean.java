package br.uece.sistemas.controleBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.uece.sistemas.model.Categoria;
import br.uece.sistemas.model.Marca;
import br.uece.sistemas.model.Produto;
import br.uece.sistemas.serviceImpl.CategoriaServiceImpl;
import br.uece.sistemas.serviceImpl.MarcaServiceImpl;
import br.uece.sistemas.serviceImpl.ProdutoServiceImpl;

public class ProdutoControlerBean {
	
	private Produto produto = new Produto();
	private ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl();
	private CategoriaServiceImpl categoriaServiceImpl = new CategoriaServiceImpl();
	private MarcaServiceImpl marcaServiceImpl = new MarcaServiceImpl();
	private List<Produto> listaDeProdutos;
	private List<SelectItem> categorias;
	private List<SelectItem> marcas;
//	File file = new File();
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public ProdutoServiceImpl getProdutoServiceImpl() {
		return produtoServiceImpl;
	}
	public void setProdutoServiceImpl(ProdutoServiceImpl produtoServiceImpl) {
		this.produtoServiceImpl = produtoServiceImpl;
	}
	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}
	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
	public void setCategorias(List<SelectItem> categorias) {
		this.categorias = categorias;
	}
	public CategoriaServiceImpl getCategoriaServiceImpl() {
		return categoriaServiceImpl;
	}
	public void setCategoriaServiceImpl(CategoriaServiceImpl categoriaServiceImpl) {
		this.categoriaServiceImpl = categoriaServiceImpl;
	}
	public MarcaServiceImpl getMarcaServiceImpl() {
		return marcaServiceImpl;
	}
	public void setMarcaServiceImpl(MarcaServiceImpl marcaServiceImpl) {
		this.marcaServiceImpl = marcaServiceImpl;
	}
	public void setMarcas(List<SelectItem> marcas) {
		this.marcas = marcas;
	}
//	public File getFile() {
//		return file;
//	}
//	public void setFile(File file) {
//		this.file = file;
//	}
	
	public List<SelectItem> getMarcas() {
		if (marcas == null) {
			marcas = new ArrayList<SelectItem>();
			List<Marca> marcaList = marcaServiceImpl.listaMarca();
			for (Marca marca : marcaList) {
				marcas.add(new SelectItem(marca, marca.getNome()));
			}
		}
		return marcas;
	}
	public List<SelectItem> getCategorias() {
		if (categorias == null) {
			categorias = new ArrayList<SelectItem>();
			List<Categoria> categoriaList = categoriaServiceImpl.listaCategoria();
			for (Categoria categoria : categoriaList) {
				categorias.add(new SelectItem(categoria, categoria.getNome()));
			}
		}
		return categorias;
	}
	
	
	// adiciona menssagens
	public static void addMessage(String messageText, Severity typeMessage) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(typeMessage, messageText, null);
		context.addMessage(null, message);
	}
	
	public void buscaProdutoPorId() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> resquestParams = context.getExternalContext()
				.getRequestParameterMap();
		Long id = Long.parseLong(resquestParams.get("id"));
		produto = produtoServiceImpl.buscaId(id);
	}
	
	public void salvar(){
		try {
			if(produto.getId() != null){
				produtoServiceImpl.salva(produto);
				addMessage("Alterado com Sucesso!!", FacesMessage.SEVERITY_INFO);
			}else{
				produtoServiceImpl.salva(produto);
				addMessage("Salvo com sucesso", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception e) {
			addMessage("Erro", FacesMessage.SEVERITY_ERROR);
		}
		produto = new Produto();
	}
	
	public List<Produto> getListaProdutos() {
		return produtoServiceImpl.listaProduto();
	}
	
	public void listaProdutos() {
		if(produto.getNome()== null || produto.getNome().trim().isEmpty()){
			listaDeProdutos = getListaProdutos();
		}else{
			listaDeProdutos = produtoServiceImpl.pesquisa(produto.getNome());
		}
	}
	
	public String novo(){
		produto = new Produto();
		return "cadastroProduto";
	}
	
	public String editar() {
		buscaProdutoPorId();
		return "cadastroProduto";
	}
	
	public String remover() {
		buscaProdutoPorId();
		produtoServiceImpl.remove(produto);
		addMessage("Produto Removido com sucesso", FacesMessage.SEVERITY_INFO);
		produto = new Produto();
		listaDeProdutos = null;
		return "listaProduto";
	}
	
//	 public void paint(OutputStream stream, Object object) throws IOException {  
//	        try {  
//	            stream.write(produto.getFile().getDados());  
//	                } catch (IOException iOException) {  
//	                       iOException.printStackTrace();  
//	                }         
//	        }  
	
//	public void listener(UploadEvent event) throws Exception {
//		UploadItem item = event.getUploadItem();
//		File file = new File();
//		file.setNome(item.getFileName());
//		file.setDados(item.getData());
//		file.setTipo(item.getContentType());
//		file.setTamanho(item.getData().length);
//		produto.setFile(file);
//	}
//	 public long getTimeStamp(){
//	        return System.currentTimeMillis();
//	    }
	
}
