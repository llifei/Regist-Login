package lf.user.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VerifyServlet")
public class VerifyServlet extends HttpServlet {
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.生成图片
		 * 2.保存图片上的文本到session域中
		 * 3.把图片响应给客户端
		 */
		VerifyCode vc=new VerifyCode();
		BufferedImage image=vc.getImage();
		request.getSession().setAttribute("session_vcode", vc.getText());//保存图片上的文本到session域中
		
		VerifyCode.output(image, response.getOutputStream());
	}

}
