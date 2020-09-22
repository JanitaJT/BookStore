package hh.swd20.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//tyhjän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String getNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "secondpageadd";
	}
	
	//lomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:/index";
	}
	
	//Kirjan poisto
	@RequestMapping(value="/deletebook/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId) {
		bookRepository.deleteById(bookId);
		return "redirect:../index";
	}
	
	//Kirjan muokkaaminen
	@RequestMapping(value= "/editbook/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		Book book = bookRepository.findById(bookId).get();
		model.addAttribute("book", book);
		return "edit";
	}
	
	
	

}
