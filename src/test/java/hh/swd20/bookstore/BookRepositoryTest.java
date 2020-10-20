package hh.swd20.bookstore;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Book;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void FindByNameBook() {
		List<Book> books = repository.findByTitle("Harry Potter");
		
		assertThat(books).hasSize(1);
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("Test", "Testi", 2011, 1234567891, 20.99, null);
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

}
