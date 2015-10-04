package backingBeans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Article;
import services.ClientService;

@Named
@RequestScoped
public class SearchPage implements Serializable{

	private String phrase;
	
	private List<Article> articles;
	
	@Inject
	ClientService service;

	public void init(){
		articles = service.findPhraseInArticles(phrase);
	}
	
	public List<Article> getArticles() {
		return articles;
	}

	public String getPhrase() {
		return phrase;
	}

	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}


	
}
