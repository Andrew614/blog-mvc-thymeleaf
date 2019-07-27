package wcci.simpleblog.repositories;

import org.springframework.data.repository.CrudRepository;

import wcci.simpleblog.entities.BlogTag;

public interface BlogTagRepository extends CrudRepository<BlogTag, Long> {

	BlogTag findByName(String name);

}
