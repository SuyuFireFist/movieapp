package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.BoardVO;
import mapper.BoardMapper;

@Repository
public class BoardDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String,Object> param = new HashMap<String,Object>();
	
	public void wirte(BoardVO board) {
		int seq = maxSeq() +1 ;
		board.setSeq(seq);
		template.getMapper(BoardMapper.class).insert(board);
	}
	private int maxSeq() {
		return template.getMapper(BoardMapper.class).maxseq();
	}
	public int count() {
		return template.getMapper(BoardMapper.class).count();
	}
	
	public List<BoardVO> list(Integer pageNum, int limit) {
		param.clear();
		int startrow = (pageNum -1) * limit +1;
		int endrow = startrow + limit -1;
		param.put("startrow",startrow);
		param.put("endrow",endrow);
		return template.getMapper(BoardMapper.class).list(param);
	}
	public BoardVO detail(Integer num) {
		return template.getMapper(BoardMapper.class).detail(num);
	}
	public void hitadd(Integer num) {
		template.getMapper(BoardMapper.class).hitadd(num);
	}
	public void delete(int num) {
		template.getMapper(BoardMapper.class).delete(num);
		
	}
	public void update(BoardVO board) {
		template.getMapper(BoardMapper.class).update(board);
		
	}
	
	

}
