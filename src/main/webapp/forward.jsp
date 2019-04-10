<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<%
String path = request.getContextPath();
String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String msg = (String)request.getAttribute("msg");
out.print("<div>"+msg+"，3秒之后跳转</div>");
%>

<meta http-equiv="refresh"content="3; url=<%=basepath%>"/>