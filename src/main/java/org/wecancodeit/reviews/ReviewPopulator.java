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
	private ReviewRepository reviewRepo;
	
	@Resource
	private MediumRepository mediumRepo;
	
	@Resource
	private TagRepository tagRepo;
		
	@Override
	public void run(String... args) throws Exception {

		Medium book = new Medium("book");
		Medium graphicNovel = new Medium("graphic novel");
		Medium anime = new Medium("anime");
		Medium series = new Medium("streaming series");
		Medium videoGame = new Medium("video game");
		mediumRepo.save(book);
		mediumRepo.save(graphicNovel);
		mediumRepo.save(anime);
		mediumRepo.save(series);
		mediumRepo.save(videoGame);
		
		Review wib = new Review("The Woman In Black", "images/Review_Images/womaninblack.jpg", "images/Review_Images/womaninblack_round.jpg", "Don't bother with the 2012 film that relies almost exclusively on jump-scares to reach its horror-quota. The original novel by Susan Hill is a classic for a reason. It's a slow-burn to be sure, but the dread of the main character seeps off the page, infecting the reader and adding a tinge of dread to every bump you'll hear around your house for the next week.", "5 out of 5 Spectres", book);
		Review nos4a2 = new Review("Nos4a2", "images/Review_Images/nos4a2.jpg", "images/Review_Images/nos4a2_round.jpg", "Joe Hill inherited his father Steven King's love of horror, but vastily improves on his execution and always sticks the landing. Nos4a2 weaves a tale that's equal parts adventure, fantasy and horror and it's one worth diving into. Also, if Hill's intent was to sway his readers to his side in his ongoing debate with his father regarding the coolest motorcycle manufacturer, he certainly endured the Triumph to me.", "5 out of 5 Vampire Bats", book);
		Review mia = new Review("Made In Abyss", "images/Review_Images/madeinabyss.jpg", "images/Review_Images/madeinabyss_round.jpg", "Don't let those cute character designs fool you, Made in Abyss isn't the typical anime fare. The world they inhabit literally borders a chasm whose depths hide mysteries that call out to our protagonist. Drawing her, like her long lost mother, into a world from which they will likely not return. The deeper they go, the more mosters we encounter, and the juxstaposition of their gritty appearance next to the round and soft chibi-style of our main characters is jarring in all the right away.", "5 out of 5 Monsters from the deep", anime);
		Review lak = new Review("Locke And Key", "images/Review_Images/lockeandkey.jpg", "images/Review_Images/lockeandkey_round.jpg", "Locke and Key is mesmerizing. It's a brutal and beautiful dive into a world where magic was made out of horror and it blends the two so perfectly that it leaves a lasting impression.", "5 out of 5 Skeleton Keys", graphicNovel);
		Review thohh = new Review("The Haunting of Hill House", "images/Review_Images/hauntingofhillhouse.jpg", "images/Review_Images/hauntingofhillhouse_round.jpg", "At first I hesitated to give this series the time of day (or night). My biggest pet-peeve when it comes to modern adaptations is labeling a story as based on an existing work only to completely alter the storyline so significantly it could have been its own original story, I was very pleasantly surprised by Netflix's version of the tale. Though the titular house is still central to the narrative, the origin of the house has changed and the focus is now a family whose encounter with the house in the 90's have left them all broken. The narrative unravels over the season, bouncing between their time in the house as children and their fractured adult lives, slowly threads weave together and the horror of seeing the final picture is like a punch to the gut. Netflix's The Haunting of Hill House is certainly about a haunted house, but it's so much more than that. It's about a haunted family dealing with loses that are all too real.", "5 out of 5 Moving Statues", series);
		Review bioshock = new Review("Bioshock", "images/Review_Images/bioshock.jpg", "images/Review_Images/bioshock_round.jpg", "It was the atmopshere that surprised me the most. The use of shadow and sound to ratchet up the dread is incredibly effective, making the player nervous to turn the corner. Even as you power up your abilities, the winding corridors and glimpses of splicers darking between doorways still fills you with unease as you progress in the game.", "5 out of 5 Little Sisters", videoGame);
		reviewRepo.save(wib);
		reviewRepo.save(nos4a2);
		reviewRepo.save(mia);
		reviewRepo.save(lak);
		reviewRepo.save(thohh);
		reviewRepo.save(bioshock);
		
		Tag ghosts = new Tag("ghosts", "Stories featuring those haunting spectres we all know and love", wib, lak, thohh);
		Tag monsters = new Tag("monsters", "Stories featuring all manner of inhuman beasties", mia);
		Tag fantasy = new Tag("fantasy", "Stories featuring elements based in the unexplainable or based in magic", nos4a2, mia, lak);
		Tag scifi = new Tag("sci-fi", "Stories featuring science or science-esque logic behind their main elements", bioshock);
		Tag drama = new Tag("drama", "Stories rooted in the emotional aspects of the human experience", nos4a2, mia, lak, thohh);
		tagRepo.save(ghosts);
		tagRepo.save(monsters);
		tagRepo.save(fantasy);
		tagRepo.save(scifi);
		tagRepo.save(drama);

			log.info("Reviews found with findAll():");
			log.info("-------------------------------");
			for (Review review : reviewRepo.findAll()) {
				log.info(review.toString());
			}
			log.info("");


			Optional<Review> review = reviewRepo.findById(1L);
			log.info("Review found with findOne(1L):");
			log.info("--------------------------------");
			log.info(review.toString());
			log.info("");

			log.info("Review found with findByTitle('The Woman In Black'):");
			log.info("--------------------------------------------");
			for (Review twib : reviewRepo.findByTitle("The Woman In Black")) {
			log.info(twib.toString());
			}
			log.info("");
			

			log.info("Review found with findAll() and sorted by title:");
			log.info("--------------------------------------------");
			for (Review reviews : reviewRepo.findAllByOrderByTitleAsc()) {
			log.info(reviews.toString());
			}
			log.info("");
			
			log.info("Tag found with findByReviewsContains(nos4a2):");
			log.info("-------------------------------------------"); 
			for (Tag tags : tagRepo.findByReviewsContains(nos4a2)) {
			log.info(tags.toString());
			}
			log.info("");
			

	}
}