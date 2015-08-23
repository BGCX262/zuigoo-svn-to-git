<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String appRoot = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
response.setStatus(301);
response.setHeader("Location",appRoot+"index.html");
//response.setHeader("Location",appRoot+"index");
//response.setHeader("Location",appRoot+"entry/d/task");
response.setHeader("Connection", "close");
%>