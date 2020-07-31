package com.itbank.mail;

import java.util.Random;

import org.apache.commons.mail.HtmlEmail;



public class MailUtil {

	
	
	public void sendMail(String email,String subject,String msg) throws Exception{
	// mail server 
	String charSet ="utf-8";
	String hostSMTP = "smtp.naver.com";
	String hostSMTPid ="cosp0412";
	String hostSMTPpwd="po6568tygh!";
	
	// 보내는 사람
	String fromEmail = "cosp0412@naver.com";
	String fromName= "열공 TEAM";
	
	// email 전송
	
	try {
		HtmlEmail mail = new HtmlEmail();
		mail.setDebug(true);
		mail.setCharset(charSet);
		mail.setSSLOnConnect(true);	//ssl사용
		
		mail.setHostName(hostSMTP);
		mail.setSmtpPort(465); //smtp 포트번호
		
		mail.setAuthentication(hostSMTPid, hostSMTPpwd);
		mail.setStartTLSEnabled(false);
		mail.addTo(email);
		mail.setFrom(fromEmail,fromName,charSet);
		mail.setSubject(subject);
		mail.setHtmlMsg(msg);
		mail.send();
		System.out.println("메일발송완료");
	}catch(Exception e) {
		e.printStackTrace();
	}
	

	}
	//키 생성코드
	public static String createKey() throws Exception{
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();
		
		for(int i =0;i<10;i++) {
			int index = rnd.nextInt(3);
			
			switch(index) {
			case 0:
				key.append((char) ((int) (rnd.nextInt(26))+97));
				break;			
			case 1:
				key.append((char) ((int) (rnd.nextInt(26))+65));
				break;
			case 2:
				key.append((rnd.nextInt(10)));
				break;
			}
		}
		return key.toString();
	}
	
	
	
	
}

