package managendbeans.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import modelos.Componente;
import servico.ServicoComponente;


	public class ComponenteDataModel extends ListDataModel<Componente> implements SelectableDataModel<Componente> 
	{
		private static ServicoComponente servico = new ServicoComponente();
		
		public ComponenteDataModel()
		{
			
		}

		public ComponenteDataModel(List <Componente> list)
		{
		   super(list);	
		}

		
		@Override
		public Componente getRowData(String rowKey) {
			
			for(Componente f: servico.getComponentes())
			   if(Integer.parseInt(rowKey) ==  f.getId())
				   return f;
			
			return null;
		}

		@Override
		public Object getRowKey(Componente componente) {
			return componente.getId();
		}
		
	}





	
	

