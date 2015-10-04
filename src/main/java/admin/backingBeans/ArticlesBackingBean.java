package admin.backingBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import admin.AdminArticleService;
import entities.Article;

@Named
@ViewScoped
public class ArticlesBackingBean implements Serializable{
	
	private List<Article> articles;
	
	@Inject
	AdminArticleService service;
	
	@PostConstruct
	public void init(){
		articles = service.getAll();
	}
	
	public List<Article> getArticles(){
		return articles;
	}
	
	public void delete(Article article){
		service.removeArticle(article);
	}
	
}
