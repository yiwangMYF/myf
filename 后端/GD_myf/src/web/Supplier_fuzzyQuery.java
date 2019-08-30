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
 * 根据供应商id或名称进行模糊查询
 * 
 * @author 毛燕丰
 * @caeateTime 2019年4月26日下午1:19:24
 * @package_name web
 * @file_name Supplier_fuzzyQuery.java
 */

@WebServlet("/supplier_fuzzyQuery")
public class Supplier_fuzzyQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取输出流
		Writer out = response.getWriter();
		// 从session中获取当前用户信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		// 获取用户提交的数据
		String text = request.getParameter("ss").trim();
		// 查询满足条件的数据
		SupplierServices supService = new SupplierServices();
		List<Supplier> supList = supService.fuzzyQuery(userId, text);
		// 将数据传给前台
		out.append("{");
		if (supList.size() == 0) {
			out.append("\"success\":false");
		} else {
			out.append("\"success\":true,");
			out.append("\"data\":[");
			int index = 0;
			for (Supplier sup : supList) {
				// 将该JavaBean对象转化为jsonObject对象
				JSONObject jsonObj = new JSONObject(sup);
				if (index > 0) {
					out.append(",");
				}
				out.append(jsonObj.toString());
				index++;
			}
			out.append("]");
		}
		out.append("}");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
