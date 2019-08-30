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
/**
 * ������֤�û��޸�����ʱ�ύ��ԭ�����Ƿ���ȷ
 * @author ë���
 * @caeateTime 2019��4��14������1:34:29
   @package_name web
	@file_name CheckPasswordServlet.java
 */

@WebServlet("/checkPassword")
public class CheckPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("��ʼ��֤ԭ�����Ƿ���ȷ");
		//��ȡsession����
		HttpSession session = request.getSession();
		//��ȡ�洢��session�е��û���Ϣ
		User user = (User) session.getAttribute("user_info");
		//��ȡԭ����
		String oldPassword = request.getParameter("oldPassword");
		System.out.println(oldPassword);
		System.out.println(user.getUserId());
		//���������
		Writer out=response.getWriter();
		if(user.getUserPassword().equals(oldPassword)) {
			System.out.println("ԭ������ȷ��");
			out.append("{\"isCorrect\":true}");	
		}else {
			System.out.println("ԭ���벻��ȷ��");
			out.append("{\"isCorrect\":flase}");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
