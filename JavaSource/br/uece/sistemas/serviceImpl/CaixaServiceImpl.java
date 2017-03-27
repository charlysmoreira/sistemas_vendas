package br.uece.sistemas.serviceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.uece.sistemas.entidade.report.PedidoDoReletorio;
import br.uece.sistemas.model.Caixa;
import br.uece.sistemas.model.ProdutoCaixa;
import br.uece.sistemas.service.CaixaService;

public class CaixaServiceImpl implements CaixaService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Caixa salva(Caixa caixa) {
		entityManager.getTransaction().begin();
		if (caixa.getId() == null || caixa.getId() == 0) {
			entityManager.persist(caixa);
		} else {
			caixa = entityManager.merge(caixa);
		}
		entityManager.getTransaction().commit();
		return caixa;
	}
	
	public List<ProdutoCaixa> pesquisaPedidoPorNumero(Integer numero){
		List<ProdutoCaixa> listaProdutoCaixa = new ArrayList<ProdutoCaixa>();
		String stringQuary = "SELECT p.nome, p.valor, i.quantidade FROM Produto p INNER JOIN itemPedido i ON "+
		"p.id = i.produto_id INNER JOIN pedido d on d.id = i.id_pedido where d.numero = "+"'"+numero+"'";
		Query query =  getEntityManager().createNativeQuery(stringQuary);
		ProdutoCaixa retorno = null;
		List<Object> resut = query.getResultList();
		String nomeProduto = "";
		double valorProduto = 0;
		int quantidadeProduto = 0;
		double totalParcial = 0;
		for (Object object : resut) {
		 Object[] aObjects = (Object[]) object;
			if (aObjects[0] != null) {
				nomeProduto =(String) aObjects[0];
			}
			if (aObjects[1] != null) {
				BigDecimal valor = (BigDecimal)aObjects[1];
				valorProduto = valor.doubleValue();
			}
			if (aObjects[2] != null) {
				quantidadeProduto = (Integer) aObjects[2];
			}
		totalParcial = quantidadeProduto*valorProduto;
		retorno = new ProdutoCaixa(nomeProduto, valorProduto, quantidadeProduto, totalParcial);
		listaProdutoCaixa.add(retorno);
		}
	return listaProdutoCaixa;
}

}
