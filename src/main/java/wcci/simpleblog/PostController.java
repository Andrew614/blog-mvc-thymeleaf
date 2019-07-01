package wcci.simpleblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepo;

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

}
