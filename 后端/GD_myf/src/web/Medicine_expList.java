package web;

import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import domain.Medicine_stock_date;
import domain.PageBean;
import domain.StockInfo;
import domain.User;
import services.MedicineService;
/**
 * 获取近期和过期药品的分页数据
 * @author 毛燕丰
 * @caeateTime 2019年5月8日上午11:06:42
   @package_name web
	@file_name Medicine_expList.java
 */

@WebServlet("/medicine_expList")
public class Medicine_expList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取输出流
		Writer out=response.getWriter();
		//从session中获取当前用户信息
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//获取请求的当前页数
		String currentPage_str=request.getParameter("currentPage");
		if(currentPage_str==null) {
			currentPage_str="1";
		}
		int currentPage=Integer.parseInt(currentPage_str);
		MedicineService medService=new MedicineService();
	
		//获取页面信息
		PageBean<Medicine_stock_date> pageBean=medService.getPageBean_expList(userId,currentPage);
		
		if(pageBean==null) {
			System.out.println("1");
			out.append("{\"success\":false}");
		}else {
			out.append("{\"success\":true,\"data\":[");
			int index=0;
			for(Medicine_stock_date s:pageBean.getList()) {
				JSONObject jsonObj= new JSONObject(s);
				System.out.println("1:"+s.toString());
				jsonObj.put("mId", s.getmId());
				jsonObj.put("mName",s.getmName());
				jsonObj.put("mLotNum",s.getmLotNum());
				jsonObj.put("sNum",s.getsNum());
				if(index>0) {
					out.append(",");
				}
				out.append(jsonObj.toString());
				System.out.println("2:"+jsonObj.toString());
				index++;		
			}
			out.append("]");
			out.append(",");
			out.append("\"pageBean\":");
			JSONObject pageBean_json= new JSONObject(pageBean);
			out.append(pageBean_json.toString());
		
			System.out.println(pageBean_json.toString());
			out.append("}");
			
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
