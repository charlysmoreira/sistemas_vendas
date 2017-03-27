package br.uece.sistemas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String codigo;
	private String nome;
	private double valor;
	private String especificao;
	private String descricao;
	private Long codigoBarra;
	@ManyToOne
	private Categoria categoria;
	@ManyToOne
	private Marca marca;
//	@OneToOne(cascade = CascadeType.ALL)  
//	private File file;
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}
	public Produto(Long id, String codigo, String nome, double valor,
		String especificao, String descricao, Long codigoBarra,
		Categoria categoria, Marca marca) {
	super();
	this.id = id;
	this.codigo = codigo;
	this.nome = nome;
	this.valor = valor;
	this.especificao = especificao;
	this.descricao = descricao;
	this.codigoBarra = codigoBarra;
	this.categoria = categoria;
	this.marca = marca;
}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEspecificao() {
		return especificao;
	}
	public void setEspecificao(String especificao) {
		this.especificao = especificao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Long getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(Long codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
//	public File getFile() {
//		return file;
//	}
//	public void setFile(File file) {
//		this.file = file;
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
