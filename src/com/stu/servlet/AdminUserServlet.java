package com.stu.servlet;

import com.stu.bean.AdminUserBean;
import com.stu.service.AdminUserService;
import com.stu.utils.Md5Encrypt;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * �û���Ϣ������
 * @author Administrator
 *
 */

@WebServlet("/adminUser")
public class AdminUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("");
		if("updateImg".equals(method)) {
			updateImg(request, response);
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("updateImg".equals(method)) {
			updateImg(request, response);	
		}
		else if ("updateMsg".equals(method)) {
			updateMsg(request, response);
		}
		else if ("updatePwd".equals(method)) {
			updatePwd(request, response);
		}
		else if("submit".equals(method)) {
			submit(request, response);
		}
		else if ("usernameCheck".equals(method)) {
			usernameCheck(request, response);
		}
		else if ("regUser".equals(method)) {
			regUser(request, response);
		}
	}

	/**
	 * �޸��û�ͷ����Ϣ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileupload = new ServletFileUpload(factory);
		String path = getServletContext().getRealPath("");		
		PrintWriter out = response.getWriter();
		try {
			List<FileItem> itemList = fileupload.parseRequest(request);
			for (FileItem fileItem : itemList) {
				if(fileItem.isFormField()) {
					
				}
				else {
					HttpSession session = request.getSession();
					AdminUserBean bean = (AdminUserBean)session.getAttribute("userBean");
					String username = bean.getAdminusername();
					String filename = fileItem.getName();
					String[] filenames = filename.split("\\.");
					filename = "fileupload/"+ username + "." + filenames[1];
					fileItem.write(new File(path+"/"+filename));
					AdminUserService service = new AdminUserService();
					int result = service.updateimg(filename, username);
					if(result==0){
						out.print("<script>alert('�޸�ʧ��');history.back();</script>");
					}
					else {
						bean.setImagepath(filename);
						out.print("<script>alert('�޸ĳɹ�');window.location.href='pages/jsp/modifyInfo.jsp'</script>");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * �޸��û���Ϣ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileupload = new ServletFileUpload(factory);
		String path = getServletContext().getRealPath("");
		PrintWriter out = response.getWriter();
		String truename = request.getParameter("trueName");
		String sex = request.getParameter("sex");
		String username = request.getParameter("adminusername");
		AdminUserService service = new AdminUserService();
		HttpSession session = request.getSession();
		AdminUserBean userBean = (AdminUserBean)session.getAttribute("userBean");
		userBean.setTruename(truename);
		userBean.setSex(sex);
		int result = service.updateMsg(userBean);
		if(result==0) {
			out.print("<script>alert('��Ϣ�޸�ʧ��');window.location.href='pages/jsp/modifyInfo.jsp';</script>");
		}
		else {
			out.print("<script>alert('��Ϣ�޸ĳɹ�');window.location.href='pages/jsp/modifyInfo.jsp';</script>");
		}
			
	}
	
	/**
	 * �޸��û�������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		AdminUserBean userBean = (AdminUserBean)session.getAttribute("userBean");
		String username = userBean.getAdminusername();
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		String reppwd = request.getParameter("reppwd");
		AdminUserService sevice = new AdminUserService();
		int result = sevice.updatePwd(newpwd,username,oldpwd);
		if(result==0) {
			out.print("<script>alert('�޸�ʧ��,ԭ����������������룡');window.location.href='pages/jsp/modifyPwd.jsp'</script>");
		}
		else {
			out.print("<script>alert('�޸ĳɹ�');window.location.href='pages/jsp/modifyPwd.jsp'</script>");
		}
			
	}
	
	
	/**
	 * �û���¼��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void submit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String pwd = Md5Encrypt.MD5(request.getParameter("password"));
		String imgverify = request.getParameter("verify");
		AdminUserService service = new AdminUserService();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String verify = (String)session.getAttribute("rand");
		AdminUserBean userBean = service.select(username,pwd);
		try {
			if(verify.equals(imgverify)) {
				if(userBean!=null) {
					session.setAttribute("userBean", userBean);
					out.print(1);	
				}
				else {
					out.print(2);
				}
			}
			else {
				out.print(0);
			}
			
		}catch(NullPointerException e) {
			out.print(3);
		}catch(Exception e) {
			out.print(4);
		}
		
		
	}
	
	/**
	 * ����û�����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void usernameCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		AdminUserService service = new AdminUserService();
		int result = service.selectUser(username);
		out.print(result);

			
	}
	
	/**
	 * �û�ע����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserBean userBean = new AdminUserBean();
		//�����ļ��ӹ���
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//�����ļ�������
		ServletFileUpload fileupload = new ServletFileUpload(factory);
		//��ȡ�ϴ��ļ���·��
		String path = getServletContext().getRealPath("");
		PrintWriter out = response.getWriter();
		try {
			//��������������Ŀؼ��б�
			List<FileItem> itemList = fileupload.parseRequest(request);
			//��������
			for (FileItem fileItem : itemList) {
				if(fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String val = fileItem.getString("UTF-8");
					if("username".equals(name)) {
						userBean.setAdminusername(val);				
					}
					else if("password".equals(name)) {
						userBean.setAdminuserpwd(val);
					}
					else if("trueName".equals(name)) {
						userBean.setTruename(val);
					}
					else if("user_sex".equals(name)) {
						userBean.setSex(val);
					}
				}
				else {
					
					String filename = fileItem.getName();
					String[] filenames = filename.split("\\.");
					String newfilename = userBean.getAdminusername() + "." + filenames[1];
					userBean.setImagepath("fileupload/"+newfilename);
					fileItem.write(new File(path + "/fileupload/" + newfilename));
				}
			}
			
			AdminUserService service = new AdminUserService();
			int result = service.add(userBean);
			
			if(result==0) {		
				out.print("<script>alert('ע��ʧ��');history.back();</script>");
			}
			else {
				out.print("<script>alert('ע��ɹ�');window.location.href='index.jsp';</script>");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
	}
	
	
	
	
	
	
}
