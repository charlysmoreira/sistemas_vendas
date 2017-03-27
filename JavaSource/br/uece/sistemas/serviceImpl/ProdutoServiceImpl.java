package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Produto;
import br.uece.sistemas.service.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Produto salva(Produto produto) {
		entityManager.getTransaction().begin();
		if (produto.getId() == null || produto.getId() == 0) {
			entityManager.persist(produto);
		} else {
			produto = entityManager.merge(produto);
		}
		entityManager.getTransaction().commit();
		return produto;
	}

	public List<Produto> listaProduto() {
		return entityManager.createQuery("from Produto").getResultList();
	}

	public void remove(Produto produto) {
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
	}

	public List<Produto> pesquisa(String nome) {
		List<Produto> produtos =entityManager.createQuery("from Produto p where lower(p.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return produtos;
	}

	public Produto buscaId(Long id) {
		return entityManager.find(Produto.class, id);
	}

}
