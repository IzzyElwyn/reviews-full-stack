package org.wecancodeit.reviews;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String sqrImg;
	private String rndImg;
	@Lob
	@Column
	private String content;
	private String ranking;

	@ManyToOne
	private Medium medium;

	@ManyToMany(mappedBy = "reviews")
	private Collection<Tag> tags;

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


	public String getContent() {
		return content;
	}

	public String getRanking() {
		return ranking;
	}
	
	public Medium getMedium() {
		return medium;
	}
	
	public Collection<Tag> getTags() {
		return tags;
	}
	

	public Review() {
	}

	public Review(String title, String sqrImg, String rndImg, String content, String ranking, Medium medium) {
		this.title = title;
		this.sqrImg = sqrImg;
		this.rndImg = rndImg;
		this.content = content;
		this.ranking = ranking;
		this.medium = medium;
	}

	@Override
	public String toString() {
		return String.format("Review[id=%d, title='%s', sqrImg='%s', rndImg='%s', content='%s', ranking='%s', medium='%s']", id,
				title, sqrImg, rndImg, content, ranking, medium);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
