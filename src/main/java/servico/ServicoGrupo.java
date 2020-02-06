package servico;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelos.Grupo;
import modelos.Fabricante;
import modelos.Componente;

@ManagedBean(name="servGrupo")
@ApplicationScoped

public class ServicoGrupo extends Servico {

	private Grupo aux = new Grupo();
	
	public void saveGrupo(Grupo grupo) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(grupo);
		em.getTransaction().commit();
		em.close();
	}

	public Grupo upDate(Grupo grupoSelecionado) {
		EntityManager em = emf.createEntityManager();
		Grupo f = em.find(Grupo.class, grupoSelecionado.getCodigo());
		em.refresh(f);
		em.close();
		return f;
	}

	public List<Componente> getComponenteGrupo(Grupo grupoSelecionado) {

		List<Componente> componentes = null;
		EntityManager em = emf.createEntityManager();
		Grupo f = em.find(Grupo.class, grupoSelecionado.getCodigo());
		em.refresh(f);
		componentes = f.getComponentes();
		em.close();

		return componentes;
	}
	
	public boolean removeGrupo(Grupo grupo) {

		boolean sucesso = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Grupo f = em.find(Grupo.class, grupo.getCodigo());
			em.remove(f);
			em.getTransaction().commit();
			em.close();
			sucesso = true;
		} catch (Exception e) {

		}

		return sucesso;

	}

	public void upDateGrupo(Grupo f) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(f);
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public List<Grupo> getGrupos() {

		List<Grupo> grupos;
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select f From Grupo f", Grupo.class);
		grupos = q.getResultList();
		em.close();
		return grupos;
	}

	public List<Fabricante> getFabricanteGrupo(Grupo grupoSelecionado) {
		
		List<Fabricante> fabricantes = null;
		EntityManager em = emf.createEntityManager();
		Grupo f = em.find(Grupo.class, grupoSelecionado.getCodigo());
		em.refresh(f);
		fabricantes = f.getFabricantes();
		em.close();

		return fabricantes;
	}

	public Grupo pesquisar (int codigo) {
		EntityManager em = emf.createEntityManager();
		
		aux = em.find(Grupo.class, codigo);
		em.close();
		
		return aux;
	}
}
