package managendbeans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


import modelos.Componente;
import servico.ServicoComponente;

@FacesConverter("converterComponente")
public class ComponenteConverter implements Converter {
	ServicoComponente servico = new ServicoComponente();
	

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

	if (value != null && !value.isEmpty()) {
		for (Componente f : servico.getComponentes())
			if (f.getCodigodatasheet().equals(value))
				return f;
	}
     	
	return null;

}


	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object componente) {
		if (componente == null || componente.equals("")) {
			return null;
		} else {
			return ((Componente) componente).getCodigodatasheet();

		}
		
	
		
		

	}
}
