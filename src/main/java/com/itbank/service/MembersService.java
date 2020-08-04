package com.itbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itbank.dao.MembersDAO;
import com.itbank.vo.MembersVO;


@Service
public class MembersService {

	@Autowired MembersDAO dao;
	
	
	public boolean emailcheck(String email) {
		MembersVO vo = null;
		vo = dao.emailcheck(email);
		return vo != null;
	}

	@Transactional(timeout=5)	// 5초안에 처리가 안되면 rollback해라
	public MembersVO insertMembers(MembersVO vo) {
		boolean exist = emailcheck(vo.getEmail());
		int flag = 0;
		if(exist == false) {
			flag = dao.insertMembers(vo);
		}
		
		if(flag == 1) {
			return dao.selectMembers(vo);
		}
		return null;
	}

	public MembersVO selectMembers(MembersVO vo) {
		try {
			return dao.selectMembers(vo);
		}catch(EmptyResultDataAccessException e) {
			System.out.println(e);
			return null;
		}
	}
	public void updatepw(MembersVO vo) {
		dao.updatePw(vo);
	}

	public MembersVO selectMember(String email) {
		return dao.selectMember(email);
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
	
}
