package modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Fabricante {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column
	private int numero;
	@Column
	private String nome;
	@Column
	private boolean ativo;
	@SuppressWarnings("unused")
	private String boolAtivo; 
	
	@ManyToMany
	private List <Grupo> grupos = new ArrayList <Grupo>();

	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getBoolAtivo() {
		if (getAtivo()) {
			return "SIM";
		} else {
			return "NÃO";
		}
	}
	
	public void setBoolAtivo(String boolAtivo) {
		this.boolAtivo = boolAtivo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 310;
		int result = 1;
		result = prime * result + numero;
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
		Fabricante other = (Fabricante) obj;
		if (numero != other.numero)
			return false;
		return true;
	}
	

}
