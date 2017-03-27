package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Marca;
import br.uece.sistemas.service.MarcaService;

public class MarcaServiceImpl implements MarcaService {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();

    public Marca salva(Marca marca) {
    	entityManager.getTransaction().begin();
		if (marca.getId() == null || marca.getId() == 0) {
			entityManager.persist(marca);
		} else {
			marca = entityManager.merge(marca);
		}
		entityManager.getTransaction().commit();
		return marca;
	}

    public List<Marca> listaMarca() {
    	return entityManager.createQuery("from Marca").getResultList();
	}

    public void remove(Marca marca) {
    	entityManager.getTransaction().begin();
		entityManager.remove(marca);
		entityManager.getTransaction().commit();
		
	}

    public List<Marca> pesquisa(String nome) {
    	List<Marca> marcas =entityManager.createQuery("from Marca m where lower(m.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return marcas;
	}

    public Marca buscaId(Long id) {
    	return entityManager.find(Marca.class, id);
	}
	
	
}
