package com.stu.service;

import com.stu.bean.TeacherInfoBean;
import com.stu.dbconn.DBConnection;
import com.stu.service.imp.ObjectServiceImp;
import com.stu.utils.ObjectUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ʦ��Ϣ������
 * @author Administrator
 *
 */
public class TeacherInfoService extends DBConnection implements ObjectServiceImp {

	/**
	 * �����Ϣ
	 */
	@Override
	public int add(Object obj) {
		TeacherInfoBean bean = (TeacherInfoBean) obj;
		int result = 0;
		//ƴ��sql���
		String sql = "insert into teachinfo(teachtypeid,depinfoid,teachinfocode,teachinfoname,teachinfosex,teachknowl,teachinfodeg,teachinfospec,teachinfotitle)" + 
		"values(?,?,?,?,?,?,?,?,?)";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, Integer.parseInt(bean.getTeachTypeId()));
			pst.setInt(2, Integer.parseInt(bean.getDepInfoId()));
			pst.setString(3, bean.getTeachInfoCode());
			pst.setString(4, bean.getTeachInfoname());
			pst.setString(5, bean.getTeachSex());
			pst.setString(6, bean.getTeachKnowl());
			pst.setString(7, bean.getTeachDeg());
			pst.setString(8, bean.getTeachSpec());
			pst.setString(9, bean.getTeachTitle());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		
		return result;
	}
	
	
	/**
	 * ��ѯ��Ϣ
	 */
	@Override
	public List<?> select(Object obj) {
		List<TeacherInfoBean> teacherList = new ArrayList<TeacherInfoBean>();
		TeacherInfoBean bean = (TeacherInfoBean) obj;
		//ƴ��sql���
		StringBuffer sb = new StringBuffer();
		sb.append("select type.teachtypename,d.depinfoname,t.*,tc.teachcourseid from teachinfo t join teachtype type USING(teachtypeid) join depinfo d USING(depinfoid) left join teachcourse tc USING(teachinfoid) where 1=1 ");
		
		if(ObjectUtils.isEmpty(bean.getTeachInfoId())) {//ͨ��������ѯ
			sb.append(" and teachinfoid = "+bean.getTeachInfoId());
		}
		if(ObjectUtils.isEmpty(bean.getTeachTypeId())){//ͨ����ʦ����ѯ
			sb.append(" and teachtypeid = "+bean.getTeachTypeId());
		}
		if(ObjectUtils.isEmpty(bean.getDepInfoId())) {//ͨ��Ժϵid��ѯ
			sb.append(" and depinfoid = "+bean.getDepInfoId());
		}
		if(ObjectUtils.isEmpty(bean.getTeachInfoname())) {//ͨ����ʦ������ѯ
			sb.append(" and teachinfoname like '%"+bean.getTeachInfoname()+"%'");
		}
		if(ObjectUtils.isEmpty(bean.getTeachInfoCode())) {//ͨ����ʦ��Ų�ѯ
			sb.append(" and teachinfocode = '"+bean.getTeachInfoCode()+"'");
		}
		
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sb.toString());
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				TeacherInfoBean tBean = new TeacherInfoBean();
				tBean.setTeachTypeName(rs.getString(1));
				tBean.setDepInfoName(rs.getString(2));
				tBean.setTeachInfoId(rs.getInt(3)+"");
				tBean.setTeachTypeId(rs.getInt(4)+"");
				tBean.setDepInfoId(rs.getInt(5)+"");
				tBean.setTeachInfoCode(rs.getString(6));
				tBean.setTeachInfoname(rs.getString(7));
				tBean.setTeachSex(rs.getString(8));
				tBean.setTeachKnowl(rs.getString(9));
				tBean.setTeachDeg(rs.getString(10));
				tBean.setTeachSpec(rs.getString(11));
				tBean.setTeachTitle(rs.getString(12));
				tBean.setTeachRmk(rs.getString(13));
				tBean.setTeachcourseid(rs.getInt(14)+"");
				teacherList.add(tBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return teacherList;
	}

	/**
	 * ������Ϣ
	 */
	@Override
	public int update(Object obj) {
		TeacherInfoBean bean = (TeacherInfoBean) obj;
	
		int result = 0;
		
		//ƴ��sql���
		String sql = "update teachinfo set teachtypeid = ?,depinfoid = ?,teachinfocode = ?,teachinfoname = ?,teachinfosex = ?,teachknowl = ?,teachinfodeg = ?,teachinfospec = ?,teachinfotitle = ? where teachinfocode = ?";
		
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, Integer.parseInt(bean.getTeachTypeId()));
			pst.setInt(2, Integer.parseInt(bean.getDepInfoId()));
			pst.setString(3, bean.getTeachInfoCode());
			pst.setString(4, bean.getTeachInfoname());
			pst.setString(5, bean.getTeachSex());
			pst.setString(6, bean.getTeachKnowl());
			pst.setString(7, bean.getTeachDeg());
			pst.setString(8, bean.getTeachSpec());
			pst.setString(9, bean.getTeachTitle());
			pst.setString(10, bean.getTeachInfoCode());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return result;
	}

	
	/**
	 * ɾ����Ϣ
	 */
	@Override
	public int delete(int objId) {
		int result = 0;
		
		//ƴ��sql���
		String sql = "delete from teachinfo where teachInfoId = ?";
		
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, objId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
	
		return result;
	}

	
}
