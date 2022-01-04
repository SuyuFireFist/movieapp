package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import vo.MovieVO;

public interface MovieMapper {

	@Insert("insert into movie (num,title,subtitle,image,pubdate,actor,userrating) values (#{num},#{title},#{subtitle},#{image},#{pubdate},#{actor},#{userrating})")
	public void insert(MovieVO movie);

	@Select("select nvl(max(num),0) from movie")
	public int maxNum();
	
	@Select({"<script>",
			"select count(*) from movie",
			"<if test='searchtype != null and searchcontent != null'>"
			+ " where ${searchtype} like '%${searchcontent}%'</if>",
			"</script>"})
	public int moviecount(@Param("searchtype") String searchtype,@Param("searchcontent") String searchcontent);

	@Select({"<script>",
			"select * from "
			+ "(select rownum rnum , num, title, image, pubdate, director, userrating from"
			+ "(select * from movie "
			+ "<if test='searchtype != null and searchcontent != null'> "
			+ " where ${searchtype} like '%${searchcontent}%'</if>"
			+ "order by num))"
			+ " where rnum >= #{startrow} and rnum &lt;= #{endrow}",
			"</script>"})
	public List<MovieVO> list(Map<String, Object> param);

	@Select({"<script>",
		"select * from"
		+ " (select rownum rnum , num, title, image, pubdate, director,userrating from movie"
		+ " where director='${value}'"
		+ " order by pubdate desc)"
		+ " where rnum &lt;= 5",
		"</script>"})
	public List<MovieVO> listByDirector(String director);
	
	@Select("select * from movie"
			+ " where num=#{value}")
	public MovieVO selectOne(Integer num);

	@Update("update movie set name=#{name}, subject=#{subject}, content=#{content},"
			+ "file1=#{fileurl} where num=#{num}")
	public void update(MovieVO movie);
	
	@Delete("delete from movie where num=#{value}")
	public void delete(int num);
	
	@Update("update movie set grpstep=grpstep+1 "
			+ "where grp=#{grp} and grpstep > #{grpstep}")
	public void updateGrpStep(MovieVO movie);

}
