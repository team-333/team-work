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

import com.itbank.amazonS3.S3Utill;
import com.itbank.dao.StudyDAO;
import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.StudyVO;

@Service
public class StudyService {
	
	@Autowired
	private StudyDAO Sdao;
	
	private S3Utill s3utill;
	
	public List<StudyVO> selectAllStudies() {
		return Sdao.selectAllStudies();
	}

	public int insertStudy(MultipartHttpServletRequest mpRequest, int memberId) {
		
		//Amazon S3
		s3utill = new S3Utill();
		
		// file upload
		MultipartFile teamPicture = mpRequest.getFile("teamPicture");
		
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
		System.out.println("팀장 멤버 아이디 : " + memberId);
		
		if(teamPicture.isEmpty()) {
			vo.setTeamName(req.getParameter("teamName"));
			vo.setTeamInfo(req.getParameter("teamInfo"));
			vo.setTeamPublic(teamPublic);
			vo.setTeamPicture(req.getContextPath() + "/img/profile-picture-default.png");
			vo.setDelegate(memberId);
			
			int result = Sdao.insertStudy(vo);
			
			//memberTeam DB insert
			if(result == 1) {
				int seq = Sdao.selectSeq();
				System.out.println("팀 ID : " + seq);
				MemberTeamVO mo = new MemberTeamVO();
				mo.setMemberId(memberId);
				mo.setTeamId(seq);
				int seqResult = Sdao.memberTeamInsert(mo);
				System.out.println("memberTeam 테이블 생성완료 : " + seqResult);
			}
			
			return result;
		}
		
		
		String originalName = teamPicture.getOriginalFilename();
		String extName = originalName.substring(originalName.lastIndexOf("."));
		String storedFileName = UUID.randomUUID().toString().replace("-", "") + extName;
		
		try {

			File file = new File(storedFileName);
			teamPicture.transferTo(file);
			
			s3utill.fileUpload("yeol-gong-study-picture", "studies/" + storedFileName, file);
			
			vo.setTeamName(req.getParameter("teamName"));
			vo.setTeamInfo(req.getParameter("teamInfo"));
			vo.setTeamPublic(teamPublic);
			vo.setTeamPicture("https://yeol-gong-study-picture.s3.ap-northeast-2.amazonaws.com/studies/" +storedFileName);
			vo.setDelegate(memberId);
			int result = Sdao.insertStudy(vo);
			
			//memberTeam DB insert
			if(result == 1) {
				int seq = Sdao.selectSeq();
				System.out.println("팀 ID : " + seq);
				MemberTeamVO mo = new MemberTeamVO();
				mo.setMemberId(memberId);
				mo.setTeamId(seq);
				int seqResult = Sdao.memberTeamInsert(mo);
				System.out.println("memberTeam생성완료 : " + seqResult);
			}
			
			
			return result;
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}

	public List<StudyVO> selectMemberStudies(int memberId) {
		
		return Sdao.selectMemberStudies(memberId);
	}

	public StudyVO selectStudy(int teamId) {
		return Sdao.selectStudy(teamId);
	}

	public int joinStudy(int teamId, int memberId) {
		
		MemberTeamVO vo = new MemberTeamVO();
		
		vo.setMemberId(memberId);
		vo.setTeamId(teamId);
		
		int result = Sdao.memberTeamInsert(vo);
		
		if (result != 1) {
			System.out.println("스터디 가입에러");
			return result;
		}
		
		return result;
	}
	
	public int waitingTeam(int teamId, int memberId) {

		MemberTeamVO vo = new MemberTeamVO();

		vo.setMemberId(memberId);
		vo.setTeamId(teamId);

		return Sdao.waitingTeam(vo);
	}

	public List<StudyVO> searchStudylist(String text) {
		
		return Sdao.searchStudylist(text);
	}

	public List<Integer> selectTeamId(int memberId) {
		
		return Sdao.selectTeamId(memberId);
	}

	public List<StudyVO> searchText(String searchtext) {
	
		return Sdao.searchText(searchtext);
		
	}

}
