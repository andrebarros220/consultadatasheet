package managendbeans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelos.Componente;
import servico.ServicoComponente;

@FacesConverter("converterComponenteValor")
public class ComponenteConverterValor implements Converter {
	ServicoComponente servicovalor = new ServicoComponente();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && !value.isEmpty()) {
			for (Componente v : servicovalor.getComponentes())
				if (v.getValor().equals(value))
					return v;
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object componente) {
		if (componente == null || componente.equals("")) {
			return null;
		} else {
			return ((Componente) componente).getValor();

		}

	}
}
