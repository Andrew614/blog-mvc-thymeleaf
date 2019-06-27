package wcci.simpleblog;

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

public class CategoryControllerTest {

	@InjectMocks
	private CategoryController underTest;
	
	@Mock
	private CategoryRepository categoryRepo;
	
	@Mock
	Category category1;
	
	@Mock
	Category category2;
	
	@Mock
	private Model model;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldBeAbleToGetAllCategories() {
		String categories = underTest.getAllCategories(model);
		assertThat(categories, is("categoriesTemplate"));
	}
	
	@Test
	public void shouldAddCategoriesToModel() {
		Collection<Category> categories = Arrays.asList(category1, category2);
		when(categoryRepo.findAll()).thenReturn(categories);
		underTest.getAllCategories(model);
		verify(model).addAttribute("categoriesAttribute", categories);
	}
	
	@Test
	public void shouldBeAbleToGetOneCategory() {
		Optional<Category> OptCategory1 = Optional.of(category1);
		when(categoryRepo.findById(0L)).thenReturn(OptCategory1);
		String category = underTest.getCategory(model, 0L);
		assertThat(category, is("categoryTemplate"));
	}
	
	@Test
	public void shouldAddCategoryToModel() {
		Optional<Category> OptCategory1 = Optional.of(category1);
		when(categoryRepo.findById(0L)).thenReturn(OptCategory1);
		underTest.getCategory(model, 0L);
		verify(model).addAttribute("categoryAttribute", category1);
	}
}
