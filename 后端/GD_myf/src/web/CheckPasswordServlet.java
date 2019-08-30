package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
/**
 * 用于验证用户修改密码时提交的原密码是否正确
 * @author 毛燕丰
 * @caeateTime 2019年4月14日下午1:34:29
   @package_name web
	@file_name CheckPasswordServlet.java
 */

@WebServlet("/checkPassword")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("开始验证原密码是否正确");
		//获取session对象
		HttpSession session = request.getSession();
		//获取存储在session中的用户信息
		User user = (User) session.getAttribute("user_info");
		//获取原密码
		String oldPassword = request.getParameter("oldPassword");
		System.out.println(oldPassword);
		System.out.println(user.getUserId());
		//创建输出流
		Writer out=response.getWriter();
		if(user.getUserPassword().equals(oldPassword)) {
			System.out.println("原密码正确！");
			out.append("{\"isCorrect\":true}");	
		}else {
			System.out.println("原密码不正确！");
			out.append("{\"isCorrect\":flase}");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
