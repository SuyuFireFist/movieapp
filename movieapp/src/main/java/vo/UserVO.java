package vo;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserVO {
	
	@NotEmpty(message="아이디를 입력해주세요")
	String id;
	
	@NotEmpty(message="비밀번호를 입력해주세요")
	String pwd;
	
	@NotEmpty(message="이름을 입력해주세요")
	String name;
	
	String gender;
	
	@NotEmpty(message="이메일을 입력해주세요")
	String email;
	
	@NotEmpty(message="전화번호를 입력해주세요")
	String tel;
}
