package wcci.simpleblog.repositories;

import org.springframework.data.repository.CrudRepository;

import wcci.simpleblog.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByName(String name);

}
