package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BoardDao;
import vo.BoardVO;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;
	
	public void boardwrite(BoardVO board, HttpServletRequest request) {
		boardDao.wirte(board);
		
	}

	public int boardcount() {
		return boardDao.count();
	}

	public List<BoardVO> boardlist(Integer pageNum, int limit) {
		return boardDao.list(pageNum,limit);
	}

	public BoardVO getBoard(Integer num) {
		return boardDao.detail(num);
	}

	public void hitadd(Integer num) {
		boardDao.hitadd(num);
		
	}

	public void boardDelete(int num) {
		boardDao.delete(num);
		
	}

	public void boardUpdate(BoardVO board, HttpServletRequest request) {
		boardDao.update(board);
		
	}

	
}
