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
 * ʵ������������
 * @author ë���
 * @caeateTime 2019��5��16������8:55:53
   @package_name web
	@file_name Sales_report.java
 */

@WebServlet("/sales_report")
public class Sales_report extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out=response.getWriter();
		//��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//��ȡ��ʼʱ��
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
		//��ȡָ�����ڷ�Χ�ڵ�ҩƷ�������
		MedicineService medService = new MedicineService();
		List<Map<String,Object>> dataList = medService.getMedicineSales(userId,startDate,endDate);
		if(dataList.size()==0) {
			System.out.println("������");
			out.append("{\"success\":false}");
		}else {
			System.out.println("������");
			out.append("{\"success\":true,\"data\":[");
			int index=0;
			//�������ϻ�ȡ����
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
