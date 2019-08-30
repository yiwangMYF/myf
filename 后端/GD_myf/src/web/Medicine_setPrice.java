package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import services.MedicineService;
/**
 * ����ҩƷ�۸�
 * @author ë���
 * @caeateTime 2019��5��5������2:15:16
   @package_name web
	@file_name Medicine_setPrice.java
 */

@WebServlet("/medicine_setPrice")
public class Medicine_setPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�����
		Writer out = response.getWriter();
		// ��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		
		String mId = request.getParameter("mId");
		String outPrice =request.getParameter("outPrice");
		MedicineService medService =new MedicineService();
		medService.setPrice(userId,mId,outPrice);
		out.append("{\"success\":true}");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
