package org.wecancodeit.reviews;

public class Review {

	private long id;
	private String title;
	private String img;
	private String category;
	private String content;
	private String ranking;

	public long getId() {
		return id;
	}

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

	public Review(long id, String title, String img, String category, String content, String ranking) {
		this.id = id;
		this.title = title;
		this.img = img;
		this.category = category;
		this.content = content;
		this.ranking = ranking;
	}

}
