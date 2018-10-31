package org.wecancodeit.reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {

	@Resource
	private CommentRepository commentRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@RequestMapping("/add-comment")
	public String addComment(String username, String commentContent, Long reviewId) {
		Optional<Review> reviewCommented = reviewRepo.findById(reviewId);
		Review review = reviewCommented.get();
		
		Comment comment = new Comment(username, commentContent, review);
		commentRepo.save(comment);

		return "redirect:/review?id=" + reviewId;
	}

}
