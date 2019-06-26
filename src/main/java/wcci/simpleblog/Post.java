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

	protected Post() {
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((blogTags == null) ? 0 : blogTags.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (blogTags == null) {
			if (other.blogTags != null)
				return false;
		} else if (!blogTags.equals(other.blogTags))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
