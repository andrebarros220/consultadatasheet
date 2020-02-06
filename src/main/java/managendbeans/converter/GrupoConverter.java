package managendbeans.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelos.Grupo;
import servico.ServicoGrupo;


@FacesConverter("converterGrupo")
public class GrupoConverter implements Converter {
	ServicoGrupo servico = new ServicoGrupo();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		
		if (value != null && !value.isEmpty()) {
			  for(Grupo f : servico.getGrupos())
				 if(f.getNome().equals(value))
				  	return f;
					
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,
			Object grupo) {
		if (grupo == null || grupo.equals("")) {
			return null;
		} else {
			return ((Grupo) grupo).getNome();

		}
	}

}
