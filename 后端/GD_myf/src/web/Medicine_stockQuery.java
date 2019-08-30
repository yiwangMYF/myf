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

import domain.StockInfo;
import domain.User;
import services.MedicineService;
/**
 * ����ҩƷ���ƽ���ģ����ѯ
 * @author ë���
 * @caeateTime 2019��5��6������10:17:16
   @package_name web
	@file_name Medicine_stockQuery.java
 */

@WebServlet("/medicine_stockQuery")
public class Medicine_stockQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out=response.getWriter();
		//��session�л�ȡ��ǰ�û���Ϣ
		HttpSession session =request.getSession();
		User user= (User) session.getAttribute("user_info");
		String userId= user.getUserId();
		
		String mName=request.getParameter("mName");
		MedicineService medService=new MedicineService();
		List<StockInfo> list=medService.getDataWithMId(userId,mName);
		out.append("{\"success\":true,\"data\":[");
		int index=0;
		for(StockInfo s:list) {
			JSONObject jsonObj= new JSONObject(s);
			System.out.println(s.toString());
			jsonObj.put("mId", s.getmId());
			jsonObj.put("mName",s.getmName());
			if(index>0) {
				out.append(",");
			}
			out.append(jsonObj.toString());
			System.out.println(jsonObj.toString());
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
