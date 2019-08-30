package web;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import domain.Supplier;
import domain.User;
import services.SupplierServices;
/**
 * ��ѯ��ǰ�û������й�Ӧ��
 * @author ë���
 * @caeateTime 2019��4��25������2:08:54
   @package_name web
	@file_name Supplier_query.java
 */
@WebServlet("/supplier_query")
public class Supplier_query extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out=response.getWriter();
		//��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		//�����ݿ��в�ѯ����ǰ�û������й�Ӧ��
		SupplierServices supService = new SupplierServices();
		List<Supplier> supList =supService.query(userId);
		//�����ݴ���ǰ̨
		out.append("{");
		out.append("\"success\":true,");
		out.append("\"data\":[");
		int index=0;
		for(Supplier sup:supList) {
			//����JavaBean����ת��ΪjsonObject����
			JSONObject jsonObj =new JSONObject(sup);
			if(index>0) {
				out.append(",");
			}
			out.append(jsonObj.toString());
			index++;	
		}
		out.append("]");
		out.append("}");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
