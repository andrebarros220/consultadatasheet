package managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import org.apache.poi.EmptyFileException;
import org.primefaces.event.RowEditEvent;
import Util.messagesFaces;
import modelos.Componente;
import modelos.Grupo;
import modelos.Fabricante;
import servico.ServicoComponente;
import servico.ServicoGrupo;

@SuppressWarnings("unused")
@ManagedBean(name = "componenteManagedBean")
@ViewScoped
public class ComponenteMB {

	private Componente componente = new Componente();
	private Grupo grupo;
	private Componente aux = new Componente();
	private List<Componente> compon;
	private ServicoComponente servicoComponente = new ServicoComponente();
	private ServicoGrupo servicoGrupo = new ServicoGrupo();
	private List<Componente> componentes = null;
	private List<Componente> componentesselec = null;
	private List<Componente> componentesGrupo = null;
	private List<Fabricante> fabricantesGrupo = null;
	private List<Componente> compon2 = null;
	private List<Componente> comptotais;
	private List<Componente> compfiltrados;
	private ArrayList<Componente> comptotal;
	private ArrayList<Componente> compselec;
	private List<Componente> componentestotal;
	private List<Componente> componentesselecionados;

	// Getters and Setters

	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

	public List<Componente> getComponentesselec() {
		return componentesselec;
	}

	public void setComponentesselec(List<Componente> componentesselec) {
		this.componentesselec = componentesselec;
	}

	public List<Componente> getComptotais() {
		return comptotais;
	}

	public void setComptotais(List<Componente> comptotais) {
		this.comptotais = comptotais;
	}

	public List<Componente> getCompfiltrados() {
		return compfiltrados;
	}

	public void setCompfiltrados(List<Componente> compfiltrados) {
		this.compfiltrados = compfiltrados;
	}

	public List<Grupo> getGrupos() {
		return servicoGrupo.getGrupos();
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setCurso(Grupo grupo) {
		this.grupo = grupo;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public List<Componente> getListaComponentes() {
		if (componentes == null)
			componentes = servicoComponente.getComponentes();

		return componentes;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public ArrayList<Componente> getComptotal() {
		return comptotal;
	}

	public void setComptotal(ArrayList<Componente> comptotal) {
		this.comptotal = comptotal;
	}

	public ArrayList<Componente> getCompselec() {
		return compselec;
	}

	public void setCompselec(ArrayList<Componente> compselec) {
		this.compselec = compselec;
	}

	public List<Componente> getComponentesGrupo() {
		if (componentesGrupo == null)
			return componentesGrupo;
		else {
			return componentesGrupo;
		}
	}

	public List<Fabricante> getFabricanteGrupo() {
		if (fabricantesGrupo == null)
			return fabricantesGrupo;
		else {
			return fabricantesGrupo;
		}
	}

	public List<Componente> getComponentestotal() {
		return componentestotal;
	}

	public void setComponentestotal(List<Componente> componentestotal) {
		this.componentestotal = componentestotal;
	}

	public List<Componente> getComponentesselecionados() {
		return componentesselecionados;
	}

	public void setComponentesselecionados(List<Componente> componentesselecionados) {
		this.componentesselecionados = componentesselecionados;
	}

	public String ComponentesTotalToString() {
		return componentestotal.toString();
	}

	public String ComponentesSelecionadosToString() {
		return componentesselecionados.toString();
	}

	public List<Componente> getCompon() {
		return compon;
	}

	public void setCompon(List<Componente> compon) {
		this.compon = compon;
	}

	// Alterar na Linha da Tabela
	public void onRowEdit(RowEditEvent event) {
		Componente p = (Componente) event.getObject();
		servicoComponente.upDateComponente(p);
	}

	// Salvar
	public void salvar() {
		try {
			grupo.addComponente(componente);
			componente.setGrupo(grupo);
			servicoComponente.saveComponente(componente);
			componente = new Componente();
			grupo = null;
			componentes = null;
			messagesFaces.adicionarMsgInfo("Salvo com sucesso !");
		} catch (Exception e) {
			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", " O Componente não pode ser Salvo ");
			FacesContext.getCurrentInstance().addMessage(null, error);

		}
	}

	// Pesquisar
	public void pesquisarComponentes() {
		componentesGrupo = servicoComponente.allComponentesGrupo(grupo);
		fabricantesGrupo = servicoComponente.allFabricantesGrupo(grupo);
	}

	// Pesquisar por Código
	public List<Componente> pesquisarComponentesCodigo() {
		try {
			compon = servicoComponente.ComponenteSelecao(componente);
			FacesMessage msg = new FacesMessage("Sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (NullPointerException e) {
			FacesMessage msg = new FacesMessage("Código não encontrado!      " + "Refaça a busca!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return compon;
	}

	// Pesquisar por Valor
	public List<Componente> pesquisarComponentesValor() {
		try {
			comptotais = servicoComponente.ComponenteSelecaoValor(componente);
     		FacesMessage msg = new FacesMessage("Sucesso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (NullPointerException e) {
			FacesMessage msg = new FacesMessage("Valor não encontrado!" + "Refaça a Busca");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} finally {

		}
		return comptotais;

	}

	// Excluir
	public void remove(Componente componente) {
		try {
			componentes.remove(componente);
			servicoComponente.removeComponente(componente);
			messagesFaces.adicionarMsgInfo("Removido com sucesso !");
		} catch (NullPointerException e) {
			FacesMessage msg = new FacesMessage("Ocorreu um erro ao tentar Deletar");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} finally {

		}
	}

}
