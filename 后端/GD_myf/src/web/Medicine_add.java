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

import domain.Medicine;
import domain.Purchase_bill;
import domain.User;
import services.MedicineService;
/**
 * ʵ��ҩƷ��⹦�ܣ�����ҩƷ��Ϣ��¼��Ͷ�����Ϣ¼�룩
 * @author ë���
 * @caeateTime 2019��4��28������9:06:11
   @package_name web
	@file_name Medicine_add.java
 */
@WebServlet("/medicine_add")
public class Medicine_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out=response.getWriter();
		//��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//��������������
		Purchase_bill purBill = new Purchase_bill();
		//����ҩƷ��Ϣ����
		Medicine medi = new Medicine();
		//��ǰ̨��ȡ���ݲ���װ����Ӧ�Ķ�����
		try {
			BeanUtils.populate(purBill, request.getParameterMap());
			BeanUtils.populate(medi, request.getParameterMap());
			//���㶩���Ľ����ܶ�
			purBill.setTotal();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MedicineService  medService = new MedicineService();
		//ҩƷ���
		Boolean isSuccess=medService.addMedicine(medi,purBill,userId);
		System.out.println("123:"+isSuccess);
		if(isSuccess==true) {
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
