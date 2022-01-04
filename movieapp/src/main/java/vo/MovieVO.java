package vo;

import lombok.Data;

@Data
public class MovieVO {
	private int num;
	private String title;
	private String subtitle;
	private String image;
	private String pubdate;
	private String director;
	private String actor;
	private String userrating;
}
