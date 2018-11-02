package org.wecancodeit.reviews;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String img;
	@Lob
	@Column
	private String content;
	private String ranking;
	
	@JsonIgnore
	@ManyToOne
	private Medium medium;

	@JsonIgnore
	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	@JsonIgnore
	@ManyToMany(mappedBy = "reviews")
	private Collection<Tag> tags;

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}


	public String getRndImg() {
		return img;
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

	public Collection<Comment> getComments() {
		return comments;
	}

	public void setComment(Comment comment) {
		comments.add(comment);
	}

	public void deleteComment(Comment comment) {
		comments.remove(comment);
	}

	public Collection<Tag> getTags() {
		return tags;
	}
	
	public Collection<String> getTagsUrls() {
		Collection<String> urls = new ArrayList<>();
		for (Tag tag : tags) {
			urls.add(format("/show-reviews/%d/tags/%s", this.getId(), tag.getName().toLowerCase()));
			
		}
		
		return urls;
	}
	
	public String getMediumUrl() {
		
		return format("/show-reviews/%d/medium/%s", this.getId(), medium.getType().toLowerCase());
			
	}

	public Review() {
	}

	public Review(String title, String img, String content, String ranking, Medium medium,
			Comment... comments) {
		this.title = title;
		this.img = img;
		this.content = content;
		this.ranking = ranking;
		this.medium = medium;
		this.comments = new HashSet<>(Arrays.asList(comments));
	}

	@Override
	public String toString() {
		return String.format(
				"Review[id=%d, title='%s', img='%s', content='%s', ranking='%s', medium='%s']", id,
				title, img, content, ranking, medium);
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