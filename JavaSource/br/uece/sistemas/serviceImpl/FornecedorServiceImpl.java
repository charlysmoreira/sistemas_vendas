package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Fornecedor;
import br.uece.sistemas.service.FornecedorService;

public class FornecedorServiceImpl implements FornecedorService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Fornecedor salva(Fornecedor fornecedor) {
		entityManager.getTransaction().begin();
		if (fornecedor.getId() == null || fornecedor.getId() == 0) {
			entityManager.persist(fornecedor);
		} else {
			fornecedor = entityManager.merge(fornecedor);
		}
		entityManager.getTransaction().commit();
		return fornecedor;
	}
	
	public List<Fornecedor> listaFornecedor() {
		return entityManager.createQuery("from Fornecedor").getResultList();
	}
	
	public void remove(Fornecedor fornecedor) {
		entityManager.getTransaction().begin();
		entityManager.remove(fornecedor);
		entityManager.getTransaction().commit();
		
	}
	public List<Fornecedor> pesquisa(String nome) {
		List<Fornecedor> fornecedores =entityManager.createQuery("from Fornecedor f where lower(f.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return fornecedores;
	}
	public Fornecedor buscaId(Long id) {
		return entityManager.find(Fornecedor.class, id);
	}
    
	

}
