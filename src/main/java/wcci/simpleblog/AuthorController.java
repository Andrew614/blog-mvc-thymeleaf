package wcci.simpleblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	AuthorRepository authorRepo;
	
	@RequestMapping("")
	public String getAllAuthors(Model model) {
		model.addAttribute("authorsAttribute", authorRepo.findAll());
		return "authorsTemplate";
	}

	@RequestMapping("/{id}")
	public String getAuthor(Model model, @PathVariable Long id) {
		model.addAttribute("authorAttribute", authorRepo.findById(id).get());
		return "authorTemplate";
	}
	
	@PostMapping("/add")
	public String addAuthor(String name) {
		Author authorToAdd = new Author(name);
		if (authorRepo.findByName(authorToAdd.getName()) == null) {
			authorRepo.save(authorToAdd);
	     }	
		return "redirect:/authors";
	}

}
