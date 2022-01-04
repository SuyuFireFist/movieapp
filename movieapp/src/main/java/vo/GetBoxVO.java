package vo;

public class GetBoxVO {
	private String boxofficeType; // 문자열 박스오피스 종류를 출력합니다.
	private String showRange; // 문자열 박스오피스 조회 일자를 출력합니다.
	private String rnum; // 문자열 순번을 출력합니다.
	private String rank; // 문자열 해당일자의 박스오피스 순위를 출력합니다.
	private String rankInten; // 문자열 전일대비 순위의 증감분을 출력합니다.
	private String rankOldAndNew; // 문자열 랭킹에 신규진입여부를 출력합니다.
	// “OLD” //기존 , “NEW” : 신규
	private String movieCd; // 문자열 영화의 대표코드를 출력합니다.
	private String movieNm; // 문자열 영화명(국문)을 출력합니다.
	private String openDt; // 문자열 영화의 개봉일을 출력합니다.
	private String salesAmt; // 문자열 해당일의 매출액을 출력합니다.
	private String salesShare; /// 문자열 해당일자 상영작의 매출총액 대비 해당 영화의 매출비율을 출력합니다.
	private String salesInten; // 문자열 전일 대비 매출액 증감분을 출력합니다.
	private String salesChange; // 문자열 전일 대비 매출액 증감 비율을 출력합니다.
	private String salesAcc; // 문자열 누적매출액을 출력합니다.
	private String audiCnt; // 문자열 해당일의 관객수를 출력합니다.
	private String audiInten; // 문자열 전일 대비 관객수 증감분을 출력합니다.
	private String audiChange; // 문자열 전일 대비 관객수 증감 비율을 출력합니다.
	private String audiAcc; // 문자열 누적관객수를 출력합니다.
	private String scrnCnt; // 문자열 해당일자에 상영한 스크린수를 출력합니다.
	private String showCnt; // 문자열 해당일자에 상영된 횟수를 출력합니다.
	private String image;

	public GetBoxVO() {
	}

	public GetBoxVO(String boxofficeType, String showRange, String rnum, String rank, String rankInten,
			String rankOldAndNew, String movieCd, String movieNm, String openDt, String salesAmt, String salesShare,
			String salesInten, String salesChange, String salesAcc, String audiCnt, String audiInten, String audiChange,
			String audiAcc, String scrnCnt, String showCnt, String image) {
		super();
		this.boxofficeType = boxofficeType;
		this.showRange = showRange;
		this.rnum = rnum;
		this.rank = rank;
		this.rankInten = rankInten;
		this.rankOldAndNew = rankOldAndNew;
		this.movieCd = movieCd;
		this.movieNm = movieNm;
		this.openDt = openDt;
		this.salesAmt = salesAmt;
		this.salesShare = salesShare;
		this.salesInten = salesInten;
		this.salesChange = salesChange;
		this.salesAcc = salesAcc;
		this.audiCnt = audiCnt;
		this.audiInten = audiInten;
		this.audiChange = audiChange;
		this.audiAcc = audiAcc;
		this.scrnCnt = scrnCnt;
		this.showCnt = showCnt;
		this.image = image;
	}

	public String getBoxofficeType() {
		return boxofficeType;
	}

	public void setBoxofficeType(String boxofficeType) {
		this.boxofficeType = boxofficeType;
	}

	public String getShowRange() {
		return showRange;
	}

	public void setShowRange(String showRange) {
		this.showRange = showRange;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getRankInten() {
		return rankInten;
	}

	public void setRankInten(String rankInten) {
		this.rankInten = rankInten;
	}

	public String getRankOldAndNew() {
		return rankOldAndNew;
	}

	public void setRankOldAndNew(String rankOldAndNew) {
		this.rankOldAndNew = rankOldAndNew;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}

	public String getMovieNm() {
		return movieNm;
	}

	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}

	public String getOpenDt() {
		return openDt;
	}

	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}

	public String getSalesAmt() {
		return salesAmt;
	}

	public void setSalesAmt(String salesAmt) {
		this.salesAmt = salesAmt;
	}

	public String getSalesShare() {
		return salesShare;
	}

	public void setSalesShare(String salesShare) {
		this.salesShare = salesShare;
	}

	public String getSalesInten() {
		return salesInten;
	}

	public void setSalesInten(String salesInten) {
		this.salesInten = salesInten;
	}

	public String getSalesChange() {
		return salesChange;
	}

	public void setSalesChange(String salesChange) {
		this.salesChange = salesChange;
	}

	public String getSalesAcc() {
		return salesAcc;
	}

	public void setSalesAcc(String salesAcc) {
		this.salesAcc = salesAcc;
	}

	public String getAudiCnt() {
		return audiCnt;
	}

	public void setAudiCnt(String audiCnt) {
		this.audiCnt = audiCnt;
	}

	public String getAudiInten() {
		return audiInten;
	}

	public void setAudiInten(String audiInten) {
		this.audiInten = audiInten;
	}

	public String getAudiChange() {
		return audiChange;
	}

	public void setAudiChange(String audiChange) {
		this.audiChange = audiChange;
	}

	public String getAudiAcc() {
		return audiAcc;
	}

	public void setAudiAcc(String audiAcc) {
		this.audiAcc = audiAcc;
	}

	public String getScrnCnt() {
		return scrnCnt;
	}

	public void setScrnCnt(String scrnCnt) {
		this.scrnCnt = scrnCnt;
	}

	public String getShowCnt() {
		return showCnt;
	}

	public void setShowCnt(String showCnt) {
		this.showCnt = showCnt;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "GetBoxVO [rank=" + rank + "\n movieCd=" + movieCd + "\n movieNm=" + movieNm + "\n openDt=" + openDt + "]";
	}

	

}
