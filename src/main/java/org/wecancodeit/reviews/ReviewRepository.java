package org.wecancodeit.reviews;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long>{

	List<Review> findByTitle(String title);
	
	Review getByTitle(String title);
	
	List<Review> findAllByOrderByTitleAsc();

}
