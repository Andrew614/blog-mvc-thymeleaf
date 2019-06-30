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

public class BlogTagControllerTest {

	@InjectMocks
	private BlogTagController underTest;

	@Mock
	private BlogTagRepository blogTagRepo;

	@Mock
	private BlogTag blogTag1;

	@Mock
	private BlogTag blogTag2;

	@Mock
	private Model model;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldBeAbleToGetAllTags() {
		String tags = underTest.getAllBlogTags(model);
		assertThat(tags, is("blogTagsTemplate"));
	}

	@Test
	public void shouldHaveTagsInModel() {
		Collection<BlogTag> blogTags = Arrays.asList(blogTag1, blogTag2);
		when(blogTagRepo.findAll()).thenReturn(blogTags);
		underTest.getAllBlogTags(model);
		verify(model).addAttribute("blogTagsAttribute", blogTags);
	}

	@Test
	public void shouldHaveTagInModel() {
		Optional<BlogTag> blogTag = Optional.of(blogTag1);
		when(blogTagRepo.findById(blogTag1.getId())).thenReturn(blogTag);
		underTest.getOneblogTag(model, 0L);
		verify(model).addAttribute("blogTagAttribute", blogTag);

	}

	@Test
	public void shouldBeAbleToGetOneTag() {
		String tag = underTest.getOneblogTag(model, 0L);
		assertThat(tag, is("blogTagTemplate"));

	}
}
