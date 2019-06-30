package wcci.simpleblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class PostController {

	@Autowired
	private PostRepository postRepo;

	public String getAllPosts(Model model) {
		model.addAttribute("postsAttribute", postRepo.findAll());
		return "postsTemplate";

	}

	public String getOnePost(Model model, long id) {
		model.addAttribute("postAttribute", postRepo.findById(id));
		return "postTemplate";
	}

}
