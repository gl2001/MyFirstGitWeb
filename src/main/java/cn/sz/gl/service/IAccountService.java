package cn.sz.gl.service;

import java.util.List;
import java.util.Map;

import cn.sz.gl.pojo.Account;

public interface IAccountService {

	public List<Integer> findAccidByUserid(Integer userid);
	
	/**
	 * �����˺ţ���ѯ�˻���Ϣ
	 * @param accid
	 * @return
	 */
	public Account findAccByAccid(Integer accid);
	
	/**
	 * �˻�������
	 * @param map : ��Ҫ�����˻����accid���鼮�۸�price
	 * @return
	 */
	public boolean minusPriceInBalance(Integer accid,Double price);
}
