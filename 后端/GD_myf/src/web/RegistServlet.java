package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UserServices;
/**
 * 实现注册功能
 * @author 毛燕丰
 * @caeateTime 2019年4月13日下午4:36:45
   @package_name web
	@file_name RegistServlet.java
 */
@WebServlet("/registServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public RegistServlet() {
        super();
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("开始注册");
		//获取提交的数据
		String userId= request.getParameter("userId");
		String userName= request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String userGender= request.getParameter("userGender");
		System.out.println(request.getMethod());
		
		//获取输出流
		Writer out =response.getWriter();
		
		UserServices userService = new UserServices();
		boolean success=userService.insertUser(userId,userName,userPassword,userGender);
		if(success=true) {
			System.out.println("1212121");
			out.append("{\"success\":true}");
		}else {
			out.append("{\"success\":false}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
