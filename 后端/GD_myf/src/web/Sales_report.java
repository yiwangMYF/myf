package web;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import domain.User;
import services.MedicineService;
/**
 * 实现销量报表功能
 * @author 毛燕丰
 * @caeateTime 2019年5月16日上午8:55:53
   @package_name web
	@file_name Sales_report.java
 */

@WebServlet("/sales_report")
public class Sales_report extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取输出流
		Writer out=response.getWriter();
		//从session中获取当前用户信息
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//获取起始时间
		String startDate_str = request.getParameter("startDate");
		String endDate_str =request.getParameter("endDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=null;
		Date endDate=null;
		try {
			 startDate=sdf.parse(startDate_str);
			endDate=sdf.parse(endDate_str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(startDate);
		//获取指定日期范围内的药品销售情况
		MedicineService medService = new MedicineService();
		List<Map<String,Object>> dataList = medService.getMedicineSales(userId,startDate,endDate);
		if(dataList.size()==0) {
			System.out.println("无数据");
			out.append("{\"success\":false}");
		}else {
			System.out.println("有数据");
			out.append("{\"success\":true,\"data\":[");
			int index=0;
			//遍历集合获取数据
			for(Map<String,Object> map:dataList) {
				if(index>0) {
					out.append(",");
				}
				JSONObject json = new JSONObject();
				for(String s:map.keySet()) {
					System.out.println(s+map.get(s));
					json.put(s, map.get(s));
				}
				out.append(json.toString());
				index++;
			}
			out.append("]");
			out.append("}");
		}
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
