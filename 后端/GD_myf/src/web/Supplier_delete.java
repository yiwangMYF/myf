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
 * 删除供应商
 * @author 毛燕丰
 * @caeateTime 2019年4月25日下午8:38:41
   @package_name web
	@file_name Supplier_delete.java
 */

@WebServlet("/supplier_delete")
public class Supplier_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取输出流
		Writer out=response.getWriter();
		//从session中获取当前用户信息
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//获取所要删除的供应商id
		String supId=request.getParameter("id");
		System.out.println(supId);
		//从数据库中删除指定userId和supId的数据
		SupplierServices supService = new SupplierServices();
		Boolean delSuccess=supService.delete(userId,supId);
		if(delSuccess) {
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
