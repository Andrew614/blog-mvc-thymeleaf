package wcci.simpleblog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

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
		String authors = underTest.findAll(model);
		assertThat(authors, is("authorsTemplate"));
	}

	@Test
	public void shouldAddAuthorsToModel() {
		Collection<Author> authors = Arrays.asList(author1, author2);
		when(authorRepo.findAll()).thenReturn(authors);
		underTest.findAll(model);
		verify(model).addAttribute("authorsAttribute", authors);
	}

}
