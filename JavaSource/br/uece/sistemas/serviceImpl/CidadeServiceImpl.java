package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Cidade;
import br.uece.sistemas.service.CidadeService;

public class CidadeServiceImpl implements CidadeService {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Cidade salva(Cidade cidade) {
		entityManager.getTransaction().begin();
		if (cidade.getId() == null || cidade.getId() == 0) {
			entityManager.persist(cidade);
		} else {
			cidade = entityManager.merge(cidade);
		}
		entityManager.getTransaction().commit();
		return cidade;
	}

	public List<Cidade> listaCidade() {
		return entityManager.createQuery("from Cidade").getResultList();
	}

	public List<Cidade> pesquisa(String nome) {
		List<Cidade> cidades =entityManager.createQuery("from Cidade c where lower(c.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return cidades;
	}

	public Cidade buscaId(Long id) {
		return entityManager.find(Cidade.class, id);
	}

}
