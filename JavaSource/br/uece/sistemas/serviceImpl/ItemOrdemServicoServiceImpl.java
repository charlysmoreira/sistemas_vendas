package br.uece.sistemas.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.ItemOrdemServico;
import br.uece.sistemas.service.ItemOrdemServicoService;

public class ItemOrdemServicoServiceImpl implements ItemOrdemServicoService{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public ItemOrdemServico salva(ItemOrdemServico itemOrdemServico) {
		entityManager.getTransaction().begin();
		if (itemOrdemServico.getId() == null || itemOrdemServico.getId() == 0) {
			entityManager.persist(itemOrdemServico);
		} else {
			itemOrdemServico = entityManager.merge(itemOrdemServico);
		}
		entityManager.getTransaction().commit();
		return itemOrdemServico;
	}

}
