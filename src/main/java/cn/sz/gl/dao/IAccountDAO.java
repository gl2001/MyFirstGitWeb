package cn.sz.gl.dao;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Account;

public interface IAccountDAO {

	public List<Integer> findAccidByUserid(Integer userid);
	
	public Account findAccByAccid(Integer accid);
	
	public int minusPriceInBalance(Map<String,Object> map);
}
