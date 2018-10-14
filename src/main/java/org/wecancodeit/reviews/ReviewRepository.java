package org.wecancodeit.reviews;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository {

	private Map<Long, Review> reviewList = new HashMap<>();
	
	public ReviewRepository() {
		Review thresher = new Review(1, "Thresher Shark", "images/ThresherShark.jpg", "Alopiidae", "They use their elongated caudal fin to whip the water around fish and stun them. It doesn't get cooler than this.", "5 out of 5 Chomps");
		Review greatWhite = new Review(2, "Great White Shark", "images/GreatWhite.jpg","Lamnidae","Classic. Elegant.", "5 out of 5 chomps");
		Review frilled = new Review(3, "Frilled Shark", "images/FrilledShark.jpg", "Chlamydoselachidae", "They look like freaking sea serpents! A+", "5 out of 5 chomps");
		Review whale = new Review(4, "Whale Shark", "images/WhaleShark.jpg", "Cetorhinidae", "Gentle Giants, the largest fish", "5 out of 5 filter feeders");
		Review sandTiger = new Review(5, "Sand Tiger Shark", "images/SandTiger.jpg","Odontaspididae","Will make you happy your parents made you get braces", "5 out of 5 chomps");
		Review bull = new Review (6, "Bull Shark", "images/BullShark.jpg", "Carcharhinidae", "Can switch from salt water to fresh water, most likely to bite humans", "5 out of 5 terrifying chomps");
		
		reviewList.put(thresher.getId(), thresher);
		reviewList.put(frilled.getId(), frilled);
		reviewList.put(whale.getId(), whale);
		reviewList.put(bull.getId(), bull);
		reviewList.put(greatWhite.getId(), greatWhite);
		reviewList.put(sandTiger.getId(), sandTiger);
			
	}
	
	//Constructor for Testing Purposes
	public ReviewRepository(Review...reviews) {
		for (Review review : reviews) {
		reviewList.put(review.getId(), review);
		}
	}

	public Review returnOne(long id) {
		return reviewList.get(id);
	}

	public Collection<Review> returnAll() {
		return reviewList.values();
	}

}
