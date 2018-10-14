package org.wecancodeit.reviews;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
	
	@Resource
	ReviewRepository reviewRepo;
	
	@RequestMapping("/show-reviews")
	public String returnAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.returnAll());
		return "reviews";
	}
	
	@RequestMapping("/review")
	public String returnOneReview(@RequestParam(value="id") Long id, Model model) {
		model.addAttribute("reviews", reviewRepo.returnOne(id));
		return "review";
	}

}
