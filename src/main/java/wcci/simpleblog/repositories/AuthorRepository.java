package wcci.simpleblog.repositories;

import org.springframework.data.repository.CrudRepository;

import wcci.simpleblog.entities.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	Author findByName(String firstName);

}
