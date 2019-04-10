package cn.sz.gl.util;

public class MoneyLessException extends RuntimeException {

	public MoneyLessException(){
		
	}
	
	public MoneyLessException(String msg){
		super(msg);
	}
}
