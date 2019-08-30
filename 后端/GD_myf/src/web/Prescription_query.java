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

import domain.Prescription;
import services.PrescriptionServices;
/**
 * ���ݻ����籣���Ų�ѯ�䴦����Ϣ
 * @author ë���
 * @caeateTime 2019��5��15������10:22:51
   @package_name web
	@file_name Prescription_query.java
 */
@WebServlet("/prescription_query")
public class Prescription_query extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�����
		Writer out=response.getWriter();		
		String pId=request.getParameter("ss").trim();
		System.out.println(pId);
		PrescriptionServices preService = new PrescriptionServices();
		//��ȡ��������
		List<Prescription> list = preService.getPreList(pId);
		if(list==null) {
			out.append("{\"success\":false}");
		}else {
			out.append("{\"success\":true,\"data\":[");
			int index=0;
			for(Prescription pre:list) {
				if(index>0) {
					out.append(",");
				}
				System.out.println("11111"+pre.toString());
				JSONObject jsonObj =new JSONObject(pre);
				jsonObj.put("pId",pre.getpId());
				jsonObj.put("pName",pre.getpName());
				jsonObj.put("pSex",pre.getpSex());
				jsonObj.put("pBirth",pre.getpBirth());
				jsonObj.put("med_detais",pre.getMed_details());			
				System.out.println("22222"+jsonObj.toString());
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
