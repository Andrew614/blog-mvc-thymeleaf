package wcci.simpleblog;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	private String name;

	@OneToMany(mappedBy = "category")
	private List<Post> posts;

	@Id
	@GeneratedValue
	private Long id;

	public Category(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Post> getPosts() {
		return posts;
	}

}
