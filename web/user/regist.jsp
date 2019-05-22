<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
      <script type="text/javascript">
  function _change(){
  	/*
  	1.得到img元素
  	2.修改其src为/j2ee/VerifyCodeServlet
  	*/
  	var imgEle=document.getElementById("img");
  	imgEle.src="/j2ee/VerifyServlet?a="+new Date().getTime();
  }
  </script>
  <body>

  <p style="color:red;font-weight:900">${msg }</p>
  <form action="Regist" method="post">
    <h1>注册</h1> 
   	<pre>
   	
	用户名：<input type="text" name="username" value="${user.getUsername() }"/>${errors.username }<br/>
   	密  码：<input type="password"name="password" value="${user.getPassword() }"/>${errors.password }<br/>
   	验证码：<input type="text" name="verifyCode" value="${user.getVerifyCode() }" size="3"/><a href="javascript:_change()"><img id="img" src="/j2ee/VerifyServlet"/></a>	
   		${errors.verifycode }<a href="javascript:_change()" style="font-size:4px">看不清？换一张</a>
   		  
   		<input type="submit" value="注册"/>
    </pre>
   	
   </form>
  </body>
</html>
