package lf.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import lf.user.domain.User;
import lf.user.service.UserException;
import lf.user.service.UserService;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 依赖UserService
		UserService userService = new UserService();

		/*
		 * 封装表单数据到User对象中
		 */
//		User user = new User();
//		String username = (String) request.getAttribute("username");
//		String password = (String) request.getAttribute("password");
//		String verifycode=(String) request.getAttribute("VerifyCode");
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setVeriyfCode(verifycode);
		User form=CommonUtils.toBean(request.getParameterMap(),User.class);
		
		

		
		/*
		 * 校验表单
		 */
		Map<String,String>errors=new HashMap<String, String>();
		//对用户名进行校验
		String username=form.getUsername();//获取表单的username
		if(username==null||username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		}else if(username.length()<3||username.length()>15) {
			errors.put("username", "用户名长度必须在3~15之间！");
		}
		//对密码进行校验
		String password=form.getPassword();//获取表单的password
		if(password==null||password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		}else if(password.length()<3||password.length()>15) {
			errors.put("password", "密码长度必须在3~15之间！");
		}
		//对验证码进行校验
		String session_vcode=(String) request.getSession().getAttribute("session_vcode");
		String verifycode=form.getVerifyCode();//获取表单的verifycode
		if(verifycode==null||verifycode.trim().isEmpty()) {
			errors.put("verifycode", "验证码不能为空！");
		}else if(!verifycode.equalsIgnoreCase(session_vcode)) {
			errors.put("verifycode", "验证码错误！");
		}
		
		/*
		 * 判断map是否为空，不为空，说明存在错误
		 */
		if(errors!=null&&errors.size()>0) {
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		
	
		/*
		 * 调用service的regist()方法 
		 * 1.如果方法没有问题，输出“注册成功”
		 * 2.如果方法抛出异常，把错误信息保存到request域，转发到regist.jsp（显示错误信息）
		 */
		try {
			userService.regist(form);
			response.getWriter()
					.println("<h1>注册成功!</h1><a href='" + request.getContextPath() + "/user/login.jsp" + "'>点击此处登陆</a>");

		} catch (UserException e) {
			String message = e.getMessage();
			request.setAttribute("msg", message);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
		}
	}
}
