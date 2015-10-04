package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@NamedQueries({
		@NamedQuery(name=Article.ALL_ARTICLES, query="SELECT a from Article a ORDER BY a.date DESC"),
		@NamedQuery(name=Article.BY_PHRASE, query="SELECT a from Article a WHERE a.data LIKE ?1 ORDER BY a.date DESC"),
		@NamedQuery(name=Article.BY_TITLE, query="SELECT a from Article a WHERE a.titleId = ?1")
	}	
)
@Entity
public class Article implements Serializable{
	
	public static final String ALL_ARTICLES = "allArticles";
	public static final String BY_PHRASE = "searchByPhrase";
	
	private static final long serialVersionUID = 1L;
	public static final String BY_TITLE = "byTitle";
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="title_id", unique=true)
	private String titleId;
	
	@Size(min=3, max=140, message="Title has to be between {min} and {max} characters")
	private String title;
	
	@Size(min=3, max=5000, message="Post has to be between {min} and {max} characters")
	private String data;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@OneToMany(mappedBy="article", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	@OrderBy("date ASC")
	private List<Comment> comments;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> tags;
		
	public Article(){}
	
	public Article(String title, String data){
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitleId() {
		return titleId;
	}
	
	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	
	public String getPrettyByLine(){
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		StringBuilder sb = new StringBuilder();
		return sb.append("Posted ").append(sdf.format(date)).append(" by T.Skrebe").toString();
	}

	
	public String getSummary(){
		if (data.length() <= 500)
			return data;
		
		return data.substring(0, data.lastIndexOf(" ", 500));
		
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	public long getId() {
		return id;
	}
}