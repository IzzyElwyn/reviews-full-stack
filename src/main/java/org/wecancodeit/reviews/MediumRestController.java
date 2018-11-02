package org.wecancodeit.reviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show-mediums")
public class MediumRestController {

	
	@Resource
	private ReviewRepository reviewRepo; 
	
	@Resource
	private MediumRepository mediumRepo;
	
	@RequestMapping("")
	public Iterable<Medium> findAllMediums() {
		return mediumRepo.findAll();
	}
	
	@RequestMapping("/{id}")
	public Optional<Medium> findOneMedium(@PathVariable long id){
		return mediumRepo.findById(id);
	}
	
	@RequestMapping("/{mediumType}/reviews")
	public Collection<Review> findAllReviewsByMedium(@PathVariable(value="mediumType") String mediumType) {
		Medium medium = mediumRepo.findByTypeIgnoreCaseLike(mediumType);
		return reviewRepo.findAllByMedium(medium);
	}
}
