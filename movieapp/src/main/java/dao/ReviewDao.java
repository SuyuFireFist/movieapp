package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mapper.ReviewMapper;
import vo.ReviewVO;

@Repository
public class ReviewDao {
	@Autowired
	private SqlSessionTemplate template;
	private Map<String, Object> param = new HashMap<String, Object>();

	public List<ReviewVO> reviewSearch(Integer num, Integer reviewPageNum, int limit) {
		param.clear();
		int startrow = (reviewPageNum - 1) * limit + 1;
		int endrow = startrow + limit - 1;
		param.put("startrow", startrow);
		param.put("endrow", endrow);
		param.put("num", num);

		return template.getMapper(ReviewMapper.class).list(param);
	}

	public int reviewCount(Integer num) {
		return template.getMapper(ReviewMapper.class).reviewCount(num);
	}
	
	public int totalReviewCount() {
		return template.getMapper(ReviewMapper.class).totalReviewCount();
	}

	public void reviewWrite(ReviewVO review) {
		template.getMapper(ReviewMapper.class).insert(review);
	}

	public void reviewDelete(Integer i) {
		template.getMapper(ReviewMapper.class).delete(i);
	}


}
