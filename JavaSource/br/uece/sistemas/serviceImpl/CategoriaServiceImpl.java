package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Categoria;
import br.uece.sistemas.service.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Categoria salva(Categoria categoria) {
		entityManager.getTransaction().begin();
		if (categoria.getId() == null || categoria.getId() == 0) {
			entityManager.persist(categoria);
		} else {
			categoria = entityManager.merge(categoria);
		}
		entityManager.getTransaction().commit();
		return categoria;
	}
	
	public List<Categoria> listaCategoria() {
		return entityManager.createQuery("from Categoria").getResultList();
	}
	
	public void remove(Categoria categoria) {
		entityManager.getTransaction().begin();
		entityManager.remove(categoria);
		entityManager.getTransaction().commit();
		
	}
	public List<Categoria> pesquisa(String nome) {
		List<Categoria> categorias =entityManager.createQuery("from Categoria c where lower(c.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return categorias;
	}
	public Categoria buscaId(Long id) {
		return entityManager.find(Categoria.class, id);
	}
    
	

}
