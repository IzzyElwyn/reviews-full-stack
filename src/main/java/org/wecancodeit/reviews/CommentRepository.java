package org.wecancodeit.reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Comment getByContent(String commentContent);

	Collection<Comment> findByReview(Review reviewName);

}
