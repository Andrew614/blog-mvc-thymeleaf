package wcci.simpleblog;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Post {

	private String title;
	private String content;
	private LocalDateTime publishDate;

	@ManyToOne
	private Author author;

	@ManyToOne
	private Category category;

	@ManyToMany
	private Collection<BlogTag> blogTags;

	@Id
	@GeneratedValue
	private Long id;

	protected Post() {
	}

	public Post(String title, Author author, Category category, String content, BlogTag... blogTags) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.content =content;
		this.blogTags = new HashSet<BlogTag>(Arrays.asList(blogTags));
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

	public Collection<BlogTag> getBlogTags() {
		return blogTags;
	}
	
//	public void addBlogTag(BlogTag blogTag) {
//		this.blogTags.add(blogTag);
//	}
//	
//	public void addCategory(Category category) {
//		this.category = category;
//	}
//
//	public void addAuthor(Author author) {
//		this.author = author;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
