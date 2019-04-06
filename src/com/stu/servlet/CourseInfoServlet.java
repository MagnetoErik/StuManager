package com.stu.servlet;

import com.alibaba.fastjson.JSON;
import com.stu.bean.CourseInfoBean;
import com.stu.bean.CoursrTypeBean;
import com.stu.service.CourseInfoService;
import com.stu.service.CourseTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * �γ���Ϣ������
 * @author Administrator
 *
 */
@WebServlet("/courseInfo")
public class CourseInfoServlet extends HttpServlet {

	//��ȡ�γ���Ϣ�������
	CourseInfoService service = new CourseInfoService();
	//��ȡ�γ����������
	CourseTypeService ctSrevice = new CourseTypeService();
	
			
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)) {
			//��ѯ���пγ���Ϣ
			list(request, response);
		}
		else if ("searchList".equals(method)) {
			//����ָ������Ϣ��ѯ�γ���Ϣ�б�
			searchList(request, response);
		}
		else if("add".equals(method)) {
			//��ӿγ���Ϣ
			add(request, response);
		}
		else if("queryCourseTypeList".equals(method)) {
			//ajax��ѯ�γ����
			queryCourseTypeList(request, response);
		}
		else if("selectCourseCN".equals(method)) {
			//ajax��ѯ�γ̴���Ϳγ������Ƿ�Ψһ
			selectCourseCN(request, response);
		}
		else if("updateCourseInfoForWord".equals(method)) {
			//������Ҫ�༭������ת�����ƶ���ҳ��
			updateCourseInfoForWord(request, response);
		}
		else if("updateCourseInfoMsg".equals(method)) {
			//���¿γ���Ϣ
			updateCourseInfoMsg(request, response);
		}
		else if("delete".equals(method)) {
			//ɾ���γ���Ϣ
			delete(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)) {
			//��ѯ���пγ���Ϣ
			list(request, response);
		}
		else if ("searchList".equals(method)) {
			//����ָ������Ϣ��ѯ�γ���Ϣ�б�
			searchList(request, response);
		}
		else if("add".equals(method)) {
			//��ӿγ���Ϣ
			add(request, response);
		}
		else if("queryCourseTypeList".equals(method)) {
			//ajax��ѯ�γ����
			queryCourseTypeList(request, response);
		}
		else if("selectCourseCN".equals(method)) {
			//ajax��ѯ�γ̴���Ϳγ������Ƿ�Ψһ
			selectCourseCN(request, response);
		}
		else if("updateCourseInfoForWord".equals(method)) {
			//������Ҫ�༭������ת�����ƶ���ҳ��
			updateCourseInfoForWord(request, response);
		}
		else if("updateCourseInfoMsg".equals(method)) {
			//���¿γ���Ϣ
			updateCourseInfoMsg(request, response);
		}
	}

	/**
	 * 	��ѯ���пγ���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		//�γ���Ϣ�б�
		List<CourseInfoBean> courseList = (List<CourseInfoBean>) service.select(new CourseInfoBean());
		CourseTypeService ctSrevice = new CourseTypeService();
		@SuppressWarnings("unchecked")
		//�γ�����б�
		List<CourseInfoBean> courseTypeList = (List<CourseInfoBean>) ctSrevice.select(new CoursrTypeBean());
		//���б�������������� 
		request.setAttribute("courseList", courseList);
		//���γ�����б��������������
		request.setAttribute("courseTypeList", courseTypeList);
		//ת����ָ����jspҳ��
		request.getRequestDispatcher("pages/jsp/CourseInfo/courseList.jsp").forward(request, response);
	}

	
	/**
	 * �û�ָ����ѯ���ݲ�ѯ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�γ����id
		String courseTypeId = request.getParameter("courseTypeId");
		//��ȡ�γ�����
		String courseInfoName = request.getParameter("courseInfoName");
		//�����γ���Ϣbean
		CourseInfoBean bean = new CourseInfoBean();
		//����ȡ�������ݴ���γ���Ϣbean
		bean.setCourseTypeId(courseTypeId);
		bean.setCourseInfoName(courseInfoName);
		//���ÿγ���Ϣ�����ѯ�û�ָ��������
		@SuppressWarnings("unchecked")
		List<CourseInfoBean> courseList = (List<CourseInfoBean>) service.select(bean);
		@SuppressWarnings("unchecked")
		//�γ�����б�
		List<CourseInfoBean> courseTypeList = (List<CourseInfoBean>) ctSrevice.select(new CoursrTypeBean());
		//���γ����id��������������
		request.setAttribute("courseTypeId", courseTypeId);
		//���γ����ƴ�������������
		request.setAttribute("courseInfoName", courseInfoName);
		//����ѯ�����б�������������
		request.setAttribute("courseList", courseList);
		//���γ�����б��������������
		request.setAttribute("courseTypeList", courseTypeList);
		//ת����ָ����jspҳ��
		request.getRequestDispatcher("pages/jsp/CourseInfo/courseList.jsp").forward(request, response);
	}
	
	/**
	 * ajax��ѯ�γ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryCourseTypeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		//��ȡ�γ�����б�
		List<CoursrTypeBean> courseList = (List<CoursrTypeBean>) ctSrevice.select(new CoursrTypeBean());
		//���б�ת����json�ַ���
		String jsonStr = JSON.toJSONString(courseList);
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��json�γ�����б�ת�����ַ�����Ӧ��ǰ̨
		out.print(jsonStr);
	}
	
	/**
	 * ajax��ѯ�γ̴���Ϳγ������Ƿ�Ψһ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectCourseCN(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��ȡ�γ̴���
		String courseInfoCode = request.getParameter("courseInfoCode");
		//��ȡ�γ�����
		String courseInfoName = request.getParameter("courseInfoName");
		//��ȡ�γ���Ϣ����
		CourseInfoBean bean = new CourseInfoBean();
		//����������ֵ����γ���Ϣ����
		bean.setCourseInfoCode(courseInfoCode);
		bean.setCourseInfoName(courseInfoName);
		//�����û����������ݲ�ѯ�Ƿ���ڼ�¼
		@SuppressWarnings("unchecked")
		List<CourseInfoBean> courseList = (List<CourseInfoBean>) service.select(bean);
		//������ص��б���Ϊ0 �򲻴��ڶ�Ӧ�Ŀγ̴�����߿γ�����
		if(courseList.size()==0) {
			//���0 0����������Ϣ���������
			out.print(0);
		}
		else {
			//���1 	1���������Ϣ��ֹ���
			out.print(1);
		}
	}
	/**
	 * ��ӿγ���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��ȡ�γ����id
		String courseTypeId = request.getParameter("courseTypeId");
		//��ȡ�γ̴���
		String courseInfoCode = request.getParameter("courseInfoCode");
		if(!courseInfoCode.startsWith("0")) {
			courseInfoCode = "0" + courseInfoCode;
		}
		//��ȡ�γ�����
		String courseInfoName = request.getParameter("courseInfoName");
		//��ȡ�γ̽���
		String courseInfoProj = request.getParameter("courseInfoProj");
		//��ȡ����ѧʱ
		String courseInfoRstPer = request.getParameter("courseInfoRstPer");
		//��ȡʵ��ѧʱ
		String courseInfoPraPer = request.getParameter("courseInfoPraPer");
		//��ȡ�γ�ѧ��
		String courseInfoCreHor = request.getParameter("courseInfoCreHor");
		//��ȡ�γ���Ϣbean
		CourseInfoBean bean = new CourseInfoBean();
		//����ȡ�������ݴ���γ���Ϣbean
		bean.setCourseTypeId(courseTypeId);
		bean.setCourseInfoCode(courseInfoCode);
		bean.setCourseInfoName(courseInfoName);
		bean.setCourseInfoProj(courseInfoProj);
		bean.setCourseInfoRstPer(courseInfoRstPer);
		bean.setCourseInfoPraPer(courseInfoPraPer);
		bean.setCourseInfoPraPer(courseInfoPraPer);
		bean.setCourseInfoCreHor(courseInfoCreHor);
		//���÷������ݴ������ݿ�,���ؽ��
		int result = service.add(bean);
		//�������1 ,����ӳɹ�
		if(result==1) {
			out.print("<script>alert('��ӿγ���Ϣ�ɹ�');window.location.href='courseInfo?method=list';</script>");
		}
		else {
			//�������ʧ��
			out.print("<script>alert('��ӿγ���Ϣʧ��');history.back();</script>");

		}
	}
	
	/**
	 * ������Ҫ�༭������ת�����ƶ���ҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateCourseInfoForWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�γ����id
		String courseInfoId = request.getParameter("courseInfoId");
		//��ȡ�γ���Ϣbean
		CourseInfoBean bean = new CourseInfoBean();
		//����ҳ���ȡ�����ݴ���γ���Ϣbean
		bean.setCourseInfoId(courseInfoId);
		@SuppressWarnings("unchecked")
		//�������ݲ�ѯ����
		List<CourseInfoBean> courseList = (List<CourseInfoBean>) service.select(bean);
		//�����ݴ�������������
		request.setAttribute("courseList", courseList.get(0));
		//ת����ָ��ҳ��
		request.getRequestDispatcher("pages/jsp/CourseInfo/addEditCourse.jsp").forward(request, response);
	}
	
	/**
	 * ���¿γ���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateCourseInfoMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��ȡ�γ���Ϣ����id
		String courseInfoId = request.getParameter("courseInfoId");
		//��ȡ�γ����id
		String courseTypeId = request.getParameter("courseTypeId");
		//��ȡ�γ̴���
		String courseInfoCode = request.getParameter("courseInfoCode");
		//��ȡ�γ�����
		String courseInfoName = request.getParameter("courseInfoName");
		//��ȡ�γ̽���
		String courseInfoProj = request.getParameter("courseInfoProj");
		//��ȡ����ѧʱ
		String courseInfoRstPer = request.getParameter("courseInfoRstPer");
		//��ȡʵ��ѧʱ
		String courseInfoPraPer = request.getParameter("courseInfoPraPer");
		//��ȡ�γ�ѧ��
		String courseInfoCreHor = request.getParameter("courseInfoCreHor");
		//��ȡ�γ���Ϣbean
		CourseInfoBean bean = new CourseInfoBean();
		//����ȡ�������ݴ���γ���Ϣbean
		bean.setCourseInfoId(courseInfoId);
		bean.setCourseTypeId(courseTypeId);
		bean.setCourseInfoCode(courseInfoCode);
		bean.setCourseInfoName(courseInfoName);
		bean.setCourseInfoProj(courseInfoProj);
		bean.setCourseInfoRstPer(courseInfoRstPer);
		bean.setCourseInfoPraPer(courseInfoPraPer);
		bean.setCourseInfoPraPer(courseInfoPraPer);
		bean.setCourseInfoCreHor(courseInfoCreHor);
		//���¿γ���Ϣ
		int result = service.update(bean);
		if(result==0) {
			//������صĽ������0�����޸�ʧ��
			out.print("<script>alert('�޸Ŀγ���Ϣʧ��');history.back();</script>");
		}
		else {
			//�޸ĳɹ�
			out.print("<script>alert('�޸Ŀγ���Ϣ�ɹ�');window.location.href='courseInfo?method=list';</script>");
		}
	}
	
	/**
	 * 	ɾ���γ���Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��ȡ�γ���Ϣid
		String courseInfoId = request.getParameter("courseInfoId");
		@SuppressWarnings("unchecked")
		//��ѯ��ǰ�γ��Ƿ��н�ʦ�ν�
		List<CourseInfoBean> courseList = (List<CourseInfoBean>) service.delSelect(Integer.parseInt(courseInfoId));
		if("0".equals(courseList.get(0).getTeachInfoId())) {
			//�����Ӧ�Ŀγ�û�н�ʦ�ν̣������ɾ��
			int result = service.delete(Integer.parseInt(courseInfoId));
			if(result==0) {
				//������ؽ������0����ɾ��ʧ��
				out.print("<script>alert('ɾ��ʧ��');history.back();</script>");
			}
			else {
				//ɾ���ɹ�
				out.print("<script>alert('ɾ���ɹ�');window.location.href='courseInfo?method=list';</script>");
			}
		}
		else {
			//��Ӧ�Ŀγ��н�ʦ�ν̣���ֹɾ��
			out.print("<script>alert('�ÿγ��н�ʦ�ν̣���ֹɾ����');history.back();</script>");
		}
	}
}
