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
$(function(){
	$("#tb tr:odd").attr("bgcolor","gray");
});

function showBook(bid){
	location = "bc/showbook?bookid="+bid;
}

</script>
</head>
<body>
<h3>这里是main.jsp页面</h3>
<a href="uc/prelogin" >登录</a>
<a href="" >注册</a>
<table id="tb" width="800" border="1" cellspacing="0"  >
	<tr>
		<th>编号</th>
		<th>书名</th>
		<th>出版社</th>
		<th>价格</th>
		<th>作者</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${booklist }" var="bl" >
	<tr >
		<td>${bl.bookid }</td>
		<td><a href="javascript:void(0);" onclick="showBook('${bl.bookid}');" >${bl.bookName }</a></td>
		<td>${bl.publicDept }</td>
		<td>${bl.bookPrice }</td>
		<td>${bl.bookAuth }</td>
		<td></td>
	</tr>
	</c:forEach>
</table>
</body>
</html>