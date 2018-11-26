package com.lionxxw.kqsystem.code.base;


import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;

import java.util.List;

/**
 * <p>Description: 基本接口方法 </p>
 *
 * @author lionxxw
 * @version 1.0
 * @time 16/5/5 下午8:01
 */
public interface BaseService<T> {

    /**
     * <p>Description: 保存对象 </p>
     *
     * @param obj 数据对象
     * @return T
     * @author lionxxw
     * @date 16/5/5 下午8:20
     * @version 1.0
     */
    T save(T obj) throws Exception;

    /**
     * <p>Description: 根据主键id删除操作,物理删除 </p>
     *
     * @param id 主键id
     * @return boolean
     * @author lionxxw
     * @date 16/5/5 下午8:20
     * @version 1.0
     */
    boolean delById(Long id) throws Exception;

    /**
     * <p>Description: 更新对象 </p>
     *
     * @param obj 数据对象
     * @author lionxxw
     * @date 16/5/5 下午8:21
     * @version 1.0
     */
    void update(T obj) throws Exception;

    /**		
     * <p>Description: 根据主键id获取对象 </p>
     * 
     * @param id 主键id
     * @return T
     * @author lionxxw
     * @date 16/5/5 下午8:23
     * @version 1.0
     */
    T getById(Long id) throws Exception;

    /**		
     * <p>Description: 根据对象参数查询数据 </p>
     * 
     * @param obj 对象
     * @return  T
     * @author lionxxw
     * @date 16/5/5 下午8:23
     * @version 1.0
     */
    List<T> queryByParam(T obj) throws Exception;

    /**		
     * <p>Description: 根据对象参数分页查询数据 </p>
     * 
     * @param obj 对象
     * @param query 分页查询参数
     * @return PageResult<T>
     * @author lionxxw
     * @date 16/5/5 下午8:24
     * @version 1.0
     */
    PageResult<T> queryByPage(T obj, PageQuery query) throws Exception;
}
