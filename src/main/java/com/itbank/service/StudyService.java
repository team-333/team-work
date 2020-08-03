package com.itbank.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.itbank.dao.StudyDAO;
import com.itbank.vo.StudyVO;

@Service
public class StudyService {
	
	@Autowired
	private StudyDAO Sdao;
	
	public List<StudyVO> selectAllStudies() {
		return Sdao.selectAllStudies();
	}

	public int insertStudy(MultipartHttpServletRequest mpRequest) {
		
		// file upload
		String filePath = mpRequest.getSession().getServletContext().getRealPath("\\resources\\study_img\\");
		System.out.println(filePath);
		
		MultipartFile teamPicture = mpRequest.getFile("teamPicture");
		System.out.println((teamPicture));
		StudyVO vo = new StudyVO();
		HttpServletRequest req = (HttpServletRequest) mpRequest;
		
		String preTeamPublic = req.getParameter("teamPublic");
		int teamPublic = 0;
		if (preTeamPublic == "" || preTeamPublic == null) {
			teamPublic = 0;
		}
		else {
			teamPublic = 1;
		}
		
		System.out.println("팀 이름 : " + req.getParameter("teamName"));
		System.out.println("팀 소개 : " + req.getParameter("teamInfo"));
		System.out.println("공개 여부 : " + teamPublic);
		
		if(teamPicture.isEmpty()) {
			vo.setTeamName(req.getParameter("teamName"));
			vo.setTeamInfo(req.getParameter("teamInfo"));
			vo.setTeamPublic(teamPublic);
			vo.setTeamPicture(req.getContextPath() + "/img/profile-picture-default.png");
			
			int result = Sdao.insertStudy(vo);
			
			return result;
		}
		
		
		String originalName = teamPicture.getOriginalFilename();
		String extName = originalName.substring(originalName.lastIndexOf("."));
		String storedFileName = UUID.randomUUID().toString().replace("-", "") + extName;
		
		try {

			File file = new File(filePath + "\\" + storedFileName);
			teamPicture.transferTo(file);
			
			vo.setTeamName(req.getParameter("teamName"));
			vo.setTeamInfo(req.getParameter("teamInfo"));
			vo.setTeamPublic(teamPublic);
			vo.setTeamPicture(req.getContextPath() + "/study_img/" +storedFileName);
			
			int result = Sdao.insertStudy(vo);
			
			return result;
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
}
