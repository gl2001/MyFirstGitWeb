package cn.sz.gl.dao;

import java.util.List;

import cn.sz.gl.pojo.Book;

public interface IBookDAO {

	public List<Book> findAllBook();
	
	public Book findBookByBookid(Integer bookid);
	
	public Integer findCountByBookid(Integer bookid);
	
	public int plusBookInStore(Integer bookid);
	
	public Double findPriceByBookid(Integer bookid);
}
