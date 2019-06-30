package wcci.simpleblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class BlogTagController {

	@Autowired
	private BlogTagRepository blogTagRepo;
	
	public String getAllBlogTags(Model model) {
		model.addAttribute("blogTagsAttribute", blogTagRepo.findAll());
		return "blogTagsTemplate";
	}

	public String getOneblogTag(Model model, long id) {
		model.addAttribute("blogTagAttribute", blogTagRepo.findById(id));
		return "blogTagTemplate";
		
	}

}
