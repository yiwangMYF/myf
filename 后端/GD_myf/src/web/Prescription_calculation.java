package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.User;
import services.MedicineService;
/**
 * 计算处方药品的总费用
 * @author 毛燕丰
 * @caeateTime 2019年5月14日上午11:12:17
   @package_name web
	@file_name Prescription_calculation.java
 */

@WebServlet("/prescription_calculation")
public class Prescription_calculation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取输出流
		Writer out=response.getWriter();
		//从session中获取当前用户信息
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//获得处方用药详情
		String med_details=request.getParameter("med_details");
		JSONArray jsonArr = new JSONArray(med_details);
		System.out.println(jsonArr.toString());
		MedicineService medService = new MedicineService();
		//创建字符串对象存储med_detais信息
		String detais="";
		//存储总费用
		double cost=0;
		int index=0;
		//遍历json数组
		for(int i=0;i<jsonArr.length();i++) {
			JSONObject jsonObj =jsonArr.getJSONObject(i);
			if(index>0) {
				detais=detais+";";
			}
			String mName= jsonObj.getString("name");
			detais=detais+mName+"共";
			String num_str=jsonObj.getString("num");
			int num =Integer.parseInt(num_str);
			detais=detais+num_str;
			//查询当前药品的售价
			double outPrice=medService.getOutPriceByName(userId,mName);
			//计算当前总费用
			cost=cost+outPrice*num;
			System.out.println(cost);
			index++;
		}
		String cost_str =String.valueOf(cost);
		//将计算结果和用药信息返回前端
		JSONObject json1 =new JSONObject();
		json1.put("success", true);
		json1.put("med_details",detais);
		json1.put("cost", cost);
		out.append(json1.toString());
		System.out.println(json1.toString());
		
		
	
	
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
