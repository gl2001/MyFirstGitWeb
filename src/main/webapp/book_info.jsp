<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
<script type="text/javascript">


</script>
</head>
<body>
<h3>这里是book_info.jsp页面</h3>
书籍编号：${book.bookid }<br/>
书籍名字：${book.bookName }<br/>
出版社：${book.publicDept }<br/>
价格：${book.bookPrice }<br/>
出版日期：${book.publicDate }<br/>
作者:${book.bookAuth }<br/>
图片:<img src="${book.imgPath }" height="100" border="1" /><br/>
简介：${book.summary }<br/>
<a href="bc/prebuy?bookid=${book.bookid }" >购买</a>
<a href="" >返回</a>
</body>
</html>