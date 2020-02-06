package Util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class messagesFaces {
	
	public static void adicionarMsgInfo(String mensagem){
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,null);
		
		FacesContext facescontext = FacesContext.getCurrentInstance();
		facescontext.addMessage(null, facesMessage);
		
	}

public static void adicionarMsgError(String mensagem){
	FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,null);
	
	FacesContext facescontext = FacesContext.getCurrentInstance();
	facescontext.addMessage(null, facesMessage);
	}
	
}
