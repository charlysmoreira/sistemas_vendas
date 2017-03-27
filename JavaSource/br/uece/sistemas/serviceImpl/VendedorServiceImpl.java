package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Vendedor;
import br.uece.sistemas.service.VendedorService;

public class VendedorServiceImpl implements VendedorService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Vendedor salva(Vendedor vendedor) {
		entityManager.getTransaction().begin();
		if (vendedor.getId() == null || vendedor.getId() == 0) {
			entityManager.persist(vendedor);
		} else {
			vendedor = entityManager.merge(vendedor);
		}
		entityManager.getTransaction().commit();
		return vendedor;
	}
	
	public List<Vendedor> listaVendedor() {
		return entityManager.createQuery("from Vendedor").getResultList();
	}
	
	public void remove(Vendedor vendedor) {
		entityManager.getTransaction().begin();
		entityManager.remove(vendedor);
		entityManager.getTransaction().commit();
		
	}
	public List<Vendedor> pesquisa(String nome) {
		List<Vendedor> vendedors =entityManager.createQuery("from Vendedor v where lower(v.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return vendedors;
	}
	public Vendedor buscaId(Long id) {
		return entityManager.find(Vendedor.class, id);
	}
    
	

}
