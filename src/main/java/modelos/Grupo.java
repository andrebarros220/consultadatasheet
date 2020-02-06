  package modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn
	@Column
	private int codigo;
	
	@Column
	private String nome;

	

	@OneToMany(mappedBy = "grupo")
	private List<Componente> componentes = new ArrayList<Componente>();

	@ManyToMany(mappedBy = "grupos")
	private List<Fabricante> fabricantes = new ArrayList<Fabricante>();
	
	

	public Grupo(int codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public Grupo() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

	public void addComponente(Componente componente) {
		componentes.add(componente);
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
		Grupo other = (Grupo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grupo [codigo=" + codigo + ", nome=" + nome + "]";
	}

	
}
