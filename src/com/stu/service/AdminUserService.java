package com.stu.service;

import com.stu.bean.AdminUserBean;
import com.stu.dbconn.DBConnection;
import com.stu.utils.Md5Encrypt;

import java.sql.SQLException;

public class AdminUserService extends DBConnection {

	//�޸��û�ͷ�����
	public int updateimg(String imagename,String username) {
		int result = 0;
		
		String sql = "update adminuser set imagepath = ? where adminusername = ?";
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, imagename);
			pst.setString(2, username);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn();
		}
		
		return result;
	}
	
	//�޸��û���Ϣ����
	public int updateMsg(AdminUserBean userBean) {
		int result = 0;
		
		String sql = "update adminuser set truename = ?,sex = ? where adminusername = ?";
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, userBean.getTruename());
			pst.setString(2, userBean.getSex());
			pst.setString(3, userBean.getAdminusername());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn();
		}
		return result;
	}
	
	
	//�û�ע��ҳ�����û����Ƿ����
	public int selectUser(String username) {
		int result = 0;
		
		String sql = "select adminusername from adminuser where adminusername=?";
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn();
		}
		return result;
	}
	
	//ע���û�����
	public int add(AdminUserBean userBean) {
		int result = 0;
		
		//1.��д���ݿ����
		String sql = "insert into adminuser(adminusername,adminuserpwd,truename,sex,imagepath) values(?,?,?,?,?)";
		//2.��ȡ���ݿ�����
		conn = getDBConnect();
		
		try {
			//3.��ȡԤ�������
			pst = conn.prepareStatement(sql);
			
			//4.����ռλ��
			pst.setString(1, userBean.getAdminusername());
			pst.setString(2, Md5Encrypt.MD5(userBean.getAdminuserpwd()));
			pst.setString(3, userBean.getTruename());
			pst.setString(4, userBean.getSex());
			pst.setString(5, userBean.getImagepath());
			//5.�ύSQL���
			result = pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ر���������
			super.closeConn();
		}

		return result;
	}
	
	
	//�û���½����
	public AdminUserBean select(String username,String pwd) {
		
		AdminUserBean bean = null;
		
		String sql = "select adminusername,truename,sex,imagepath from adminuser where adminusername = ? and adminuserpwd = ?";
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pwd);
			rs = pst.executeQuery();
			if(rs.next()) {
				bean = new AdminUserBean();
				bean.setAdminusername(rs.getString(1));
				bean.setTruename(rs.getString(2));
				bean.setSex(rs.getString(3));
				bean.setImagepath(rs.getString(4));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn();
		}
		return bean;
	}
	
	public int updatePwd(String newpwd,String username,String oldpwd) {
		int result = 0;
		
		String sql = "update adminuser set adminuserpwd = ? where adminusername = ? and adminuserpwd = ?";
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, Md5Encrypt.MD5(newpwd));
			pst.setString(2, username);
			pst.setString(3, Md5Encrypt.MD5(oldpwd));
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConn();
		}
		
		
		return result;
	}
}






















