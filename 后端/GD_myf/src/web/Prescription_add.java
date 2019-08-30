package web;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import domain.Prescription;
import domain.User;
import services.PrescriptionServices;
/**
 * ��Ӵ���
 * @author ë���
 * @caeateTime 2019��5��14������6:20:37
   @package_name web
	@file_name Prescription_add.java
 */
@WebServlet("/prescription_add")
public class Prescription_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out=response.getWriter();
		//��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//������������
		Prescription pre= new Prescription();
		try {
			BeanUtils.populate(pre, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String med=request.getParameter("med");
		pre.setUserId(userId);
		PrescriptionServices preService = new PrescriptionServices();
		Boolean isSuccess = preService.addPreInfo(pre,med);
		if(isSuccess==false) {
			out.append("{\"success\":false}");
		}else {
			out.append("{\"success\":true}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
