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
		//�Ȳ�ѯ����������������Ϊ0�����׳���治����쳣
		int book_count = bookdao.findCountByBookid(bookid);
		if(book_count<=0){
			throw new StoreLessException("��治��");
		}
		//���������һ
		boolean count_flag = bookdao.plusBookInStore(bookid)>0;
		
		
		//������Ҫ�ȴ����ݿ��ѯ�鼮�����¼۸��Ƕ���
		Double price = bookdao.findPriceByBookid(bookid);
		
		//��ѯ�˻����
		Account acc = accdao.findAccByAccid(accid);
		
		//�ж��˻�����Ƿ�С���鼮�ĵ��ۣ����С�ڵ��ۣ����׳������쳣
		if(acc.getBalance()<price){
			throw new MoneyLessException("����");
		}
		
		//�˻�������
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accid", accid);
		map.put("price", price);
		boolean balance_flag = accdao.minusPriceInBalance(map)>0;
		
		return count_flag&&balance_flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	


	

	


}
