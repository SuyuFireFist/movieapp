package controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.GetBoxService;
import service.NmovieService;
import service.MovieService;
import vo.GetBoxVO;
import vo.MovieVO;
import vo.NmovieVO;
import vo.ReviewVO;

@Controller
@RequestMapping("movie")
public class MovieController {
	@Autowired
	private GetBoxService gservice;
	@Autowired
	private NmovieService nservice;
	@Autowired
	MovieService mservice;
	
	@RequestMapping(value = "/main")
	public ModelAndView main () {
		ModelAndView mav = new ModelAndView();
		List<GetBoxVO> blist = gservice.getBoxOffice();
		List<NmovieVO> nlist = new ArrayList<NmovieVO>();
		
		for (GetBoxVO bm : blist) {
			NmovieVO n = nservice.getMovieDetail(bm.getMovieNm(), 0);

			nlist.add(n);
		}
		
		mav.addObject("blist", blist);
		mav.addObject("nlist", nlist);
		mav.setViewName("movie/main");
		return mav;
	}
	
	@RequestMapping("list")
	public ModelAndView list(Integer pageNum,String searchtype,String searchcontent) {
		ModelAndView mav = new ModelAndView();
		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		if(searchtype == null || searchcontent == null
				|| searchtype.trim().equals("") || searchcontent.trim().equals("")) {
			searchtype = null;
			searchcontent=null;
		}
		
		int limit = 5;// 한페이지에 보여줄 게시물의 수
		int movieListCount = mservice.moviecount(searchtype,searchcontent);
		List<MovieVO> searchedMovieList = mservice.movieSearch(pageNum, limit,searchtype,searchcontent);
		// 최대 필요한 페이지 수
		int maxpage = (int) ((double) movieListCount / limit + 0.95);
		// 화면에 표시할 페이지의 시작번호
		int startpage = (int) ((pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		// 화면에 표시할 페이지의 끝 번호
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int movieListno = movieListCount - (pageNum - 1) * limit;
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("movieListCount", movieListCount);
		mav.addObject("searchedMovieList", searchedMovieList);
		mav.addObject("movieListno", movieListno);
		return mav;
	}
	
	@RequestMapping("detail") // 영화 상세보기
	public ModelAndView detail(Integer num, Integer pageNum,Principal principal,Authentication authentication) {
		ModelAndView mav = new ModelAndView();
		
		
		//영화상세정보 불러오기
		if (num != null) {
			MovieVO movie = mservice.getMovie(num);
			mav.addObject("movie", movie);
			//같은 감독의 다른 영화 불러오기
			List<MovieVO> sameDirectorMovieList = mservice.movieSearchByDirector(movie.getDirector());
			mav.addObject("sameDirectorMovieList",sameDirectorMovieList);
		} else
			return new ModelAndView("redirect:/movie/list");

		//리뷰 상세정보 불러오기
		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}
		int limit = 5;// 한번에 보여줄 리뷰의 수
		
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String id = (String) userDetails.getUsername();
		
		int reviewListCount = mservice.reviewcount(num);
		List<ReviewVO> reviewList = mservice.reviewSearch(num,pageNum,limit);

		// 리뷰 페이징 처리
		int maxpage = (int) ((double) reviewListCount / limit + 0.95);
		int startpage = (int) ((pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int reviewListno = reviewListCount - (pageNum - 1) * limit;
		
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("reviewListCount", reviewListCount);
		mav.addObject("reviewList", reviewList);
		mav.addObject("reviewListno", reviewListno);
		
		return mav;
	}
	
	@RequestMapping("reviewWrite")
	public String idCheckReviewWrite(Integer userrating,String review,Integer movie_seq,Principal principal,Authentication authentication) {
		if(review!=null) {
			ReviewVO insert_review = new ReviewVO();
			
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String user_id = (String) userDetails.getUsername();
			
			insert_review.setUser_id(user_id);
			insert_review.setSeq(mservice.totalReviewCount()+1);
			insert_review.setMovieseq(movie_seq);
			insert_review.setReview(review);
			insert_review.setUserrating(userrating);
			
			mservice.reviewWrite(insert_review);
		}
		
		return "redirect:detail?num="+movie_seq;
	}
	
	@RequestMapping("reviewDelete")
	public String idCheckReviewDelete(Integer seq,Integer movie_seq) {
		mservice.reviewDelete(seq);
		
		return "redirect:detail?num="+movie_seq;
	}
	
	@GetMapping("update")
	public String movieUpdate() {
		return "movie/addMovie";
	}
	
	@PostMapping("update")
	public String addMovie(@ModelAttribute MovieVO movie) {
		mservice.insertMovie(movie);
		return "";
	}
	
	@RequestMapping("*")
	public String test() {
		return "redirect:list";
	}
}
