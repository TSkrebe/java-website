package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
@NamedQueries(
		@NamedQuery(name=Comment.ALL_COMMENTS, query="SELECT c FROM Comment c ORDER BY c.date DESC")
)
@Entity
public class Comment implements Serializable{
	
	public static final String ALL_COMMENTS = "last_comments";
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank(message="Sorry you should have a name that is visible to people...")
	@Size(min=3, max=20, message="Your name has to be between {min} and {max} characters")
	private String name;
	
	@NotBlank(message="Ooops! It looks like your comments is... Invisible?!")
	@Size(min=3, max=1000, message="Your comment has to be between {min} and {max} charaters")
	private String data;

	private Date date;
	
	@ManyToOne
	@JoinColumn(name="article_id")
	private Article article;
	
	public Comment(){}
	
	public Comment(String name, String data){
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Article getArticle() {
		return article;
	}
	
	public String getSummary(){
		if (data.length() <= 140)
			return data;
		return data.substring(0, 140) + " ...";
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	public String getPrettyDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
		return sdf.format(date);
	}
	
	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
	public long getId(){
		return id;
	}

	public void clean() {
		id = 0;
		data = null;
	}


}
