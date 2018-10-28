package org.wecancodeit.reviews;

import org.springframework.data.repository.CrudRepository;


public interface MediumRepository extends CrudRepository<Medium, Long> {
	
	Medium findByReviews(Review review);

	Medium findByType(String mediumType);
	
}
