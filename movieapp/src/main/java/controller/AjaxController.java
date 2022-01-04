package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.BoardService;


//@RestController : View 없이 controller에서 직접 데이터를 클라이언트로 전송 기능
//							 spring4 버전 이후에서 가능함
//@ResponseBody :   View 없이 controller에서 직접 데이터를 클라이언트로 전송 기능
//@Controller : 클라이언트의 요청 + 객체화
@RestController
@RequestMapping("ajax")
public class AjaxController {
	@Autowired //객체 주입. ShopService객체를 저장.  
	BoardService service;
	/*
	 * produces="text/html; charset=UTF-8
	 * 	클라이언트에 전송될 데이터의종류와 인코딩 방식을 설정
	 * 
	 * 미국,일본,중국,유로 4개의 통화코드만 브라우저로 전달하기
	 * */
	@RequestMapping("select")
	public List<String> select(String sido, String sigu , String com, HttpServletRequest request){
		BufferedReader fr = null;
		try {
			fr = new BufferedReader(
					new FileReader(request.getServletContext().getRealPath("/")+"WEB-INF/views/board/cinema.txt"));		
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		//fr.lines() : 파일의 내용을 Stream 객체로 리턴
		//					한줄씩 Stream으로 전달
		//flatMap : "서울시 강남구 역삼동"의 내용 중 서울시 컬럼의 내용들만 데이터로 변경
		List<String> list = null;
		//list:파일에 존재하는 시도 정보를 한개씩만 List로 저장함
		if(sido == null && sigu ==null && com==null)// 파라미터값이 없는경우, 초기값으로 시도 정보추출 기능
			list = fr.lines().flatMap(s->{
				// arr=> {"강원도","속초시","메가박스","속초"} => 배열 형태
				// arr[0] : 시도 정보
				// arr[1] : 시구군 정보
				// arr[2] : 업체 정보
				// arr[3] : 영화관 정보
				String[] arr=s.split("\\s+"); // 시군구 문자들을 배열로 저장
				List<String> data = new ArrayList<String>();
				if(arr.length>=4) // 시도 구군 동리 정보가 모두 존재하는 데이터
					data.add(arr[0].trim()); // 시도 null이고 gu도 null이므로 시 정보만 넣음
			return data.stream();
			}).distinct().collect(Collectors.toList());
		else if (sigu ==null && com==null) { // sido 가선택된 경우 , si 파라미ㅓ값 : 서울특별시
			list = fr.lines().flatMap(s->{
				String[] arr=s.split("\\s+");
				List<String> data = new ArrayList<String>();
				//arr배열의 요소갯수가 3개이상이고 첫번쨰 요소가 si파라미터 값과 같고,
				//arr요소의 첫번쨰, 두번째 데이터가 다른값인 경우
				if(arr.length>=4 && arr[0].equals(sido) && !arr[0].equals(arr[1])) {
			data.add(arr[1].trim());//구군 정보만 data에 추가
			}
			return data.stream();
		}).distinct().sorted().collect(Collectors.toList());
		}
		else if (com == null) { // si 가선택된 경우 , si 파라미ㅓ값 : 서울특별시
			list = fr.lines().flatMap(s->{
				String[] arr=s.split("\\s+");
				List<String> data = new ArrayList<String>();
				//arr배열의 요소갯수가 3개이상이고 첫번쨰 요소가 si파라미터 값과 같고,
				//arr요소의 첫번쨰, 두번째 데이터가 다른값인 경우
				if(arr.length>=4 && arr[0].equals(sido) &&
						arr[1].equals(sigu) && !arr[1].equals(arr[2])) {
			data.add(arr[2].trim());//구군 정보만 data에 추가
			}
			return data.stream();
		}).distinct().sorted().collect(Collectors.toList());
	}else { //si 파라미터ㅏㅄ과 gu파라미터값이 모두있는경우 -> 동정보 결과로 저장
		list = fr.lines().flatMap(s->{
			String[] arr=s.split("\\s+");
			List<String> data = new ArrayList<String>();
			//arr배열의 요소갯수가 3개이상이고 첫번쨰 요소,두번째요소 가 파라미터 값과 같고,
			//arr요소의 두번쨰, 세번째 데이터가 다른값인 경우
			if(arr.length>=4 && arr[0].trim().equals(sido)&&
					arr[1].trim().equals(sigu) && arr[2].trim().equals(com)
					&& !arr[2].equals(arr[3])) {
				if(arr.length>4) arr[3] +=" "+arr[4];
				data.add(arr[3].trim());
			}
			return data.stream();
	}).distinct().sorted().collect(Collectors.toList());
}
		return list;
	}

}

