package cn.sz.gl.service;

import java.util.List;

import cn.sz.gl.pojo.Book;

public interface IBookService {

	/**
	 * 查询全部
	 * 要求：在掌握了查询全部的写法后，要求使用存储过程，来实现多条件查询+分页
	 * @return
	 */
	public List<Book> findAllBook();
	
	/**
	 * 根据书籍编号，查询书籍信息
	 * @param bookid
	 * @return
	 */
	public Book findById(Integer bookid);
	
	/**
	 * 根据书籍编号，查询该书籍的库存有多少本
	 * @param bookid
	 * @return
	 */
	public Integer findCountByBookid(Integer bookid);
	
	/**
	 * 库存数量减一
	 * @param bookid
	 */
	public boolean plusBookInStore(Integer bookid);
	
	/**
	 * 根据书籍编号，查询书籍价格
	 * @param bookid
	 * @return
	 */
	public Double findPriceByBookid(Integer bookid);
	
	/**
	 * 实现买书
	 * @param bookid
	 * @param accid
	 * @return
	 */
	public boolean buybook(Integer bookid,Integer accid);
}
