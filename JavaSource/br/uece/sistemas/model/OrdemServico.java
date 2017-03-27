package br.uece.sistemas.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import br.com.sistemas.enumeration.SituacaoOS;

@Entity
public class OrdemServico {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String referencia;
	private Date dataInicio;
	private Date dataFinal;
	private Date dataGeral;
	private int diaGarantia;
	@ManyToOne
	private Cliente cliente;
	private String descricao;
	private String defeito;
	private String observacao;
	@Enumerated(EnumType.STRING)
	private SituacaoOS situacaoOS;
	private String laudo;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_ordemServico")
	private List<ItemOrdemServico> listaItemOrdemServicos;
	@ManyToOne
	private Vendedor vendedor;
	
	public OrdemServico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Date getDataGeral() {
		return dataGeral;
	}
	public void setDataGeral(Date dataGeral) {
		this.dataGeral = dataGeral;
	}
	public int getDiaGarantia() {
		return diaGarantia;
	}
	public void setDiaGarantia(int diaGarantia) {
		this.diaGarantia = diaGarantia;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDefeito() {
		return defeito;
	}
	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}
	public SituacaoOS getSituacaoOS() {
		return situacaoOS;
	}

	public void setSituacaoOS(SituacaoOS situacaoOS) {
		this.situacaoOS = situacaoOS;
	}
	public String getLaudo() {
		return laudo;
	}

	public void setLaudo(String laudo) {
		this.laudo = laudo;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<ItemOrdemServico> getListaItemOrdemServicos() {
		return listaItemOrdemServicos;
	}

	public void setListaItemOrdemServicos(
			List<ItemOrdemServico> listaItemOrdemServicos) {
		this.listaItemOrdemServicos = listaItemOrdemServicos;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
