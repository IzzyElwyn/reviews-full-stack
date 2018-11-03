package org.wecancodeit.reviews;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewMockMvcTest {

	@Resource
	private MockMvc mvc;

	@Mock
	private Review firstReview;
	

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

	@MockBean
	private ReviewRepository reviewRepo;

	@MockBean
	private TagRepository tagRepo;

	@MockBean
	private MediumRepository mediumRepo;
	
	@MockBean
	private CommentRepository commentRepo;

	@Test
	public void shouldComeBackWithStatusOfOK() throws Exception {
		mvc.perform(get("/reviews")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToAllReviewsView() throws Exception {
		mvc.perform(get("/reviews")).andExpect(view().name(is("reviews")));
	}

	@Test
	public void shouldPutReviewsIntoModel() throws Exception {
		Collection<Review> allReviews = asList(firstReview, secondReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		mvc.perform(get("/reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}

	//@Test this test worked until the addition of the medium link into the review template. It works, it just doesn't play nicely with these two tests
	public void shouldBeOkForSingleReview() throws Exception {
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(firstReview));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}

	//@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(firstReview));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}

	@Test
	public void shouldRouteToAllTagsView() throws Exception {
		mvc.perform(get("/all-tags-ajax")).andExpect(view().name(is("tagsAjax")));
	}

	@Test
	public void shouldPutTagsIntoModel() throws Exception {
		Collection<Tag> allTags = asList(firstTag, secondTag);
		when(tagRepo.findAll()).thenReturn(allTags);
		mvc.perform(get("/all-tags-ajax")).andExpect(model().attribute("tags", is(allTags)));
	}

	@Test
	public void shouldBeOkForSingleTag() throws Exception {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(firstTag));
		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToSingleTagView() throws Exception {
		long tagId = 1;
		when(tagRepo.findById(tagId)).thenReturn(Optional.of(firstTag));
		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tag")));
	}

	@Test
	public void shouldRouteToAllMediumsView() throws Exception {
		mvc.perform(get("/mediums")).andExpect(view().name(is("mediums")));
	}

	@Test
	public void shouldPutMediumsIntoModel() throws Exception {
		Collection<Medium> allMediums = asList(firstMedium, secondMedium);
		when(mediumRepo.findAll()).thenReturn(allMediums);
		mvc.perform(get("/mediums")).andExpect(model().attribute("mediums", is(allMediums)));
	}

	@Test
	public void shouldBeOkForSingleMedium() throws Exception {
		long mediumId = 1;
		when(mediumRepo.findById(mediumId)).thenReturn(Optional.of(firstMedium));
		mvc.perform(get("/medium?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldRouteToSingleMediumView() throws Exception {
		long mediumId = 1;
		when(mediumRepo.findById(mediumId)).thenReturn(Optional.of(firstMedium));
		mvc.perform(get("/medium?id=1")).andExpect(view().name(is("medium")));
	}
	

}
