package servico;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelos.Fabricante;
import modelos.Grupo;
import modelos.Componente;

@ApplicationScoped
public class ServicoComponente extends Servico {

	private Componente aux = new Componente();
	
	public void upDateComponente(Componente p) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();

	}
	
	@SuppressWarnings("unchecked")
	public List<Componente> allComponentesGrupo(Grupo grupo)
	{
	    List<Componente> componentesGrupo = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a From Componente a where a.grupo.codigo = :codigo ");
		query.setParameter("codigo", grupo.getCodigo());
		componentesGrupo = query.getResultList();
		em.close();
		
		return componentesGrupo;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Componente> ComponenteSelecao(Componente componente)
	{
		List<Componente> componenteselecionado = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a From Componente a where a.codigodatasheet = :codigodatasheet ");
		query.setParameter("codigodatasheet", componente.getCodigodatasheet());
		componenteselecionado =  query.getResultList();
		em.close();
		
		return componenteselecionado;
	}
	
	@SuppressWarnings("unchecked")
	public List<Componente> ComponenteSelecaoValor(Componente componente)
	{
		List<Componente> componentesvalorselecionado = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select v FROM Componente v where v.valor = :valor" );
		query.setParameter("valor", componente.getValor());
		componentesvalorselecionado = query.getResultList();
		em.close();
		
		return componentesvalorselecionado;
	}
	
	@SuppressWarnings( "unchecked" )
	public List<Componente> PesquisaComponenteValor()
	{
		EntityManager em = emf.createEntityManager();
		List<Componente> componentesvalor;
		try{Query q = em.createNamedQuery("Componente.selecionaValor");
		componentesvalor = q.getResultList();	
		}catch(Exception e){
			componentesvalor = new ArrayList<Componente>();
			}finally{em.close();
			}return componentesvalor;
			
	}	
		
	
	
	@SuppressWarnings("unchecked")
	public List<Componente> allComponentesGrupoUnidade(Grupo grupo)
	{
	    List<Componente> componentesGrupo = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a From Componente a where a.grupo.codigo = :codigo ");
		query.setParameter("codigo", grupo.getCodigo());
		componentesGrupo = query.getResultList();
		em.close();
		
		return componentesGrupo;
	}
	@SuppressWarnings("unchecked")
	public List<Componente> allComponentes(Componente componente)
	{
	    List<Componente> componentesunidades = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a From Componente a where a.componente.id = :id ");
		query.setParameter("id", componente.getId());
		componentesunidades = query.getResultList();
		em.close();
		
		return componentesunidades;
	}
	
	@SuppressWarnings("unchecked")
	public List<Componente> allComponentesvalor(Componente componente)
	{
	    List<Componente> componentesvalor = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a From Componente a where a.componente.tensao = :tensao ");
		query.setParameter("tensao", componente.getTensao());
		componentesvalor = query.getResultList();
		em.close();
		
		return componentesvalor;
	}
	
	@SuppressWarnings("unchecked")
	public List<Grupo> allGrupoFabricante(Fabricante fabricante)
	{
	    List<Grupo> fabricanteGrupo = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a From Grupo a join a.fabricantes p where p.numero = :codigo");
		query.setParameter("codigo", fabricante.getNumero());
		fabricanteGrupo = query.getResultList();
		em.close();
		
		return fabricanteGrupo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Fabricante> allFabricantesGrupo(Grupo grupo)
	{
	    List<Fabricante> fabricantesGrupo = null;
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a From Fabricante a join a.grupos p where p.codigo = :codigo ");
		query.setParameter("codigo", grupo.getCodigo());
		fabricantesGrupo = query.getResultList();
		em.close();
		
		return fabricantesGrupo;
	}

	public void saveComponente(Componente componente) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(componente);
		em.getTransaction().commit();
		em.close();
	}
	
	public Componente  pesquisar(Componente componente) {
		EntityManager em = emf.createEntityManager();
		
		aux = em.find(Componente.class, componente.getId());
		em.close();
		
		return aux;
	}
	
	

	@SuppressWarnings("unchecked")
	public List<Componente> getComponentes() {
		List<Componente> componentes;
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select p From Componente p", Componente.class);
		componentes = q.getResultList();
		em.close();
		return componentes;
	}

	public boolean removeComponente(Componente componente) {

		boolean sucesso = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Componente p = em.find(Componente.class, componente.getId());
			em.remove(p);
			em.getTransaction().commit();
			em.close();
			sucesso = true;
		} catch (Exception e) {

		}

		return sucesso;
	}
}
