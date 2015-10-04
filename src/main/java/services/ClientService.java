package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import entities.Article;
import entities.Comment;
import entities.SubscribeEmail;

@Stateless
@LocalBean
public class ClientService extends AbstractService<Article>{

	@Override
	public List<Article> getAll() {
		return em.createNamedQuery(Article.ALL_ARTICLES, Article.class).getResultList();
	}

	public List<Article> findPhraseInArticles(String phrase) {
		return em.createNamedQuery(Article.BY_PHRASE, Article.class).setParameter(1, "%" + phrase + "%").getResultList();
	}

	public List<Comment> getTopComments() {
		return em.createNamedQuery(Comment.ALL_COMMENTS, Comment.class).setMaxResults(3).getResultList();
	}

	public void persistEmail(SubscribeEmail email) {
		em.persist(email);
		
	}

	public Article getEntityByTitle(String titleId) {
		return em.createNamedQuery(Article.BY_TITLE, Article.class).setParameter(1, titleId).getSingleResult();
	}
	
	
}
