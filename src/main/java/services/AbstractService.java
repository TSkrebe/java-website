package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Article;
import entities.Comment;
import entities.SubscribeEmail;


public abstract class AbstractService<T> implements Service<T>{
	
	@PersistenceContext
	public EntityManager em;

	
	@Override
	public T getEntity(long id, Class<T> c) {
		return em.find(c, id);
	}

	@Override
	public void mergeEntity(T article){
		em.merge(article);
	}

	@Override
	public void setEntity(T article) {
		em.persist(article);
		
	}
	
	

}
