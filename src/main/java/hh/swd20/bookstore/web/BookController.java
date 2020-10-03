package hh.swd20.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.bookstore.domain.*;

@CrossOrigin
@Controller
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
	@Autowired
	private CategoryRepository caterepo;

	
	//kirja listaus
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String startingPage(Model model) {
		List<Book> booklist = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", booklist);
		return "firstpage";

	}
	
	//REST to get all books
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) bookRepository.findAll();
	}
	
	//REST finding by id
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
		return bookRepository.findById(bookId);
	}
	
	//Rest save new book
	@RequestMapping(value="/books", method = RequestMethod.POST)
	public @ResponseBody Book saveBookRest(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	//tyhjän kirjalomakkeen muodostaminen
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String getNewBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", caterepo.findAll());
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
