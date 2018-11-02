package org.wecancodeit.reviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show-reviews")
public class ReviewRestController {
	
	@Resource
	private ReviewRepository reviewRepo; 
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private MediumRepository mediumRepo;
	
	@RequestMapping("")
	public Iterable<Review> findAllReviews() {
		return reviewRepo.findAll(); 
	}
	
	@RequestMapping("/{id}")
	public Optional<Review> findOneReview(@PathVariable long id){
		return reviewRepo.findById(id);
	}
	
	@RequestMapping("/tags/{tagName}")
	public Collection<Review> findAllReviewsByTags(@PathVariable(value="tagName") String tagName) {
		Tag tag = tagRepo.findByNameIgnoreCaseLike(tagName);
		return reviewRepo.findByTagsContains(tag);
	}
	
	@RequestMapping("/medium/{mediumType}")
	public Collection<Review> findAllReviewsByMedium(@PathVariable(value="mediumType") String mediumType) {
		Medium medium = mediumRepo.findByTypeIgnoreCaseLike(mediumType);
		return reviewRepo.findAllByMedium(medium);
}
}
