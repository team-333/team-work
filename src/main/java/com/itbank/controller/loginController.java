package com.itbank.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.itbank.mail.MailUtil;
import com.itbank.naver.NaverLoginBO;
import com.itbank.service.MembersService;
import com.itbank.vo.MembersVO;


@Controller
public class loginController {

	@Autowired MembersService ms;
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
	this.naverLoginBO = naverLoginBO;
	}
	
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
	String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
	model.addAttribute("url", naverAuthUrl);
	return "home";
	}

	

	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
	System.out.println("여기는 callback");
	OAuth2AccessToken oauthToken;
	oauthToken = naverLoginBO.getAccessToken(session, code, state);
	apiResult = naverLoginBO.getUserProfile(oauthToken); //String형식의 json데이터
	/** apiResult json 구조
	{"resultcode":"00",
	"message":"success",
	"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
	**/
	//2. String형식인 apiResult를 json형태로 바꿈
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(apiResult);
	JSONObject jsonObj = (JSONObject) obj;
	//3. 데이터 파싱
	//Top레벨 단계 _response 파싱
	JSONObject response_obj = (JSONObject)jsonObj.get("response");
	//response의 nickname값 파싱
	String nickname = (String)response_obj.get("email");
	System.out.println("nickname" +nickname);

	session.setAttribute("sessionId",nickname); //세션 생성
	model.addAttribute("result", apiResult);
	return "main";
	}

	@RequestMapping(value="resultpage/")
	public void resultpage() {}
	
	@RequestMapping(value="searchpw/",method=RequestMethod.POST)
	public ModelAndView searchpw(MembersVO vo) throws Exception{
		ModelAndView mv = new ModelAndView("resultpage");
		System.out.println(ms.emailcheck(vo.getEmail()));
		if(ms.emailcheck(vo.getEmail())) {
			
			
			MailUtil mu= new MailUtil();
			String newp=MailUtil.createKey();
			vo.setPassword(newp);
			ms.updatepw(vo);
			
			String subject = "열공팀에서 보낸 임시비밀번호";
			String msg="";
			msg+="<div align='center' style='border:1px solid black; font-family : verdana'>";
			
			msg+="<h3 style ='color : blue;'>임시비밀번호입니다</h3>";
			msg+="<h2><strong>";
			msg+=newp;
			msg+="</strong></h2>";
			msg+="</div>";
			mu.sendMail(vo.getEmail(), subject, msg);
			mv.addObject("msg","이메일 발송 완료");
			mv.addObject("url","");
			return mv;
		}
		mv.addObject("msg","일치하는 이메일이 없습니다.");
		mv.addObject("url","searchpw/");
		return mv;
	}
	
	
	@RequestMapping(value="searchpw/",method=RequestMethod.GET)
	public void searchpw() {};
	
	
	@RequestMapping(value="checkemail/", method=RequestMethod.GET,produces="application/text;charset=utf8")
	@ResponseBody 	
	public String checkemail(HttpServletRequest request) {
		try {
			String email = request.getParameter("email");
			boolean exist = ms.emailcheck(email);
			System.out.println(exist);
			return exist ? "사용중" : "생성가능";
			
		} catch (Exception e) {	
			return "통신 실패 ";
		}
	}
	
	
	
	@RequestMapping(value="join/",method=RequestMethod.POST)
	public ModelAndView join(MembersVO vo) {
		ModelAndView mv = new ModelAndView("joinresult");
		
		MembersVO vo2 = ms.insertMembers(vo);
		
		if(vo2 == null) {
			mv.setViewName("signup");
		}
		return mv;
	}
	
	
	
	@RequestMapping(value="login/",method=RequestMethod.POST)
	public ModelAndView login(HttpSession session, MembersVO vo) {
		ModelAndView mv = new ModelAndView("redirect:/main/");
		MembersVO check = ms.selectMembers(vo);
		System.out.println("email :" +vo.getEmail());
		if(check != null) {
			session.setAttribute("ls", check);
			System.out.println("로그인성공");
			return mv;
		}
		mv.setViewName("redirect:/");
		return mv;
	}
	
	
	@RequestMapping(value="logout/")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/");
		System.out.println("로그아웃접속");
		session.invalidate();
		return mv;
		
			
	}
	
	
	
}
