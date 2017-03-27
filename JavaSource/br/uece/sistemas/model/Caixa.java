package br.uece.sistemas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Caixa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Vendedor caixa;
	@ManyToOne
	private Vendedor funcionario;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_caixa")
	private List<ItemCaixa> listaItemCaixa;
	private double totalVenda;
	
	public Caixa() {
		// TODO Auto-generated constructor stub
	}

	public Caixa(Long id, Vendedor caixa, Vendedor funcionario,
			List<ItemCaixa> listaItemCaixa, double totalVenda) {
		this.id = id;
		this.caixa = caixa;
		this.funcionario = funcionario;
		this.listaItemCaixa = listaItemCaixa;
		this.totalVenda = totalVenda;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Vendedor getCaixa() {
		return caixa;
	}
	public void setCaixa(Vendedor caixa) {
		this.caixa = caixa;
	}
	public Vendedor getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Vendedor funcionario) {
		this.funcionario = funcionario;
	}
	public List<ItemCaixa> getListaItemCaixa() {
		return listaItemCaixa;
	}
	public void setListaItemCaixa(List<ItemCaixa> listaItemCaixa) {
		this.listaItemCaixa = listaItemCaixa;
	}
	public double getTotalVenda() {
		return totalVenda;
	}
	public void setTotalVenda(double totalVenda) {
		this.totalVenda = totalVenda;
	}

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
		Caixa other = (Caixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
