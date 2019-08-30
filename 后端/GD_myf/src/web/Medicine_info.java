package web;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Medicine_base;
import domain.Supplier;
import services.MedicineService;
import services.SupplierServices;
/**
 * ��ѯҩƷ��Ϣ
 * @author ë���
 * @caeateTime 2019��5��7������9:41:34
   @package_name web
	@file_name Medicine_info.java
 */

@WebServlet("/medicine_info")
public class Medicine_info extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out=response.getWriter();		
		String text=request.getParameter("ss").trim();
		//�����û��������Ϣ��ѯ���ܵ�ҩƷ��Ϣ
		MedicineService  medService = new MedicineService();
		SupplierServices supService = new SupplierServices();
		List<Medicine_base> list = medService.fuzzyQueryByMedName(text);
		if(list.size()==0) {
			out.append("{\"success\":false}");
		}else {
			out.append("{\"success\":true,\"data\":[");
			int index=0;
			for(Medicine_base mb:list) {
				
				JSONObject jsonObj =new JSONObject();
				jsonObj.put("mId",mb.getmId());
				jsonObj.put("mName",mb.getmName());
				jsonObj.put("mSpec",mb.getmSpec());
				jsonObj.put("mCateGory",mb.getmCateGory());
				Supplier sup = supService.queryById(mb.getSupId());
				jsonObj.put("supName",sup.getSupName());
				jsonObj.put("supAddress", sup.getSupAddress());
				jsonObj.put("supTel",sup.getSupTel());
				if(index>0) {
					out.append(",");
				}
				
				out.append(jsonObj.toString());
				index++;
			}
			out.append("]");
			out.append("}");
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
