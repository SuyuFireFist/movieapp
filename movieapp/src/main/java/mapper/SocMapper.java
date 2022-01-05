package mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import vo.SocVO;

public interface SocMapper {

	@Select("select * from (SELECT rownum r,l.* FROM (SELECT rownum, a.* FROM letter a WHERE rcv_id=#{user_id} order by rownum desc)l) WHERE r between #{currentPage}*5-4 and #{currentPage}*5")
	List<SocVO> findletter(HashMap<String, Object> map);

	@Select("select count(*) totalcnt from letter where rcv_id =#{user_id}")
	int listcnt(String user_id);

	@Select("select * from (SELECT rownum r,l.* FROM (SELECT rownum, a.* FROM letter a WHERE rcv_id = #{user_id} order by rownum desc)l) WHERE r between #{currentPage}*5-4 and #{currentPage}*5")
	List<SocVO> getrcvlet(HashMap<String, Object> map);

	@Select("select * from (SELECT rownum r,l.* FROM (SELECT rownum, a.* FROM letter a WHERE user_id = #{user_id} order by rownum desc)l ) WHERE r between #{currentPage}*5-4 and #{currentPage}*5")
	List<SocVO> getsendlet(HashMap<String, Object> map);

	@Select("select count(*) totalcnt from letter where user_id=#{user_id}")
	int listcnt2(String user_id);

	@Select("select * from (SELECT rownum r,l.* FROM (SELECT rownum, a.* FROM letter a WHERE rcv_id = #{user_id} AND readyn='N' order by rownum desc)l ) WHERE r between #{currentPage}*5-4 and #{currentPage}*5")
	List<SocVO> getdontread(HashMap<String, Object> map);

	@Select("SELECT user_id FROM letter WHERE letter_seq = #{letter_seq}")
	Object findrcvid(int parseInt);

	@Select("select id from member where id = #{id}")
	Object rcvletid(String string);

	@Insert("INSERT INTO letter (letter_seq, user_id, title, content, rcv_id, write_date, read_date) VALUES (let_seq.nextval, #{user_id}, #{title}, #{content}, #{rcv_id}, sysdate, sysdate)")
	void writeletter(SocVO sv);

	@Delete("DELETE FROM letter WHERE letter_seq = #{letter_seq}")
	void removeletter(Integer integer);
	
	@Select("SELECT letter_seq,user_id,title,content,rcv_id, write_date, sysdate,readyn,sent_del,rcv_del FROM letter where letter_seq = #{letter_seq}")
	SocVO getletter(int letter_seq);

	@Update("UPDATE letter set readyn = 'Y' where letter_seq = #{letter_seq}")
	void readletter(int letter_seq);

	@Update("UPDATE letter set read_date = sysdate where letter_seq = #{letter_seq}")
	void readdate(int letter_seq);

	

}
