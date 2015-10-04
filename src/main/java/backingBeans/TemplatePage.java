package backingBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.registry.infomodel.EmailAddress;

import org.hibernate.validator.constraints.NotBlank;

import entities.Comment;
import entities.SubscribeEmail;import services.ClientService;

@Named
@RequestScoped
public class TemplatePage {

	@NotBlank(message="No input")
	private String searchPhrase;
	
	private SubscribeEmail email = new  SubscribeEmail();
	
	private List<Comment> comments;
	
	@Inject
	private ClientService service;
	
	@PostConstruct
	public void initData(){
		System.out.println("init template");
		comments = service.getTopComments();
		searchPhrase = "";
	}
	
	public void subscribeEmail(){
		try{
			service.persistEmail(email);
			email = new SubscribeEmail();
		}catch(Exception e){
			FacesMessage facesMsg = new FacesMessage("This email address is already subscribed");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
	}
	


	public String search(){
		return "/search.xhtml?phrase=" + searchPhrase + "&faces-redirect=true";
	}
	
	public String getSearchPhrase() {
		return searchPhrase;
	}
	
	public void setSearchPhrase(String searchPhrase) {
		this.searchPhrase = searchPhrase;
	}

	public List<Comment> getComments(){
		return comments;
	}
	
	public SubscribeEmail getEmail(){
		return email;
	}
	@PreDestroy
	public void destor(){
		System.out.println("destr templ");
	}
	
	
	
}
