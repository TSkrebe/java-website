package backingBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Article;
import entities.Comment;
import services.ClientService;

@Named
@RequestScoped
public class WelcomePage {
	
	private List<Article> articles;

	@Inject
	private ClientService service;
	
	@PostConstruct
	public void initData(){
		System.out.println("init welcome");
		articles = service.getAll();
	}
	
	public List<Article> getArticles(){
		return articles;
	}
	
	@PreDestroy
	public void destr(){
		System.out.println("destroy welcome");
	}

}
