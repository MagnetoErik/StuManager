package com.stu.servlet;

import com.stu.bean.DepInfoBean;
import com.stu.bean.SpecilinfoBean;
import com.stu.service.DepInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Ժϵ��Ϣ������
 * @author Administrator
 *
 */

@WebServlet("/depInfo")
public class DepInfoServlet extends HttpServlet {

	DepInfoService service = new DepInfoService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)) {//��ѯȫ��Ժϵ��Ϣ�б�
			searchDepList(request, response);
		}
		else if("searchDepList".equals(method)) {//��ѯ�û�ָ����Ժϵ��Ϣ
			searchDepList(request, response);
		}
		else if("updateDepInfoForWord".equals(method)) {//�༭Ժϵ��Ϣת����ָ����jspҳ��
			updateSelectDepMsg(request, response);
		}
		else if ("updateDepInfoMsg".equals(method)) {
			updateDepInfoMsg(request, response);
		}
		else if("add".equals(method)) {//���Ժϵ��Ϣ
			addDepInfo(request, response);
			
		}
		else if("delete".equals(method)) {//ɾ��Ժϵ��Ϣ
			deleteDepInfoMsg(request, response);
		}
		else if("ajaxSelectDepCode".equals(method)) {//ajax��ˢ������ ��ѯԺϵcode�Ƿ����
			ajaxSelectDepCode(request, response);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)) {//��ѯȫ��Ժϵ��Ϣ�б�
			searchDepList(request, response);
		}
		else if("searchDepList".equals(method)) {//��ѯ�û�ָ����Ժϵ��Ϣ
			searchDepList(request, response);
		}
		else if("updateDepInfoForWord".equals(method)) {//�༭Ժϵ��Ϣת����ָ����jspҳ��
			updateSelectDepMsg(request, response);
		}
		else if ("updateDepInfoMsg".equals(method)) {//����Ժϵ��Ϣ
			updateDepInfoMsg(request, response);
		}
		else if("add".equals(method)) {//�����Ϣ
			addDepInfo(request, response);
			
		}
		else if("delete".equals(method)) {//ɾ����Ϣ
			deleteDepInfoMsg(request, response);
		}
		else if("ajaxSelectDepCode".equals(method)) {//ajax��ˢ������ ��ѯԺϵcode�Ƿ����
			ajaxSelectDepCode(request, response);
		}
	}
	
	
	/**
	 * 		����Ժϵ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addDepInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DepInfoBean Bean = new DepInfoBean();
		String depinfocode = request.getParameter("depinfocode");
		if(!depinfocode.startsWith("0")) {
			depinfocode= "0" + depinfocode;
		}
		Bean.setDepinfocode(depinfocode);
		if(service.select(Bean).size()!=0) {
			out.print("<script>alert('����Ժϵ��Ϣʧ�ܣ�Ժϵid�Ѵ��ڣ�������������Ϣ');history.back();</script>");
		}
		else {
			String depinfoname = request.getParameter("depinfoname");
			String depinfopreoftech = request.getParameter("depinfopreoftech");
			String depinfoassteach = request.getParameter("depinfoassteach");
			Bean.setDepinfocode(depinfocode);
			Bean.setDepinfoname(depinfoname);
			Bean.setDepinfopreoftech(depinfopreoftech);
			Bean.setDepinfoassteach(depinfoassteach);

			int result = service.add(Bean);
			if(result==0) {
				out.print("<script>alert('����Ժϵ��Ϣʧ��');history.back();</script>");
			}
			else {
				out.print("<script>alert('����Ժϵ��Ϣ�ɹ�');window.location.href='depInfo?method=list'</script>");
			}
		}
	}
	
	/**
	 * �޸�Ժϵ��Ϣ
	 * @param request  ����������
	 * @param response   ��Ӧ������
	 * @throws ServletException  Servlet�쳣�׳�
	 * @throws IOException  IO���쳣�׳�
	 */
	public void updateDepInfoMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DepInfoBean Bean = new DepInfoBean();
		String depinfocode = request.getParameter("depinfocode");
		String depinfoname = request.getParameter("depinfoname");
		String depinfopreoftech = request.getParameter("depinfopreoftech");
		String depinfoassteach = request.getParameter("depinfoassteach");
		String depinfospnum = request.getParameter("depinfospnum");
		String depinfoclassnum = request.getParameter("depinfoclassnum");
		Bean.setDepinfocode(depinfocode);
		Bean.setDepinfoname(depinfoname);
		Bean.setDepinfopreoftech(depinfopreoftech);
		Bean.setDepinfoassteach(depinfoassteach);
		Bean.setDepinfospnum(depinfospnum);
		Bean.setDepinfoclassnum(depinfoclassnum);
		int result = service.update(Bean);
		if(result==0) {
			out.print("<script>alert('�޸�Ժϵ��Ϣʧ��');history.back();</script>");
		}
		else {
			out.print("<script>alert('�޸�Ժϵ��Ϣ�ɹ�');window.location.href='depInfo?method=list'</script>");
		}
	}
	

	
	/**
	 *   ��ѯԺϵ��Ϣ�б��ָ����Ժϵ��Ϣ�б�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchDepList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepInfoBean depBean = new DepInfoBean();
		String depname = request.getParameter("depname");
		depBean.setDepinfoname(depname);
		List<DepInfoBean> beanList = service.select(depBean);
		request.setAttribute("depname", depname);
		request.setAttribute("beanList", beanList);
		request.getRequestDispatcher("pages/jsp/DepInfo/depList.jsp").forward(request, response);
	}
	
	/**
	 * 		�û�����޸İ�ť����Ϣ����ָ����jspҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateSelectDepMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String depcode = request.getParameter("depcode");
		DepInfoBean bean = new DepInfoBean();
		bean.setDepinfocode(depcode);
		List<DepInfoBean> depList = service.select(bean);
		request.setAttribute("depList", depList.get(0));
		request.getRequestDispatcher("pages/jsp/DepInfo/addOrEditDep.jsp").forward(request, response);
	}
	
	
	/**
	 * ɾ����Ϣ
	 * @param request  ����������
	 * @param response   ��Ӧ������
	 * @throws ServletException  Servlet�쳣�׳�
	 * @throws IOException  IO���쳣�׳�
	 */
	public void deleteDepInfoMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String depinfoid = request.getParameter("depid");
		SpecilinfoBean bean = new SpecilinfoBean();
		bean.setDepinfoid(depinfoid);
		//����Ժϵid
		int result = service.delete(bean);
		if(result==0) {
			out.print("<script>alert('ɾ��Ժϵ��Ϣ�ɹ�');window.location.href='depInfo?method=list';</script>");
		}
		else {
			out.print("<script>alert('ɾ��Ժϵ��Ϣʧ��');history.back();</script>");
		}
		
	}
	
	/**
	 * ajax���������� ��ѯԺϵ����Ƿ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ajaxSelectDepCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����ҳ�������Ժϵ���
		String depinfocode = request.getParameter("depinfocode");
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//���ҳ�洫����Ժϵ��ŵ�һλ����0������λ��һ��0
		if(!depinfocode.startsWith("0")) {
			depinfocode= "0" + depinfocode;
		}
		DepInfoBean bean = new DepInfoBean();
		bean.setDepinfocode(depinfocode);
		//ͨ��Ժϵ��Ų�ѯרҵ��Ϣ
		if(service.select(bean).size()==0) {
			//������ؼ��ϳ���Ϊ0���򲻴���Ժϵ��Ϣ������0
			out.print(0);
		}else {
			//���� �������ݣ�����1
			out.print(1);
		}
		
	}
}
