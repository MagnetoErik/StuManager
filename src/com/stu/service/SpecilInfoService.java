package com.stu.service;

import com.stu.bean.ClassInfoBean;
import com.stu.bean.SpecilinfoBean;
import com.stu.dbconn.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecilInfoService extends DBConnection {

	
	/**
	 * Ժϵ��������
	 * @param bean Ժϵ��Ϣ����
	 * @return
	 */
	public int add(SpecilinfoBean bean) {
		int result = 0;
		
		String sql = "insert into specilinfo(depinfoid,spilinfocode,spilinfoname,spilinfoaim,spilinfoprodire) values(?,?,?,?,?)";
		
		conn = getDBConnect();
		
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, bean.getDepinfoid());
			pst.setString(2, bean.getSpilinfocode());
			pst.setString(3, bean.getSpilinfoname());
			pst.setString(4, bean.getSpilinfoaim());
			pst.setString(5, bean.getSpilinfoprodire());
			result = pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConn();
		}
		return result;
	}
	

	
	/**
	 * רҵ��Ϣ�б��ѯ����
	 * @param bean  רҵ��Ϣ����
	 * @return רҵ��Ϣ����
	 */
	public List<SpecilinfoBean> select(SpecilinfoBean bean) {
		
		List<SpecilinfoBean> beanList = new ArrayList<SpecilinfoBean>();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select d.depinfoname,s.* from depinfo d join specilinfo s ON d.depinfoid = s.depinfoid");
		
			if(bean.getDepinfoid()!=null&&!"".equals(bean.getDepinfoid())) {
				sb.append(" and d.depinfoid = "+bean.getDepinfoid());
				if (bean.getSpilinfoname()!=null&&!"".equals(bean.getSpilinfoname())) {
					sb.append(" and s.spilinfoname = '"+bean.getSpilinfoname()+"'");
				}
			}
			else if (bean.getSpilinfoid()!=null&&!"".equals(bean.getSpilinfoid())) {
				sb.append(" and s.specilinfo = "+bean.getSpilinfoid());
			}
			else if (bean.getSpilinfocode()!=null&&!"".equals(bean.getSpilinfocode())) {
				sb.append(" and s.spilinfocode = "+bean.getSpilinfocode());
			}
			else if (bean.getSpilinfoname()!=null&&!"".equals(bean.getSpilinfoname())) {
				sb.append(" and s.spilinfoname = '%"+bean.getSpilinfoname()+"%'");
			}
			else if (bean.getSpilinfoaim()!=null&&!"".equals(bean.getSpilinfoaim())) {
				sb.append(" and s.spilinfoaim = "+bean.getSpilinfoaim());
			}
			else if (bean.getSpilinfoprodire()!=null&&!"".equals(bean.getSpilinfoprodire())) {
				sb.append(" and s.spilinfoprodire = "+bean.getSpilinfoprodire());
			}
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sb.toString());
			rs = pst.executeQuery();		
			while(rs.next()) {
				SpecilinfoBean beans = new SpecilinfoBean();
				beans.setDepinfoname(rs.getString(1));
				beans.setSpilinfoid(rs.getInt(2)+"");
				beans.setDepinfoid(rs.getInt(3)+"");
				beans.setSpilinfocode(rs.getString(4));
				beans.setSpilinfoname(rs.getString(5));
				beans.setSpilinfoaim(rs.getString(6));
				beans.setSpilinfoprodire(rs.getString(7));
				beanList.add(beans);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeConn();
		}
		return beanList;
	}

	
	
	/**
	 * רҵ��Ϣ������
	 * @param bean
	 * @return
	 */
	public int update(SpecilinfoBean bean) {
		int result = 0;
		
		String sql = "update specilinfo set spilinfoname = ?,spilinfoaim = ?,spilinfoprodire = ? where spilinfocode = ?";
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, bean.getSpilinfoname());
			pst.setString(2, bean.getSpilinfoaim());
			pst.setString(3, bean.getSpilinfoprodire());
			pst.setString(4, bean.getSpilinfocode());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn();
		}
		
		
		
		return result;
	}
	
	/**
	 * 		ɾ��רҵ��Ϣ����
	 * @param bean  �༶��Ϣ����
	 * @return  �������0����ɾ��ʧ�ܣ��������1����ɾ���ɹ�
	 */
	public int delete(ClassInfoBean bean) {
		int result = 0;
		
		//ƴ��sql���
		String sql = "delete from specilinfo where spilinfoid = ?";
		
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			
			//����ռλ��
			pst.setInt(1, Integer.parseInt(bean.getSpilinfoId()));
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر����ݿ�����
			closeConn();
		}
		return result;
	}
	
	/**
	 *  ��ѯԺϵ��Ϣ��ҳ��ʾ
	 * @param i ��Ҫ��ʾ��ҳ��  ÿҳ10������
	 * @return   10��Ժϵ��Ϣ��Ϣ����
	 */
	public List<SpecilinfoBean> selectSpInfoPages(int i){
		List<SpecilinfoBean> spList = new ArrayList<SpecilinfoBean>();
		
		//ƴ��sql���
		String sql = "call pages(?)";
		
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, i);
			rs = pst.executeQuery();
			while(rs.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return spList;
	}
	
	
}
