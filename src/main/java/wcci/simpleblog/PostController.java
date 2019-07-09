package wcci.simpleblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private BlogTagRepository blogTagRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;

	@RequestMapping("")
	public String getAllPosts(Model model) {
		model.addAttribute("postsAttribute", postRepo.findAll());
		return "postsTemplate";

	}
	
	@RequestMapping("/{id}")
	public String getOnePost(Model model, @PathVariable long id) {
		model.addAttribute("postAttribute", postRepo.findById(id).get());
		return "postTemplate";
	}
	
	@PostMapping("/add")
	public String addPost(String title, String author, String category, String content, String blogTag) {
		Post postToAdd = new Post(title, content);
		postRepo.save(postToAdd);
		
		Author authorToAdd = new Author(author);
		authorRepo.save(authorToAdd);
		postToAdd.addAuthor(authorToAdd);
		
		Category categoryToAdd = new Category(category);
		categoryRepo.save(categoryToAdd);
		postToAdd.addCategory(categoryToAdd);
		
		BlogTag blogTagToAdd = new BlogTag(blogTag);
		blogTagRepo.save(blogTagToAdd);
		postToAdd.addBlogTag(blogTagToAdd);
		
//		String[] blogTags = blogTag.split(" ");
//		for (String tagToAdd : blogTags) {
//			BlogTag blogTagToAdd = new BlogTag(tagToAdd);
//			if (blogTagRepo.findByName(blogTagToAdd.getName()) == null) {
//				blogTagRepo.save(blogTagToAdd);
//			}
//			postRepo.findById(blogTagToAdd.getId()).get().addBlogTag(blogTagRepo.findByName(blogTagToAdd.getName()));
//		}
		
		return "redirect:/posts";
	}

}
