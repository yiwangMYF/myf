package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Supplier;
import domain.User;
import services.SupplierServices;

/**
 * 添加供应商
 * 
 * @author 毛燕丰
 * @caeateTime 2019年4月25日上午11:26:16
 * @package_name web
 * @file_name Supplier_add.java
 */
@WebServlet("/supplier_add")
public class Supplier_add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取输出流
		Writer out = response.getWriter();
		// 从session中获取当前用户信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		// 获取用户提交的供应商信息
		String supId = request.getParameter("supId");
		String supName = request.getParameter("supName");
		String supTel = request.getParameter("supTel");
		String supAddress = request.getParameter("supAddress");
		// 向数据库中添加信息
		SupplierServices supService = new SupplierServices();
		// 查询supplier表中是否有该供应商
		Supplier sup = supService.queryById(supId);
		Boolean isOk = null;
		if (sup==null) {
			//在supplier表和sup_user表中同时添加数据
			isOk = supService.addSupInTwo(userId, supId, supName, supTel, supAddress);
		}else {	
			//只向sup_user表中添加数据
			isOk=supService.addSup_user(userId,supId);
		}
		if (isOk == true) {
			out.append("{\"success\":true}");
		} else {
			out.append("{\"success\":false}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
