package admin.backingBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import admin.AdminArticleService;
import entities.Article;

@Named
@ViewScoped
public class AdminHomePage implements Serializable{

	private Part part;
	
	private Article article = new Article();
	private List<String> images;
	
	@PostConstruct
	public void init(){

			images = new ArrayList<>(); 
			File uploads = new File(System.getProperty("jboss.server.data.dir"), "images");
			for (File f : uploads.listFiles()){
				images.add("/images/"+f.getName());
			}
			System.out.println(uploads.getPath());

	}
	
	@Inject
	AdminArticleService service;
	
	public void upload(){
		System.out.println("here");

		if (part != null){
		
			String fileName = part.getSubmittedFileName();
			String prefix = null, suffix = null;
			int lastDot = fileName.lastIndexOf('.');
			if (lastDot != -1){
				prefix = fileName.substring(0, lastDot);
				suffix = fileName.substring(lastDot, fileName.length());
			}else{
				prefix = fileName;
			}
			
			try(InputStream is = part.getInputStream()){	
				File uploads = new File(System.getProperty("jboss.server.data.dir"), "images");
				File location = File.createTempFile(prefix, suffix, uploads);
				Files.copy(is, location.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	

	
	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public void post(){
		setTitleId(article);
		article.setDate(new Date());
		try{
			service.setEntity(article);
			service.sendEmails(article.getTitle());
			article = new Article();

		}catch(Exception ex){
			FacesMessage fm = new FacesMessage("same title?");
			FacesContext.getCurrentInstance().addMessage(null, fm);
		}
	}



	private void setTitleId(Article article) {
		String titleId = article.getTitle().trim().replace(' ', '_');
		article.setTitleId(titleId);
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public Article getArticle() {
		return article;
	}

}
