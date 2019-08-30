package web;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
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

import domain.User;
import services.MedicineService;
import services.PrescriptionServices;
/**
 * 财务统计功能
 * @author 毛燕丰
 * @caeateTime 2019年5月17日上午8:54:48
   @package_name web
	@file_name Financial_report.java
 */
@WebServlet("/financial_report")
public class Financial_report extends HttpServlet {
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
		MedicineService medService = new MedicineService();
		PrescriptionServices preService = new PrescriptionServices();
		//获得指定时间段内的进货总支出
		double pur_totalExpenses = medService.getPurTotalExpenses(userId,startDate,endDate);
		System.out.println("进货总支出："+pur_totalExpenses);
		//获得指定时间段内药品耗损的费用
		double frmloss_totalExpenses = medService.getFrmlossTotalExpenses(userId,startDate,endDate);
		System.out.println("药品耗损总费用："+frmloss_totalExpenses);
		//获得指定时间段内药品的销售总额
		double sales=0;
		try {
			sales = preService.getSales(userId,startDate,endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("药品销售总额："+sales);
		//获得指定时间段内药品退货总额
		double return_totalExpenses = medService.getReturnTotalExpenses(userId,startDate,endDate);
		System.out.println("药品退货总费用："+return_totalExpenses);
		out.append("{\"success\":true,\"data\":");
		JSONObject json = new JSONObject();
		json.put("pur_totalExpenses", pur_totalExpenses);
		json.put("frmloss_totalExpenses", frmloss_totalExpenses);
		json.put("return_totalExpenses", return_totalExpenses);
		json.put("sales", sales);
		out.append(json.toString());
		out.append("}");
		System.out.println(json.toString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
