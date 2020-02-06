package managendbeans.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import modelos.Grupo;
import servico.ServicoGrupo;


public class GrupoDataModel extends ListDataModel<Grupo> implements SelectableDataModel<Grupo> 
{
	private static ServicoGrupo servico = new ServicoGrupo();
	
	public GrupoDataModel()
	{
		
	}

	public GrupoDataModel(List <Grupo> list)
	{
	   super(list);	
	}

	
	@Override
	public Grupo getRowData(String rowKey) {
		
		for(Grupo f: servico.getGrupos())
		   if(Integer.parseInt(rowKey) ==  f.getCodigo())
			   return f;
		
		return null;
	}

	@Override
	public Object getRowKey(Grupo grupo) {
		return grupo.getCodigo();
	}
	
}




