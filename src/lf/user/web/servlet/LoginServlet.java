package lf.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import lf.user.domain.User;
import lf.user.service.UserException;
import lf.user.service.UserService;

/**
 * UserServlet层
 * @author 11031
 *
 */
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//请求编码(POST)
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		//依赖UserService
		UserService userService=new UserService();
		
		//封装表单数据到User对象中
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		
		
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
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
			return;
		}
		
		
		try {
			User user=userService.login(form);
			request.getSession().setAttribute("sessionUser", user);
			response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
		}catch(UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		}
		

	}

}