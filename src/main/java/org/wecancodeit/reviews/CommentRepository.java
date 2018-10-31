package org.wecancodeit.reviews;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Comment getByContent(String commentContent);

}
