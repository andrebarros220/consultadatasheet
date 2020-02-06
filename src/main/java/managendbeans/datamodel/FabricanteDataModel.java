package managendbeans.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import modelos.Fabricante;
import servico.ServicoFabricante;


public class FabricanteDataModel extends ListDataModel<Fabricante> implements SelectableDataModel<Fabricante> 
{
	private static ServicoFabricante servico = new ServicoFabricante();
	
	public FabricanteDataModel()
	{
		
	}

	public FabricanteDataModel(List <Fabricante> list)
	{
	   super(list);	
	}

	
	@Override
	public Fabricante getRowData(String rowKey) {
		
		for(Fabricante r: servico.getFabricantes())
		   if(Integer.parseInt(rowKey) ==  r.getNumero())
			   return r;
		
		return null;
	}

	@Override
	public Object getRowKey(Fabricante fabricante) {
		return fabricante.getNumero();
	}
	
}