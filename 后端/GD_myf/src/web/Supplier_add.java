package web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Supplier;
import domain.User;
import services.SupplierServices;

/**
 * ��ӹ�Ӧ��
 * 
 * @author ë���
 * @caeateTime 2019��4��25������11:26:16
 * @package_name web
 * @file_name Supplier_add.java
 */
@WebServlet("/supplier_add")
public class Supplier_add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�����
		Writer out = response.getWriter();
		// ��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		// ��ȡ�û��ύ�Ĺ�Ӧ����Ϣ
		String supId = request.getParameter("supId");
		String supName = request.getParameter("supName");
		String supTel = request.getParameter("supTel");
		String supAddress = request.getParameter("supAddress");
		// �����ݿ��������Ϣ
		SupplierServices supService = new SupplierServices();
		// ��ѯsupplier�����Ƿ��иù�Ӧ��
		Supplier sup = supService.queryById(supId);
		Boolean isOk = null;
		if (sup==null) {
			//��supplier���sup_user����ͬʱ�������
			isOk = supService.addSupInTwo(userId, supId, supName, supTel, supAddress);
		}else {	
			//ֻ��sup_user�����������
			isOk=supService.addSup_user(userId,supId);
		}
		if (isOk == true) {
			out.append("{\"success\":true}");
		} else {
			out.append("{\"success\":false}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
