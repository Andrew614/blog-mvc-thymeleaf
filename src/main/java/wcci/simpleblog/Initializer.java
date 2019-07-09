package wcci.simpleblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private BlogTagRepository blogTagRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Author author1 = new Author("Captain America");
		Author author2 = new Author("Jessica Jones");
		Author author3 = new Author("Luke Cage");
		Author author4 = new Author("Deadpool");
		authorRepo.save(author1);
		authorRepo.save(author2);
		authorRepo.save(author3);
		authorRepo.save(author4);
		
		Category category1 = new Category("category1");
		Category category2 = new Category("category2");
		Category category3 = new Category("category3");
		categoryRepo.save(category1);
		categoryRepo.save(category2);
		categoryRepo.save(category3);
		
		BlogTag blogTag1 = new BlogTag("Marvel");
		BlogTag blogTag2 = new BlogTag("Super Heroes");
		BlogTag blogTag3 = new BlogTag("Heroes");
		blogTagRepo.save(blogTag1);
		blogTagRepo.save(blogTag2);
		blogTagRepo.save(blogTag3);
		
		Post post1 = new Post("title1", author1, category1, "content1", blogTag1);
		Post post2 = new Post("title2", author2, category2, "content2", blogTag1);
		Post post3 = new Post("title3", author3, category3, "content3", blogTag2);
		Post post4 = new Post("title4", author4, category1, "content3", blogTag3);
		Post post5 = new Post("title5", author2, category2, "content3", blogTag3);
		postRepo.save(post1);
		postRepo.save(post2);
		postRepo.save(post3);
		postRepo.save(post4);
		postRepo.save(post5);
	}

}
