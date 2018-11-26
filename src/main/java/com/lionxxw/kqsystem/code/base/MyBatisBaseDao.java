package com.lionxxw.kqsystem.code.base;



import com.lionxxw.kqsystem.code.utils.reflect.DynamicMethod;

import java.util.Date;

/**
 * <p>Description: mybatisDao 基类 </p>
 * 
 * @author lionxxw
 * @date 16/5/6 上午8:59
 * @version 1.0
 */
public abstract class MyBatisBaseDao<T> {
	
	public int deleteByPrimaryKey(Long id) throws Exception{
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "deleteByPrimaryKey", new Object[]{id});
	}
	
	public T selectByPrimaryKey(Long id) throws Exception{
		return (T) DynamicMethod.invokeMethod(getMapper(), "selectByPrimaryKey", new Object[]{id});
	}

	public int insert(T obj) throws Exception{
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "insert", new Object[]{obj});
	}
	
	public int updateByPrimaryKey(T obj) throws Exception{
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "updateByPrimaryKey", new Object[]{obj});
	}
	
	public int insertSelective(T obj) throws Exception{
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "insertSelective", new Object[]{obj});
	}
	
	public int updateByPrimaryKeySelective(T obj) throws Exception{
		return (Integer) DynamicMethod.invokeMethod(getMapper(), "updateByPrimaryKeySelective", new Object[]{obj});
	}
	
	public abstract Object getMapper();
}
