package managedbeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import Util.messagesFaces;
import managendbeans.datamodel.GrupoDataModel;
import modelos.Componente;
import modelos.Grupo;
import modelos.Fabricante;
import servico.ServicoGrupo;

@ManagedBean(name = "grupoManagedBean")
@RequestScoped
public class GrupoMB {

	private Grupo grupo = new Grupo();
	private Grupo grupoSelecionado;
	private Grupo aux = new Grupo();
	private ServicoGrupo servico = new ServicoGrupo();
	private List<Grupo> grupos;

	// Getters and Setters

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		if (grupoSelecionado != null)
			this.grupoSelecionado = servico.upDate(grupoSelecionado);
	}

	public List<Componente> getComponentesGrupo() {
		if (grupoSelecionado != null)
			return servico.getComponenteGrupo(grupoSelecionado);
		else
			return null;
	}

	public List<Fabricante> getFabricantes() {
		if (grupoSelecionado != null)
			return servico.getFabricanteGrupo(grupoSelecionado);
		else
			return null;
	}

	public DataModel<Grupo> getGrupos() {
		if (grupos == null)
			grupos = servico.getGrupos();

		return new GrupoDataModel(grupos);
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public void onRowSelect(SelectEvent event) {

	}

	public void onRowUnselect(SelectEvent event) {

	}

	// Editar na Linha
	public void onRowEdit(RowEditEvent event) {
		Grupo f = (Grupo) event.getObject();
		servico.upDateGrupo(f);
	}

	// Salvar
	public void salvar() {
		servico.saveGrupo(grupo);
		grupo = new Grupo();
		messagesFaces.adicionarMsgInfo("Salvo com sucesso !");
	}

	// Pesquisar
	public void pesquisar() {
		aux = servico.pesquisar(grupo.getCodigo());
		grupo.setCodigo(aux.getCodigo());
		grupo.setNome(aux.getNome());
	}

	// Excluir
	public void remove(Grupo grupo) {
		if (servico.removeGrupo(grupo)) {
			grupos.remove(grupo);
			grupoSelecionado = null;
			messagesFaces.adicionarMsgInfo("Removido com sucesso !");
		} else {
			FacesMessage msg = new FacesMessage("Impossível remover grupo com Componentes", "Nome: " + grupo.getNome());
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

}
