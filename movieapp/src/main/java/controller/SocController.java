package controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import mapper.SocMapper;
import vo.PageVO;
import vo.SocVO;

@Controller
@Log4j
public class SocController {
	@Autowired
	private SocMapper socmapper;
	
	@RequestMapping(value = "letterhome")
	public ModelAndView letterhome(Principal principal,Authentication authentication, int currentPage) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String user_id = (String) userDetails.getUsername();
		
		ModelAndView homeview = new ModelAndView();
		List<SocVO> letlist = new ArrayList<SocVO>();
		
		PageVO page = new PageVO();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("currentPage", currentPage);
		
		letlist = socmapper.findletter(map);	
		int totalCount = socmapper.listcnt(user_id);
		int cntPerPage=5;
		int totalPage=(int)Math.ceil((double)totalCount/ cntPerPage);
		int cntPerPageGroup=5;
		int startPage=((int)(currentPage-1)/cntPerPageGroup)*cntPerPageGroup+1;
		int endPage=startPage+cntPerPageGroup-1;
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		page.setCurrentPage(currentPage);
		page.setTotalPage(totalPage);
		page.setList(letlist);
		page.setStartPage(startPage);
		page.setEndPage(endPage);
		
		homeview.setViewName("letter/letterhome");
		homeview.addObject("page",page);
		return homeview;
	}

	@RequestMapping(value="lettermyrcv")
	public ModelAndView lettermyrcv(Principal principal,Authentication authentication, @RequestParam(value="rcv_id")String user_id, int currentPage) {
		ModelAndView rcvletmv = new ModelAndView();
		List<SocVO> letlist = new ArrayList<SocVO>();
		PageVO page = new PageVO();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("currentPage", currentPage);
		letlist = socmapper.getrcvlet(map);
		int totalCount = socmapper.listcnt(user_id);
		int cntPerPage=5;
		int totalPage=(int)Math.ceil((double)totalCount/ cntPerPage);
		int cntPerPageGroup=5;
		int startPage=((int)(currentPage-1)/cntPerPageGroup)*cntPerPageGroup+1;
		int endPage=startPage+cntPerPageGroup-1;
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		page.setCurrentPage(currentPage);
		page.setTotalPage(totalPage);
		page.setList(letlist);
		page.setStartPage(startPage);
		page.setEndPage(endPage);
		rcvletmv.setViewName("letter/letterhome3");
		rcvletmv.addObject("page",page);
		return rcvletmv;
	}
	
	@RequestMapping(value="/lettermysend")
	public ModelAndView lettermysend(@RequestParam(value="user_id") String user_id, int currentPage) {
		ModelAndView sendletmv = new ModelAndView();
		List<SocVO> letlist = new ArrayList<SocVO>();
		PageVO page = new PageVO();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("currentPage", currentPage);
		letlist = socmapper.getsendlet(map);
		int totalCount = socmapper.listcnt2(user_id);
		int cntPerPage=5;
		int totalPage=(int)Math.ceil((double)totalCount/ cntPerPage);
		int cntPerPageGroup=5;
		int startPage=((int)(currentPage-1)/cntPerPageGroup)*cntPerPageGroup+1;
		int endPage=startPage+cntPerPageGroup-1;
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		page.setCurrentPage(currentPage);
		page.setTotalPage(totalPage);
		page.setList(letlist);
		page.setStartPage(startPage);
		page.setEndPage(endPage);
		sendletmv.setViewName("letter/letterhome2");
		sendletmv.addObject("page",page);

		return sendletmv;
	}
	
	@RequestMapping(value="/letdontread")
	public ModelAndView dontletread(@RequestParam(value="rcv_id")String user_id, int currentPage){
		System.out.println("currentPage="+currentPage);
		if(currentPage==0) {
			currentPage=1;
		}
		System.out.println("letdontread");
		ModelAndView dontreadmv = new ModelAndView();
		List<SocVO> letlist = new ArrayList<SocVO>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		PageVO page = new PageVO();
		map.put("user_id", user_id);
		map.put("currentPage", currentPage);
		letlist = socmapper.getdontread(map);
		int totalCount = socmapper.listcnt(user_id);
		int cntPerPage=5;
		int totalPage=(int)Math.ceil((double)totalCount/ cntPerPage);
		int cntPerPageGroup=5;
		int startPage=((int)(currentPage-1)/cntPerPageGroup)*cntPerPageGroup+1;
		int endPage=startPage+cntPerPageGroup-1;
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		page.setCurrentPage(currentPage);
		page.setTotalPage(totalPage);
		page.setList(letlist);
		page.setStartPage(startPage);
		page.setEndPage(endPage);
		System.out.println("page="+page);
		dontreadmv.setViewName("letter/letterhome");
		dontreadmv.addObject("page",page);
		return dontreadmv;
	}

	@RequestMapping(value = "letterwrite")
	public ModelAndView letterwrite(String letter_seq) {
		ModelAndView wrletter = new ModelAndView();
		if (letter_seq != null) {
			wrletter.addObject("letrcvid", socmapper.findrcvid(Integer.parseInt(letter_seq)));
		}
		wrletter.setViewName("letter/letterwrite");

		return wrletter;
	}
	
	@RequestMapping(value = "sendletter")
	public ModelAndView sendForm(SocVO sv) {
		ModelAndView sendletter = new ModelAndView();
		int currentPage=1;
		String[] real_rcv_id = sv.getRcv_id().split(",");
		int check=0;
		for (int i = 0; i < real_rcv_id.length; i++) {
			System.out.println(":::::"+real_rcv_id[i]);
			if(socmapper.rcvletid(real_rcv_id[i])==null) {
				System.out.println("입력값이 잘못되었습니다.");
				check=1;
			}
		}
		if(check==0){
			for (int i = 0; i < real_rcv_id.length; i++) {
				sv.setRcv_id(real_rcv_id[i]);
				socmapper.writeletter(sv);
			}
		}
		sendletter.addObject("currentPage", currentPage);
		sendletter.setViewName("redirect:letterhome");
		return sendletter;
	}
	
	@RequestMapping(value = "deleteletter")
	public ModelAndView removeForm(Principal principal,Authentication authentication,
			@RequestParam(value = "letter_seq") List<Integer> letter_seq) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String user_id = (String) userDetails.getUsername();
		ModelAndView removeletter = new ModelAndView();
		for (int i = 0; i < letter_seq.size(); i++) {
			socmapper.removeletter(letter_seq.get(i));
		}
		System.out.println("받는사람아이디"+socmapper.rcvletid(user_id));
		if(user_id.equals(socmapper.rcvletid(user_id))) {
			removeletter.setViewName("redirect:lettermysend?currentPage=1&&user_id="+user_id);
		}else {
			removeletter.setViewName("redirect:letterhome?currentPage=1");
		}
		return removeletter;
	}
	
	@RequestMapping(value = "letterresult")
	public ModelAndView resultForm(@RequestParam(value = "letter_seq") int letter_seq) {
		ModelAndView resultlet = new ModelAndView();
		SocVO letsv = new SocVO();
		letsv = socmapper.getletter(letter_seq);
		socmapper.readletter(letter_seq);
		socmapper.readdate(letter_seq);
		resultlet.addObject("letsv", letsv);
		resultlet.setViewName("letter/letterresult");
		return resultlet;
	}
}
