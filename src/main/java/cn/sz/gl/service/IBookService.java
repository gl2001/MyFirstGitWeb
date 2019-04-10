package cn.sz.gl.service;

import java.util.List;

import cn.sz.gl.pojo.Book;

public interface IBookService {

	/**
	 * ��ѯȫ��
	 * Ҫ���������˲�ѯȫ����д����Ҫ��ʹ�ô洢���̣���ʵ�ֶ�������ѯ+��ҳ
	 * @return
	 */
	public List<Book> findAllBook();
	
	/**
	 * �����鼮��ţ���ѯ�鼮��Ϣ
	 * @param bookid
	 * @return
	 */
	public Book findById(Integer bookid);
	
	/**
	 * �����鼮��ţ���ѯ���鼮�Ŀ���ж��ٱ�
	 * @param bookid
	 * @return
	 */
	public Integer findCountByBookid(Integer bookid);
	
	/**
	 * ���������һ
	 * @param bookid
	 */
	public boolean plusBookInStore(Integer bookid);
	
	/**
	 * �����鼮��ţ���ѯ�鼮�۸�
	 * @param bookid
	 * @return
	 */
	public Double findPriceByBookid(Integer bookid);
	
	/**
	 * ʵ������
	 * @param bookid
	 * @param accid
	 * @return
	 */
	public boolean buybook(Integer bookid,Integer accid);
}
