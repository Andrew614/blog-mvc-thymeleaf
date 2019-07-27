package wcci.simpleblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wcci.simpleblog.entities.Author;
import wcci.simpleblog.entities.BlogTag;
import wcci.simpleblog.entities.Category;
import wcci.simpleblog.entities.Post;
import wcci.simpleblog.repositories.AuthorRepository;
import wcci.simpleblog.repositories.BlogTagRepository;
import wcci.simpleblog.repositories.CategoryRepository;
import wcci.simpleblog.repositories.PostRepository;

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
		model.addAttribute("authorsAttribute", authorRepo.findAll());
		model.addAttribute("blogTagsAttribute", blogTagRepo.findAll());
		model.addAttribute("categoriesAttribute", categoryRepo.findAll());
		return "postsTemplate";

	}
	
	@RequestMapping("/{id}")
	public String getOnePost(Model model, @PathVariable long id) {
		model.addAttribute("postAttribute", postRepo.findById(id).get());
		return "postTemplate";
	}
	
	@PostMapping("/add")
	public String addPost(String title, String author, String category, String content, String blogTag) {
		Author authorToAdd = authorRepo.findByName(author);
		Category categoryToAdd = categoryRepo.findByName(category);
		BlogTag blogTagToAdd = blogTagRepo.findByName(blogTag);
		
		Post postToAdd = new Post(title, authorToAdd, categoryToAdd, content, blogTagToAdd);
		postRepo.save(postToAdd);
		
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
