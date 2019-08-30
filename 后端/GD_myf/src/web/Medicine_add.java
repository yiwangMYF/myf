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
 * 实现药品入库功能（包括药品信息的录入和订单信息录入）
 * @author 毛燕丰
 * @caeateTime 2019年4月28日上午9:06:11
   @package_name web
	@file_name Medicine_add.java
 */
@WebServlet("/medicine_add")
public class Medicine_add extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取输出流
		Writer out=response.getWriter();
		//从session中获取当前用户信息
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//创建进货单对象
		Purchase_bill purBill = new Purchase_bill();
		//创建药品信息对象
		Medicine medi = new Medicine();
		//从前台获取数据并封装到相应的对象中
		try {
			BeanUtils.populate(purBill, request.getParameterMap());
			BeanUtils.populate(medi, request.getParameterMap());
			//计算订单的交易总额
			purBill.setTotal();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MedicineService  medService = new MedicineService();
		//药品入库
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
