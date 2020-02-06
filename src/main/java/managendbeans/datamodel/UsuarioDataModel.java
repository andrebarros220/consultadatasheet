package managendbeans.datamodel;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import modelos.Usuario;
import servico.ServicoUsuario;

public class UsuarioDataModel extends ListDataModel<Usuario> implements SelectableDataModel<Usuario> {

	private static ServicoUsuario servico = new ServicoUsuario();
	
	public UsuarioDataModel()
	{
		
	}

	public UsuarioDataModel(List <Usuario> list)
	{
	   super(list);	
	}

	@Override
	public Usuario getRowData(String rowKey) {
		
		for(Usuario f: servico.getUsuarios())
		   if(Integer.parseInt(rowKey) ==  f.getID())
			   return f;
		
		return null;
	}

	@Override
	public Object getRowKey(Usuario usuario) {
		return usuario.getID();
	}
	
}
