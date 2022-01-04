package vo;

import java.util.List;

import lombok.Data;

@Data
public class PageVO {
	private int currentPage;
	private int totalPage;
	private List<SocVO> list;
	private int startPage;
	private int endPage;
}
