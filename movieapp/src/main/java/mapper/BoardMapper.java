package mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import vo.BoardVO;

public interface BoardMapper {

	@Insert("insert into photo_board"
			+ "(seq,user_id,p_title,p_file_path,p_content,p_log_time,p_hit,p_cinema"
			+ ",p_wdate,p_region,p_movie)"
			+ " values(#{seq}, #{user_id},#{p_title}, #{p_file_path},#{p_content},"
			+ " sysdate, 0, #{p_cinema},#{p_wdate}, #{p_region},#{p_movie})")
	void insert(BoardVO board);

	@Select("select nvl(max(seq), 0) from photo_board")
	int maxseq();

	@Select({"select count(*) from photo_board"})
	int count();

	
	@Select({"select * from "
    + "(select rownum rnum,seq,user_id,p_title,p_file_path,p_content,"
    + "p_log_time,p_hit,p_cinema,p_wdate,p_region,p_movie from "
    + "(select * from photo_board) order by seq desc)"
    + " where rnum >= #{startrow} and rnum <= #{endrow}"})
	List<BoardVO> list(Map<String, Object> param);
	
	@Select({"select seq,user_id,p_title,p_file_path,p_content,p_log_time,p_hit,p_cinema, "
			+ " p_wdate,p_region,p_movie from photo_board where seq=#{value}"})
	BoardVO detail(Integer num);
	
	@Update({"update photo_board set p_hit = p_hit +1 where seq=#{value}"})
	void hitadd(Integer num);

	@Delete({"delete from photo_board where seq=#{value}"})
	void delete(int num);

	@Update("update photo_board set p_region=#{p_region}, p_cinema=#{p_cinema}, p_movie=#{p_movie},"
			+	"p_wdate=#{p_wdate}, p_title=#{p_title}, p_content=#{p_content} where seq=#{seq}")
	void update(BoardVO board);
}
