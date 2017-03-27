package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Cliente;
import br.uece.sistemas.service.ClienteService;

public class ClienteServiceImpl implements ClienteService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Cliente salva(Cliente cliente) {
		entityManager.getTransaction().begin();
		if (cliente.getId() == null || cliente.getId() == 0) {
			entityManager.persist(cliente);
		} else {
			cliente = entityManager.merge(cliente);
		}
		entityManager.getTransaction().commit();
		return cliente;
	}
	
	public List<Cliente> listaCliente() {
		return entityManager.createQuery("from Cliente").getResultList();
	}
	
	public void remove(Cliente cliente) {
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		
	}
	public List<Cliente> pesquisa(String nome) {
		List<Cliente> clientes =entityManager.createQuery("from Cliente c where lower(c.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return clientes;
	}
	public Cliente buscaId(Long id) {
		return entityManager.find(Cliente.class, id);
	}
    
	

}
