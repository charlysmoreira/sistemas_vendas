package br.uece.sistemas.serviceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.FormaDePagamento;

public class FormaDePagamentoServiceImpl {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
    public FormaDePagamento salva(FormaDePagamento formaDePagamento) {
		entityManager.getTransaction().begin();
		if (formaDePagamento.getId() == null || formaDePagamento.getId() == 0) {
			entityManager.persist(formaDePagamento);
		} else {
			formaDePagamento = entityManager.merge(formaDePagamento);
		}
		entityManager.getTransaction().commit();
		return formaDePagamento;
	}

}
