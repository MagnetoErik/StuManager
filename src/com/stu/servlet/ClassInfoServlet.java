package com.stu.servlet;

import com.alibaba.fastjson.JSON;
import com.stu.bean.ClassInfoBean;
import com.stu.bean.DepInfoBean;
import com.stu.bean.SpecilinfoBean;
import com.stu.service.ClassInfoService;
import com.stu.service.DepInfoService;
import com.stu.service.SpecilInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * �༶��Ϣ������
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@WebServlet("/class")
public class ClassInfoServlet extends HttpServlet {

	
	ClassInfoService service = new ClassInfoService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)){//��ѯȫ���༶��Ϣ�б�
			select(request, response);	
		}
		else if ("ajaxList".equals(method)) {//��̬��ѯԺϵ����Ӧ��רҵ��Ϣ
			selectAjaxList(request, response);
		}
		else if ("search".equals(method)) {//�û�����ָ����������������
			search(request, response);
		}
		else if ("updateClassInfoForWord".equals(method)) {//����ѯ���������ָ����jspҳ��
			updateClassInfoForWord(request, response);
		}
		else if ("add".equals(method)) {//addEditClass��ѯԺϵ��Ϣ
			selectDepToAddJsp(request, response);
		}
		else if ("addSp".equals(method)) {//addEditClass��ѯԺϵ��Ϣ
			selectSpToAddJsp(request, response);
		}		
		else if ("addMsg".equals(method)) {//���רҵ��Ϣ
			add(request, response);
		}
		else if ("updateClassInfoMsg".equals(method)) {//����רҵ��Ϣ
			updateClassInfoMsg(request, response);
		}
		else if("delete".equals(method)) {//ɾ��רҵ��Ϣ
			delete(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)){//��ѯȫ���༶��Ϣ�б�
			select(request, response);	
		}
		else if ("ajaxList".equals(method)) {//��̬��ѯԺϵ����Ӧ��רҵ��Ϣ
			selectAjaxList(request, response);
		}
		else if ("search".equals(method)) {//�û�����ָ����������������
			search(request, response);
		}
		else if ("updateClassInfoForWord".equals(method)) {//����ѯ���������ָ����jspҳ��
			updateClassInfoForWord(request, response);
		}
		else if ("add".equals(method)) {//addEditClass��ѯԺϵ��Ϣ
			selectDepToAddJsp(request, response);
		}
		else if ("addSp".equals(method)) {//addEditClass��ѯԺϵ��Ϣ
			selectSpToAddJsp(request, response);
		}		
		else if ("addMsg".equals(method)) {//���רҵ��Ϣ
			add(request, response);
		}
		else if ("updateClassInfoMsg".equals(method)) {//����רҵ��Ϣ
			updateClassInfoMsg(request, response);
		}
		else if("delete".equals(method)) {//ɾ��רҵ��Ϣ
			delete(request, response);
		}
	}
	
	
	/**
	 * ��Ӱ༶��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String classInfoCode = request.getParameter("classInfoCode");
		String classInfoName = request.getParameter("classInfoName");
		String classInfosum = request.getParameter("classInfosum");
		String classinformk = request.getParameter("classinformk");
		String specializeId = request.getParameter("specializeId");
		ClassInfoBean bean = new ClassInfoBean();
		bean.setSpilinfoId(specializeId);
		bean.setClassInfoCode(classInfoCode);
		bean.setClassInfoName(classInfoName);
		bean.setClassInfosum(classInfosum);
		bean.setClassinformk(classinformk);
		int result = service.add(bean);
		if(result==0) {
			out.print("<scrpit>alert('��Ӱ༶��Ϣ�ɹ�');window.location.href='class?method=list';</script>");
		}else {
			out.print("<scrpit>alert('��Ӱ༶��Ϣʧ��');history.back();</script>");
		}
	}
	/**
	 * ��ѯȫ���༶��Ϣ�б�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String depid = request.getParameter("depid");
		ClassInfoBean classBean = new ClassInfoBean();
		classBean.setDepinfoid(depid);
		@SuppressWarnings("unchecked")
		List<ClassInfoBean> classList = (List<ClassInfoBean>) service.select(classBean);
		if(classList.size()==0) {
			classList=null;
		}
		DepInfoService depService = new DepInfoService();
		DepInfoBean bean = new DepInfoBean();
		bean.setDepinfoid(depid);
		List<DepInfoBean> depList = depService.select(bean);
		SpecilInfoService spService = new SpecilInfoService();
		List<SpecilinfoBean> spList = spService.select(new SpecilinfoBean());		
		request.setAttribute("classList", classList);
		request.setAttribute("depinfoid", depid);
		request.setAttribute("depList", depList);
		request.setAttribute("spList", spList);
		request.getRequestDispatcher("pages/jsp/ClassInfo/classList.jsp").forward(request, response);
	}
	
	
	/**
	 * ����ѯ��רҵ��Ϣ��Ժϵ��Ϣ����addEditClass.jspҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectDepToAddJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepInfoService depService = new DepInfoService();
		List<DepInfoBean> depList = depService.select(new DepInfoBean());
		request.setAttribute("depList", depList);
		request.getRequestDispatcher("pages/jsp/ClassInfo/addEditClass.jsp").forward(request, response);
	}
		
	/**
	 * addEditClass ��ˢ������רҵ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectSpToAddJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		SpecilinfoBean bean = new SpecilinfoBean();
		String id = request.getParameter("id");
		bean.setDepinfoid(id);
		SpecilInfoService spService = new SpecilInfoService();
		List<SpecilinfoBean> spList = spService.select(bean);
		String jsonStr = JSON.toJSONString(spList);
		out.print(jsonStr);
	}
		
		
		
	
	
	
	/**
	 * ͨ��ajax��ˢ������Ժϵ���Ӧ��רҵ�б�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectAjaxList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String depid = request.getParameter("depid");
		ClassInfoBean bean = new ClassInfoBean();
		bean.setDepinfoid(depid);
		@SuppressWarnings("unchecked")
		List<ClassInfoBean> classList = (List<ClassInfoBean>) service.select(bean);
		String jsonStr = JSON.toJSONString(classList);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}
	
	
	/**
	 *  classList �������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String depinfoid = request.getParameter("depinfoid");
		String specializeId = request.getParameter("specializeId");
		String className = request.getParameter("className");
		ClassInfoBean bean = new ClassInfoBean();
		bean.setDepinfoid(depinfoid);
		bean.setSpilinfoId(specializeId);
		bean.setClassInfoName(className);
		@SuppressWarnings("unchecked")
		List<ClassInfoBean> classList = (List<ClassInfoBean>) service.select(bean);
		DepInfoService depService = new DepInfoService();
		List<DepInfoBean> depList = depService.select(new DepInfoBean());
		SpecilInfoService spService = new SpecilInfoService();
		List<SpecilinfoBean> spList = spService.select(new SpecilinfoBean());	
		request.setAttribute("depinfoid", depinfoid);
		request.setAttribute("specializeId", specializeId);
		request.setAttribute("className", className);
		request.setAttribute("classList", classList);
		request.setAttribute("depList", depList);
		request.setAttribute("spList", spList);
		request.getRequestDispatcher("pages/jsp/ClassInfo/classList.jsp").forward(request, response);
	}

	/**
	 * �����û�֮ǰ�Ƚ���Ϣת����ָ����jspҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateClassInfoForWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DepInfoService depService = new DepInfoService();
		List<DepInfoBean> depList = depService.select(new DepInfoBean());
		SpecilInfoService spService = new SpecilInfoService();
		List<SpecilinfoBean> spList = spService.select(new SpecilinfoBean());
		
		String classInfoCode = request.getParameter("classInfoCode");
		ClassInfoBean bean = new ClassInfoBean();
		bean.setClassInfoCode(classInfoCode);
		@SuppressWarnings("unchecked")
		List<ClassInfoBean> classList = (List<ClassInfoBean>) service.select(bean);
		if(classList.size()==0) {
			classList=null;
		}
		request.setAttribute("classList", classList.get(0));
		request.setAttribute("depList", depList);
		request.setAttribute("spList", spList);
		request.getRequestDispatcher("pages/jsp/ClassInfo/addEditClass.jsp").forward(request, response);
	}
	
	/**
	 * ���רҵ��Ϣ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String classInfoCode = request.getParameter("classInfoCode");
		String classInfoName = request.getParameter("classInfoName");
		String classInfosum	= request.getParameter("classInfosum");
		String classinformk = request.getParameter("classinformk");
		String specializeId = request.getParameter("specializeId");
		ClassInfoBean bean = new ClassInfoBean();
		bean.setSpilinfoId(specializeId);
		bean.setClassInfoCode(classInfoCode);
		bean.setClassInfoName(classInfoName);
		bean.setClassInfosum(classInfosum);
		bean.setClassinformk(classinformk);
		//���רҵ��Ϣ
		int result = service.add(bean);
		if(result==0) {//����������0�������רҵ��Ϣʧ�ܡ�
			out.print("<script>alert('���רҵ��Ϣʧ�ܣ�');history.back();</script>");
		}
		else {
			out.print("<script>alert('���רҵ��Ϣ�ɹ���');window.location.href='class?method=list'</script>");
		}
	}
	/**
	 * �����û���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateClassInfoMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String classInfoId = request.getParameter("classInfoId");
		String classInfoCode = request.getParameter("classInfoCode");
		String classInfoName = request.getParameter("classInfoName");
		String classInfosum	= request.getParameter("classInfosum");
		String classinformk = request.getParameter("classinformk");
		String specializeId = request.getParameter("specializeId");
		ClassInfoBean bean = new ClassInfoBean();
		bean.setClassInfoID(classInfoId);
		bean.setSpilinfoId(specializeId);
		bean.setClassInfoCode(classInfoCode);
		bean.setClassInfoName(classInfoName);
		bean.setClassInfosum(classInfosum);
		bean.setClassinformk(classinformk);
		int result = service.update(bean);
		if(result==0){
			out.print("<script>alert('���°༶��Ϣʧ�ܣ�');history.back();</script>");
		}
		else {
			out.print("<script>alert('���°༶��Ϣ�ɹ���');window.location.href='class?method=list'</script>");
		}
	}
	
	
	/**
	 * ɾ���༶��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String classInfoId = request.getParameter("classInfoId");
		ClassInfoBean bean = new ClassInfoBean();
		bean.setClassInfoID(classInfoId);
		@SuppressWarnings("unchecked")
		List<ClassInfoBean> classList = (List<ClassInfoBean>) service.select(bean);
		bean = classList.get(0);
		if(Integer.parseInt(bean.getClassInfosum())==0) {
			int result = service.delete(Integer.parseInt(classInfoId));
			if(result==0) {
				out.print("<script>alert('ɾ���༶��Ϣʧ��');history.back();</script>");
			}
			else {
				out.print("<script>alert('ɾ���༶��Ϣ�ɹ�');window.location.href='class?method=list';</script>");
			}
		}
		else {
			out.print("<script>alert('ɾ���༶��Ϣʧ�ܣ��༶������Ϊ��');history.back();</script>");
		}
	}
	
	
	
	
	
	
	
	
	
	
}
