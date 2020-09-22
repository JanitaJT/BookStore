package hh.swd20.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.domain.*;

@Controller
public class BookController {
	
	@Autowired
	BookRepository bookRepository;

	
	//kirja listaus
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String startingPage(Model model) {
		List<Book> booklist = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", booklist);
		return "firstpage";

	}
	
	

}
