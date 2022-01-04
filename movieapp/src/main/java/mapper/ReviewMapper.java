package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import vo.ReviewVO;

public interface ReviewMapper {

	@Insert("insert into review "
			+ "(seq,user_id,review,userrating,movieseq)"
			+ " values (#{seq},#{user_id},#{review},#{userrating},#{movieseq})")
	public void insert(ReviewVO review);

	@Select("select nvl(max(seq),0) from review")
	public int totalReviewCount();
	
	@Select({"<script>",
			"select * from "
			+ "(select rownum rnum , seq, user_id, review, userrating, movieseq from review"
			+ " where movieseq = '${num}' order by seq desc)"
			+ " where rnum >= ${startrow} and rnum &lt;= ${endrow}",
			"</script>"})
	public List<ReviewVO> list(Map<String, Object> param);

	@Update("update review set name=#{name}, subject=#{subject}, content=#{content},"
			+ "file1=#{fileurl} where num=#{num}")
	public void update(ReviewVO review);
	
	@Delete("delete from review where seq=#{value}")
	public void delete(int num);

	@Select({"<script>",
			"select count(*) from review"
			+ " where movieseq = '${value}'",
			"</script>"})
	public int reviewCount(int num);
}
