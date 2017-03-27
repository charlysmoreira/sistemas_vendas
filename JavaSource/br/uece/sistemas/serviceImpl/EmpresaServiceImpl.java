package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.uece.sistemas.model.Categoria;
import br.uece.sistemas.model.Empresa;
import br.uece.sistemas.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
	
	public Empresa salva(Empresa empresa) {
		entityManager.getTransaction().begin();
		if (empresa.getId() == null || empresa.getId() == 0) {
			entityManager.persist(empresa);
		} else {
			empresa = entityManager.merge(empresa);
		}
		entityManager.getTransaction().commit();
		return empresa;
	}

	public List<Empresa> listaEmpresa() {
		return entityManager.createQuery("from Empresa").getResultList();
	}

	public void remove(Empresa empresa) {
		// TODO Auto-generated method stub
		
	}

	public List<Empresa> pesquisa(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	public Empresa buscaId(Long id) {
		return entityManager.find(Empresa.class, id);
	}
    
    
}
