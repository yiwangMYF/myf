package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import domain.Medicine_Sale;
import domain.User;
import services.MedicineService;

/**
 * ��ȡ��ǰҩƷ���ۼ�
 * 
 * @author ë���
 * @caeateTime 2019��5��5������12:22:10
 * @package_name web
 * @file_name Medicine_getNowPrice.java
 */
@WebServlet("/medicine_getNowPrice")
public class Medicine_getNowPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�����
		Writer out = response.getWriter();
		// ��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		
		String mId =request.getParameter("mId");
		MedicineService medService = new MedicineService();
		Medicine_Sale ms=medService.queryOutPrice(userId, mId);
		if(ms==null) {
			out.append("{\"success\":false}");
		}else {
			//��javaBean����ת��ΪJSONObjct����
			JSONObject jsonObj = new JSONObject(ms);
			out.append("{\"success\":true,\"data\":");
			out.append(jsonObj.toString());
			out.append("}");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
