package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Empresa;
import br.uece.sistemas.model.OrdemServico;
import br.uece.sistemas.service.OrdemServicoService;

public class OrdemServicoServiceImpl implements OrdemServicoService {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
	
	public OrdemServico salva(OrdemServico ordemServico) {
		entityManager.getTransaction().begin();
		if (ordemServico.getId() == null || ordemServico.getId() == 0) {
			entityManager.persist(ordemServico);
		} else {
			ordemServico = entityManager.merge(ordemServico);
		}
		entityManager.getTransaction().commit();
		return ordemServico;
	}

	public List<OrdemServico> listaOrdemServico() {
		return entityManager.createQuery("from OrdemServico").getResultList();
	}

	public void remove(OrdemServico ordemServico) {
		// TODO Auto-generated method stub
		
	}

	public List<OrdemServico> pesquisa(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrdemServico buscaId(Long id) {
		return entityManager.find(OrdemServico.class, id);
	}
    
    
}
