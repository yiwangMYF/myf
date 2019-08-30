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
import services.SupplierServices;
/**
 * �������ݿ�sup_user�����Ƿ���ڸù�Ӧ��id
 * @author ë���
 * @caeateTime 2019��4��27������7:18:23
   @package_name web
	@file_name CheckSupId.java
 */
@WebServlet("/checkSupId")
public class CheckSupId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�����
		Writer out = response.getWriter();
		// ��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		//��ȡsupId
		String supId = request.getParameter("supId").trim();
		System.out.println("supid:"+supId);
		//�����ݿ��в���
		SupplierServices supService = new SupplierServices();
		Boolean isExist =supService.queryById_two(userId,supId);
		if(isExist) {
			out.append("{\"isExist\":true}");
		}else {
			out.append("{\"isExist\":false}");
		}
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
