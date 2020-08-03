package com.itbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbank.dao.StudyDAO;
import com.itbank.vo.StudyVO;

@Service
public class StudyService {
	
	@Autowired
	private StudyDAO Sdao;
	
	public List<StudyVO> selectAllStudies() {
		return Sdao.selectAllStudies();
	}
}
