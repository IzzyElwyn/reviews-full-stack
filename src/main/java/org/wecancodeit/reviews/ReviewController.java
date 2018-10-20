package org.wecancodeit.reviews;

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
	
}
