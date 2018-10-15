package org.wecancodeit.reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ReviewPopulator implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(ReviewPopulator.class);

	@Resource
	private ReviewRepository repository;
		
	@Override
	public void run(String... args) throws Exception {

		repository.save(new Review("The Woman In Black", "images/Review_Images/womaninblack.jpg", "images/Review_Images/womaninblack_round.jpg", "Book", "Don't bother with the 2012 film that relies almost exclusively on jump-scares to reach its horror-quota. The original novel by Susan Hill is a classic for a reason. It's a slow-burn to be sure, but the dread of the main character seeps off the page, infecting the reader and adding a tinge of dread to every bump you'll hear around your house for the next week.", "5 out of 5 Spectres"));
		repository.save(new Review("Nos4a2", "images/Review_Images/nos4a2.jpg", "images/Review_Images/nos4a2_round.jpg", "Book","Joe Hill inherited his father Steven King's love of horror, but vastily improves on his execution and always sticks the landing. Nos4a2 weaves a tale that's equal parts adventure, fantasy and horror and it's one worth diving into. Also, if Hill's intent was to sway his readers to his side in his ongoing debate with his father regarding the coolest motorcycle manufacturer, he certainly endured the Triumph to me.", "5 out of 5 Vampire Bats"));
		repository.save(new Review("Made In Abyss", "images/Review_Images/madeinabyss.jpg", "images/Review_Images/madeinabyss_round.jpg",  "Anime", "Don't let those cute character designs fool you, Made in Abyss isn't the typical anime fare. The world they inhabit literally borders a chasm whose depths hide mysteries that call out to our protagonist. Drawing her, like her long lost mother, into a world from which they will likely not return. The deeper they go, the more mosters we encounter, and the juxstaposition of their gritty appearance next to the round and soft chibi-style of our main characters is jarring in all the right away.", "5 out of 5 Monsters from the deep"));
		repository.save(new Review("Locke And Key", "images/Review_Images/lockeandkey.jpg", "images/Review_Images/lockeandkey_round.jpg", "Graphic Novel", "Locke and Key is mesmerizing. It's a brutal and beautiful dive into a world where magic was made out of horror and it blends the two so perfectly that it leaves a lasting impression.", "5 out of 5 Skeleton Keys"));
		repository.save(new Review("The Haunting of Hill House", "images/Review_Images/hauntingofhillhouse.jpg", "images/Review_Images/hauntingofhillhouse_round.jpg", "Streaming Series","At first I hesitated to give this series the time of day (or night). My biggest pet-peeve when it comes to modern adaptations is labeling a story as based on an existing work only to completely alter the storyline so significantly it could have been its own original story, I was very pleasantly surprised by Netflix's version of the tale. Though the titular house is still central to the narrative, the origin of the house has changed and the focus is now a family whose encounter with the house in the 90's have left them all broken. The narrative unravels over the season, bouncing between their time in the house as children and their fractured adult lives, slowly threads weave together and the horror of seeing the final picture is like a punch to the gut. Netflix's The Haunting of Hill House is certainly about a haunted house, but it's so much more than that. It's about a haunted family dealing with loses that are all too real.", "5 out of 5 Moving Statues"));
		repository.save(new Review("Bioshock", "images/Review_Images/bioshock.jpg", "images/Review_Images/bioshock_round.jpg", "Video Game","It was the atmopshere that surprised me the most. The use of shadow and sound to ratchet up the dread is incredibly effective, making the player nervous to turn the corner. Even as you power up your abilities, the winding corridors and glimpses of splicers darking between doorways still fills you with unease as you progress in the game.", "5 out of 5 Little Sisters"));
		
			log.info("Reviews found with findAll():");
			log.info("-------------------------------");
			for (Review review : repository.findAll()) {
				log.info(review.toString());
			}
			log.info("");


			Optional<Review> review = repository.findById(1L);
			log.info("Review found with findOne(1L):");
			log.info("--------------------------------");
			log.info(review.toString());
			log.info("");

			log.info("Review found with findByTitle('The Woman In Black'):");
			log.info("--------------------------------------------");
			for (Review twib : repository.findByTitle("The Woman In Black")) {
			log.info(twib.toString());
			}
			log.info("");
			

			log.info("Review found with findAll() and sorted by title:");
			log.info("--------------------------------------------");
			for (Review reviews : repository.findAllByOrderByTitleAsc()) {
			log.info(reviews.toString());
			}
			log.info("");
		

	}
}
