package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Totalizadores;
import br.uece.sistemas.service.TotalizadoresService;

public class TotalizadoresServiceImpl implements TotalizadoresService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Totalizadores salva(Totalizadores totalizadores) {
		entityManager.getTransaction().begin();
		if (totalizadores.getId() == null || totalizadores.getId() == 0) {
			entityManager.persist(totalizadores);
		} else {
			totalizadores = entityManager.merge(totalizadores);
		}
		entityManager.getTransaction().commit();
		return totalizadores;
	}
	
	public List<Totalizadores> listaTotalizadores() {
		return entityManager.createQuery("from Totalizadores").getResultList();
	}
	
	public void remove(Totalizadores totalizadores) {
		entityManager.getTransaction().begin();
		entityManager.remove(totalizadores);
		entityManager.getTransaction().commit();
		
	}
	public List<Totalizadores> pesquisa(String nome) {
		List<Totalizadores> totalizadores =entityManager.createQuery("from Totalizadores c where lower(c.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return totalizadores;
	}
	public Totalizadores buscaId(Long id) {
		return entityManager.find(Totalizadores.class, id);
	}
    
	

}
