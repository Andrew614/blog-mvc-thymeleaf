package wcci.simpleblog.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import wcci.simpleblog.entities.Author;
import wcci.simpleblog.entities.BlogTag;
import wcci.simpleblog.entities.Category;
import wcci.simpleblog.entities.Post;

public class PostTest {
	
	private Author author;
	private Category category;
	private Post post;
	private BlogTag blogTag;
	
	@Before
	public void setup() {
		author = new Author("Andrew");
		category = new Category("food");
		blogTag = new BlogTag("fruits");
		post = new Post("title", author, category, "content", blogTag);
	}

	@Test
	public void postShouldHaveAnAuthor() {
		Author expectedAuthor = post.getAuthor();
		assertThat(expectedAuthor, is(author));
	}
	
	@Test
	public void postShouldHaveACategory() {
		Category expectedCategory = post.getCategory();
		assertThat(expectedCategory, is(category));
	}
	
	@Test
	public void postShouldHaveABlogTag() {
		Collection<BlogTag> expectedBlogTag = post.getBlogTags();
		assertThat(expectedBlogTag, hasItem(blogTag));
	}
	
	@Test
	public void postShouldHaveMultipleTags() {
		BlogTag blogTag2 = new BlogTag("heatlh");
		BlogTag blogTag3 = new BlogTag("fitness");
		Post post2 = new Post("title", author, category, "content", blogTag, blogTag2, blogTag3);
		Collection<BlogTag> expectedBlogTag = post2.getBlogTags();
		assertThat(expectedBlogTag, containsInAnyOrder(blogTag, blogTag2, blogTag3));
	}
}
