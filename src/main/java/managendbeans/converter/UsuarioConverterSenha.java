package managendbeans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelos.Usuario;
import servico.ServicoUsuario;

@FacesConverter("converterUsuarioSenha")
public class UsuarioConverterSenha implements Converter{
	ServicoUsuario servico = new ServicoUsuario();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		if (value != null && !value.isEmpty()) {
			for (Usuario f : servico.getUsuarios())
				if (f.getSenha().equals(value))
					return f;
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object usuario) {
		if (usuario == null || usuario.equals("")) {
			return null;
		} else {
			return ((Usuario) usuario).getSenha();
		}
	}


}
