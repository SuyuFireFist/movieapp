package vo;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;



public class BoardVO {
	private int seq;
	private String user_id;
	@NotEmpty(message="간단소개를 작성해주세요")
	private String p_title;
	private String p_file_path;
	@NotEmpty(message="내용을 입력하세요")
	private String p_content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date p_log_time;
	private int p_hit;
	@NotEmpty(message="상영 영화관을 입력해주세요")
	private String p_cinema;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date p_wdate;
	@NotEmpty(message="지역을 선택해주세요")
	private String p_region;
	//@NotEmpty(message="상영영화를 검색해주세요")
	private String p_movie;
	private String sido; 
	private String sigu;
	private String com;
	private String cine;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public String getP_file_path() {
		return p_file_path;
	}
	public void setP_file_path(String p_file_path) {
		this.p_file_path = p_file_path;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public Date getP_log_time() {
		return p_log_time;
	}
	public void setP_log_time(Date p_log_time) {
		this.p_log_time = p_log_time;
	}
	public int getP_hit() {
		return p_hit;
	}
	public void setP_hit(int p_hit) {
		this.p_hit = p_hit;
	}
	public String getP_cinema() {
		return p_cinema;
	}
	public void setP_cinema(String p_cinema) {
		this.p_cinema = p_cinema;
	}
	public Date getP_wdate() {
		return p_wdate;
	}
	public void setP_wdate(Date p_wdate) {
		this.p_wdate = p_wdate;
	}
	public String getP_region() {
		return p_region;
	}
	public void setP_region(String p_region) {
		this.p_region = p_region;
	}
	public String getP_movie() {
		return p_movie;
	}
	public void setP_movie(String p_movie) {
		this.p_movie = p_movie;
	}
	
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigu() {
		return sigu;
	}
	public void setSigu(String sigu) {
		this.sigu = sigu;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public String getCine() {
		return cine;
	}
	public void setCine(String cine) {
		this.cine = cine;
	}
	@Override
	public String toString() {
		return "Board [seq=" + seq + ", user_id=" + user_id + ", p_title=" + p_title + ", p_file_path=" + p_file_path
				+ ", p_content=" + p_content + ", p_log_time=" + p_log_time + ", p_hit=" + p_hit + ", p_cinema="
				+ p_cinema + ", p_wdate=" + p_wdate + ", p_region=" + p_region + ", p_movie=" + p_movie + ", sido="
				+ sido + ", sigu=" + sigu + ", com=" + com + ", cine=" + cine + "]";
	}

	
	
	
	
	
	
}
