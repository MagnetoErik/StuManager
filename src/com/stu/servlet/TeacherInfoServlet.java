package com.stu.servlet;

import com.stu.bean.DepInfoBean;
import com.stu.bean.TeacherInfoBean;
import com.stu.service.DepInfoService;
import com.stu.service.TeacherInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * ��ʦ��Ϣ������
 * @author Administrator
 *
 */
@WebServlet("/teacherInfo")
public class TeacherInfoServlet extends HttpServlet{

	TeacherInfoService service = new TeacherInfoService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)) {//��ѯ��ʦ�б�
			selectTeacherInfoList(request, response);
		}
		else if("add".equals(method)) {//��ӽ�ʦ��Ϣ
			add(request, response);
		}
		else if("search".equals(method)) {//ָ����ѯ��Ϣ�����ؽ�ʦ��Ϣ
			search(request, response);
		}
		else if("updateTeacherInfoForWord".equals(method)) {
			//����༭����ѯ��Ӧ�������Ϣ��������addEditTeacher.jsp
			updateTeacherInfoForWord(request, response);
		}
		else if("updateClassInfoMsg".equals(method)) {
			//���½�ʦ��Ϣ
			updateClassInfoMsg(request, response);
		}
		else if("delete".equals(method)) {//ɾ����ʦ��Ϣ
			dalete(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)) {//��ѯ��ʦ�б�
			selectTeacherInfoList(request, response);
		}
		else if("add".equals(method)) {//��ӽ�ʦ��Ϣ
			add(request, response);
		}
		else if("search".equals(method)) {//ָ����ѯ��Ϣ�����ؽ�ʦ��Ϣ
			search(request, response);
		}
		else if("updateTeacherInfoForWord".equals(method)) {
			//����༭����ѯ��Ӧ�������Ϣ��������addEditTeacher.jsp
			updateTeacherInfoForWord(request, response);
		}
		else if("updateClassInfoMsg".equals(method)) {
			//���½�ʦ��Ϣ
			updateClassInfoMsg(request, response);
		}
		else if("delete".equals(method)) {//ɾ����ʦ��Ϣ
			dalete(request, response);
		}
	}
	
	
	/**
	 * 	 ��ѯȫ����ʦ��Ϣ�б���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectTeacherInfoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<TeacherInfoBean> teacherList = (List<TeacherInfoBean>) service.select(new TeacherInfoBean());
		DepInfoService depservice = new DepInfoService();
		List<DepInfoBean> depList = depservice.select(new DepInfoBean());	
		request.setAttribute("teacherList", teacherList);
		request.setAttribute("depList", depList);
		request.getRequestDispatcher("pages/jsp/TeacherInfo/teacherList.jsp").forward(request, response);
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ʦ���
		String teachinfocode = request.getParameter("teachinfocode");
		TeacherInfoBean bean = new TeacherInfoBean();
		bean.setTeachInfoCode(teachinfocode);
		@SuppressWarnings("unchecked")
		List<TeacherInfoBean> teacherList = (List<TeacherInfoBean>) service.select(bean);
		if(teacherList.size()==0) {
			//��ȡ��ʦ����ID
			String teacherTypeId = request.getParameter("teacherTypeId");
			//��ȡԺϵ��ϢID
			String depInfoId = request.getParameter("depInfoId");
			//��ȡ��ʦ����
			String teachinfoname = request.getParameter("teachinfoname");
			//��ȡ�Ա�
			String teachinfosex = request.getParameter("teachinfosex");
			//��ȡѧ��
			String teachknowl = request.getParameter("teachknowl");
			//��ȡѧλ
			String teachDeg = request.getParameter("teachDeg");
			//��ȡ��ѧרҵ
			String teachSpec = request.getParameter("teachSpec");
			//��ȡְ��
			String teachTitle = request.getParameter("teachTitle");
			bean.setTeachTypeId(teacherTypeId);
			bean.setDepInfoId(depInfoId);
			bean.setTeachInfoCode(teachinfocode);
			bean.setTeachInfoname(teachinfoname);
			bean.setTeachSex(teachinfosex);
			bean.setTeachKnowl(teachknowl);
			bean.setTeachDeg(teachDeg);
			bean.setTeachSpec(teachSpec);
			bean.setTeachTitle(teachTitle);
			//��ȡ�����ഫ�ص����� ���Ϊ1����ӳɹ����������Ϊ0�����ʧ��
			int result = service.add(bean);
			PrintWriter out = response.getWriter();
			if(result==0) {
				out.print("<script>alert('��ӽ�ʦ��Ϣʧ��');history.back();</script>");
			}
			else {
				out.print("<script>alert('��ӽ�ʦ��Ϣ�ɹ�');window.location.href='teacherInfo?method=list';</script>");
			}
		}
	}
	
	/**
	 * ָ����ѯ��Ϣ�����ؽ�ʦ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡԺϵid
		String depinfoid = request.getParameter("depinfoid");
		//��ȡ��ʦ����
		String teachInfoname = request.getParameter("teachInfoname");
		TeacherInfoBean bean = new TeacherInfoBean();
		bean.setDepInfoId(depinfoid);
		bean.setTeachInfoname(teachInfoname);
		@SuppressWarnings("unchecked")
		//��ѯ��Ӧ�Ľ�ʦ��Ϣ
		List<TeacherInfoBean> teacherList = (List<TeacherInfoBean>) service.select(bean);
		//������س���Ϊ�գ��򲻴��ڽ�ʦ��Ϣ
		if(teacherList.size()==0) {
			teacherList = null;
		}
		request.setAttribute("depinfoid", depinfoid);
		request.setAttribute("teachInfoname", teachInfoname);
		request.setAttribute("teacherList", teacherList);
		//��request response ����������� ת����ָ��ҳ��
		request.getRequestDispatcher("pages/jsp/TeacherInfo/teacherList.jsp").forward(request, response);
	}
	
	
	/**
	 * ����༭����ѯ��Ӧ�������Ϣ��������addEditTeacher.jsp
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateTeacherInfoForWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ʦ��id
		String teachInfoId = request.getParameter("teachInfoId");
		TeacherInfoBean bean = new TeacherInfoBean();
		bean.setTeachInfoId(teachInfoId);
		@SuppressWarnings("unchecked")
		//ͨ����ʦid ��ѯ��ϸ��Ϣ
		List<TeacherInfoBean> teacherList = (List<TeacherInfoBean>) service.select(bean);
		//����ϸ��Ϣ��������������
		request.setAttribute("teacherList", teacherList.get(0));
		//����Ϣת����ָ��ҳ��
		request.getRequestDispatcher("pages/jsp/TeacherInfo/addEditTeacher.jsp").forward(request, response);
	}
	
	/**
	 * 	���½�ʦ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateClassInfoMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ʦ���
		String teachinfocode = request.getParameter("teachinfocode");
		//��ȡ��ʦ����ID
		String teacherTypeId = request.getParameter("teacherTypeId");
		//��ȡԺϵ��ϢID
		String depInfoId = request.getParameter("depInfoId");
		//��ȡ��ʦ����
		String teachinfoname = request.getParameter("teachinfoname");
		//��ȡ�Ա�
		String teachinfosex = request.getParameter("teachinfosex");
		//��ȡѧ��
		String teachknowl = request.getParameter("teachknowl");
		//��ȡѧλ
		String teachDeg = request.getParameter("teachDeg");
		//��ȡ��ѧרҵ
		String teachSpec = request.getParameter("teachSpec");
		//��ȡְ��
		String teachTitle = request.getParameter("teachTitle");
		TeacherInfoBean bean = new TeacherInfoBean();
		bean.setTeachTypeId(teacherTypeId);
		bean.setDepInfoId(depInfoId);
		bean.setTeachInfoCode(teachinfocode);
		bean.setTeachInfoname(teachinfoname);
		bean.setTeachSex(teachinfosex);
		bean.setTeachKnowl(teachknowl);
		bean.setTeachDeg(teachDeg);
		bean.setTeachSpec(teachSpec);
		bean.setTeachTitle(teachTitle);
		PrintWriter out = response.getWriter();
		//���Ķ�Ӧ�Ľ�ʦ��Ϣ���������0 ���޸�ʧ�� ����1 ���޸ĳɹ�
		int result = service.update(bean);
		if(result==0) {
			out.print("<script>alert('�޸Ľ�ʦ��Ϣʧ�ܣ�');history.back();</script>");
		}else {
			out.print("<script>alert('�޸Ľ�ʦ��Ϣ�ɹ���');window.location.href='teacherInfo?method=list';</script>");
		}
	}
	
	/**
	 * ɾ����ʦ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void dalete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��ȡ��ʦ��Ϣid
		String teachInfoId = request.getParameter("teachInfoId");
		TeacherInfoBean bean = new TeacherInfoBean();
		bean.setTeachInfoId(teachInfoId);
		@SuppressWarnings("unchecked")
		//��ѯ��Ӧ�Ľ�ʦ��Ϣ
		List<TeacherInfoBean> teacherList = (List<TeacherInfoBean>) service.select(bean);
		bean = teacherList.get(0);
		//�����ʦ������ �ο���Ϣ
		if(Integer.parseInt(bean.getTeachcourseid())==0) {
			//ɾ����ʦ��Ϣ���������0 ��ɾ��ʧ�ܣ�����1 ��ɾ���ɹ�
			int result = service.delete(Integer.parseInt(teachInfoId));
			if(result==0) {
				out.print("<script>alert('ɾ����ʦ��Ϣʧ��');history.back();</script>");
			}
			else {
				out.print("<script>alert('ɾ����ʦ��Ϣ�ɹ�');window.location.href='teacherInfo?method=list';</script>");
			}
		}
		else {
			out.print("<script>alert('�ý�ʦ���ο���Ϣ����ֹɾ����');history.back();</script>");
		}
	}
	
	
	
	

}
