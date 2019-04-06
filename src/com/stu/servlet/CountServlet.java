package com.stu.servlet;

import com.alibaba.fastjson.JSON;
import com.stu.bean.CountBean;
import com.stu.service.CountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ͳ�Ʒ���
 * @author Administrator
 *
 */
@WebServlet("/count")
public class CountServlet extends HttpServlet {

	CountService service = new CountService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("countDep".equals(method)) {
			//ͳ��Ժϵ����
			countDep(request, response);
		}
		else if("countSp".equals(method)) {
			//ͳ�Ƹ���רҵ����
			countSp(request, response);
		}
		else if("countSex".equals(method)) {
			//ͳ��רҵ�� ��Ů��������
			countSex(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("countDep".equals(method)) {
			//ͳ��Ժϵ����
			countDep(request, response);
		}
	}

	
	/**
	 *  ͳ�Ƹ���Ժϵ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void countDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ѯ����Ժϵ��Ӧ��������
		List<CountBean> countList = service.countDep();
		//���б�ת����json�ַ���
		String jsonStr = JSON.toJSONString(countList);
		//��json�ַ�����������������
		request.setAttribute("jsonStr", jsonStr);
		//����������������ָ����ҳ��
		request.getRequestDispatcher("pages/jsp/depSpCount/depCount.jsp").forward(request, response);
	}
	
	/**
	 * ͳ�Ƹ���רҵ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void countSp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ�洫����Ժϵid
		int id = Integer.parseInt(request.getParameter("id"));
		//ͳ�Ƶ�ǰԺϵ�¸���רҵ��������
		List<CountBean> countList = service.countSp(id);
		//���б�ת����json�ַ���
		String jsonStr = JSON.toJSONString(countList);	
		//��json�ַ�����������������
		request.setAttribute("jsonStr", jsonStr);		
		//����������������ָ����ҳ��
		request.getRequestDispatcher("pages/jsp/depSpCount/spCount.jsp").forward(request, response);
	}
	/**
	 * ͳ��רҵ�� ��Ů��������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void countSex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ�洫����רҵid
		int id = Integer.parseInt(request.getParameter("id"));
		//�����ݿ�����ݴ���list��
		List<CountBean> countSexList = service.countSex(id);
		//���б�ת����json�ַ���
		String jsonStr = JSON.toJSONString(countSexList);	
		//��json�ַ�����������������
		request.setAttribute("jsonStr", jsonStr);		
		//����������������ָ����ҳ��
		request.getRequestDispatcher("pages/jsp/depSpCount/sexCount.jsp").forward(request, response);
	}
	
}
