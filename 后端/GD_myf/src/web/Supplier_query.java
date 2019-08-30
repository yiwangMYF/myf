package web;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import domain.Supplier;
import domain.User;
import services.SupplierServices;
/**
 * 查询当前用户的所有供应商
 * @author 毛燕丰
 * @caeateTime 2019年4月25日下午2:08:54
   @package_name web
	@file_name Supplier_query.java
 */
@WebServlet("/supplier_query")
public class Supplier_query extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取输出流
		Writer out=response.getWriter();
		//从session中获取当前用户信息
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//从数据库中查询出当前用户的所有供应商
		SupplierServices supService = new SupplierServices();
		List<Supplier> supList =supService.query(userId);
		//将数据传给前台
		out.append("{");
		out.append("\"success\":true,");
		out.append("\"data\":[");
		int index=0;
		for(Supplier sup:supList) {
			//将该JavaBean对象转化为jsonObject对象
			JSONObject jsonObj =new JSONObject(sup);
			if(index>0) {
				out.append(",");
			}
			out.append(jsonObj.toString());
			index++;	
		}
		out.append("]");
		out.append("}");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
