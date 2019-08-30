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

/**
 * ʵ����ҳ������û���Ϣ
 * @author ë���
 * @caeateTime 2019��4��21������3:31:02
   @package_name web
	@file_name UserInfo.java
 */
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserInfo() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out =response.getWriter();
		//��ȡ�û�����
		HttpSession session = request.getSession();
		User user= (User) session.getAttribute("user_info");
		String user_name= user.getUserName();
		//����json����
		JSONObject jsonObj= new JSONObject();
		jsonObj.put("user_name", user_name);
		
		//��ҳ�������Ϣ���û����ƣ�
		out.append(jsonObj.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
