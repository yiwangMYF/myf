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
import services.UserServices;
/**
 * ʵ���޸����빦��
 * @author ë���
 * @caeateTime 2019��4��15������11:06:42
   @package_name web
	@file_name AlertPd.java
 */
@WebServlet("/alertPd")
public class AlertPd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out =response.getWriter();
		//��ȡ�û��ύ������
		String newPassword =request.getParameter("newPassword");
		System.out.println("��ʼ�޸�����");
		//��session�л�ȡ��ǰ�û���id
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("user_info");
		
		UserServices userService= new UserServices();
		boolean alert_success=userService.setNewPassword(user.getUserId(),newPassword);
		if(alert_success==true) {
			System.out.println("��Ŷ���޸ĳɹ���");
			out.append("{\"alert_success\":true}");
		}else {
			System.out.println("��Ŷ���޸�ʧ�ܣ�");
			out.append("{\"alert_success\":false}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
