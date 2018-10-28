package org.wecancodeit.reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long>{

	Collection<Review> findByTitle(String title);

	Review getByTitle(String title);

	Collection<Review> findAllByOrderByTitleAsc();

	Collection<Review> findByTagsContains(Tag tag);

	Collection<Review> findAllByMedium(Medium medium);

}
