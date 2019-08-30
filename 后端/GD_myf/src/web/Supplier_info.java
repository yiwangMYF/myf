package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Supplier;
import services.SupplierServices;
/**
 * 加载供应商信息
 * @author 毛燕丰
 * @caeateTime 2019年4月26日上午10:35:49
   @package_name web
	@file_name Supplier_info.java
 */
@WebServlet("/supplier_info")
public class Supplier_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("--------开始加载信息了-------------------");
		Writer out = response.getWriter();
		//获取页面传递的supId
		String supId = request.getParameter("supId");
		System.out.println("supId:"+supId);
		SupplierServices supService= new SupplierServices();
		Supplier sup = supService.queryById(supId);
		//System.out.println(sup.getSupName());
		//将JavaBean对象转化为JSONObject对象
		JSONObject jsonObj= new JSONObject(sup);
		out.append("{\"success\":true,\"data\":");
		out.append(jsonObj.toString());
		out.append("}");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
