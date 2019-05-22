 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆</title>
    
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
   <%--本页面提供登陆表单，还要显示错误信息 --%> <br>
  <%
	/*
	*读取名为uname的cookie
	*如果为空显示""
	*如果不为空显示cookie的值
	*/
	String uname="";
  	Cookie[]cs=request.getCookies();//获取请求中所有的cookie
  	if(cs!=null)//如果存在cookie
  		for(Cookie c:cs){//循环遍历所有的cookie
  			if("uname".equals(c.getName())){//查找名为uname的cookie
  				uname=c.getValue();//获取这个cookie的值，赋给uname变量
  			}
  		}
  	
   %>
  <%
  		String message="";
   		String msg=(String)request.getAttribute("msg");
   		if(null!=msg)
   			message=msg;
   %>
  <font color="red"><b><%=message%></b></font>
  <form action="Login" method="post">
    <h1>登陆</h1> 
   	<pre>
   	
	用户名：<input type="text" name="username" value="${user.getUsername() }"/>${errors.username }<br/>
   	密  码：<input type="password"name="password" value="${user.getPassword() }"/>${errors.password }<br/>
   	验证码：<input type="text" name="verifyCode" value="${user.getVerifyCode() }" size="3"/><a href="javascript:_change()"><img id="img" src="/j2ee/VerifyServlet"/></a>	
   		${errors.verifycode }<a href="javascript:_change()" style="font-size:4px">看不清？换一张</a>
   		  
   		<input type="submit" value="登陆"/>
    </pre>
   	
   </form>
  </body>
</html>
