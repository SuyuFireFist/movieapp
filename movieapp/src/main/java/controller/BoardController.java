package controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vo.BoardVO;
import service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@GetMapping("write")
	public ModelAndView getBoard() {
		ModelAndView mav = new ModelAndView();
		BoardVO board = new BoardVO();
		mav.addObject("board",board);
		return mav;
	}
	
	@PostMapping("write")
	public ModelAndView write(@Valid BoardVO board, BindingResult bresult,
			HttpServletRequest request,Principal principal,Authentication authentication) {
		ModelAndView mav = new ModelAndView();
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String user_id = (String) userDetails.getUsername();
		
		board.setUser_id(user_id);
		
		
		board.setP_cinema(board.getSigu()+"  "+board.getCom()+board.getCine());
		service.boardwrite(board,request);
		mav.setViewName("redirect:list");
		return mav;
	}
	
	@RequestMapping("list")
		public ModelAndView list(Integer pageNum) {
		ModelAndView mav = new ModelAndView();
		if(pageNum ==null || pageNum.toString().equals("")) {
		pageNum=1;
		}
		int limit=10;
		int listcount =service.boardcount();
		List<BoardVO> boardlist = service.boardlist(pageNum,limit);
		int maxpage = (int)((double)listcount/limit + 0.95);
		int startpage=(int)((pageNum/10.0 +0.9) -1) * 10 +1;
		int endpage = startpage +9;
		if(endpage > maxpage) endpage = maxpage;
		int boardno = listcount - (pageNum -1) * limit;
		mav.addObject("pageNum",pageNum);
		mav.addObject("maxpage",maxpage);
		mav.addObject("startpage",startpage);
		mav.addObject("endpage",endpage);
		mav.addObject("listcount",listcount);
		mav.addObject("boardlist",boardlist);
		mav.addObject("boardno",boardno);
		return mav;
		}
	
	@RequestMapping("detail")
	public ModelAndView detail(Integer seq) {
			ModelAndView mav = new ModelAndView();
			BoardVO board = service.getBoard(seq);
			System.out.println(board);
			service.hitadd(seq);
			mav.addObject("board",board);
			return mav;
	}
	
	@RequestMapping("delete")
	public ModelAndView delete(BoardVO board) {
		ModelAndView mav = new ModelAndView();
		int num=board.getSeq();
		service.boardDelete(num);
		mav.setViewName("redirect:list");
		return mav;
		}
	
	@GetMapping("update")
	public ModelAndView getBoard(Integer seq) {
		ModelAndView mav = new ModelAndView();
		if(seq != null) {
			BoardVO board = service.getBoard(seq);
			mav.addObject("board",board);
		}
		return mav;
	}
	
	@PostMapping("update")
	public ModelAndView update(@Valid BoardVO board, BindingResult bresult,
		HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(bresult.hasErrors()) {
			mav.getModel().putAll(bresult.getModel());
			return mav;
		}
		try {
		board.setP_cinema(board.getSigu()+"  "+board.getCom()+board.getCine());
		service.boardUpdate(board, request);
		mav.setViewName("redirect:detail?seq="+board.getSeq());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mav;
		}
}
