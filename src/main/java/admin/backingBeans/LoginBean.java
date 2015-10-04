package admin.backingBeans;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
@Named
public class LoginBean {

  public String logout() {
	
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) 
        context.getExternalContext().getRequest();
    try {
      request.logout();
      return "/index.xhtml";
    } catch (ServletException e) {
      context.addMessage(null, new FacesMessage("Logout failed."));
    }
    return null;
  }
}