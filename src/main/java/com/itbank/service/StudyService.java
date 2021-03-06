package com.itbank.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.itbank.amazonS3.S3Utill;
import com.itbank.dao.StudyDAO;
import com.itbank.vo.MemberTeamVO;
import com.itbank.vo.StudyVO;
import com.itbank.vo.TagVO;
import com.itbank.vo.TeamTagVO;

@Service
public class StudyService {
	
	@Autowired
	private StudyDAO Sdao;
	
	private S3Utill s3utill;
	
	public List<StudyVO> selectAllStudies() {
		
		List<StudyVO> studyList = new ArrayList<StudyVO>();
		
		
		for (StudyVO vo : Sdao.selectAllStudies()) {
			StudyVO svo = new StudyVO();
			List<TagVO> tagList = Sdao.selectStudyTag(vo.getTeamId());

			svo = vo;
			svo.setTagList(tagList);
			studyList.add(svo);
			
		}
		
		
		return studyList;
	}
	
	@Transactional(timeout = 5)
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
			
			// 태그를 먼저 넣고, 태그 아이디를 받아와서 team_tag에 넣기
			String[] tagList = mpRequest.getParameterValues("hashTag");
			if (tagList != null) {
				for(int i = 0; i < tagList.length; i++) {
					
					TagVO tvo = Sdao.selectTag(tagList[i]);
					
					if(tvo == null) {
						Sdao.insertTag(tagList[i]);
						TeamTagVO ttvo = new TeamTagVO();
						ttvo.setTeamId(Sdao.selectSeq());
						ttvo.setTagId(Sdao.maxTagNum());
						Sdao.insertTeamTag(ttvo);	
					} else {
						TeamTagVO ttvo = new TeamTagVO();
						ttvo.setTeamId(Sdao.selectSeq());
						ttvo.setTagId(tvo.getTagId());
						
						Sdao.insertTeamTag(ttvo);
					}
					
				}
			}
			
			return result;
		}
		
		
		String originalName = teamPicture.getOriginalFilename();
		String extName = originalName.substring(originalName.lastIndexOf("."));
		String storedFileName = UUID.randomUUID().toString().replace("-", "") + extName;
		String servletContextPath = mpRequest.getSession().getServletContext().getRealPath(".");
		
		try {

			File file = new File(servletContextPath, storedFileName);
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
				
				String[] tagList = mpRequest.getParameterValues("hashTag");
				if (tagList != null) {
					for(int i = 0; i < tagList.length; i++) {
						
						TagVO tvo = Sdao.selectTag(tagList[i]);
						
						if(tvo == null) {
							Sdao.insertTag(tagList[i]);
							TeamTagVO ttvo = new TeamTagVO();
							ttvo.setTeamId(Sdao.selectSeq());
							ttvo.setTagId(Sdao.maxTagNum());
							Sdao.insertTeamTag(ttvo);	
						} else {
							TeamTagVO ttvo = new TeamTagVO();
							ttvo.setTeamId(Sdao.selectSeq());
							ttvo.setTagId(tvo.getTagId());
							
							Sdao.insertTeamTag(ttvo);
						}
					}
				}
				
			}
			if(file.exists()) {
				file.delete();
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

	public int toggleChange(StudyVO sv) {
		int result = Sdao.toggleChange(sv);
		return result;

	}
	
	@Transactional
	public int scheduleDelete(String deleteChkTime) {
		
		List<StudyVO> sv = Sdao.schedule(deleteChkTime);
		int resultChk =0;
		if(sv.size() != 0 ) {
			for(int i=0; i<sv.size(); i++) {
			
			int result = Sdao.boardCommentDelete(sv.get(i).getTeamId());
			int result1 = Sdao.boardDelete(sv.get(i).getTeamId());
				
			int result2 = Sdao.memberTeamDelete(sv.get(i).getTeamId());
			int result3 = Sdao.waitDelete(sv.get(i).getTeamId());
			int result4 = Sdao.teamTagDelete(sv.get(i).getTeamId());
				
			int result5 = Sdao.teamDelete(sv.get(i).getTeamId());
			
			System.out.println(result +  result1 + result2 + result3 + result4 + result5);
			resultChk = 1 ;
			}
		}
		

		return resultChk;
	}

	public List<Integer> selectTeamId(int memberId) {
		
		return Sdao.selectTeamId(memberId);
	}

	public List<StudyVO> searchedByName(String query) {
		
		List<StudyVO> studyList = new ArrayList<StudyVO>();
		
		
		for (StudyVO vo : Sdao.searchedByName(query)) {
			StudyVO svo = new StudyVO();
			List<TagVO> tagList = Sdao.selectStudyTag(vo.getTeamId());

			svo = vo;
			svo.setTagList(tagList);
			studyList.add(svo);
			
		}
			
		return studyList;

	}

	public List<StudyVO> searchedByTag(String query) {
		
		TagVO vo = Sdao.selectTag(query);
		
		if (vo == null) {
			return null;
		} else {
			List<StudyVO> studyList = new ArrayList<StudyVO>();
			
			
			for (StudyVO vo2 : Sdao.searchedByTag(vo.getTagId()) ) {
				StudyVO svo = new StudyVO();
				List<TagVO> tagList = Sdao.selectStudyTag(vo2.getTeamId());

				svo = vo2;
				svo.setTagList(tagList);
				studyList.add(svo);
				
			}
				
			return studyList;
			
			
		}
		
	}
	
}
