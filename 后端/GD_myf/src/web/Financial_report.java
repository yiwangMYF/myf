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
 * ����ͳ�ƹ���
 * @author ë���
 * @caeateTime 2019��5��17������8:54:48
   @package_name web
	@file_name Financial_report.java
 */
@WebServlet("/financial_report")
public class Financial_report extends HttpServlet {
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
		MedicineService medService = new MedicineService();
		PrescriptionServices preService = new PrescriptionServices();
		//���ָ��ʱ����ڵĽ�����֧��
		double pur_totalExpenses = medService.getPurTotalExpenses(userId,startDate,endDate);
		System.out.println("������֧����"+pur_totalExpenses);
		//���ָ��ʱ�����ҩƷ����ķ���
		double frmloss_totalExpenses = medService.getFrmlossTotalExpenses(userId,startDate,endDate);
		System.out.println("ҩƷ�����ܷ��ã�"+frmloss_totalExpenses);
		//���ָ��ʱ�����ҩƷ�������ܶ�
		double sales=0;
		try {
			sales = preService.getSales(userId,startDate,endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ҩƷ�����ܶ"+sales);
		//���ָ��ʱ�����ҩƷ�˻��ܶ�
		double return_totalExpenses = medService.getReturnTotalExpenses(userId,startDate,endDate);
		System.out.println("ҩƷ�˻��ܷ��ã�"+return_totalExpenses);
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
