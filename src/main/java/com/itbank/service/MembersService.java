package com.itbank.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.itbank.amazonS3.S3Utill;
import com.itbank.dao.MembersDAO;
import com.itbank.vo.MembersVO;


@Service
public class MembersService {

	@Autowired MembersDAO dao;
	
	private S3Utill s3utill;
	
	public boolean emailcheck(String email) {
		MembersVO vo = null;
		vo = dao.emailcheck(email);
		return vo != null;
	}
	
	@Transactional(timeout=5)	// 5초안에 처리가 안되면 rollback해라
	public int insertMembers(MembersVO vo) {
		boolean exist = emailcheck(vo.getEmail());
		
		if(!exist) {
			vo.setPictureUrl("/");
			return dao.insertMembers(vo);
		}
			
		return 0;

	}

	public MembersVO selectMembers(MembersVO vo) {
		try {
			return dao.selectMembers(vo);
		}catch(EmptyResultDataAccessException e) {
			System.out.println("selectMember Error : " + e);
			return null;
		}
	}


	public void updatepw(MembersVO vo) {
		dao.updatePw(vo);
	}
	
	public MembersVO selectMember(int memberId) {
		return dao.selectMember(memberId);
	}

	public void updateTitle(MembersVO vo) {
		dao.updateTitle(vo);
	}

	public void updateContext(MembersVO vo) {
		dao.updateContext(vo);

	}

	public void updateUsername(MembersVO vo) {
		dao.updateUsername(vo);

	}

	public void updateEmail(MembersVO vo) {
		dao.updateEmail(vo);

	}

	public boolean checkPassword(MembersVO vo) {

		MembersVO vo1 = null;
		vo1 = dao.checkPassword(vo1);
		return vo1 != null;
	}

	public void updatePassword(MembersVO vo) {
		dao.updatePassword(vo);
	}

	public int changeProfilePic(MultipartHttpServletRequest mpRequest, MembersVO vo) {
		//Amazon S3
		s3utill = new S3Utill();
		
		// 전 프로필 사진 삭제
		if (vo.getPictureUrl() != null || vo.getPictureUrl() != "") {
			String pictureUrl = vo.getPictureUrl();
			String fileName = pictureUrl.substring(pictureUrl.lastIndexOf("/")+ 1);
			s3utill.fileDelete("profile", fileName);
		}
		
		// file upload
		MultipartFile profilePic = mpRequest.getFile("profile-pic");
		
		String originalName = profilePic.getOriginalFilename();
		String extName = originalName.substring(originalName.lastIndexOf("."));
		String storedFileName = UUID.randomUUID().toString().replace("-", "") + extName;

		File file = new File(storedFileName);
		try {
			profilePic.transferTo(file);
			s3utill.fileUpload("yeol-gong-study-picture", "profile/" + storedFileName, file);
		} catch (IllegalStateException e) {
			System.out.println("프로필 사진 변경 오류" + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("프로필 사진 변경 오류" + e);
			e.printStackTrace();
		}
		
		vo.setPictureUrl("https://yeol-gong-study-picture.s3.ap-northeast-2.amazonaws.com/profile/" +storedFileName);
		
		
		
		return dao.changeProfileUrl(vo);
	}

	public int deleteAccount(int memberId) {
		return dao.deleteAccount(memberId);
		
	}
}
