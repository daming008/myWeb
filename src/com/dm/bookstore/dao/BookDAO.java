package com.dm.bookstore.dao;

import java.util.Collection;
import java.util.List;

import com.dm.bookstore.domain.Book;
import com.dm.bookstore.domain.ShoppingCartItem;
import com.dm.bookstore.web.CirteriaBook;
import com.dm.bookstore.web.Page;

public interface BookDAO {
	
	/**
	 * ����id��ȡָ��Book����
	 * @param id
	 * @return
	 */
	public abstract Book getBook(int id);
	
	/**
	 * ��ȡ�����CirteriaBook���󷵻����Ӧ��Page ����
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CirteriaBook cb);
	
	//���ݴ����CirteraBook���󷵻����Ӧ�ļ�¼��
	public abstract long getTotalBookNumber(CirteriaBook cb);
	
	//���ݴ����CirteriaBook ��pageSize���ص�ǰҳ��Ӧ��List
	public abstract List<Book> getPageList(CirteriaBook cb,int pageSize);
	
	//����ָ��id�� book��storeNumber�ֶε�ֵ
	public abstract int getStoreNumber(Integer id);
	
	//���ݴ����ShoppingCartItem�ļ��ϣ���������books���ݱ��storenumerou ��salsesnumber�ֶε�ֵ
	public abstract void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);
}
