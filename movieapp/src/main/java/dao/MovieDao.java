package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mapper.MovieMapper;
import vo.MovieVO;

@Repository
public class MovieDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, Object> param = new HashMap<String, Object>();
	
	public int moviecount(String searchtype, String searchcontent) {
		return template.getMapper(MovieMapper.class).moviecount(searchtype,searchcontent);
	}
	public List<MovieVO> movieSearch(Integer pageNum, int limit, String searchtype, String searchcontent) {
		param.clear();
		int startrow = (pageNum - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		param.put("startrow", startrow);
		param.put("endrow", endrow);
		if(searchtype != null && searchcontent != null) {
			param.put("searchtype", searchtype);
			param.put("searchcontent", searchcontent);
		}

		return template.getMapper(MovieMapper.class).list(param);
	}

	public List<MovieVO> movieSearchByDirector(String director) {
		return template.getMapper(MovieMapper.class).listByDirector(director);
	}
	
	public MovieVO selectOne(Integer num) {
		return template.getMapper(MovieMapper.class).selectOne(num);
	}
	public void insertMovie(MovieVO movie) {
		template.getMapper(MovieMapper.class).insert(movie);
	}
	

}
