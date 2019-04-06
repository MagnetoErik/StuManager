package com.stu.dbconn;

import java.util.Properties;

//��ȡ���ݿ������ļ�
public class ReaderProp {
	//���������ļ�
	private static Properties pop = new Properties();
	
	//ʹ�ø����Զ����ô���飬������һ��
	static {
		try {
			//���ط���
			pop.load(ReaderProp.class.getResourceAsStream("sql.pro"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�������ݿ�����
	public static String DRIVER = pop.getProperty("jdbc.driverClassName");
	
	//�����������ݿ��ַ
	public static String URL = pop.getProperty("jdbc.url");
	
	//����������
	public static String USERNAME = pop.getProperty("jdbc.username");
	
	//������������
	public static String PWD = pop.getProperty("jdbc.password");
	
	
}
