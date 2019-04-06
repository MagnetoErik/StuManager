package com.stu.service.imp;

import java.util.List;

public interface ObjectServiceImp {
	
	/**
	 * 
	 * @param bean
	 * @return
	 */
	public int add(Object obj);
	
	public List<?> select(Object obj);
	
	public int update(Object obj);
	
	public int delete(int objId);
}
