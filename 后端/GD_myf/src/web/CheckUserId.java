package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import services.UserServices;
/**
 * ��֤�û�ע���id(���֤��)�Ƿ��ѱ�ע���
 * @author ë���
 * @caeateTime 2019��4��13������10:12:38
   @package_name web
	@file_name CheckUserId.java
 */

@WebServlet("/checkUserId")
public class CheckUserId extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CheckUserId() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("������checkUserId");
		//�����������
		Writer out = response.getWriter();
		//��ȡ�ύ��userId
		String userId= request.getParameter("userId");
		System.out.println(userId);
		//�����û��������
		UserServices userService= new UserServices();
		User user=userService.queryById(userId);
		//System.out.println(user.getUserId());
		if(user!=null) {
			System.out.println("true+++2222");
			out.append("{\"isExist\":true}");
		}
		else {
			System.out.println("false+22222");
			out.append("{\"isExist\":false}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
