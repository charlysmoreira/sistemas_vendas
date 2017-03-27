package br.uece.sistemas.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.uece.sistemas.model.Usuario;
import br.uece.sistemas.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SVendas");
    EntityManager entityManager = emf.createEntityManager();
    
	public Usuario salva(Usuario usuario) {
		entityManager.getTransaction().begin();
		if (usuario.getId() == null || usuario.getId() == 0) {
			entityManager.persist(usuario);
		} else {
			usuario = entityManager.merge(usuario);
		}
		entityManager.getTransaction().commit();
		return usuario;
	}

	public List<Usuario> listaUser() {
		return entityManager.createQuery("from Usuario").getResultList();
	}

	public void remove(Usuario usuario) {
		entityManager.getTransaction().begin();
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
	}
	
	public List<Usuario> pesquisa(String nome) {
		List<Usuario> usuarios =entityManager.createQuery("from Usuario u where lower(u.nome) like lower(:nome)").setParameter("nome", "%"+nome+"%").getResultList();
		return usuarios;
	}

	public Usuario buscaId(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	public Usuario verificaUsuario(String nome, String senha) {
		String hql = "from Usuario u where u.nome = :login and u.senha = :senha";
		Query query = entityManager.createQuery(hql)
			.setParameter("login", nome)
			.setParameter("senha", senha);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


}
