package com.stu.servlet;

import com.alibaba.fastjson.JSON;
import com.stu.bean.ClassInfoBean;
import com.stu.bean.SpecilinfoBean;
import com.stu.bean.StudentInfoBean;
import com.stu.service.ClassInfoService;
import com.stu.service.SpecilInfoService;
import com.stu.service.StudentInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * ѧ����Ϣ������
 * @author Administrator
 *
 */

@WebServlet("/studentInfo")
public class StudentInfoServlet extends HttpServlet {

	StudentInfoService service = new StudentInfoService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)) {//��ѯѧ���б�
			selectList(request, response);
		}
		else if("updateSpecilInfoForWord".equals(method)) {
			//�޸���Ϣ������ǰ��Ӧ����Ϣ������ָ����jspҳ��
			updateSpecilInfoForWord(request, response);
		}
		else if("ajaxSelectClassList".equals(method)) {
			//addEditStu.jspҳ����ˢ������רҵ��Ӧ�İ༶��Ϣ
			ajaxSelectClassList(request, response);
		}
		else if("add".equals(method)) {
			//���ѧ����Ϣ
			add(request, response);
		}
		else if("updateStuInfoMsg".equals(method)) {
			//�޸�ѧ����Ϣ
			updateStuInfoMsg(request, response);
		}
		else if("delete".equals(method)) {
			//ɾ��ѧ����Ϣ
			delete(request, response);
		}
		else if("query".equals(method)) {
			//����ָ����Ϣ��ѯѧ����Ϣ
			query(request, response);
		}
		else if("code".equals(method)) {
			//�Զ����addEditStu��ѧ�����
			code(request, response);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)) {//��ѯѧ���б�
			selectList(request, response);
		}
		else if("updateSpecilInfoForWord".equals(method)) {
			//�޸���Ϣ������ǰ��Ӧ����Ϣ������ָ����jspҳ��
			updateSpecilInfoForWord(request, response);
		}
		else if("ajaxSelectClassList".equals(method)) {
			//addEditStu.jspҳ����ˢ������רҵ��Ӧ�İ༶��Ϣ
			ajaxSelectClassList(request, response);
		}
		else if("add".equals(method)) {
			//���ѧ����Ϣ
			add(request, response);
		}
		else if("updateStuInfoMsg".equals(method)) {
			//�޸�ѧ����Ϣ
			updateStuInfoMsg(request, response);
		}
		else if("delete".equals(method)) {
			//ɾ��ѧ����Ϣ
			delete(request, response);
		}
		else if("query".equals(method)) {
			//����ָ����Ϣ��ѯѧ����Ϣ
			query(request, response);
		}
	}
	
	
	/**
	 * 		 ��ѯѧ���б�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ѯ�ܷ�ҳ��
		int maxPage = service.maxPages()-1;
		int count = Integer.parseInt(request.getParameter("count"));
		String id = request.getParameter("id");
		if("next".equals(id)) {
			count+=1;
		}
		else if("last".equals(id)) {
			count = count - 1;
		}
		else if("top".equals(id)) {
			count = 1;
		}
		else if("buttom".equals(id)) {
			count = maxPage;
		}
		//��ѯȫ��ѧ����Ϣ�б�
		List<StudentInfoBean> stuList = (List<StudentInfoBean>) service.pages(16*count-1);
		//���б��������������
		request.setAttribute("stuList", stuList);
		//����ǰ��ҳ��������������
		request.setAttribute("count", count);
		//���ܷ�ҳ����������������
		request.setAttribute("maxPage", maxPage);
		//ת����ָ����ҳ��
		request.getRequestDispatcher("pages/jsp/StudentInfo/stuList.jsp").forward(request, response);
	}

	/**
	 * 		�޸���Ϣ������ǰ��Ӧ����Ϣ������ָ����jspҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateSpecilInfoForWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ�洫����ѧ����Ϣid
		String stdinfoid = request.getParameter("stdinfoid");
		StudentInfoBean bean = new StudentInfoBean();
		//��ѧ����Ϣid����ѧ����Ϣbean
		bean.setStdinfoid(stdinfoid);
		@SuppressWarnings("unchecked")
		//��ѯ���Ӧ��ѧ����Ϣ
		List<StudentInfoBean> stuList = (List<StudentInfoBean>) service.select(bean);
		//�����ص�ѧ����Ϣ��������������
		request.setAttribute("stuList", stuList.get(0));
		//����������������ָ��ҳ��
		request.getRequestDispatcher("pages/jsp/StudentInfo/addEditStu.jsp").forward(request, response);
				
	}
	
	
	/**
	 * addEditStu.jspҳ����ˢ������רҵ��Ӧ�İ༶��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void ajaxSelectClassList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҳ�洫����רҵid
		String id = request.getParameter("id");
		//��ȡ�༶��Ϣ����
		ClassInfoService classService = new ClassInfoService();
		ClassInfoBean bean = new ClassInfoBean();
		//��רҵ��Ϣid����༶��Ϣ����
		bean.setSpilinfoId(id);
		@SuppressWarnings("unchecked")
		//���ص�ǰרҵ�����еİ༶��Ϣ
		List<ClassInfoBean> classList = (List<ClassInfoBean>) classService.select(bean);
		//���б�ת��Ϊjson�ַ���
		String jsonStr = JSON.toJSONString(classList);
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��json�ַ���������ǰ̨
		out.print(jsonStr);
	}
	
	/**
	 * ���ѧ����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��ȡѧ����Ϣ���
		String stdinfocode = request.getParameter("stdinfocode");
		if(stdinfocode.startsWith("0")) {
			stdinfocode = "0"+stdinfocode;
		}
		//��ȡѧ����Ϣbean
		StudentInfoBean bean = new StudentInfoBean();
		//��ѧ����Ŵ���ѧ����Ϣbean
		bean.setStdinfocode(stdinfocode);
		//ͨ��ѧ����Ϣ�����ѯ��ǰѧ������Ƿ���Ψһ
		@SuppressWarnings("unchecked")
		List<StudentInfoBean> list = (List<StudentInfoBean>) service.select(bean);
		//����������б���Ϊ0�������ݿ�û�ж�Ӧ��ѧ����ſ�������ע��
		if(list.size()==0) {
			//��ȡѧ������
			String stdinfoname = request.getParameter("stdinfoname");
			//��ȡ�Ա�
			String stdinfosex = request.getParameter("stdinfosex");
			//��ȡ���֤��
			String stdinfocard = request.getParameter("stdinfocard");
			//��ȡ��������
			String stdinfobirthd = request.getParameter("stdinfobirthd");
			//��ȡ����
			String stdinfonatns = request.getParameter("stdinfonatns");
			//��ȡ�绰����
			String stdinfotel = request.getParameter("stdinfotel");
			//��ȡ��������
			String stdinfoemail = request.getParameter("stdinfoemail");
			//��ȡ�༶id
			String classInfoId = request.getParameter("classInfoId");
			//���������ѧ����Ϣbean
			bean.setClassinfoid(classInfoId);
			bean.setStdinfocode(stdinfocode);
			bean.setStdinfoname(stdinfoname);
			bean.setStdinfosex(stdinfosex);
			bean.setStdinfocard(stdinfocard);
			bean.setStdinfobirthd(stdinfobirthd);
			bean.setStdinfonatns(stdinfonatns);
			bean.setStdinfotel(stdinfotel);
			bean.setStdinfoemail(stdinfoemail);
			//���ѧ����Ϣ
			int result = service.add(bean);
			//������ؽ��Ϊ0�����������ʧ��
			if(result==0) {
				out.print("<script>alert('���ѧ����Ϣʧ�ܣ�');history.back();</script>");		
			}
			else {
				//����������ݳɹ� 
				out.print("<script>alert('���ѧ����Ϣ�ɹ���');window.location.href='studentInfo?method=list';</script>");
			}
		}else {
			//�����򷵻�ҳ����ʾ��ѧ������Ѵ���
			out.print("<script>alert('ѧ������Ѵ��ڣ����������룡');history.back();</script>");
		}
	}
	
	/**
	 * ����ѧ����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateStuInfoMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��ȡѧ����Ϣid
		String stdinfoid = request.getParameter("stdinfoid");
		//��ȡѧ����Ϣ���
		String stdinfocode = request.getParameter("stdinfocode");
		//��ȡѧ������
		String stdinfoname = request.getParameter("stdinfoname");
		//��ȡ�Ա�
		String stdinfosex = request.getParameter("stdinfosex");
		//��ȡ���֤��
		String stdinfocard = request.getParameter("stdinfocard");
		//��ȡ��������
		String stdinfobirthd = request.getParameter("stdinfobirthd");
		//��ȡ����
		String stdinfonatns = request.getParameter("stdinfonatns");
		//��ȡ�绰����
		String stdinfotel = request.getParameter("stdinfotel");
		//��ȡ��������
		String stdinfoemail = request.getParameter("stdinfoemail");
		//��ȡ�༶id
		String classInfoId = request.getParameter("classInfoId");
		//��ȡѧ����Ϣbean
		StudentInfoBean bean = new StudentInfoBean();
		//���������ѧ����Ϣbean
		bean.setStdinfoid(stdinfoid);//����ѧ����Ϣid
		bean.setClassinfoid(classInfoId);//���ð༶��Ϣid
		bean.setStdinfocode(stdinfocode);//����ѧ�����
		bean.setStdinfoname(stdinfoname);//����ѧ������
		bean.setStdinfosex(stdinfosex);//�����Ա�
		bean.setStdinfocard(stdinfocard);//�������֤��
		bean.setStdinfobirthd(stdinfobirthd);//���ó�������
		bean.setStdinfonatns(stdinfonatns);//��������
		bean.setStdinfotel(stdinfotel);//���õ绰����
		bean.setStdinfoemail(stdinfoemail);//���õ�������
		//��ȡѧ����Ϣ����,����ѧ����Ϣ
		int result = service.update(bean);
		//�������ֵ����0�������ʧ��
		if(result==0) {
			out.print("<script>alert('����ѧ����Ϣʧ�ܣ�');history.back();</script>");
		}else {
			//�������ֵ����1������³ɹ�
			out.print("<script>alert('����ѧ����Ϣ�ɹ���');window.location.href='studentInfo?method=list';</script>");
		}
	}
	
	/**
	 * ɾ��ѧ����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		//��ȡѧ����Ϣid
		String stdinfoid = request.getParameter("stdinfoid");
		StudentInfoBean bean = new StudentInfoBean();
		bean.setStdinfoid(stdinfoid);
		@SuppressWarnings("unchecked")
		//ͨ��ѧ����Ϣid����ѧ���Ƿ����ѡ����Ϣ
		List<StudentInfoBean> stuList = (List<StudentInfoBean>) service.select(bean);
		//ѧ��������ѡ����Ϣ������ɾ��
		if(Integer.parseInt(stuList.get(0).getCount())==0) {
			//ͨ��ѧ������idɾ��ѧ����Ϣ
			int result = service.delete(Integer.parseInt(stdinfoid));
			if(result==0) {
				out.print("<script>alert('ɾ��ѧ����Ϣʧ��');history.back();</script>");
			}
			else {
				out.print("<script>alert('ɾ��ѧ����Ϣ�ɹ�');window.location.href='studentInfo?method=list';</script>");
			}
		}
		else {
			//ѧ������ѡ����Ϣ������ɾ��
			out.print("<script>alert('ɾ��ѧ����Ϣʧ�ܣ�ѧ������ѡ����Ϣ��ֹɾ����');history.back();</script>");
		}
	}
	
	/**
	 * ����ָ����Ϣ��ѯѧ����Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡԺϵid
		String depId = request.getParameter("depId");
		//��ȡרҵid
		String spId = request.getParameter("spId");
		//��ȡ�༶id
		String classId = request.getParameter("classId");
		//��ȡѧ������
		String stuName = request.getParameter("stuName");
		//��ȡѧ����Ϣ����
		StudentInfoBean stuBean = new StudentInfoBean();
		//����Ϣ����ѧ����Ϣ����
		stuBean.setClassinfoid(classId);
		stuBean.setStdinfoname(stuName);
		//���ݲ�ѯ������ѯ��Ӧ��ѧ����Ϣ
		@SuppressWarnings("unchecked")
		List<StudentInfoBean> stuList = (List<StudentInfoBean>) service.select(stuBean);
		//רҵ��Ϣ����
		SpecilInfoService spService = new SpecilInfoService();
		//��ѯרҵ��Ϣ,����רҵ��Ϣ�б�
		List<SpecilinfoBean> spList = spService.select(new SpecilinfoBean());	
		//�༶��Ϣ����
		ClassInfoService classService = new ClassInfoService();
		@SuppressWarnings("unchecked")
		//��ѯ�༶��Ϣ�����ذ༶��Ϣ�б�
		List<ClassInfoBean> classList = (List<ClassInfoBean>) classService.select(new ClassInfoBean());
		//����ѯ��ָ�����û���Ϣ��������������
		request.setAttribute("stuList", stuList);
		//��Ժϵid��������������
		request.setAttribute("depId", depId);
		//��רҵid��������������
		request.setAttribute("spId", spId);
		//���༶id��������������
		request.setAttribute("classId", classId);
		//��רҵ�б�������������
		request.setAttribute("spList", spList);
		//���γ��б�������������
		request.setAttribute("classList", classList);
		request.getRequestDispatcher("pages/jsp/StudentInfo/stuList.jsp").forward(request, response);
		
	}
	
	/**
	 * 			�Զ����addEditStu��ѧ�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void code(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		//��ѯѧ����Ϣ�б���code�����ֵ
		int code = service.max();
		//��ȡ��Ӧ��
		PrintWriter out = response.getWriter();
		out.print(code);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
