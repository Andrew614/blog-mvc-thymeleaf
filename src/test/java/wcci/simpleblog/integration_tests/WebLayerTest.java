package wcci.simpleblog.integration_tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import wcci.simpleblog.entities.Author;
import wcci.simpleblog.entities.BlogTag;
import wcci.simpleblog.entities.Category;
import wcci.simpleblog.entities.Post;
import wcci.simpleblog.repositories.AuthorRepository;
import wcci.simpleblog.repositories.BlogTagRepository;
import wcci.simpleblog.repositories.CategoryRepository;
import wcci.simpleblog.repositories.PostRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebLayerTest {
	

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AuthorRepository authorRepo;
	
	@MockBean
	private BlogTagRepository tagRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@MockBean 
	private PostRepository postRepo;
	
	@Mock
	private Author author;
	
	@Mock
	private BlogTag tag;
	
	@Mock
	private Category category;
	
	@Mock
	private Post post;
	
	@Test
	public void shouldReturnAllAuthorsPage() throws Exception {
		mockMvc.perform(get("/authors")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void shouldReturnOneAuthorPage() throws Exception {
		when(authorRepo.findById(author.getId())).thenReturn(Optional.of(author));
		mockMvc.perform(get("/authors/" + author.getId())).andExpect(status().isOk());
	}
	
	@Test
	public void shouldAddAuthor() throws Exception {
		Author authorToAdd = new Author("new author");
		mockMvc.perform(post("/authors/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(authorToAdd))).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void shouldReturnAllTagsPage() throws Exception {
		mockMvc.perform(get("/tags")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldReturnOneTagPage() throws Exception {
		when(tagRepo.findById(tag.getId())).thenReturn(Optional.of(tag));
		mockMvc.perform(get("/tags/" + tag.getId())).andExpect(status().isOk());
	}
	
	@Test
	public void shouldAddTag() throws Exception {
		BlogTag tagToAdd = new BlogTag("new tag");
		mockMvc.perform(post("/tags/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(tagToAdd))).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void shouldReturnAllCategoriesPage() throws Exception {
		mockMvc.perform(get("/categories")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldReturnOneCategoryPage() throws Exception {
		when(categoryRepo.findById(category.getId())).thenReturn(Optional.of(category));
		mockMvc.perform(get("/categories/" + category.getId())).andExpect(status().isOk());
	}
	
	@Test
	public void shouldAddCategory() throws Exception {
		Category categoryToAdd = new Category("new category");
		mockMvc.perform(post("/categories/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(categoryToAdd))).andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void shouldReturnAllPostsPage() throws Exception {
		mockMvc.perform(get("/posts")).andExpect(status().isOk());
	}
	
//	@Test
//	public void shouldReturnSinglePostPage() throws Exception {
//		when(postRepo.findById(post.getId())).thenReturn(Optional.of(post));
//		mockMvc.perform(get("/posts/" + post.getId())).andExpect(status().isOk());
//	}
	
	@Test
	public void shouldAddPost() throws Exception {
		Author existingAuthor = new Author("author");
		BlogTag existingTag = new BlogTag("tag");
		Category existingCategory = new Category("category");
		Post postToAdd = new Post("title", existingAuthor, existingCategory, "content", existingTag);
		mockMvc.perform(post("/posts/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(postToAdd))).andExpect(status().is3xxRedirection());
	}
	
}
