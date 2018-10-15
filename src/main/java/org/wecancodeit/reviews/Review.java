package org.wecancodeit.reviews;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String title;
	private String sqrImg;
	private String rndImg;
	private String category;
	@Lob
	@Column
	private String content;
	private String ranking;
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getSqrImg() {
		return sqrImg;
	}

	public String getRndImg() {
		return rndImg;
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
	

	protected Review() {}

	public Review(String title, String sqrImg, String rndImg, String category, String content, String ranking) {
		this.title = title;
		this.sqrImg = sqrImg;
		this.rndImg = rndImg;
		this.category = category;
		this.content = content;
		this.ranking = ranking;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Review[id=%d, title='%s', sqrImg='%s', rndImg='%s', category='%s', content='%s', ranking='%s']",
				id, title, sqrImg, rndImg, category, content, ranking);
	}


}
