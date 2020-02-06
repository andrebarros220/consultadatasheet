package managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import Util.messagesFaces;
import managendbeans.datamodel.FabricanteDataModel;
import modelos.Grupo;
import modelos.Fabricante;
import servico.ServicoComponente;
import servico.ServicoGrupo;
import servico.ServicoFabricante;

@ManagedBean(name = "fabricanteManagedBean")
@SessionScoped
public class FabricanteMB {

	private Fabricante fabricante = new Fabricante();
	private Fabricante fabricanteSelecionado = new Fabricante();
	private ServicoFabricante servicoFabr = new ServicoFabricante();
	private ServicoGrupo servicoGr = new ServicoGrupo();
	private ServicoComponente servicoComp = new ServicoComponente();
	private Fabricante aux = new Fabricante();
	private List<Fabricante> fabricantesGrupo = null;
	private List<Grupo> gruposp = null;
	private DualListModel<Grupo> dualList = new DualListModel<Grupo>();

	// Getters and Setters

	public void setGrupos(DualListModel<Grupo> grupos) {
		this.dualList = grupos;
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public FabricanteDataModel getFabricantes() {
		return new FabricanteDataModel(servicoFabr.getFabricantes());
	}

	public List<Fabricante> getFabricantesGrupo() {
		if (fabricantesGrupo == null)
			return fabricantesGrupo;
		else {
			return fabricantesGrupo;
		}
	}

	public List<Fabricante> allFabricantes() {
		return servicoFabr.getFabricantes();
	}

	public void addGrupoToFabricante(Fabricante fabricante) {
		fabricanteSelecionado = fabricante;

	}

	public void PesquisarGrupo() {
		gruposp = servicoComp.allGrupoFabricante(fabricante);
	}

	public List<Grupo> getGrupoFabricante() {
		if (gruposp != null)
			return servicoFabr.getGruposFabricante(fabricanteSelecionado);
		else {
			return null;
		}
	}

	public void onRowSelect(SelectEvent event) {

	}

	// Dual List para o PickList Primefaces
	public DualListModel<Grupo> getGrupos() {

		List<Grupo> source = new ArrayList<Grupo>();
		List<Grupo> target = new ArrayList<Grupo>();

		source.addAll(servicoGr.getGrupos());

		if (fabricanteSelecionado != null) {
			target.addAll(fabricanteSelecionado.getGrupos());
			source.removeAll(fabricanteSelecionado.getGrupos());
		}

		dualList.setSource(source);
		dualList.setTarget(target);

		return dualList;

	}

	// Associa o Grupo ao Fabricante
	public void associar() {
		for (Grupo f : dualList.getSource()) {
			f.getFabricantes().remove(fabricanteSelecionado);
		}

		for (Grupo f : dualList.getTarget()) {
			f.getFabricantes().add(fabricanteSelecionado);
		}

		fabricanteSelecionado.getGrupos().clear();
		fabricanteSelecionado.getGrupos().addAll(dualList.getTarget());

		servicoFabr.upDateFabricante(fabricanteSelecionado);

		fabricanteSelecionado.getGrupos().clear();
		fabricanteSelecionado.getGrupos().clear();

	}

	// Alterar na Linha da Tabela
	public void onRowEdit(RowEditEvent event) {
		Fabricante f = (Fabricante) event.getObject();
		servicoFabr.upDateFabricante(f);
	}

	// Salvar
	public void salvar() {
		servicoFabr.saveFabricante(fabricante);
		fabricante = new Fabricante();
		messagesFaces.adicionarMsgInfo("Salvo com sucesso !");

	}

	// Pesquisar
	public void pesquisar() {
		aux = servicoFabr.pesquisar(fabricante.getNumero());
		fabricante.setNumero(aux.getNumero());
		fabricante.setNome(aux.getNome());
	}

	// Excluir
	public void remove(Fabricante fabricante) {
		servicoFabr.removeFabricante(fabricante);
	}

}
