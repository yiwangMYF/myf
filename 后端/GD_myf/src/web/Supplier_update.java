package web;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Supplier;
import services.SupplierServices;
/**
 * ��Ӧ����Ϣ�޸�
 * @author ë���
 * @caeateTime 2019��4��26������10:05:45
   @package_name web
	@file_name Supplier_update.java
 */
@WebServlet("/supplier_update")
public class Supplier_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer out =response.getWriter();
		//����Supplier����
		Supplier sup =new Supplier();
		//��ǰ̨���ݵ����ݷ�װ��Supplier������
		try {
			BeanUtils.populate(sup, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SupplierServices supService = new SupplierServices();
		Boolean isSuccess =supService.update(sup);
		if(isSuccess) {
			out.append("{\"success\":true}");
		}else {
			out.append("{\"success\"��false}");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
