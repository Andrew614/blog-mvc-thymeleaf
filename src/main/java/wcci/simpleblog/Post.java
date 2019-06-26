package wcci.simpleblog;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Post {

	private String title;
	private String content;

	@ManyToOne
	private Author author;

	@ManyToOne
	private Category category;

	@ManyToMany
	private List<BlogTag> blogTags;

	@Id
	@GeneratedValue
	private Long id;

	public Post(String title, Author author, Category category, String content, BlogTag... blogTags) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.content = content;
		this.blogTags = new ArrayList<BlogTag>();
		for (BlogTag blogTag : blogTags) {
			this.blogTags.add(blogTag);
		}
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Author getAuthor() {
		return author;
	}

	public Category getCategory() {
		return category;
	}

	public String getContent() {
		return content;
	}

	public List<BlogTag> getBlogTag() {
		return blogTags;
	}

}
