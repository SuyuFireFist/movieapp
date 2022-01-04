package controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j;
import mapper.UserMapper;
import vo.FriendVO;
import vo.UserVO;

@Controller
@Log4j
public class UsersController {
	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "users/login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		UserVO user = new UserVO();
		model.addAttribute("user", user);
		return "users/signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute("user") UserVO user
    		,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "/users/signup";
		}
		
        userMapper.insertUser(user);
        userMapper.insertAuthority(user.getId(), "ROLE_USER");
        log.info("유저 생성됨 => "+user);
        return "redirect:/users/login";
    }
	
	@RequestMapping(value ="/idCheck")
	public String idCheck(Model model,@RequestParam(value = "id") String id) {
		String result="";
		UserVO user = userMapper.idCheck(id);
		if( user == null) {
			result = "사용가능한 아이디입니다.";
		}else {
			result="이미 사용중인 아이디입니다.";
		}
		
		model.addAttribute("result",result);
		return "/users/idCheck";
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(Model model,Principal principal,Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		model.addAttribute("id", userDetails.getUsername());
//		System.out.println(userDetails.toString());
		
		UserVO user = userMapper.getUser(userDetails.getUsername());
		model.addAttribute("user",user);
//		System.out.println(user.toString());
		
		return "users/useredit";
	}

	@RequestMapping(value="/mypage/update", method=RequestMethod.POST)
	public String update(@ModelAttribute UserVO user) {
		log.info("유저정보 수정 : "+user.getId());
		userMapper.update(user);
		return "redirect:/mypage";
	}
	
	//친구목록
	@RequestMapping(value="/friend", method=RequestMethod.GET)
	public String friend(Model model,Principal principal,Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String id = (String) userDetails.getUsername();
		
		List<FriendVO> friend =  userMapper.friendlist(id);
		List<String> list = new ArrayList<String>();
		for(int i = 0; friend.size() > i; i++) {
			if(friend.get(i).getId().equals(id)) {
				list.add(friend.get(i).getFr_id());
			}else if(friend.get(i).getFr_id().equals(id)){
				list.add(friend.get(i).getId());
			}
		}
		
		List<String> frlist = userMapper.friendreslist(id);
		System.out.println(frlist);
		
		model.addAttribute("friend",list);
		model.addAttribute("frlist", frlist);
		log.info(id+"의 친구목록 : "+ list);
		log.info(frlist);

		return "users/friend";
	}
	
	@RequestMapping(value="/friendreqsearch", method=RequestMethod.POST)
	public String searchid(Model model,Principal principal,Authentication authentication,@RequestParam(value = "fr_id") String fr_id, FriendVO f) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String id = (String) userDetails.getUsername();
		
		List<String> freqlist = userMapper.friendreqlist(id);
		model.addAttribute("freqlist",freqlist);
		
		f.setId(id);
		f.setFr_id(fr_id);
		String result="";
		if(userMapper.getMember(fr_id)==null) {
			result = "존재하지 않는 아이디입니다.";
		}else {
			result = "전송 성공";
			userMapper.reqfriend(f);
		}
		model.addAttribute("result",result);
		return "users/friendreq";
	}
	
	
	@RequestMapping(value="/friendreq")
	public String friendreq(Model model,Principal principal,Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String id = (String) userDetails.getUsername();
		
		List<String> freqlist = userMapper.friendreqlist(id);
		model.addAttribute("freqlist",freqlist);
		
		return "users/friendreq";
	}
	
	@RequestMapping(value="/frienddel")
	public String frienddel(Model model,Principal principal,Authentication authentication,@RequestParam(value = "friend") String friend, FriendVO f) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String id = (String) userDetails.getUsername();
		f.setId(id);
		f.setFr_id(friend);
		userMapper.frienddel(f);
		f.setId(friend);
		f.setFr_id(id);
		userMapper.frienddel(f);
		
		return "/users/friend";
	}
	
	@RequestMapping(value="/friendacc")
	public String friendacc(Model model,Principal principal,Authentication authentication,@RequestParam(value = "reqfriend") String reqfriend, FriendVO f) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String id = (String) userDetails.getUsername();
		f.setId(reqfriend);
		f.setFr_id(id);
		userMapper.friendacc(f);
		return "/users/friend";
	}
	
	
}
