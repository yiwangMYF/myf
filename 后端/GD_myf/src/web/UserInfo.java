package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import domain.User;

/**
 * 实现向页面添加用户信息
 * @author 毛燕丰
 * @caeateTime 2019年4月21日下午3:31:02
   @package_name web
	@file_name UserInfo.java
 */
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserInfo() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取输出流
		Writer out =response.getWriter();
		//获取用户名称
		HttpSession session = request.getSession();
		User user= (User) session.getAttribute("user_info");
		String user_name= user.getUserName();
		//创建json对象
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("user_name", user_name);
		
		//向页面回显信息（用户名称）
		out.append(jsonObj.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
