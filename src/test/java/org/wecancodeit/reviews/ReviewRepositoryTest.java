package org.wecancodeit.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

public class ReviewRepositoryTest {

	ReviewRepository underTest;
	
	private long firstReviewId = 1;
	
	private long secondReviewId = 2;
	
	private Review firstReview = new Review(firstReviewId, "Review Title", "Review Image", "Review Category", "Review Content", "Ranking");
	
	private Review secondReview = new Review(secondReviewId, "Review Title", "Review Image", "Review Category", "Review Content", "Ranking");
	
	@Test
	public void shouldFindReview() {
		underTest = new ReviewRepository(firstReview);
		Review result = underTest.returnOne(firstReviewId);
		assertThat(result, is(firstReview));
	}
	
	@Test
	public void shouldFindSecondReview() {
		underTest = new ReviewRepository(secondReview);
		Review result = underTest.returnOne(secondReviewId);
		assertThat(result, is(secondReview));
	}
	
	@Test
	public void shouldReturnAllReviews() {
		underTest = new ReviewRepository(firstReview, secondReview);
		Collection<Review> results = underTest.returnAll();
		assertThat(results, containsInAnyOrder(firstReview, secondReview));
	}
}
