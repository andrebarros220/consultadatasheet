package modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
//import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@NamedQuery(name ="Usuario.Autenticar", query = "SELECT usuario FROM Usuario usuario WHERE usuario.nome = :nome AND usuario.senha = :senha")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn
	@Column
	private int ID;

	@Column
	private String nome;
	
	@Column
	private String senha;
	
	

		//Getters an Setters	

	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public int hashCode() {
		final int prime = 310;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	

	

	
	
	
	
	
	
	
}
