package org.wecancodeit.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
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
	private TagRepository tagRepo;
	

	@Resource
	private MediumRepository mediumRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	
	@Test
	public void shouldSaveAndLoadReview() {
		Medium medium = mediumRepo.save(new Medium("book"));
		Review review = reviewRepo.save(new Review("review","stuff", "stuff", "stuff", medium));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		result.get();
		assertThat(review.getTitle(), is("review"));
	}
	
	@Test
	public void shouldGenerateReviewId() {
		Medium medium = mediumRepo.save(new Medium("book"));
		Review review = reviewRepo.save(new Review("review", "stuff", "stuff", "stuff", medium));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(reviewId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadTag() {
		Tag tag = tagRepo.save(new Tag("tag"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		result.get();
		assertThat(tag.getName(), is("tag"));
	}
	
	@Test
	public void shouldEstablishTagToReviewRelationship() {
		Medium medium = mediumRepo.save(new Medium("book"));
		Review haunting = reviewRepo.save(new Review("haunting", "stuff", "stuff", "stuff", medium));
		Review womanInBlack = reviewRepo.save(new Review("woman in black", "stuff", "stuff", "stuff", medium));
		
		Tag tag = new Tag("ghosts", womanInBlack, haunting);
		tag = tagRepo.save(tag);
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		
		assertThat(tag.getReviews(), containsInAnyOrder(womanInBlack, haunting));

	}
	
	@Test
	public void shouldFindTagsForReview() {
		Medium medium = mediumRepo.save(new Medium("book"));
		Review womanInBlack = reviewRepo.save(new Review("woman in black", "stuff", "stuff", "stuff", medium));
		
		Tag ghosts = tagRepo.save(new Tag("ghosts", womanInBlack));
		Tag curses = tagRepo.save(new Tag("curses",  womanInBlack));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Tag> tagsForReviews = tagRepo.findByReviewsContains(womanInBlack);
		
		assertThat(tagsForReviews, containsInAnyOrder(ghosts, curses));
		}
	
	@Test
	public void shouldFindReviewsForTag() {
		Medium medium = mediumRepo.save(new Medium("book"));
		Review womanInBlack = reviewRepo.save(new Review("woman in black", "stuff", "stuff", "stuff", medium));
		Review hohh = reviewRepo.save(new Review("haunting of hill house", "stuff", "stuff", "stuff", medium));
		Tag ghosts = tagRepo.save(new Tag("ghosts", womanInBlack, hohh));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForTag = reviewRepo.findByTagsContains(ghosts);
		
		assertThat(reviewsForTag, containsInAnyOrder(womanInBlack, hohh));
	}

	@Test
	public void shouldSaveAndLoadMedium() {
		Medium medium = mediumRepo.save(new Medium("book"));
		long mediumId = medium.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Medium> result = mediumRepo.findById(mediumId);
		result.get();
		assertThat(medium.getType(), is("book"));
	}
	
	@Test
	public void shouldEstablishMediumToReviewRelationship() {
		Medium book = new Medium("book");
		mediumRepo.save(book);
		long mediumId = book.getId();
		
		Review womanInBlack = new Review("woman in black", "stuff", "stuff", "stuff", book);
		reviewRepo.save(womanInBlack);
		Review nos4a2 = new Review("nos4a2", "stuff", "stuff", "stuff", book);
		reviewRepo.save(nos4a2);
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Medium>result = mediumRepo.findById(mediumId);
		book = result.get();
		assertThat(book.getReviews(), containsInAnyOrder(womanInBlack, nos4a2));
		
	}
	
	@Test
	public void shouldFindReviewByMedium() {
		Medium medium = mediumRepo.save(new Medium("book"));
		Review womanInBlack = reviewRepo.save(new Review("woman in black", "stuff", "stuff", "stuff", medium));
		Review hohh = reviewRepo.save(new Review("haunting of hill house", "stuff", "stuff", "stuff", medium));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForMedium = reviewRepo.findAllByMedium(medium);
		
		assertThat(reviewsForMedium, containsInAnyOrder(womanInBlack, hohh));
	}
	
	@Test
	public void shouldFindMediumByReview() {
		Medium book = mediumRepo.save(new Medium("book"));
		Review hohh = reviewRepo.save(new Review("haunting of hill house", "stuff", "stuff", "Stuff", book));
		
		entityManager.flush();
		entityManager.clear();
		
		Medium mediumForReview = mediumRepo.findByReviews(hohh);
		assertThat(mediumForReview, is(book));
	}
	
	@Test
	public void shouldSaveAndLoadComment() {
		Medium medium = mediumRepo.save(new Medium("book"));
		Review hohh = reviewRepo.save(new Review("haunting of hill house", "stuff", "stuff", "Stuff", medium));
		Comment comment = commentRepo.save(new Comment("username", "comment", hohh));
		
		entityManager.flush();
		entityManager.clear();
		
		
		Optional<Comment> result = commentRepo.findById(comment.getId());
		result.get();
		assertThat(comment.getContent(), is("comment"));
	}
	
	@Test
	public void shouldEstablishReviewtoCommentRelationship() {
		Medium book = new Medium("book");
		mediumRepo.save(book);
		Review womanInBlack = new Review("woman in black", "stuff", "stuff", "stuff", book);
		reviewRepo.save(womanInBlack);
		Comment comment = commentRepo.save(new Comment("username", "comment", womanInBlack));
		commentRepo.save(comment);

		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review>result = reviewRepo.findById(womanInBlack.getId());
		womanInBlack = result.get();
		assertThat(womanInBlack.getComments(), contains(comment));
	}
	

}
