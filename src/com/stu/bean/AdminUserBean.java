package com.stu.bean;

import java.util.Date;


/**
 * �û���Ϣ��
 * @author Administrator
 *
 */
public class AdminUserBean {

	private int adminuserid;
	
	private String adminusername;
	
	private String adminuserpwd;
	
	private Date adminusertime;
	
	private String truename;
	
	private String sex;
	
	private String imagepath;

	public int getAdminuserid() {
		return adminuserid;
	}

	public void setAdminuserid(int adminuserid) {
		this.adminuserid = adminuserid;
	}

	public String getAdminusername() {
		return adminusername;
	}

	public void setAdminusername(String adminusername) {
		this.adminusername = adminusername;
	}

	public String getAdminuserpwd() {
		return adminuserpwd;
	}

	public void setAdminuserpwd(String adminuserpwd) {
		this.adminuserpwd = adminuserpwd;
	}

	public Date getAdminusertime() {
		return adminusertime;
	}

	public void setAdminusertime(Date adminusertime) {
		this.adminusertime = adminusertime;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
}
