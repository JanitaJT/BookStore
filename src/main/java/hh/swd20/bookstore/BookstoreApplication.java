package hh.swd20.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;
import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("Test data");
			

			Category cate1 = new Category("Scifi");
			Category cate2 = new Category("Fantasy");
			Category cate3 = new Category("Thriller");
			categoryRepository.save(cate1);
			categoryRepository.save(cate2);
			categoryRepository.save(cate3);
			
			Book bookCate = new Book("Vargbröder", "Michelle Paver", 2008,1034728944, 25.95, cate2);
			Book book1 = new Book("Harry Potter", "JK.Rowling", 2007, 1034728942, 26.95, cate2);
			Book book2 = new Book("Da Vinci Code", "Dan Brown", 2003, 1034728943, 37.95, cate3);
			bookRepository.save(book1);
			bookRepository.save(book2);
			bookRepository.save(bookCate);
			
			User user1 = new User("user","$2b$10$nD21pS8NNhTszXHcxL9EheD.Ja5uyGoxQuJptgvzrEf4isnpipu2C","user.user@gmail.com","USER");
			User user2 = new User("admin", "$2b$10$W84aPB/FVKJJbUj/jDJUsOibmKipSBOUaDU22trfPtfIsXBJxxlT2","admin.admin@gmail.com" ,"ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			
			log.info("Fetch books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			log.info("Fetch categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}

			
		};
	}

}
