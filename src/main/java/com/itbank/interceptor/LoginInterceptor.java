
package com.itbank.interceptor;

 

import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

 

import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

 

public class LoginInterceptor extends HandlerInterceptorAdapter {

	

	

	// 1. request가 발생한 이후 컨트롤러의 메서드를 수행하기 전에 개입

	@Override

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)

			throws Exception {

 

 

 

		HttpSession session = request.getSession(false);

		

		if(session != null && session.getAttribute("login") != null) {

//			System.out.println("preHandle : true");

			return true;	

		}

		

 

		request.setAttribute("msg", "로그인필요");

		request.setAttribute("url", "");

		System.out.println(request.getContextPath()+"/redirect/");

		RequestDispatcher di =  request.getRequestDispatcher("/WEB-INF/views/redirect.jsp");

		di.forward(request, response);

 

		

		

	

		return false;

	}

 

 

	@Override

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,

			ModelAndView modelAndView) throws Exception {

		

	}

	

 

	@Override

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)

			throws Exception {

		// TODO Auto-generated method stub

		super.afterCompletion(request, response, handler, ex);

	}

}
