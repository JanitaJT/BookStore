package hh.swd20.bookstore.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.domain.*;

@Controller
public class BookController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String startingPage(Model model) {
		model.addAttribute("book", new Book());
		return "firstpage";
		
	}

}
