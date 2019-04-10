package cn.sz.gl.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Book implements Serializable {

	private Integer bookid;//书的编号
	private String bookName;//书籍名字
	private String publicDept;//出版社
	private Double bookPrice;//价格
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publicDate;//出版日期
	private String bookAuth;//作者
	private String imgPath;//书籍图片的路径
	private String summary;//简介
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublicDept() {
		return publicDept;
	}
	public void setPublicDept(String publicDept) {
		this.publicDept = publicDept;
	}
	public Double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public Date getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}
	public String getBookAuth() {
		return bookAuth;
	}
	public void setBookAuth(String bookAuth) {
		this.bookAuth = bookAuth;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
