package managendbeans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelos.Fabricante;
import servico.ServicoFabricante;

@FacesConverter("converterFb")
public class FabricanteConverter implements Converter {
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && !value.isEmpty()) {

			ServicoFabricante servico = new ServicoFabricante();

			for (Fabricante p : servico.getFabricantes())
				if (p.getNome().equals(value))
					return p;

		}

		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object fabricante) {
		if (fabricante == null || fabricante.equals("")) {
			return null;
		} else {
			return ((Fabricante) fabricante).getNome();

		}
	}
}
