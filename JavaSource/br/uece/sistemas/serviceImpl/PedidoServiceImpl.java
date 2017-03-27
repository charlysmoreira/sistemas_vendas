package br.uece.sistemas.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.uece.sistemas.entidade.report.PedidoDoReletorio;
import br.uece.sistemas.model.Pedido;
import br.uece.sistemas.service.PedidoService;

public class PedidoServiceImpl implements PedidoService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Pedido salva(Pedido pedido) {
		entityManager.getTransaction().begin();
		if (pedido.getId() == null || pedido.getId() == 0) {
			entityManager.persist(pedido);
		} else {
			pedido = entityManager.merge(pedido);
		}
		entityManager.getTransaction().commit();
		return pedido;
	}
	
	public List<Pedido> listaPedido() {
		return entityManager.createQuery("from Pedido").getResultList();
	}

	@Override
	public void remove(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pedido> pesquisa(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido buscaId(Long id) {
		return entityManager.find(Pedido.class, id);
	}
	
	public List<PedidoDoReletorio> pesquisaPedidoPorData(Date dataInicial, Date dataFinal){
			List<PedidoDoReletorio> listaDePedidoData = new ArrayList<PedidoDoReletorio>();
			String stringQuary = "SELECT c.nome,c.cnpj FROM pedido p INNER JOIN cliente c " +
					"ON c.id = p.cliente_Id	where p.datavenda >= "+"'"+dataInicial+"'"+
					"and p.datavenda <= "+"'"+dataFinal+"'"+" ORDER BY c.nome";
			Query query =  getEntityManager().createNativeQuery(stringQuary);
			PedidoDoReletorio retorno = null;
			List<Object> resut = query.getResultList();
			String nomeCliente = "";
			String cnpj = "";
			for (Object object : resut) {
			 Object[] aObjects = (Object[]) object;
				if (aObjects[0] != null) {
					nomeCliente =(String) aObjects[0];
				}
				if (aObjects[1] != null) {
					cnpj = (String) aObjects[1];
				}
			retorno = new PedidoDoReletorio(nomeCliente, cnpj);
			listaDePedidoData.add(retorno);
			}
		return listaDePedidoData;
	}
}
