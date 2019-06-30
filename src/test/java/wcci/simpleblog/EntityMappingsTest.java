package wcci.simpleblog;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntityMappingsTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AuthorRepository authorRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private BlogTagRepository blogTagRepo;

	private Author author;
	private Category category;
	private BlogTag blogTag;
	private BlogTag blogTag2;
	private Post post;
	private Post post1;
	private Post post2;

	@Before
	public void setup() {
		author = new Author("Andrew A");
		authorRepo.save(author);
		category = new Category("category");
		categoryRepo.save(category);
		blogTag = new BlogTag("food");
		blogTagRepo.save(blogTag);
		blogTag2 = new BlogTag("music");
		blogTagRepo.save(blogTag2);

		post = new Post("title", author, category, "content", blogTag);
		postRepo.save(post);
		post1 = new Post("title1", author, category, "content1", blogTag);
		postRepo.save(post1);
		post2 = new Post("title2", author, category, "content2", blogTag2);
		postRepo.save(post2);

		entityManager.flush();
		entityManager.clear();
	}

	@Test
	public void shouldSaveAndLoadAuthor() {
		Author author = new Author("Andrew A");
		authorRepo.save(author);
		Author foundAuthor = authorRepo.findById(author.getId()).get();
		assertThat(foundAuthor.getName(), is("Andrew A"));
	}

	@Test
	public void shouldGenerateAuthorId() {
		long id = author.getId();
		assertThat(id, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadCategory() {
		Category foundCategory = categoryRepo.findById(category.getId()).get();
		assertThat(foundCategory.getName(), is("category"));
	}

	@Test
	public void shouldGenerateCategoryId() {
		long id = category.getId();
		assertThat(id, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadBlogTags() {
		BlogTag foundBlogTag = blogTagRepo.findById(blogTag.getId()).get();
		assertThat(foundBlogTag.getName(), is("food"));
	}

	@Test
	public void shouldGenerateBlogTagId() {
		long id = blogTag.getId();
		assertThat(id, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadPost() {
		Post foundPost = postRepo.findById(post.getId()).get();
		assertThat(foundPost.getTitle(), is("title"));
	}

	@Test
	public void shouldGeneratePostId() {
		long id = post.getId();
		assertThat(id, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishPostToAuthorRelationship() {
		long authorId = author.getId();
		Author foundAuthor = authorRepo.findById(authorId).get();
		assertThat(foundAuthor.getPosts(), containsInAnyOrder(post, post1, post2));
	}

	@Test
	public void shouldEstablishPostToCategoryRelationship() {
		long categoryId = category.getId();
		Category foundCategory = categoryRepo.findById(categoryId).get();
		assertThat(foundCategory.getPosts(), containsInAnyOrder(post, post1, post2));
	}

	@Test
	public void shouldEstablistPostToBlogTagRelationship() {
		long blogTagId = blogTag.getId();
		BlogTag foundBlogTag = blogTagRepo.findById(blogTagId).get();
		assertThat(foundBlogTag.getPosts(), containsInAnyOrder(post, post1));
	}

}
