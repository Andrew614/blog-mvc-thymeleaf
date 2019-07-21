package wcci.simpleblog.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import wcci.simpleblog.controllers.AuthorController;
import wcci.simpleblog.entities.Author;
import wcci.simpleblog.repositories.AuthorRepository;

public class AuthorControllerTest {

	@InjectMocks
	private AuthorController underTest;

	@Mock
	private AuthorRepository authorRepo;

	@Mock
	private Author author1;

	@Mock
	private Author author2;

	@Mock
	private Model model;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldBeAbleToGetAllAuthors() {
		String authors = underTest.getAllAuthors(model);
		assertThat(authors, is("authorsTemplate"));
	}

	@Test
	public void shouldAddAuthorsToModel() {
		Collection<Author> authors = Arrays.asList(author1, author2);
		when(authorRepo.findAll()).thenReturn(authors);
		underTest.getAllAuthors(model);
		verify(model).addAttribute("authorsAttribute", authors);
	}
	
	@Test
	public void shouldBeAbleToGetOneAuthor() {
		Optional<Author> OptAuthor1 = Optional.of(author1);
		when(authorRepo.findById(0L)).thenReturn(OptAuthor1);
		String category = underTest.getAuthor(model, 0L);
		assertThat(category, is("authorTemplate"));
	}
	
	@Test
	public void shouldAddAuthorToModel() {
		Optional<Author> OptAuthor1 = Optional.of(author1);
		when(authorRepo.findById(0L)).thenReturn(OptAuthor1);
		underTest.getAuthor(model, 0L);
		verify(model).addAttribute("authorAttribute", author1);
	}

}
