package backingBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Article;
import entities.Comment;
import services.ClientService;

@Named
@ViewScoped
public class ArticlePage implements Serializable{

	private static final long serialVersionUID = 1L;

	private Article article = new Article();
	private List<Comment> comments;
	
	private Comment comment = new Comment();
	
	@Inject 
	private ClientService service;
	

	public String findArticle(){
		if (article.getTitleId() == null || article.getTitleId().isEmpty())
			return null;
		Article articleNew = service.getEntityByTitle(article.getTitleId());
		if (articleNew == null)
			return "/search.xhtml?phrase=" + article.getTitleId() + "&faces-redirect=true";
		article = articleNew;
		comments = article.getComments();
		return null;
	}
	
	public void persistComment(){
		comment.setDate(new Date());
		comment.setArticle(article);
		article.addComment(comment);
		//merge to database
		service.mergeEntity(article);
		//get new version of article with maybe parallel comments
		article = service.getEntity(article.getId(), Article.class);
		comments = article.getComments();
		comment.clean();
	}
	
	@PreDestroy
	public void dest(){
		System.out.println("destroy article");
	}
	@PostConstruct
	public void init(){
		System.out.println("init article");
	}

	public Article getArticle() {
		return article;
	}
	
	public List<Comment> getComments(){
		return comments;
	}
	
	public Comment getComment(){
		return comment;
	}
}
