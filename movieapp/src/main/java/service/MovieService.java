package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import dao.MovieDao;
import dao.ReviewDao;
import vo.MovieVO;
import vo.ReviewVO;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service // 설정에 의한 객체화 + 기능(서비스기능:컨트롤러와 모델의 중간역할). @Component로 대체 가능.
public class MovieService {
	private static String clientID = "Y95vtmMJWmmcnYykJRNb";
	private static String clientSecret = "ZvnTUnvDYW";
	URL url;
	URLConnection urlConn;
	XmlPullParserFactory factory;
	XmlPullParser parser;

	@Autowired
	MovieDao movieDao;
	
	@Autowired
	ReviewDao reviewDao;

	public int moviecount(String searchtype, String searchcontent) {
		return movieDao.moviecount(searchtype,searchcontent);
	}

	public List<MovieVO> movieSearch(Integer pageNum, int limit, String searchtype, String searchcontent) {
		return movieDao.movieSearch(pageNum,limit,searchtype,searchcontent);
	}

	public MovieVO getMovie(Integer num) {
		return movieDao.selectOne(num);
	}

	public List<MovieVO> movieSearchByDirector(String director) {
		return movieDao.movieSearchByDirector(director);
	}

	public List<ReviewVO> reviewSearch(Integer num, Integer reviewPageNum, int limit) {
		return reviewDao.reviewSearch(num,reviewPageNum,limit);
	}

	public int reviewcount(Integer num) {
		return reviewDao.reviewCount(num);
	}
	
	public int totalReviewCount() {
		return reviewDao.totalReviewCount();
	}
	
	public void reviewWrite(ReviewVO review) {
		reviewDao.reviewWrite(review);
	}

	public void reviewDelete(Integer i) {
		reviewDao.reviewDelete(i);
	}
	
	// 네이버 영화 API 용 connection
	public void con(String keyword, int yearfrom, int yearto, int display, int start) {
		try {
			url = new URL("https://openapi.naver.com/v1/search/movie.xml?query=" + URLEncoder.encode(keyword, "UTF-8")
					+ (yearfrom != 0 ? "&yearfrom=" + yearfrom : "") + (yearto != 0 ? "&yearto=" + yearto : "")
					+ (display != 0 ? "&display=" + display : "") + (start != 0 ? "&start=" + start : ""));
			urlConn = url.openConnection();
			urlConn.setRequestProperty("X-Naver-Client-Id", clientID);
			urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			factory = XmlPullParserFactory.newInstance();
			parser = factory.newPullParser();
			parser.setInput(new InputStreamReader(urlConn.getInputStream()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertMovie(MovieVO movie) {
		movieDao.insertMovie(movie);
	}
	
}
