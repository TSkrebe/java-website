package admin.backingBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import admin.AdminArticleService;
import entities.Article;
import entities.Comment;

@ViewScoped
@Named
public class CommentsBackingBean implements Serializable{

	@Inject
	private AdminArticleService service;
	
	private List<Comment> comments;
	
	@PostConstruct
	public void init(){
		comments = service.getComments();
	}

	public List<Comment> getComments() {
		init();
		return comments;
	}
	
	public void delete(Comment comment){
		Article article = comment.getArticle();
		article.getComments().remove(comment);
		service.mergeEntity(article);
	}

	
	
}
