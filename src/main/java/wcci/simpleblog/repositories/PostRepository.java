package wcci.simpleblog.repositories;

import org.springframework.data.repository.CrudRepository;

import wcci.simpleblog.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
