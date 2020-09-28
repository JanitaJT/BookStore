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

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("Test data");
			Book book1 = new Book("Harry Potter", "JK.Rowling", 2007, 1034728942, 26.95);
			Book book2 = new Book("Da Vinci Code", "Dan Brown", 2003, 1034728943, 37.95);
			bookRepository.save(book1);
			bookRepository.save(book2);

			Category cate1 = new Category("Scifi");
			Category cate2 = new Category("Fantasy");
			Category cate3 = new Category("Thriller");
			categoryRepository.save(cate1);
			categoryRepository.save(cate2);
			categoryRepository.save(cate3);

			log.info("Fetch categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}
			log.info("Fetch books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
