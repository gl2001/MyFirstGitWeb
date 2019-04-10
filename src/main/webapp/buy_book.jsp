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
function showBalance(accid){
	
	$.post("bc/showBalance",{"accid":accid},function(data){
		//console.log(data);
		$("#accname").html(data.accName);
		$("#balance").html(data.balance);
	},"json");
	
}

function buybook(){
	buyform.submit();
}

</script>
</head>
<body>
<h3>这里是buy_book.jsp页面</h3>
<form name="buyform" id="buyform" action="bc/buybook" method="post" >
<input type="hidden" value="${book.bookid }" name="bookid" id="bookid" />
书籍编号：${book.bookid }<br/>
书籍名字：${book.bookName }<br/>
出版社：${book.publicDept }<br/>
价格：${book.bookPrice }<br/>
出版日期：${book.publicDate }<br/>
作者:${book.bookAuth }<br/>
图片:<img src="${book.imgPath }" height="100" border="1" /><br/>
简介：${book.summary }<br/>
<hr/>
当前库存数量：${book_count }<br/>
<hr/>
支付账号：
<select onchange="showBalance(this.value);" name="accid" >
	<option value="0">---请选择支付账号---</option>
	<c:forEach items="${acclist }" var="al">
	<option value="${al }" >${al }</option>
	</c:forEach>
</select>
账户姓名：<span id="accname"></span>
,账户余额为：<span id="balance"></span>
<br/>
<hr/>
<a href="javascript:void(0);" onclick="buybook();" >支付</a>
<a href="" >返回</a>
</form>
</body>
</html>