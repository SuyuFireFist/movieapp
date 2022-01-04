package vo;

import lombok.Data;

@Data
public class ReviewVO {
	private int seq;
	private String user_id;
	private String review;
	private int userrating;
	private int movieseq;
}
