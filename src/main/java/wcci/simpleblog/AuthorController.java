package wcci.simpleblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class AuthorController {

	@Autowired
	AuthorRepository authorRepo;
	
	public String getAllAuthors(Model model) {
		model.addAttribute("authorsAttribute", authorRepo.findAll());
		return "authorsTemplate";
	}

	public String getAuthor(Model model, Long id) {
		model.addAttribute("authorAttribute", authorRepo.findById(id).get());
		return "authorTemplate";
	}

}
