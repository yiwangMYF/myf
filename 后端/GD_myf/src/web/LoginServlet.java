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
 * ʵ���û���¼
 * @author ë���
 * @caeateTime 2019��4��10������10:20:37
   @package_name web
	@file_name LoginServlet.java
 */

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserServices userService = new UserServices();
       
    public LoginServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1111111111");
		HttpSession session = request.getSession();
		//�����Ӧ�ͻ��˵������
		Writer out = response.getWriter();
		//�Ƚ�����֤����֤
		String checkcode= request.getParameter("checkcode");
		System.out.println(checkcode);
		//��ȡsession�д洢����֤��ֵ
		String checkcode_session = (String) session.getAttribute("checkcode_session");
		System.out.println(checkcode_session);
		if(!checkcode.equals(checkcode_session)) {
			System.out.println("3");
			out.append("{\"error_info\":\"��֤�����!\",\"success\":false}");
			return;
		}
		//��ȡ�û���¼����
		String userId= request.getParameter("userId");//�û���¼�����֤����
		String userPassword = request.getParameter("userPassword");//�û�����
		//�����˺���֤
		User user =userService.login(userId, userPassword);
		if(user!=null) {
			System.out.println("��¼�ɹ���");
			//���õ�¼�û�����Ϣ�洢��session����
			session.setAttribute("user_info", user);
			out.write("{\"success\":true}");//��¼�ɹ�������Ϣ
		}else {
			System.out.println("��¼ʧ�ܣ�");
			out.append("{\"error_info\":\"�û������������!\",\"success\":false}");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
