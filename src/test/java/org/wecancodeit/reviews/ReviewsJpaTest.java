package org.wecancodeit.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
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
	
	@Resource
	private CategoryRepository catRepo;
	
	
	@Test
	public void shouldSaveAndLoadReview() {
		Review review = reviewRepo.save(new Review("review", "stuff", "stuff", "stuff", "stuff"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		result.get();
		assertThat(review.getTitle(), is("review"));
	}
	
	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review("review", "stuff", "stuff", "stuff", "stuff"));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(reviewId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = catRepo.save(new Category("category"));
		long categoryId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = catRepo.findById(categoryId);
		result.get();
		assertThat(category.getName(), is("category"));
	}
	
	@Test
	public void shouldEstablishCategoryToReviewRelationship() {
		Review haunting = reviewRepo.save(new Review("haunting", "stuff", "stuff", "stuff", "stuff"));
		Review womanInBlack = reviewRepo.save(new Review("woman in black", "stuff", "stuff", "stuff", "stuff"));
		
		Category category = new Category("ghosts", womanInBlack, haunting);
		category = catRepo.save(category);
		long catId = category.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Category> result = catRepo.findById(catId);
		category = result.get();
		
		assertThat(category.getReviews(), containsInAnyOrder(womanInBlack, haunting));

	}
	
	@Test
	public void shouldFindCategoriesForReview() {
		Review womanInBlack = reviewRepo.save(new Review("woman in black", "stuff", "stuff", "stuff", "stuff"));
		
		Category ghosts = catRepo.save(new Category("ghosts", womanInBlack));
		Category curses = catRepo.save(new Category("curses", womanInBlack));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Category> categoriesForReview = catRepo.findByReviewsContains(womanInBlack);
		
		assertThat(categoriesForReview, containsInAnyOrder(ghosts, curses));
		}


}
