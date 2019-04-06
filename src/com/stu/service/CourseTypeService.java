package com.stu.service;

import com.stu.bean.CoursrTypeBean;
import com.stu.dbconn.DBConnection;
import com.stu.service.imp.ObjectServiceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseTypeService extends DBConnection implements ObjectServiceImp {

	@Override
	public int add(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> select(Object obj) {
		//�����ռ��ϣ��洢�γ��������
		List<CoursrTypeBean> courseList = new ArrayList<CoursrTypeBean>();
		//ƴ��sql���
		StringBuffer sb = new StringBuffer();
		sb.append("select * from  coursetype WHERE 1=1 ");
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sb.toString());
			//ִ��sql���
			rs = pst.executeQuery();
			while(rs.next()) {
				//����ѯ���������뼯����
				CoursrTypeBean bean = new CoursrTypeBean();
				bean.setCoursetypeid(rs.getInt(1)+"");
				bean.setCoursetypename(rs.getString(2));
				courseList.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر���������
			closeConn();
		}
		return courseList;
	}

	@Override
	public int update(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int objId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
