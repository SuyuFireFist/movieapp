package vo;

import lombok.Data;

@Data
public class SocVO {
	private int letter_seq;
	private String user_id;
	private String title;
	private String content;
	private String rcv_id;
	private String write_date;
	private String read_date;
	private String readyn;
	private String sent_del;
	private String rcv_del;

}
