package com.stu.service;


import com.stu.bean.ClassInfoBean;
import com.stu.dbconn.DBConnection;
import com.stu.service.imp.ObjectServiceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * �༶��Ϣ������
 * @author Administrator
 *
 */
public class ClassInfoService  extends DBConnection implements ObjectServiceImp{

	
	/**
	 * ��Ӱ༶��Ϣ
	 */
	@Override
	public int add(Object obj) {
		int result = 0;
		
		ClassInfoBean bean = (ClassInfoBean) obj;
		
		String sql = "insert into classinfo(spilinfoid,classinfocode,classinfoname,classinfosum,classinformk) values(?,?,?,?,?)";
		
		conn = getDBConnect();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(bean.getSpilinfoId()));
			pst.setString(2, bean.getClassInfoCode());
			pst.setString(3, bean.getClassInfoName());
			pst.setInt(4, Integer.parseInt(bean.getClassInfosum()));
			pst.setInt(5, Integer.parseInt(bean.getClassinformk()));
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
	 * ��ѯ�༶��Ϣ�б�
	 */
	@Override
	public List<?> select(Object obj) {
		List<ClassInfoBean> classList = new ArrayList<ClassInfoBean>();
		ClassInfoBean bean = (ClassInfoBean)obj;
		StringBuffer sb = new StringBuffer();
		
		sb.append("select d.depinfoid,d.depinfoname,s.spilinfoname,c.* from classinfo c join specilinfo s on c.spilinfoid = s.spilinfoid join depinfo d on d.depinfoid = s.depinfoid where 1 = 1 ");
		
		if(bean.getClassInfoID()!=null&&!"".equals(bean.getClassInfoID())){
			//������Ϊ��
			sb.append(" and c.classinfoid = "+bean.getClassInfoID());
		}else if(bean.getSpilinfoId()!=null&&!"".equals(bean.getSpilinfoId())){
			//רҵ��Ϣid��Ϊ��
			sb.append(" and c.spilinfoid = "+bean.getSpilinfoId());
			if(bean.getDepinfoid()!=null&&!"".equals(bean.getDepinfoid())) {
				//Ժϵid��Ϊ��
				sb.append(" and d.depinfoid = "+bean.getDepinfoid());
			}
		}else if(bean.getClassInfoCode()!=null&&!"".equals(bean.getClassInfoCode())){
			//�༶���Ų�Ϊ��
			sb.append(" and c.classinfocode = "+bean.getClassInfoCode());
		}else if(bean.getClassInfoName()!=null&&!"".equals(bean.getClassInfoName())){
			//�༶���Ʋ�Ϊ��
			sb.append(" and c.classinfoname = "+bean.getClassInfoName());
		}else if(bean.getClassInfosum()!=null&&!"".equals(bean.getClassInfosum())){
			//�༶��������Ϊ��
			sb.append(" and c.classinfosun = "+bean.getClassInfosum());
		}else if (bean.getDepinfoid()!=null&&!"".equals(bean.getDepinfoid())) {
			//Ժϵid��Ϊ��
			sb.append(" and d.depinfoid = "+bean.getDepinfoid());
		}
		
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		//��ȡԤ�������
		try {
			pst = conn.prepareStatement(sb.toString());
			rs = pst.executeQuery();
			while(rs.next()){
				ClassInfoBean cbean = new ClassInfoBean();
				cbean.setDepinfoid(rs.getInt(1)+"");
				cbean.setDepinfoname(rs.getString(2));
				cbean.setSpilinfoname(rs.getString(3));
				cbean.setClassInfoID(rs.getInt(4)+"");
				cbean.setSpilinfoId(rs.getInt(5)+"");
				cbean.setClassInfoCode(rs.getString(6));
				cbean.setClassInfoName(rs.getString(7));
				cbean.setClassInfosum(rs.getInt(8)+"");
				cbean.setClassinformk(rs.getString(9));
				classList.add(cbean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//�ر����ݿ�����
			closeConn();
		}

		return classList;
	}

	
	/**
	 * �޸İ༶��Ϣ
	 */
	@Override
	public int update(Object obj) {
		int result = 0;
		//�����ǿ��ת��ΪClassInfoBean
		ClassInfoBean bean = (ClassInfoBean)obj;
		//ƴ��sql���
		String sql = " update classinfo set classInfoCode = ?,classInfoName = ?,classInfosum = ?,classinformk = ?,spilinfoid = ? where classInfoID = ?";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			pst.setString(1,bean.getClassInfoCode());
			pst.setString(2, bean.getClassInfoName());
			pst.setInt(3, Integer.parseInt(bean.getClassInfosum()));
			pst.setInt(4, Integer.parseInt(bean.getClassinformk()));
			pst.setInt(5, Integer.parseInt(bean.getSpilinfoId()));
			pst.setInt(6, Integer.parseInt(bean.getClassInfoID()));
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
	 * ɾ���༶��Ϣ
	 */
	@Override
	public int delete(int objId) {
		int result = 0;
		//ƴ��sql���
		String sql = "delete from classinfo where classinfoid = ?";
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
