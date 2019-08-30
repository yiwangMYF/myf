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
 * ɾ����Ӧ��
 * @author ë���
 * @caeateTime 2019��4��25������8:38:41
   @package_name web
	@file_name Supplier_delete.java
 */

@WebServlet("/supplier_delete")
public class Supplier_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out=response.getWriter();
		//��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//��ȡ��Ҫɾ���Ĺ�Ӧ��id
		String supId=request.getParameter("id");
		System.out.println(supId);
		//�����ݿ���ɾ��ָ��userId��supId������
		SupplierServices supService = new SupplierServices();
		Boolean delSuccess=supService.delete(userId,supId);
		if(delSuccess) {
			out.append("{\"success\":true}");
		}else {
			out.append("{\"success\":false}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
