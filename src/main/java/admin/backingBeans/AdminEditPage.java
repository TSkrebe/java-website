package admin.backingBeans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import admin.AdminArticleService;
import entities.Article;

@Named
@ViewScoped
public class AdminEditPage implements Serializable {

	private Article article = new Article();
	@Inject
	private AdminArticleService service;
	
	public void init(){
		article = service.getEntityByTitle(article.getTitleId());
	}
	
	public void edit(){
		service.mergeEntity(article);
	}
	
	public Article getArticle() {
		return article;
	}
	
}
