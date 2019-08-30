package web;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import domain.Return_bill;
import domain.User;
import services.MedicineService;
/**
 * ҩƷ�˻�
 * @author ë���
 * @caeateTime 2019��5��7������12:36:10
   @package_name web
	@file_name Medicine_return.java
 */

@WebServlet("/medicine_return")
public class Medicine_return extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�����
		Writer out = response.getWriter();
		// ��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		
		System.out.println("��ʼ�˻�����");
		
		Return_bill rBill= new Return_bill();
		try {
			BeanUtils.populate(rBill, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rBill.toString());
		MedicineService medService=new MedicineService();
		//�˻�
		Boolean isSuccess=medService.returnMedicine(userId,rBill);
		if(isSuccess==false) {
			out.append("{\"success\":false}");
		}else {
			out.append("{\"success\":true}");
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
