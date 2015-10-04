package admin;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import entities.Article;
import entities.Comment;
import services.AbstractService;
import services.Service;

@Stateless
public class AdminArticleService extends AbstractService<Article>{

	@Override
	public List<Article> getAll() {
		return em.createNamedQuery(Article.ALL_ARTICLES, Article.class).getResultList();

	}
	
	public List<Comment> getComments(){
		return em.createNamedQuery(Comment.ALL_COMMENTS, Comment.class).getResultList();
	}


	public void removeArticle(Article article) {
		Article a = getEntity(article.getId(), Article.class);
		em.remove(a);
		
	}
	
	public Article getEntityByTitle(String titleId) {
		return em.createNamedQuery(Article.BY_TITLE, Article.class).setParameter(1, titleId).getSingleResult();
	}
	
	
	//to be implemented
	
	@Asynchronous
	public void sendEmails(String title) {		
	}
	
}
