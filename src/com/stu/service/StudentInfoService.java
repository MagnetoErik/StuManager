package com.stu.service;

import com.stu.bean.StudentInfoBean;
import com.stu.dbconn.DBConnection;
import com.stu.service.imp.ObjectServiceImp;
import com.stu.utils.ObjectUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentInfoService extends DBConnection implements ObjectServiceImp{

	
	/**
	 * �����û���Ϣ
	 */
	@Override
	public int add(Object obj) {
		int result = 0;
		//�����ת��Ϊѧ����Ϣbean
		StudentInfoBean stuBean = (StudentInfoBean) obj;
		//ƴ��sql���
		String sql = "insert into studentinfo(classinfoid,stdinfocode,stdinfoname,stdinfosex,stdinfocard,stdinfobirthd,stdinfonatns,stdinfotel,stdinfoemail) values(?,?,?,?,?,?,?,?,?)";
		//��ȡ���ݿ�����
		conn = getDBConnect();

		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, Integer.parseInt(stuBean.getClassinfoid()));
			pst.setString(2, stuBean.getStdinfocode());
			pst.setString(3, stuBean.getStdinfoname());
			pst.setString(4, stuBean.getStdinfosex());
			pst.setString(5, stuBean.getStdinfocard());
			pst.setString(6, stuBean.getStdinfobirthd());
			pst.setString(7, stuBean.getStdinfonatns());
			pst.setString(8, stuBean.getStdinfotel());
			pst.setString(9, stuBean.getStdinfoemail());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر�����
			closeConn();
		}
		
		return result;
	}

	/**
	 * ѧ����Ϣ�б�
	 */
	@Override
	public List<?> select(Object obj) {
		StudentInfoBean stuBean = (StudentInfoBean) obj;
		List<StudentInfoBean> stuList = new ArrayList<StudentInfoBean>();
		//ƴ��sql���
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT dep.depinfoname,sp.spilinfoname,class.classinfoname,stus.stdinfoid,stus.stdinfocode,stus.stdinfoname,stus.stdinfosex,stus.stdinfocard,stus.stdinfobirthd" + 
				"				,stus.stdinfonatns,stus.stdinfotel,stus.stdinfoemail,COUNT(course.courseinfoid)" + 
				"	from studentinfo stus LEFT JOIN classinfo class USING(classinfoid)" + 
				"	JOIN specilinfo sp USING(spilinfoid)" + 
				"	JOIN depinfo dep USING(depinfoid)" + 
				"	LEFT JOIN studentcourse sc on stus.stdinfoid = sc.studentinfoid" + 
				"	LEFT JOIN teachcourse tc USING(teachcourseid)" + 
				"	LEFT JOIN teachinfo teacher USING(teachinfoid)" + 
				"	LEFT JOIN courseinfo course USING(courseinfoid) " + 
				"	where 1=1 "
				);
		if(ObjectUtils.isEmpty(stuBean.getClassinfoid())&&ObjectUtils.isEmpty(stuBean.getStdinfoname())) {
			//ͨ���༶��Ϣid��ѧ��������ѯ
			sb.append("and stus.stdinfoname like '%"+stuBean.getStdinfoname()+"%'"+" and stus.classinfoid = " + stuBean.getClassinfoid() + " GROUP BY dep.depinfoname,sp.spilinfoname,class.classinfoname,stus.stdinfoid,stus.stdinfocode,stus.stdinfoname,stus.stdinfosex,stus.stdinfocard,stus.stdinfobirthd,stus.stdinfonatns,stus.stdinfotel,stus.stdinfoemail");
		}
		else if(ObjectUtils.isEmpty(stuBean.getStdinfoname())) {
			//ͨ��ѧ��������ѯ
			sb.append(" and stus.stdinfoname like '%"+stuBean.getStdinfoname()+"%'"
			+" GROUP BY dep.depinfoname,sp.spilinfoname,class.classinfoname,stus.stdinfoid,stus.stdinfocode,stus.stdinfoname,stus.stdinfosex,stus.stdinfocard,stus.stdinfobirthd,stus.stdinfonatns,stus.stdinfotel,stus.stdinfoemail");
		}
		else if (ObjectUtils.isEmpty(stuBean.getStdinfoid())) {
			//ͨ��ѧ��id��ѯ
			sb.append(" and stus.stdinfoid = "+stuBean.getStdinfoid()+" GROUP BY dep.depinfoname,sp.spilinfoname,class.classinfoname,stus.stdinfoid,stus.stdinfocode,stus.stdinfoname,stus.stdinfosex,stus.stdinfocard,stus.stdinfobirthd,stus.stdinfonatns,stus.stdinfotel,stus.stdinfoemail");
		}
		else if (ObjectUtils.isEmpty(stuBean.getStdinfocode())) {
			//ͨ��ѧ����Ų�ѯ
			sb.append(" and stus.stdinfocode = "+stuBean.getStdinfocode()+" GROUP BY dep.depinfoname,sp.spilinfoname,class.classinfoname,stus.stdinfoid,stus.stdinfocode,stus.stdinfoname,stus.stdinfosex,stus.stdinfocard,stus.stdinfobirthd,stus.stdinfonatns,stus.stdinfotel,stus.stdinfoemail");
		}
		else if (ObjectUtils.isEmpty(stuBean.getClassinfoid())) {
			sb.append(" and stus.classinfoid = "+stuBean.getClassinfoid()+" GROUP BY dep.depinfoname,sp.spilinfoname,class.classinfoname,stus.stdinfoid,stus.stdinfocode,stus.stdinfoname,stus.stdinfosex,stus.stdinfocard,stus.stdinfobirthd,stus.stdinfonatns,stus.stdinfotel,stus.stdinfoemail");
		}
		else {
			sb.append(" GROUP BY dep.depinfoname,sp.spilinfoname,class.classinfoname,stus.stdinfoid,stus.stdinfocode,stus.stdinfoname,stus.stdinfosex,stus.stdinfocard,stus.stdinfobirthd,stus.stdinfonatns,stus.stdinfotel,stus.stdinfoemail");
		}
		
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sb.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				//��ȡѧ����Ϣbean
				StudentInfoBean bean = new StudentInfoBean();
				//�����ݿ��ȡ��ֵ����list
				bean.setDepinfoname(rs.getString(1));
				bean.setSpilinfoname(rs.getString(2));
				bean.setClassinfoname(rs.getString(3));
				bean.setStdinfoid(rs.getInt(4)+"");
				bean.setStdinfocode(rs.getString(5));
				bean.setStdinfoname(rs.getString(6));
				bean.setStdinfosex(rs.getString(7));
				bean.setStdinfocard(rs.getString(8));
				bean.setStdinfobirthd(rs.getString(9));
				bean.setStdinfonatns(rs.getString(10));
				bean.setStdinfotel(rs.getString(11));
				bean.setStdinfoemail(rs.getString(12));
				bean.setCount(rs.getInt(13)+"");
				stuList.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر�����
			closeConn();
		}
		return stuList;
	}

	
	/**
	 * ����ѧ����Ϣ
	 */
	@Override
	public int update(Object obj) {
		int result = 0;
		//��objת��Ϊѧ����Ϣbean
		StudentInfoBean bean = (StudentInfoBean) obj;
		//ƴ��sql���
		String sql = " update studentinfo set classInfoId = ?,stdinfocode = ?,stdinfoname = ?,stdinfosex = ?,stdinfocard = ?,stdinfobirthd = ?,stdinfonatns = ?,stdinfotel = ?,stdinfoemail = ? where stdinfoid = ? ";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			System.out.println(bean.getClassinfoid());
			pst.setInt(1, Integer.parseInt(bean.getClassinfoid()));
			pst.setString(2, bean.getStdinfocode());
			pst.setString(3, bean.getStdinfoname());
			pst.setString(4, bean.getStdinfosex());
			pst.setString(5, bean.getStdinfocard());
			pst.setString(6, bean.getStdinfobirthd());
			pst.setString(7, bean.getStdinfonatns());
			pst.setString(8, bean.getStdinfotel());
			pst.setString(9, bean.getStdinfoemail());
			pst.setInt(10, Integer.parseInt(bean.getStdinfoid()));
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر�����
			closeConn();
		}

		return result;
	}

	
	/**
	 * ɾ��ѧ����Ϣ
	 */
	@Override
	public int delete(int objId) {
		int result = 0;
		//ƴ��sql���
		String sql = "delete from studentinfo where stdinfoid = ?";
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

	/**
	 * ��ȡѧ����Ϣ����code�����ֵ
	 * @return
	 */
	public int max() {
		int result = 0;
		//ƴ��sql���
		String sql = "select MAX(stdinfocode) from studentinfo";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡ���ݿ�����
			pst = conn.prepareStatement(sql);
			//ִ��sql���
			rs = pst.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1)+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ��ѯ������
	 * @return  ѧ����Ϣ���������
 	 */
	public int maxPages() {
		int result = 0;
		
		//ƴ��sql���
		String sql = "select CEILING(count(*)/16)" + 
				"	from studentinfo";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//ִ��sql���
			rs = pst.executeQuery();
			while(rs.next()) {
				//����ҳ�����������
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ��ҳ��ʾ����
	 * @param i
	 * @return
	 */
	public List<StudentInfoBean> pages(int i) {
		List<StudentInfoBean> stuList = new ArrayList<StudentInfoBean>();
		
		//ƴ��sql���
		String sql = "SELECT dep.depinfoname,sp.spilinfoname,class.classinfoname,stus.stdinfoid,stus.stdinfocode,stus.stdinfoname,stus.stdinfosex,stus.stdinfocard,stus.stdinfobirthd" + 
				"								,stus.stdinfonatns,stus.stdinfotel,stus.stdinfoemail,COUNT(course.courseinfoid)" + 
				"					from studentinfo stus LEFT JOIN classinfo class USING(classinfoid)" + 
				"					JOIN specilinfo sp USING(spilinfoid)" + 
				"					JOIN depinfo dep USING(depinfoid)" + 
				"					LEFT JOIN studentcourse sc on stus.stdinfoid = sc.studentinfoid" + 
				"					LEFT JOIN teachcourse tc USING(teachcourseid)" + 
				"					LEFT JOIN teachinfo teacher USING(teachinfoid)" + 
				"					LEFT JOIN courseinfo course USING(courseinfoid) " + 
				"					where 1=1 " + 
				"	GROUP BY stus.stdinfoid asc" + 
				"	limit ?,16";
		
		//��ȡ���ݿ�����
		conn = getDBConnect();
		
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, i);
			rs = pst.executeQuery();
			while(rs.next()) {
				StudentInfoBean bean = new StudentInfoBean();
				bean.setDepinfoname(rs.getString(1));
				bean.setSpilinfoname(rs.getString(2));
				bean.setClassinfoname(rs.getString(3));
				bean.setStdinfoid(rs.getInt(4)+"");
				bean.setStdinfocode(rs.getString(5));
				bean.setStdinfoname(rs.getString(6));
				bean.setStdinfosex(rs.getString(7));
				bean.setStdinfocard(rs.getString(8));
				bean.setStdinfobirthd(rs.getString(9));
				bean.setStdinfonatns(rs.getString(10));
				bean.setStdinfotel(rs.getString(11));
				bean.setStdinfoemail(rs.getString(12));
				bean.setCount(rs.getInt(13)+"");
				stuList.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر����ݿ�����
			closeConn();
		}
		return stuList;
	}
	
	
	
	
	
	
	
	
}
