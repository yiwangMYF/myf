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
import services.SupplierServices;
/**
 * 检验数据库sup_user表中是否存在该供应商id
 * @author 毛燕丰
 * @caeateTime 2019年4月27日下午7:18:23
   @package_name web
	@file_name CheckSupId.java
 */
@WebServlet("/checkSupId")
public class CheckSupId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取输出流
		Writer out = response.getWriter();
		// 从session中获取当前用户信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		//获取supId
		String supId = request.getParameter("supId").trim();
		System.out.println("supid:"+supId);
		//从数据库中查验
		SupplierServices supService = new SupplierServices();
		Boolean isExist =supService.queryById_two(userId,supId);
		if(isExist) {
			out.append("{\"isExist\":true}");
		}else {
			out.append("{\"isExist\":false}");
		}
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
