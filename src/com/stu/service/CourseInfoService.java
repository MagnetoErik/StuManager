package com.stu.service;

import com.stu.bean.CourseInfoBean;
import com.stu.dbconn.DBConnection;
import com.stu.service.imp.ObjectServiceImp;
import com.stu.utils.ObjectUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * �γ���Ϣ������
 * @author Administrator
 *
 */
public class CourseInfoService extends DBConnection implements ObjectServiceImp {

	@Override
	public int add(Object obj) {
		int result = 0;
		//��objǿ��ת���ɿγ���Ϣbean
		CourseInfoBean bean = (CourseInfoBean) obj;
		//ƴ��sql���
		String sql = "insert into courseinfo(courtypeid,courseinfocode,courseinfoname,courseinfoproj,courseinforstper,courseinfopraper,courseinfocrehor) values(?,?,?,?,?,?,?)";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(bean.getCourseTypeId()));//�γ����ID
			pst.setString(2, bean.getCourseInfoCode());//�γ̴���
			pst.setString(3, bean.getCourseInfoName());//�γ�����
			pst.setString(4, bean.getCourseInfoProj());//�γ̽���
			if(ObjectUtils.isEmpty(bean.getCourseInfoRstPer())) {//�����Ϊ�����������
				pst.setInt(5, Integer.parseInt(bean.getCourseInfoRstPer()));//����ѧʱ
			}else {
				pst.setInt(5, 0);//����ѧʱ
			}
			if(ObjectUtils.isEmpty(bean.getCourseInfoPraPer())) {//�����Ϊ�����������
				pst.setInt(6, Integer.parseInt(bean.getCourseInfoPraPer()));//ʵ��ѧʱ
			}else {
				pst.setInt(6, 0);//ʵ��ѧʱ
			}
			if(ObjectUtils.isEmpty(bean.getCourseInfoCreHor())) {//�����Ϊ�����������
				pst.setInt(7, Integer.parseInt(bean.getCourseInfoCreHor()));//�γ�ѧ��
			}else {
				pst.setInt(7, 0);//�γ�ѧ��
			}
			//ִ��sql���
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

	@Override
	public List<?> select(Object obj) {
		//��������List
		List<CourseInfoBean> courseList = new ArrayList<CourseInfoBean>();
		//�����ǿ��ת��Ϊ�γ���Ϣʵ��
		CourseInfoBean courseBean = (CourseInfoBean) obj;
		//ƴ��sql���
		StringBuffer sb = new StringBuffer();
		sb.append("select ct.coursetypename,c.*,ct.coursetypeid from courseinfo c join coursetype ct on c.courtypeid = ct.coursetypeid where 1=1 ");
		
		if(ObjectUtils.isEmpty(courseBean.getCourseTypeId())) {
			//ͨ���γ����id��ѯ
			sb.append(" and c.courtypeid = "+courseBean.getCourseTypeId());
		}
		if(ObjectUtils.isEmpty(courseBean.getCourseInfoName())) {
			//ͨ���γ���Ϣ���Ʋ�ѯ
			sb.append(" and c.courseinfoname like '%"+courseBean.getCourseInfoName()+"%'");
		}
		if(ObjectUtils.isEmpty(courseBean.getCourseInfoCode())) {
			//ͨ���γ̴����ѯ
			sb.append(" and c.courseinfocode = "+courseBean.getCourseInfoCode());
		}
		if(ObjectUtils.isEmpty(courseBean.getCourseInfoId())) {
			//ͨ���γ�����id��ѯ
			sb.append(" and c.courseinfoid ="+courseBean.getCourseInfoId());
		}
		
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sb.toString());
			//ִ��sql���
			rs = pst.executeQuery();
			while(rs.next()) {
				//�����γ���Ϣ��ʱbean
				CourseInfoBean bean = new CourseInfoBean();
				//��������ڵ���Ϣ������ʱbean
				bean.setCourseTypeName(rs.getString(1));//�γ��������
				bean.setCourseInfoId(rs.getInt(2)+"");//�γ���Ϣid
				bean.setCourseTypeId(rs.getInt(3)+"");//�γ����id
				bean.setCourseInfoCode(rs.getString(4));//�γ̱��
				bean.setCourseInfoName(rs.getString(5));//�γ�����
				bean.setCourseInfoProj(rs.getString(6));//�γ̽���
				bean.setCourseInfoRstPer(rs.getInt(7)+"");//����ѧʱ
				bean.setCourseInfoPraPer(rs.getInt(8)+"");//ʵ��ѧʱ
				bean.setCourseInfoCreHor(rs.getInt(9)+"");//�γ�ѧ��
				bean.setCourseInfoRMK(rs.getString(10));//��ע
				bean.setCourseTypeId(rs.getInt(11)+"");//�γ����id
				//����ʱbean����γ���Ϣ�б�
				courseList.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر����ݿ�����
			closeConn();
		}
		
		return courseList;
	}

	@Override
	public int update(Object obj) {
		int result = 0;
		//��objǿ��ת��Ϊ�γ���ϢBean
		CourseInfoBean bean = (CourseInfoBean) obj;
		//ƴ��sql���
		String sql = "update courseinfo set courtypeid = ?,courseInfoCode = ?,courseInfoName = ?,courseInfoProj = ?,courseInfoRstPer = ?,courseInfoPraPer = ?,courseInfoCreHor = ? where courseInfoId = ?";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, Integer.parseInt(bean.getCourseTypeId()));//�γ����ID
			pst.setString(2, bean.getCourseInfoCode());//�γ̴���
			pst.setString(3, bean.getCourseInfoName());//�γ�����
			pst.setString(4, bean.getCourseInfoProj());//�γ̽���
			pst.setInt(5, Integer.parseInt(bean.getCourseInfoRstPer()));//����ѧʱ
			pst.setInt(6, Integer.parseInt(bean.getCourseInfoPraPer()));//ʵ��ѧʱ
			pst.setInt(7, Integer.parseInt(bean.getCourseInfoCreHor()));//�γ�ѧ��
			pst.setInt(8, Integer.parseInt(bean.getCourseInfoId()));
			//ִ��sql���
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int delete(int objId) {
		int result = 0;
		//ƴ��sql���
		String sql = "delete from courseinfo where courseinfoid = ?";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, objId);
			//ִ��sql���
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//�ر����ݿ�����
			closeConn();
		}
		
		return result;
	}

	public List<?> delSelect(int i) {
		List<CourseInfoBean> courseList = new ArrayList<CourseInfoBean>();
		//ƴ��sql��� 
		String sql = "SELECT c.courseinfoid,tc.teachinfoid from courseinfo c left join teachcourse tc USING(courseinfoid) where c.courseinfoid = ?";
		//��ȡ���ݿ�����
		conn = getDBConnect();
		try {
			//��ȡԤ�������
			pst = conn.prepareStatement(sql);
			//����ռλ��
			pst.setInt(1, i);
			rs = pst.executeQuery();
			while(rs.next()) {
				CourseInfoBean cBean = new CourseInfoBean();
				cBean.setCourseInfoId(rs.getInt(1)+"");
				cBean.setTeachInfoId(rs.getInt(2)+"");
				courseList.add(cBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return courseList;
	}
}
