package com.itbank.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.itbank.service.StudyService;

@Controller
public class SchedulerTask {


	@Autowired private StudyService ss;


	@Scheduled(cron="0 0 12 * * *")

	public void scheduleRun(){

	 Calendar calendar = Calendar.getInstance();

	 Date date = new Date();

	 SimpleDateFormat dateFormat = new SimpleDateFormat("yy년 MM월 dd일");

	 String deleteChkTime = dateFormat.format(date);

	 System.out.println("스케줄 실행 : " + dateFormat.format(calendar.getTime()));

	 
	 
	 int result = ss.scheduleDelete(deleteChkTime);
	 	System.out.println(result);
	}

}