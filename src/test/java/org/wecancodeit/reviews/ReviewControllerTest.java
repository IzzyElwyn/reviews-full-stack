package org.wecancodeit.reviews;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {

	@InjectMocks
	private ReviewController underTest;

	@Mock
	private Review firstReview;
	Long reviewId;

	@Mock
	private Review secondReview;
	
	@Mock
	private Tag firstTag;
	
	@Mock
	private Tag secondTag;
	
	@Mock
	private Medium firstMedium;
	
	@Mock
	private Medium secondMedium;

	@Mock
	private ReviewRepository reviewRepo;
	
	@Mock
	private TagRepository tagRepo;
	
	@Mock
	private MediumRepository mediumRepo;
	
	@Mock
	private CommentRepository commentRepo;

	@Mock
	private Model model;

	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(firstReview));
		
		underTest.returnOneReview(reviewId, model);
		verify(model).addAttribute("reviews", firstReview);
	}
	
	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = asList(firstReview, secondReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		underTest.returnAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
		
	}
	
	@Test
	public void shouldAddSingleTagToModel() throws TagNotFoundException {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(firstTag));
		
		underTest.returnOneTag(tagId, model);
		verify(model).addAttribute("tags", firstTag);
	}
	
	@Test
	public void shouldAddAllTagsToModel() {
		Collection<Tag> allTags = asList(firstTag, secondTag);
		when(tagRepo.findAll()).thenReturn(allTags);
		
		underTest.returnAllTags(model);
		verify(model).addAttribute("tags", allTags);
		
	}
	
	@Test
	public void shouldAddSingleMediumToModel() throws MediumNotFoundException {
		long mediumId = 1;
		when(mediumRepo.findById(mediumId)).thenReturn(Optional.of(firstMedium));
		
		underTest.returnOneMedium(mediumId, model);
		verify(model).addAttribute("mediums", firstMedium);
	}
	
	@Test
	public void shouldAddAllMediumsToModel() {
		Collection<Medium> allMediums = asList(firstMedium, secondMedium);
		when(mediumRepo.findAll()).thenReturn(allMediums);
		
		underTest.returnAllMediums(model);
		verify(model).addAttribute("mediums", allMediums);
}
	
	@Test
	public void shouldAddAdditionalReviewsToModel() {
		String mediumType = "tv show";
		Medium newMedium = mediumRepo.findByType(mediumType);
		
		String reviewName = "new review";
		String reviewContent = "new review Content";
		String reviewRanking = "5 pumpkins";
				
		underTest.addReview(reviewName, null, null, reviewContent, reviewRanking,  mediumType);
		Review newReview = new Review(reviewName, null, null, reviewContent, reviewRanking,  newMedium);
		when (reviewRepo.save(newReview)).thenReturn(newReview);
	}
	
	@Test
	public void shouldBeAbleToRemoveReviewFromModelByTitle() {
		String reviewTitle = firstReview.getTitle();
		when(reviewRepo.getByTitle(reviewTitle)).thenReturn(firstReview);
		underTest.deleteReviewByTitle(reviewTitle);
		verify(reviewRepo).delete(firstReview);
	}
	
	@Test
	public void shouldRemoveReviewFromModelById() {
		underTest.reviewRepo.deleteById(reviewId);
		
		verify(reviewRepo).deleteById(reviewId);
		}
	
	@Test
	public void shouldBeAbleToAddTagToModel() {
		String tagName = "Historical";
		String tagDescription = "Stories that take place in a historical Setting";
		String reviewTitle = firstReview.getTitle();
		
		underTest.addTag(tagName, tagDescription, reviewTitle);
		Review ascReview = reviewRepo.getByTitle(reviewTitle);
		Tag newTag = new Tag(tagName, tagDescription, ascReview);
		when (tagRepo.save(newTag)).thenReturn(newTag);
		
	}
	

}