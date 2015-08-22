package com.dm.bookstore.dao;

import java.util.List;


/**
 * Dao接口，定义Dao的基本操作，由BaseDao提供实现。
 * @author Administrator
 * @param<T> dao实际操作的泛型类型
 */
public interface Dao<T> {
	
	/**
	 * 执行INSERT操作，返回插入后的记录的ID
	 * @param sql
	 * @param args
	 * @return 插入新记录的id
	 */
	long insert(String sql,Object ... args);
	
	/**
	 * 执行UPDATE操作，包括INSERT（但没有返回值），UPDATE，DELETE
	 * @param sql
	 * @param args
	 */
	void update(String sql,Object ... args);
	
	/**
	 * 执行单挑记录的查询操作，返回与记录对应的类的一个对象
	 * @param sql
	 * @param args
	 * @return 与记录对应的一个对象
	 */
	T query(String sql,Object ... args);
	
	/**
	 * 执行多条记录的查询操作，返回与记录对应的一个List
	 * @param sql
	 * @param ages
	 * @return 与记录对应的一个List
	 */
	List<T> queryForList(String sql,Object ... args);
	
	/**
	 * 执行一个属性或值的查询操作，例如查询一条记录的一个字段，返回要查询的值
	 * @param sql
	 * @param args
	 * @return
	 */
	<V> V getSingleVal(String sql,Object ... args);
	
	/**
	 * 执行批量更新操作
	 * @param sql
	 * @param args
	 */
	void batch(String sql,Object[]... args);
	
}
