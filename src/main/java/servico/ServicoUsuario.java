package servico;


import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import modelos.Usuario;

@ManagedBean(name="servUsuario")
@ApplicationScoped
public class ServicoUsuario extends Servico {

	private Usuario aux = new Usuario();
	
	public void saveUsuario(Usuario usuario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		em.close();
	}
	
	public Usuario upDate(Usuario usuarioSelecionado) {
		EntityManager em = emf.createEntityManager();
		Usuario f = em.find(Usuario.class, usuarioSelecionado.getID());
		em.refresh(f);
		em.close();
		return f;
	}
	
	public boolean removeUsuario(Usuario usuario) {
		boolean sucesso = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Usuario f = em.find(Usuario.class, usuario.getID());
			em.remove(f);
			em.getTransaction().commit();
			em.close();
	
		} catch (Exception e) {

		}
		return sucesso;
		
	}
	
	public Usuario pesquisar (int ID) {
		EntityManager em = emf.createEntityManager();
		
		aux = em.find(Usuario.class, ID);
		em.close();
		
		return aux;
	}
	
	
	public void upDateUsuario(Usuario f) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(f);
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios() {

		List<Usuario> usuarios;
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select f From Usuario f", Usuario.class);
		usuarios = q.getResultList();
		em.close();
		return usuarios;
	}

	public Usuario autenticaUsuario(Usuario usuario)
	{
		EntityManager em = emf.createEntityManager();
		try{Query q = em.createNamedQuery("Usuario.Autenticar");
		q.setParameter("senha", usuario.getSenha());
		}catch(Exception e ){
			System.out.println("Ocorreu um erro na busca do usuario por senha");
			usuario = new Usuario();
			}finally{em.close();
			}return usuario;
			
	}
	
	
	@SuppressWarnings("unchecked")
	public Usuario autenticacao(Usuario usuario)
	{
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select usuario FROM Usuario usuario WHERE usuario.nome = :nome AND usuario.senha = :senha");
		q.setParameter("nome", usuario.getNome());
		q.setParameter("senha", usuario.getSenha());
		System.out.println("nome:" + usuario.getNome());
		System.out.println("senha:" + usuario.getSenha());
		List<Usuario> resultados = q.getResultList();
        if (resultados.isEmpty()) 
        	return null;
        else if (resultados.size() == 1) 
        	return resultados.get(0);
        System.out.println("Conteudo de resultado : " + resultados.toString());
        em.close();
        throw new NonUniqueResultException();
		}
	
	public Integer Logar (Usuario usuario)
	{
		int senhabuscada = 0;
		EntityManager em = emf.createEntityManager();
		try{Query query = em.createQuery("Select s From Usuario s where s.senha = :1234");
		query.setParameter("senha", usuario.getSenha());
		senhabuscada =  query.getFirstResult();
		em.close();
		}catch(Exception e){
			usuario = new Usuario();
			}finally{em.close();
			 }
		return senhabuscada;
	}
	
}
