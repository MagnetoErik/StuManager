package com.stu.dbconn;

import java.sql.*;

public class DBConnection {
	//�������Ӷ���
	protected Connection conn = null;
	//ִ�о�̬SQL�Ķ���
	protected Statement st;
	//Ԥ�������
	protected PreparedStatement pst;
	//ִ�в�ѯ����Ľ����
	protected ResultSet rs;
	
	public Connection getDBConnect(){
		
		try {
			Class.forName(ReaderProp.DRIVER);
			conn = DriverManager.getConnection(ReaderProp.URL, ReaderProp.USERNAME, ReaderProp.PWD);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("���ݿ�����ʧ��");
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public void closeConn() {
	
			try {
				if(rs!=null)
					rs.close();
				if(st!=null)
					st.close();
				if(pst!=null)
					pst.close();
				if(conn!=null)
					conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
