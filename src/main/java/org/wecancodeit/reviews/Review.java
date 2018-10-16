package org.wecancodeit.reviews;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;



@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String title;
	private String sqrImg;
	private String rndImg;
	@Lob
	@Column
	private String content;
	private String ranking;
	
	@ManyToMany(mappedBy = "reviews")
	private Collection<Category> category;
	
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

	public Collection<Category> getCategory() {
		return category;
	}

	public String getContent() {
		return content;
	}

	public String getRanking() {
		return ranking;
	}
	

	protected Review() {}

	public Review(String title, String sqrImg, String rndImg, String content, String ranking) {
		this.title = title;
		this.sqrImg = sqrImg;
		this.rndImg = rndImg;
		this.content = content;
		this.ranking = ranking;
	}
	
	@Override
	public String toString() {
		return String.format(
				"Review[id=%d, title='%s', sqrImg='%s', rndImg='%s', content='%s', ranking='%s']",
				id, title, sqrImg, rndImg, content, ranking);
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
