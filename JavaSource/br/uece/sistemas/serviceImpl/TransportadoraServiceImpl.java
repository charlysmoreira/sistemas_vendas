package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Transportadora;
import br.uece.sistemas.service.TransportadoraService;

public class TransportadoraServiceImpl implements TransportadoraService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Transportadora salva(Transportadora transportadora) {
		entityManager.getTransaction().begin();
		if (transportadora.getId() == null || transportadora.getId() == 0) {
			entityManager.persist(transportadora);
		} else {
			transportadora = entityManager.merge(transportadora);
		}
		entityManager.getTransaction().commit();
		return transportadora;
	}
	
	public List<Transportadora> listaTransportadora() {
		return entityManager.createQuery("from Transportadora").getResultList();
	}
	
	public void remove(Transportadora transportadora) {
		entityManager.getTransaction().begin();
		entityManager.remove(transportadora);
		entityManager.getTransaction().commit();
		
	}
	public List<Transportadora> pesquisa(String nome) {
		List<Transportadora> transportadoras =entityManager.createQuery("from Transportadora t where lower(t.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return transportadoras;
	}
	public Transportadora buscaId(Long id) {
		return entityManager.find(Transportadora.class, id);
	}
    
	

}
