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
 * ���㴦��ҩƷ���ܷ���
 * @author ë���
 * @caeateTime 2019��5��14������11:12:17
   @package_name web
	@file_name Prescription_calculation.java
 */

@WebServlet("/prescription_calculation")
public class Prescription_calculation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out=response.getWriter();
		//��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//��ô�����ҩ����
		String med_details=request.getParameter("med_details");
		JSONArray jsonArr = new JSONArray(med_details);
		System.out.println(jsonArr.toString());
		MedicineService medService = new MedicineService();
		//�����ַ�������洢med_detais��Ϣ
		String detais="";
		//�洢�ܷ���
		double cost=0;
		int index=0;
		//����json����
		for(int i=0;i<jsonArr.length();i++) {
			JSONObject jsonObj =jsonArr.getJSONObject(i);
			if(index>0) {
				detais=detais+";";
			}
			String mName= jsonObj.getString("name");
			detais=detais+mName+"��";
			String num_str=jsonObj.getString("num");
			int num =Integer.parseInt(num_str);
			detais=detais+num_str;
			//��ѯ��ǰҩƷ���ۼ�
			double outPrice=medService.getOutPriceByName(userId,mName);
			//���㵱ǰ�ܷ���
			cost=cost+outPrice*num;
			System.out.println(cost);
			index++;
		}
		String cost_str =String.valueOf(cost);
		//������������ҩ��Ϣ����ǰ��
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
