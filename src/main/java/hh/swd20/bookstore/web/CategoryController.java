package hh.swd20.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	//Kategoria listaus
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public String CatePage(Model model) {
		List<Category> catelist = (List<Category>) categoryRepository.findAll();
		model.addAttribute("catelist", catelist);
		return "categorylist";
		
	}
	 //tyhjän kategorialistan muodostaminen
	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public String getNewCate(Model model) {
		model.addAttribute("category", new Category());
		return "addcate";
	}
	
	//lomakkeen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/savecate", method = RequestMethod.POST)
	public String saveCate(@ModelAttribute Category category) {
		categoryRepository.save(category);
		return "redirect:/categorylist";
		
	}
}
