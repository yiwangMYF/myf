package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * �����˳�ϵͳʱ���session
 * @author ë���
 * @caeateTime 2019��4��18������8:51:20
   @package_name web
	@file_name ClearSession.java
 */
@WebServlet("/clearSession")
public class ClearSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡsession
		HttpSession session = request.getSession();
		//���session�е�������Ϣ
		session.invalidate();
		System.out.println("���session�ɹ�");
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
