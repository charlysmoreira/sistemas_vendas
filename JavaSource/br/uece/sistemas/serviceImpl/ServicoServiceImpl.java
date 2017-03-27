package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Servico;
import br.uece.sistemas.service.ServicoService;

public class ServicoServiceImpl implements ServicoService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Servico salva(Servico servico) {
		entityManager.getTransaction().begin();
		if (servico.getId() == null || servico.getId() == 0) {
			entityManager.persist(servico);
		} else {
			servico = entityManager.merge(servico);
		}
		entityManager.getTransaction().commit();
		return servico;
	}
	
	public List<Servico> listaServico() {
		return entityManager.createQuery("from Servico").getResultList();
	}
	
	public void remove(Servico servico) {
		entityManager.getTransaction().begin();
		entityManager.remove(servico);
		entityManager.getTransaction().commit();
		
	}
	public List<Servico> pesquisa(String nome) {
		List<Servico> servicos = entityManager.createQuery("from Servico s where lower(s.nome) like lower(:nome)")
		.setParameter("nome", "%"+nome+"%").getResultList();
		return servicos;
	}
	public Servico buscaId(Long id) {
		return entityManager.find(Servico.class, id);
	}
    
	

}
