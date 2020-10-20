package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository repository;

	@Test
	public void findByNameCate() {
		List<Category> caties = repository.findByName("Thriller");
		assertThat(caties).hasSize(1);

	}

	public void addCate() {
		Category cate = new Category("Romance");
		repository.save(cate);
		assertThat(cate.getCateId()).isNotNull();
	}
}
