package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import vo.FriendVO;
import vo.UserVO;

public interface UserMapper {
	
	@Insert("insert into member (id,pwd,name,gender,email,tel,enabled) values (#{id},#{pwd},#{name},#{gender},#{email},#{tel},1)")
	public boolean insertUser(UserVO user);
	
	@Insert("insert into authorities (id, authority) VALUES (#{id}, #{authority})")
	public boolean insertAuthority(@Param("id") String id, @Param("authority") String authority);
	
	@Select("select * from member where id=#{id}")
	public UserVO getUser(String id);

	@Update("update member set pwd=#{pwd}, name=#{name}, gender=#{gender}, email=#{email}, tel=#{tel} where id=#{id}")
	public void update(UserVO user);

	@Select("select distinct * from friend where type = 1 and (id = #{id} or fr_id = #{id})")
	public List<FriendVO> friendlist(String id);
	
	@Select("select id from friend where fr_id = #{id} and type=2")
	public List<String> friendreslist(String id);

	@Select("select fr_id from friend where type = 2 and id = #{id}")
	public List<String> friendreqlist(String id);

	@Select("select * from member where id = #{id}")
	public Object getMember(String fr_id);

	@Insert("insert into friend values (#{id},#{fr_id},2)")
	public void reqfriend(FriendVO f);

	@Delete("delete friend where id = #{id} and fr_id = #{fr_id}")
	public void frienddel(FriendVO f);

	@Update("update friend set type = 1 where id = #{id} and fr_id = #{fr_id}")
	public void friendacc(FriendVO f);

	@Select("select * from member where id = #{id}")
	public UserVO idCheck(String id);
	
}
