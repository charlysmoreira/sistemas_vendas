package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Tipo;
import br.uece.sistemas.service.TipoService;

public class TipoServiceImpl implements TipoService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Tipo salva(Tipo tipo) {
		entityManager.getTransaction().begin();
		if (tipo.getId() == null || tipo.getId() == 0) {
			entityManager.persist(tipo);
		} else {
			tipo = entityManager.merge(tipo);
		}
		entityManager.getTransaction().commit();
		return tipo;
	}
	
	public List<Tipo> listaTipo() {
		return entityManager.createQuery("from Tipo").getResultList();
	}
	
	public List<Tipo> pesquisa(String nome) {
		List<Tipo> tipos =entityManager.createQuery("from Tipo t where lower(t.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return tipos;
	}
	public Tipo buscaId(Long id) {
		return entityManager.find(Tipo.class, id);
	}
    
	

}
