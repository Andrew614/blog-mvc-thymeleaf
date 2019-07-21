package wcci.simpleblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wcci.simpleblog.entities.BlogTag;
import wcci.simpleblog.repositories.BlogTagRepository;
@Controller
@RequestMapping("/tags")
public class BlogTagController {

	@Autowired
	private BlogTagRepository blogTagRepo;
	
	@RequestMapping("")
	public String getAllBlogTags(Model model) {
		model.addAttribute("blogTagsAttribute", blogTagRepo.findAll());
		return "blogTagsTemplate";
	}

	@RequestMapping("/{id}")
	public String getOneblogTag(Model model, @PathVariable long id) {
		model.addAttribute("blogTagAttribute", blogTagRepo.findById(id).get());
		return "blogTagTemplate";
	}
	
	@PostMapping("/add")
	public String addCategory(String name) {
		BlogTag blogTagToAdd = new BlogTag(name);
		if (blogTagRepo.findByName(blogTagToAdd.getName()) == null) {
			blogTagRepo.save(blogTagToAdd);
	     }	
		return "redirect:/tags";
	}

}
