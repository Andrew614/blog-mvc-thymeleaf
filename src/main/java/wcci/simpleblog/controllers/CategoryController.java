package wcci.simpleblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import wcci.simpleblog.entities.Category;
import wcci.simpleblog.repositories.CategoryRepository;
@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepo;

	@RequestMapping("")
	public String getAllCategories(Model model) {
		model.addAttribute("categoriesAttribute", categoryRepo.findAll());
		return "categoriesTemplate";
	}

	@RequestMapping("/{id}")
	public String getCategory(Model model, @PathVariable long id) {
		model.addAttribute("categoryAttribute", categoryRepo.findById(id).get());
		return "categoryTemplate";
	}
	
	@PostMapping("/add")
	public String addCategory(String name) {
		Category categoryToAdd = new Category(name);
		if (categoryRepo.findByName(categoryToAdd.getName()) == null) {
			categoryRepo.save(categoryToAdd);
	     }	
		return "redirect:/categories";
	}

}
