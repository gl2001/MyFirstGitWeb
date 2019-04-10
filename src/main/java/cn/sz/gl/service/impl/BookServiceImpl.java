package cn.sz.gl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sz.gl.dao.IAccountDAO;
import cn.sz.gl.dao.IBookDAO;
import cn.sz.gl.pojo.Account;
import cn.sz.gl.pojo.Book;
import cn.sz.gl.service.IBookService;
import cn.sz.gl.util.MoneyLessException;
import cn.sz.gl.util.StoreLessException;

@Service
public class BookServiceImpl implements IBookService {
	
	@Autowired
	private IBookDAO bookdao;
	@Autowired
	private IAccountDAO accdao;
	
	public BookServiceImpl(){
		System.out.println("11111");
	}
	
	public List<Book> findAllBook() {
		return bookdao.findAllBook();
	}
	
	public Book findById(Integer bookid) {
		if(bookid==null){
			return null;
		}
		return bookdao.findBookByBookid(bookid);
	}
	
	public Integer findCountByBookid(Integer bookid) {
		if(bookid==null){
			return null;
		}
		return bookdao.findCountByBookid(bookid);
	}
	
	public boolean plusBookInStore(Integer bookid) {
		if(bookid==null){
			return false;
		}
		int res = bookdao.plusBookInStore(bookid);
		return res>0;
	}
	
	public Double findPriceByBookid(Integer bookid) {
		return bookdao.findPriceByBookid(bookid);
	}

	@Transactional(timeout=1,isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRES_NEW)
	public boolean buybook(Integer bookid, Integer accid) {
		
	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//先查询库存数量，如果数量为0，则抛出库存不足的异常
		int book_count = bookdao.findCountByBookid(bookid);
		if(book_count<=0){
			throw new StoreLessException("库存不足");
		}
		//库存数量减一
		boolean count_flag = bookdao.plusBookInStore(bookid)>0;
		
		
		//这里需要先从数据库查询书籍的最新价格是多少
		Double price = bookdao.findPriceByBookid(bookid);
		
		//查询账户余额
		Account acc = accdao.findAccByAccid(accid);
		
		//判断账户余额是否小于书籍的单价，如果小于单价，则抛出余额不足异常
		if(acc.getBalance()<price){
			throw new MoneyLessException("余额不足");
		}
		
		//账户余额减少
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accid", accid);
		map.put("price", price);
		boolean balance_flag = accdao.minusPriceInBalance(map)>0;
		
		return count_flag&&balance_flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	


	

	


}
