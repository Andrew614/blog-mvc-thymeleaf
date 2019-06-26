package wcci.simpleblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class AuthorController {

	@Autowired
	AuthorRepository authorRepo;
	
	public String findAll(Model model) {
		model.addAttribute("authorsAttribute", authorRepo.findAll());
		return "authorsTemplate";
	}

}
