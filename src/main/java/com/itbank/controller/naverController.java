package com.itbank.controller;

import java.io.IOException;

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

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.itbank.naver.NaverLoginBO;

@Controller
public class naverController {
	
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
	System.out.println("�뿬湲곕뒗 callback");
	OAuth2AccessToken oauthToken;
	oauthToken = naverLoginBO.getAccessToken(session, code, state);
	apiResult = naverLoginBO.getUserProfile(oauthToken); //String�삎�떇�쓽 json�뜲�씠�꽣
	/** apiResult json 援ъ“
	{"resultcode":"00",
	"message":"success",
	"response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
	**/

	JSONParser parser = new JSONParser();
	Object obj = parser.parse(apiResult);
	JSONObject jsonObj = (JSONObject) obj;

	JSONObject response_obj = (JSONObject)jsonObj.get("response");

	String nickname = (String)response_obj.get("email");
	System.out.println("nickname" +nickname);

	session.setAttribute("sessionId",nickname); 
	model.addAttribute("result", apiResult);
	return "parent";
	}


}
