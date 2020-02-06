package modelos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;

@NamedQueries(
		@NamedQuery(name="Componente.selecionarValor",
		                   query = "Select v FROM Componente v where v.valor = :valor"))

@Entity
public class Componente  implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@PrimaryKeyJoinColumn
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String codigodatasheet ;
	@Column
	private int idcompgrupo;
	@Column
	private String valor;
	@Column
	private double tensao;
	@Column
	private String encapsulamento;
	@Column
	private String potencia;
	@Column
	private String temperatura;
	@Column
	private double impedancia;
	@Column
	private String tolerancia;
	@Column
	private String tipo;
	@Column
	private String tipomaterial;
	@Column
	private byte arquivo;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "codigo"))
	private Grupo grupo;
		
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigodatasheet() {
		return codigodatasheet;
	}
	public void setCodigodatasheet(String codigodatasheet) {
		this.codigodatasheet = codigodatasheet;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public double getTensao() {
		return tensao;
	}
	public void setTensao(double tensao) {
		this.tensao = tensao;
	}
	public String getEncapsulamento() {
		return encapsulamento;
	}
	public void setEncapsulamento(String encapsulamento) {
		this.encapsulamento = encapsulamento;
	}
	public String getPotencia() {
		return potencia;
	}
	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}
	public String getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}
	public double getImpedancia() {
		return impedancia;
	}
	public void setImpedancia(double impedancia) {
		this.impedancia = impedancia;
	}
	public String getTolerancia() {
		return tolerancia;
	}
	public void setTolerancia(String tolerancia) {
		this.tolerancia = tolerancia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTipomaterial() {
		return tipomaterial;
	}
	public void setTipomaterial(String tipomaterial) {
		this.tipomaterial = tipomaterial;
	}
	public byte getArquivo() {
		return arquivo;
	}
	public void setArquivo(byte arquivo) {
		this.arquivo = arquivo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 310;
		int result = 1;
		result = prime * result + id;
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
		Componente other = (Componente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
