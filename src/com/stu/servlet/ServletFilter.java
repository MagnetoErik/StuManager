package com.stu.servlet;


import com.stu.bean.AdminUserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletFilter implements Filter {

	FilterConfig config;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		request.getRequestURI();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		AdminUserBean user = (AdminUserBean)session.getAttribute("userBean");
		String regUrl = request.getRequestURI();
		String path = request.getContextPath();
		if(user==null) {
			if(regUrl.indexOf("pages")!=-1) {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('��½���ڻ���δ��½�����½���ٷ���ҳ��');location.target='_parent';window.location.href='"+path+"/index.jsp';</script>");
			}
		}
		chain.doFilter(req, resp);
	}

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		config = filterConfig;
	}

}
