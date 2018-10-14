package org.wecancodeit.reviews;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Review {
	

	public String getTitle() {
		return title;
	}

	public String getImg() {
		return img;
	}

	public String getCategory() {
		return category;
	}

	public String getContent() {
		return content;
	}

	public String getRanking() {
		return ranking;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String img;
	private String category;
	@Lob
	@Column
	private String content;
	private String ranking;

	protected Review() {}

	public Review(String title, String img, String category, String content, String ranking) {
		this.title = title;
		this.img = img;
		this.category = category;
		this.content = content;
		this.ranking = ranking;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Review[id=%d, title='%s', img='%s', category='%s', content='%s', ranking='%s']",
				id, title, img, category, content, ranking);
	}

}
