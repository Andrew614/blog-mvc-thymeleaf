package wcci.simpleblog;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	Author findByName(String firstName);

}
