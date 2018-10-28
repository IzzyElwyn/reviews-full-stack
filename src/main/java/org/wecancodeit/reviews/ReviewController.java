package org.wecancodeit.reviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	TagRepository tagRepo;

	@Resource
	MediumRepository mediumRepo;

	@RequestMapping("/reviews")
	public String returnAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}

	@RequestMapping("/reviews-sorted")
	public String returnAllReviewsSorted(Model model) {
		model.addAttribute("reviews", reviewRepo.findAllByOrderByTitleAsc());
		return "reviews";

	}

	@RequestMapping("/review")
	public String returnOneReview(@RequestParam(value = "id") long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			model.addAttribute("reviews", review.get());
			model.addAttribute("mediums", mediumRepo.findByReviews(review.get()));
			model.addAttribute("tags", tagRepo.findByReviewsContains(review.get()));
			return "review";
		}

		throw new ReviewNotFoundException();
	}

	@RequestMapping("/tag")
	public String returnOneTag(@RequestParam(value = "id") long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);

		if (tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			model.addAttribute("reviews", reviewRepo.findByTagsContains(tag.get()));
			return "tag";
		}

		throw new TagNotFoundException();

	}

	@RequestMapping("/tags")
	public String returnAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return "tags";

	}

	@RequestMapping("/medium")
	public String returnOneMedium(@RequestParam(value = "id") long id, Model model) throws MediumNotFoundException {
		Optional<Medium> medium = mediumRepo.findById(id);

		if (medium.isPresent()) {
			model.addAttribute("mediums", medium.get());
			model.addAttribute("reviews", reviewRepo.findAllByMedium(medium.get()));
			return "medium";
		}

		throw new MediumNotFoundException();
	}

	@RequestMapping("/mediums")
	public String returnAllMediums(Model model) {
		model.addAttribute("mediums", mediumRepo.findAll());
		return "mediums";

	}

	@RequestMapping("/add-review")
	public String addReview(String reviewTitle, String sqrImg, String rndImg, String reviewContent,
			String reviewRanking, String mediumType) {
		Medium medium = mediumRepo.findByType(mediumType);

		if (medium == null) {

			medium = new Medium(mediumType);
			mediumRepo.save(medium);
		}

		Review newReview = reviewRepo.getByTitle(reviewTitle);

		if (newReview == null) {
			newReview = new Review(reviewTitle, sqrImg, rndImg, reviewContent, reviewRanking, medium);
			reviewRepo.save(newReview);
		}

		return "redirect:/reviews";
	}

	@RequestMapping("/delete-review")
	public String deleteReviewByTitle(String reviewTitle) {

		if (reviewRepo.getByTitle(reviewTitle) != null) {
			Review deletedReview = reviewRepo.getByTitle(reviewTitle);
			reviewRepo.delete(deletedReview);
		}

		return "redirect:/reviews";

	}

	@RequestMapping("/del-review")
	public String deleteReviewByReviewId(Long reviewId) {
		Optional<Review> review = reviewRepo.findById(reviewId);
		Review reviewToRemove = review.get();

		Collection<Tag> tagsToUpdate = tagRepo.findByReviewsContains(reviewToRemove);
		if (tagsToUpdate.size() > 0) {
			for (Tag tag : tagsToUpdate) {
				tag.deleteReview(reviewToRemove);
			}
		}

		reviewRepo.deleteById(reviewId);

		return "redirect:/reviews";
	}

	@RequestMapping("/find-by-tag")
	public String findReviewsByTags(String tagName, Model model) {
		Tag tag = tagRepo.findByName(tagName);
		model.addAttribute("reviews", reviewRepo.findByTagsContains(tag));

		return "/tag";
	}

	@RequestMapping("/new-tag")
	public String addTag(String tagName, String tagDescription, String reviewTitle) {
		tagName = tagName.toLowerCase();
		Review review = reviewRepo.getByTitle(reviewTitle);

		if (tagRepo.findByName(tagName) == null) {
			Tag newTag = new Tag(tagName, tagDescription, review);
			tagRepo.save(newTag);
		}

		return "/tags";

	}

	@RequestMapping("/add-tag")
	public String addTagToReview(String tagName, String reviewTitle) {
		tagName = tagName.toLowerCase();

		if (tagRepo.findByName(tagName) != null) {
			Tag newTag = tagRepo.findByName(tagName);
			Review reviewTagged = reviewRepo.getByTitle(reviewTitle);
			newTag.addReview(reviewTagged);
			tagRepo.save(newTag);

		} else {

			addTag(tagName, null, reviewTitle);

		}
		Review reviewRedirect = reviewRepo.getByTitle(reviewTitle);
		long reviewId = reviewRedirect.getId();

		return "redirect:/review?id=" + reviewId;
	}

}
