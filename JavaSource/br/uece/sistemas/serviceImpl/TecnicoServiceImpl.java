package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Tecnico;
import br.uece.sistemas.service.TecnicoService;

public class TecnicoServiceImpl implements TecnicoService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Tecnico salva(Tecnico tecnico) {
		entityManager.getTransaction().begin();
		if (tecnico.getId() == null || tecnico.getId() == 0) {
			entityManager.persist(tecnico);
		} else {
			tecnico = entityManager.merge(tecnico);
		}
		entityManager.getTransaction().commit();
		return tecnico;
	}
	
	public List<Tecnico> listaTecnico() {
		return entityManager.createQuery("from Tecnico").getResultList();
	}
	
	public void remove(Tecnico tecnico) {
		entityManager.getTransaction().begin();
		entityManager.remove(tecnico);
		entityManager.getTransaction().commit();
		
	}
	public List<Tecnico> pesquisa(String nome) {
		List<Tecnico> tecnicos = entityManager.createQuery("from Tecnico t where lower(t.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return tecnicos;
	}
	public Tecnico buscaId(Long id) {
		return entityManager.find(Tecnico.class, id);
	}
    
	

}
