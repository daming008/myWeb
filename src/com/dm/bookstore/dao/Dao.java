package com.dm.bookstore.dao;

import java.util.List;


/**
 * Dao�ӿڣ�����Dao�Ļ�����������BaseDao�ṩʵ�֡�
 * @author Administrator
 * @param<T> daoʵ�ʲ����ķ�������
 */
public interface Dao<T> {
	
	/**
	 * ִ��INSERT���������ز����ļ�¼��ID
	 * @param sql
	 * @param args
	 * @return �����¼�¼��id
	 */
	long insert(String sql,Object ... args);
	
	/**
	 * ִ��UPDATE����������INSERT����û�з���ֵ����UPDATE��DELETE
	 * @param sql
	 * @param args
	 */
	void update(String sql,Object ... args);
	
	/**
	 * ִ�е�����¼�Ĳ�ѯ�������������¼��Ӧ�����һ������
	 * @param sql
	 * @param args
	 * @return ���¼��Ӧ��һ������
	 */
	T query(String sql,Object ... args);
	
	/**
	 * ִ�ж�����¼�Ĳ�ѯ�������������¼��Ӧ��һ��List
	 * @param sql
	 * @param ages
	 * @return ���¼��Ӧ��һ��List
	 */
	List<T> queryForList(String sql,Object ... args);
	
	/**
	 * ִ��һ�����Ի�ֵ�Ĳ�ѯ�����������ѯһ����¼��һ���ֶΣ�����Ҫ��ѯ��ֵ
	 * @param sql
	 * @param args
	 * @return
	 */
	<V> V getSingleVal(String sql,Object ... args);
	
	/**
	 * ִ���������²���
	 * @param sql
	 * @param args
	 */
	void batch(String sql,Object[]... args);
	
}
