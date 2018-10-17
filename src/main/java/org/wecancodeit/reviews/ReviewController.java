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
	
	@RequestMapping("/show-reviews")
	public String returnAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}


	@RequestMapping("/show-reviews-sorted")
	public String returnAllReviewsSorted(Model model) {
		model.addAttribute("reviews", reviewRepo.findAllByOrderByTitleAsc());
		return "reviews";

		}
	
	@RequestMapping("/review")
	public String returnOneReview(@RequestParam(value="id") long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		
		if(review.isPresent()) {
		model.addAttribute("reviews", review.get());
		return "review";
	}
		
		throw new ReviewNotFoundException();
	}

	@RequestMapping("/tag")
	public String returnOneTag(@RequestParam(value="id")long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);
		
		if(tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			return "tag";
		}
		
		throw new TagNotFoundException();
		
		
	}

}
