package cn.sz.gl.pojo;

import java.io.Serializable;

public class Account implements Serializable {

	private Integer accid;//�˺�
	private String accName;//�˻���
	private Integer balance;//�˻����
	private Integer userid;//�˻��������û����
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
