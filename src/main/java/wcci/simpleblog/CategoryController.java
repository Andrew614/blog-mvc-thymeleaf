package wcci.simpleblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepo;

	public String getAllCategories(Model model) {
		model.addAttribute("categoriesAttribute", categoryRepo.findAll());
		return "categoriesTemplate";
	}

	public String getCategory(Model model, Long id) {
		model.addAttribute("categoryAttribute", categoryRepo.findById(id).get());
		return "categoryTemplate";
	}


}
