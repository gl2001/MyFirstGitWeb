package cn.sz.gl.service;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Account;

public interface IAccountService {

	public List<Integer> findAccidByUserid(Integer userid);
	
	/**
	 * 根据账号，查询账户信息
	 * @param accid
	 * @return
	 */
	public Account findAccByAccid(Integer accid);
	
	/**
	 * 账户余额减少
	 * @param map : 需要传递账户编号accid和书籍价格price
	 * @return
	 */
	public boolean minusPriceInBalance(Integer accid,Double price);
}
