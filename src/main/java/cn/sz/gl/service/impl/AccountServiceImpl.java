package cn.sz.gl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sz.gl.dao.IAccountDAO;
import cn.sz.gl.pojo.Account;
import cn.sz.gl.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDAO accdao;
	
	public List<Integer> findAccidByUserid(Integer userid) {
		if(userid==null){
			return null;
		}
		return accdao.findAccidByUserid(userid);
	}

	public Account findAccByAccid(Integer accid) {
		if(accid==null){
			return null;
		}
		return accdao.findAccByAccid(accid);
	}

	public boolean minusPriceInBalance(Integer accid,Double price) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("accid", accid);
		map.put("price", price);
		int res = accdao.minusPriceInBalance(map);
		return res>0;
	}

}
