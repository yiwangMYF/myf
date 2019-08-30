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
import services.UserServices;
/**
 * 实现修改密码功能
 * @author 毛燕丰
 * @caeateTime 2019年4月15日上午11:06:42
   @package_name web
	@file_name AlertPd.java
 */
@WebServlet("/alertPd")
public class AlertPd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取输出流
		Writer out =response.getWriter();
		//获取用户提交的数据
		String newPassword =request.getParameter("newPassword");
		System.out.println("开始修改密码");
		//从session中获取当前用户的id
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("user_info");
		
		UserServices userService= new UserServices();
		boolean alert_success=userService.setNewPassword(user.getUserId(),newPassword);
		if(alert_success==true) {
			System.out.println("啊哦，修改成功！");
			out.append("{\"alert_success\":true}");
		}else {
			System.out.println("啊哦，修改失败！");
			out.append("{\"alert_success\":false}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
