package com.stu.service;

import com.stu.bean.CountBean;
import com.stu.dbconn.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountService extends DBConnection {

	/**
	 * ͳ��Ժϵ����
	 * @return  Ժϵ�����б�
	 */
	public List<CountBean> countDep() {
		List<CountBean> i = new ArrayList<CountBean>();
		
		//ƴ��sql���
		String sql = "select dep.depinfoid,COUNT(*) value,dep.depinfoname name" + 
				"	from studentinfo stu join classinfo class on stu.classinfoid = class.classinfoid" + 
				"		join specilinfo sp on sp.spilinfoid = class.spilinfoid" + 
				"		join depinfo dep on dep.depinfoid = sp.depinfoid" + 
				" GROUP BY dep.depinfoid";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//ִ��sql���
			rs = pst.executeQuery();
			while(rs.next()) {
				CountBean bean = new CountBean();
				bean.setIndex(rs.getInt(1)+"");
				bean.setCount(rs.getInt(2)+"");
				bean.setDepName(rs.getString(3));
				i.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return i;
	}
	
	/**
	 *  ͳ�Ƹ���Ժϵ��רҵ�����б�
	 * @param id Ժϵid
	 * @return  רҵ�����б�
	 */
	public List<CountBean> countSp(int id) {
		List<CountBean> i = new ArrayList<CountBean>();
		
		//ƴ��sql���
		String sql = "select sp.spilinfoid,COUNT(*) value,sp.spilinfoname name,dep.depinfoname depname" + 
				"	from studentinfo stu join classinfo class on stu.classinfoid = class.classinfoid" + 
				"			join specilinfo sp on sp.spilinfoid = class.spilinfoid" + 
				"			join depinfo dep on dep.depinfoid = sp.depinfoid" + 
				"	where dep.depinfoid=?" + 
				"	GROUP BY sp.spilinfoid";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			//ִ��sql���
			rs = pst.executeQuery();
			while(rs.next()) {
				CountBean bean = new CountBean();
				bean.setIndex(rs.getInt(1)+"");
				bean.setCount(rs.getInt(2)+"");
				bean.setSpName(rs.getString(3));
				bean.setDepName(rs.getString(4));
				i.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return i;
	}
	
	/**
	 * ͳ�Ƹ���רҵ����Ů����
	 * @param id  רҵid
	 * @return  ��Ů����
	 */
	public List<CountBean> countSex(int id) {
		List<CountBean> i = new ArrayList<CountBean>();
		
		//ƴ��sql���
		String sql = "SELECT stu.stdinfosex,count(*)" + 
				"	from studentinfo stu join classinfo class USING(classinfoid)" + 
				"	join specilinfo sp USING(spilinfoid)" + 
				" where sp.spilinfoid = ?" + 
				" GROUP BY stu.stdinfosex";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, id);
			//ִ��sql���
			rs = pst.executeQuery();
			//����������ݽ��������ݴ���list
			while(rs.next()) {
				//��ȡͳ��bean��ʱ����
				CountBean bean = new CountBean();
				//�����ݿ��ֵ����ͳ��bean
				bean.setSex(rs.getString(1));//�Ա�
				bean.setCount(rs.getInt(2)+"");//��Ů����
				//����ʱbean����list��
				i.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر����ݿ�����
			closeConn();
		}
		return i;
	}
}
