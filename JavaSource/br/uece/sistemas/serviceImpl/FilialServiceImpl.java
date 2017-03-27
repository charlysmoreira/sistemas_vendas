package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Categoria;
import br.uece.sistemas.model.Empresa;
import br.uece.sistemas.model.Filial;
import br.uece.sistemas.service.CategoriaService;

public class FilialServiceImpl {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public List<Filial> listaFilial() {
		return entityManager.createQuery("from Filial").getResultList();
	}

}
