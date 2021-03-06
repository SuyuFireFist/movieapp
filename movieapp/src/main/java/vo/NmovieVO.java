package vo;

public class NmovieVO {
	private String title;
	private String link;
	private String image;
	private String subtitle;
	private String pubDate;
	private String director;
	private String actor;
	private String userRating;

	public NmovieVO() {
	}

	public NmovieVO(String title, String link, String image, String subtitle, String pubDate, String director,
			String actor, String useRating) {
		super();
		this.title = title;
		this.link = link;
		this.image = image;
		this.subtitle = subtitle;
		this.pubDate = pubDate;
		this.director = director;
		this.actor = actor;
		this.userRating = useRating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		title = title.replaceAll("<b>", "");
		title = title.replaceAll("</b>", "");
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getUseRating() {
		return userRating;
	}

	public void setUseRating(String useRating) {
		this.userRating = useRating;
	}

	@Override
	public String toString() {
		return "NmovieVO [title=" + title + ", link=" + link + ", image=" + image + ", subtitle=" + subtitle
				+ ", pubDate=" + pubDate + ", director=" + director + ", actor=" + actor + ", useRating=" + userRating
				+ "]";
	}

}
