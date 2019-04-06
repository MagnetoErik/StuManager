package com.stu.utils;

import java.lang.reflect.Method;

public class ObjectUtils {

	
	/**
	 * 鍔ㄦ�佺粰鐢ㄦ埛瀹炰綋瀵硅薄璁剧疆鍊�
	 * @param fieldName 灞炴�у悕绉�
	 * @param value     灞炴�ц缃唴瀹�
	 */
	public static void setFieldVal(String fieldName,String value,Object obj){
		try {
			//鑾峰彇鏂规硶瀵硅薄
			Method m=obj.getClass().getMethod("set"+change(fieldName),String.class);
			//鍔ㄦ�佽皟鐢╱serBean涓殑鏂规硶
			m.invoke(obj,value);
		} catch (NoSuchMethodException e) {
			System.out.println("鍔ㄦ�佽皟鐢ㄦ柟娉曞け璐�,鏈壘鍒板搴旀柟娉�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("鍐呴儴閿欒");
		}
	}
	
	/**
	 * 鎶婂睘鐨勯瀛楁瘝鏀规垚澶у啓
	 * @param str
	 * @return
	 */
	private static String change(String str){
		return str.substring(0,1).toUpperCase()+str.substring(1);
				
	}
	
	
	public static boolean isEmpty(String str) {
		if(str!=null&&!"".equals(str)) {
			return true;
		}
		return false;
	}
}
