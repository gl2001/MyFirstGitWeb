package cn.sz.gl.pojo;

import java.io.Serializable;

public class Account implements Serializable {

	private Integer accid;//账号
	private String accName;//账户名
	private Integer balance;//账户余额
	private Integer userid;//账户所属的用户编号
	public Integer getAccid() {
		return accid;
	}
	public void setAccid(Integer accid) {
		this.accid = accid;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
}
