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

import domain.User;
import services.MedicineService;

/**
 * ��õ�ǰ����ҩƷ�Ŀ����
 * 
 * @author ë���
 * @caeateTime 2019��5��7������11:27:11
 * @package_name web
 * @file_name Medicine_getNowStock.java
 */

@WebServlet("/medicine_getNowStock")
public class Medicine_getNowStock extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�����
		Writer out = response.getWriter();
		// ��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		//���ҩƷ����׼�ĺź���������
		String mId =request.getParameter("mId");
		String mLotNum = request.getParameter("mLotNum");
		MedicineService medService=new MedicineService();
		int sNum=medService.getStock(userId,mId,mLotNum);
		System.out.println("sNum:"+sNum);
		out.append("{\"success\":true,\"data\":");
		JSONObject jsonObj =new JSONObject();
		jsonObj.put("sNum",sNum);
		out.append(jsonObj.toString());
		out.append("}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
