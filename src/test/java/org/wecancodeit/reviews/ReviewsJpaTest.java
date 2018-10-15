package org.wecancodeit.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ReviewsJpaTest extends ReviewsApplicationTests{
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	
	@Test
	public void shouldSaveAndLoadReview() {
		Review review = reviewRepo.save(new Review("review", "stuff", "stuff", "stuff", "stuff", "stuff"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		result.get();
		assertThat(review.getTitle(), is("review"));
	}
	
	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review("review", "stuff", "stuff", "stuff", "stuff", "stuff"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(reviewId, is(greaterThan(0L)));
	}

}
