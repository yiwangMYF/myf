package web;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Supplier;
import services.SupplierServices;
/**
 * 供应商信息修改
 * @author 毛燕丰
 * @caeateTime 2019年4月26日上午10:05:45
   @package_name web
	@file_name Supplier_update.java
 */
@WebServlet("/supplier_update")
public class Supplier_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer out =response.getWriter();
		//创建Supplier对象
		Supplier sup =new Supplier();
		//将前台传递的数据封装到Supplier对象中
		try {
			BeanUtils.populate(sup, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SupplierServices supService = new SupplierServices();
		Boolean isSuccess =supService.update(sup);
		if(isSuccess) {
			out.append("{\"success\":true}");
		}else {
			out.append("{\"success\"；false}");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
