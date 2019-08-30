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
 * 实现用户登录
 * @author 毛燕丰
 * @caeateTime 2019年4月10日上午10:20:37
   @package_name web
	@file_name LoginServlet.java
 */

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserServices userService = new UserServices();
       
    public LoginServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1111111111");
		HttpSession session = request.getSession();
		//获得响应客户端的输出流
		Writer out = response.getWriter();
		//先进行验证码验证
		String checkcode= request.getParameter("checkcode");
		System.out.println(checkcode);
		//获取session中存储的验证码值
		String checkcode_session = (String) session.getAttribute("checkcode_session");
		System.out.println(checkcode_session);
		if(!checkcode.equals(checkcode_session)) {
			System.out.println("3");
			out.append("{\"error_info\":\"验证码错误!\",\"success\":false}");
			return;
		}
		//获取用户登录数据
		String userId= request.getParameter("userId");//用户登录的身份证号码
		String userPassword = request.getParameter("userPassword");//用户密码
		//进行账号验证
		User user =userService.login(userId, userPassword);
		if(user!=null) {
			System.out.println("登录成功！");
			//将该登录用户的信息存储到session域中
			session.setAttribute("user_info", user);
			out.write("{\"success\":true}");//登录成功回显信息
		}else {
			System.out.println("登录失败！");
			out.append("{\"error_info\":\"用户名或密码错误!\",\"success\":false}");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
