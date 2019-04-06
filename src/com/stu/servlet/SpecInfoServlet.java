package com.stu.servlet;

import com.stu.bean.ClassInfoBean;
import com.stu.bean.SpecilinfoBean;
import com.stu.service.ClassInfoService;
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
 * רҵ��Ϣ������
 * @author Administrator
 *
 */
@WebServlet("/specInfo")
public class SpecInfoServlet extends HttpServlet {

	SpecilInfoService service = new SpecilInfoService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("method");
		if("list".equals(method)) {//��ѯȫ��רҵ��Ϣ�б�
			searchSpecilList(request, response);
		}
		else if("searchSpecilList".equals(method)) {//��ѯ�û�ָ����רҵ��Ϣ
			searchSpecilList(request, response);
		}
		else if("updateSpecilInfoForWord".equals(method)) {//�༭רҵ��Ϣת����ָ����jspҳ��
			updateSelectSpecilMsg(request, response);
		}
		else if ("updateSpecilInfoMsg".equals(method)) {
			updateSpecilInfoMsg(request, response);
		}
		else if("add".equals(method)) {//���רҵ��Ϣ
			addSpecilInfo(request, response);
			
		}
		else if("delete".equals(method)) {//ɾ��רҵ��Ϣǰ�Ȳ�ѯרҵ����û�а༶��Ϣ
			deleteMsg(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("list".equals(method)) {//��ѯȫ��רҵ��Ϣ�б�
			searchSpecilList(request, response);
		}
		else if("searchSpecilList".equals(method)) {//��ѯ�û�ָ����רҵ��Ϣ
			searchSpecilList(request, response);
		}
		else if("updateSpecilInfoForWord".equals(method)) {//�༭רҵ��Ϣת����ָ����jspҳ��
			updateSelectSpecilMsg(request, response);
		}
		else if ("updateSpecilInfoMsg".equals(method)) {//����רҵ��Ϣ
			updateSpecilInfoMsg(request, response);
		}
		else if("add".equals(method)) {//���רҵ��Ϣ
			addSpecilInfo(request, response);
			
		}
		else if("delete".equals(method)) {//ɾ��רҵ��Ϣǰ�Ȳ�ѯרҵ����û�а༶��Ϣ
			deleteMsg(request, response);
		}
		
	}

	/**
	 * 		����Ժϵ��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addSpecilInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		SpecilinfoBean Bean = new SpecilinfoBean();
		String spilinfocode = request.getParameter("spilinfocode");
		Bean.setSpilinfocode(spilinfocode);
		if(service.select(Bean).size()!=0) {
			out.print("<script>alert('����Ժϵ��Ϣʧ�ܣ�Ժϵ��Ϣ�Ѵ��ڣ�������������Ϣ');history.back();</script>");
		}
		else {
			String depinfoid = request.getParameter("depinfoid");
			String spilinfoname = request.getParameter("spilinfoname");
			String spilinfoaim = request.getParameter("spilinfoaim");
			String spilinfoprodire = request.getParameter("spilinfoprodire");
			Bean.setDepinfoid(depinfoid);
			Bean.setSpilinfocode(spilinfocode);
			Bean.setSpilinfoname(spilinfoname);
			Bean.setSpilinfoaim(spilinfoaim);
			Bean.setSpilinfoprodire(spilinfoprodire);
			int result = service.add(Bean);
			if(result==0) {
				out.print("<script>alert('����Ժϵ��Ϣʧ��');history.back();</script>");
			}
			else {
				out.print("<script>alert('����Ժϵ��Ϣ�ɹ�');window.location.href='specInfo?method=list'</script>");
			}
		}
	}
	
	/**
	 * �޸��û���Ϣ
	 * @param request  ����������
	 * @param response   ��Ӧ������
	 * @throws ServletException  Servlet�쳣�׳�
	 * @throws IOException  IO���쳣�׳�
	 */
	public void updateSpecilInfoMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		SpecilinfoBean Bean = new SpecilinfoBean();
		String spilinfocode = request.getParameter("spilinfocode");
		String spilinfoname = request.getParameter("spilinfoname");
		String spilinfoaim = request.getParameter("spilinfoaim");
		String spilinfoprodire = request.getParameter("spilinfoprodire");
		Bean.setSpilinfocode(spilinfocode);
		Bean.setSpilinfoname(spilinfoname);
		Bean.setSpilinfoaim(spilinfoaim);
		Bean.setSpilinfoprodire(spilinfoprodire);
		int result = service.update(Bean);
		if(result==0) {
			out.print("<script>alert('�޸�Ժϵ��Ϣʧ��');history.back();</script>");
		}
		else {
			out.print("<script>alert('�޸�Ժϵ��Ϣ�ɹ�');window.location.href='specInfo?method=list'</script>");
		}
	}
	

	
	/**
	 *   ��ѯԺϵ��Ϣ�б��ָ����Ժϵ��Ϣ�б�
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void searchSpecilList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpecilinfoBean Bean = new SpecilinfoBean();
		String id = request.getParameter("depid");
		String specname = request.getParameter("specname");
		Bean.setDepinfoid(id);
		Bean.setSpilinfoname(specname);
		List<SpecilinfoBean> beanList = service.select(Bean);
		request.setAttribute("id", id);
		request.setAttribute("specname", specname);
		request.setAttribute("beanList", beanList);
		request.getRequestDispatcher("pages/jsp/SpecInfo/specList.jsp").forward(request, response);
	}
	
	/**
	 * 		�û�����޸İ�ť����Ϣ����ָ����jspҳ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateSelectSpecilMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String spilinfocode = request.getParameter("spilinfocode");
		SpecilinfoBean bean = new SpecilinfoBean();
		bean.setSpilinfocode(spilinfocode);
		List<SpecilinfoBean> spList = service.select(bean);
		request.setAttribute("spList", spList.get(0));
		request.getRequestDispatcher("pages/jsp/SpecInfo/addOrEditSp.jsp").forward(request, response);
	}
	
	
	/**
	 * ɾ����Ϣ
	 * @param request  ����������
	 * @param response   ��Ӧ������
	 * @throws ServletException  Servlet�쳣�׳�
	 * @throws IOException  IO���쳣�׳�
	 */
	public void deleteMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String specilinfo = request.getParameter("specilinfo");
		ClassInfoService classService = new ClassInfoService();
		ClassInfoBean bean = new ClassInfoBean();
		bean.setSpilinfoId(specilinfo);
		@SuppressWarnings("unchecked")
		List<ClassInfoBean> classList = (List<ClassInfoBean>) classService.select(bean);
		if(classList.size()==0) {
			int result = service.delete(bean);
			if(result==0) {
				out.print("<script>alert('רҵ��Ϣɾ��ʧ��');history.back();</script>");
			}
			else {
				out.print("<script>alert('רҵ��Ϣɾ���ɹ�');window.location.href='specInfo?method=list';</script>");
			}
			
		}
		else {
			out.print("<script>alert('��רҵ�´��ڰ༶��Ϣ����ֹɾ����');history.back();</script>");
		}
	}
	
	
	/**
	 * ��ҳ��ʾҳ������ 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void selectSpInfoPages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
