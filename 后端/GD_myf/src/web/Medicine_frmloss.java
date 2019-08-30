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

import domain.Frmloss_bill;
import domain.User;
import services.MedicineService;
/**
 * ҩƷ����
 * @author ë���
 * @caeateTime 2019��5��7������7:05:33
   @package_name web
	@file_name Medicine_frmloss.java
 */
@WebServlet("/medicine_frmloss")
public class Medicine_frmloss extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�����
		Writer out = response.getWriter();
		// ��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user_info");
		String userId = user.getUserId();
		
		Frmloss_bill lossBill =new Frmloss_bill();
		try {
			BeanUtils.populate(lossBill, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(lossBill.toString());
		MedicineService medService=new MedicineService();
		Boolean isSuccess =medService.frmLoss(userId,lossBill);
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
