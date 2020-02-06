package servico;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelos.Fabricante;
import modelos.Grupo;

@ApplicationScoped
public class ServicoFabricante extends Servico {

	private Fabricante aux = new Fabricante();
	
	public void saveFabricante(Fabricante fabricante) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(fabricante);
		em.getTransaction().commit();
		em.close();
	}

	public Fabricante upDate(Fabricante fabricanteSelecionado) {
		EntityManager em = emf.createEntityManager();
		Fabricante r = em.find(Fabricante.class, fabricanteSelecionado.getNumero());
		em.refresh(r);
		em.close();

		return r;
	}

	public List<Grupo> getGruposFabricante(Fabricante fabricanteSelecionado) {

		List<Grupo> grupos = null;
		EntityManager em = emf.createEntityManager();
		Fabricante r = em.find(Fabricante.class, fabricanteSelecionado.getNumero());
		em.refresh(r);
		grupos = r.getGrupos();
		em.close();

		return grupos;
	}

	public boolean removeFabricante(Fabricante fabricante) {

		boolean sucesso = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Fabricante f = em.find(Fabricante.class, fabricante.getNumero());
			em.remove(f);
			em.getTransaction().commit();
			em.close();
			sucesso = true;
		} catch (Exception e) {

		}

		return sucesso;

	}

	public void upDateFabricante(Fabricante r) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(r);
		em.getTransaction().commit();
		em.close();
	}

	public Fabricante pesquisar(int codigo) {
		EntityManager em = emf.createEntityManager();

		aux = em.find(Fabricante.class, codigo);
		em.close();

		return aux;
	}
	
	@SuppressWarnings("unchecked")
	public List<Fabricante> getFabricantes() {

		List<Fabricante> Fabricantes;
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select r From Fabricante r", Fabricante.class);
		Fabricantes = q.getResultList();
		em.close();
		return Fabricantes;
	}
	

}
