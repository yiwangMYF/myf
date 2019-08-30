package services;

import java.sql.SQLException;

import dao.UserDao;
import domain.User;

/**
 * �û�����ҵ��㣬�����û���¼���û�ע��
 * @author ë���
 * @caeateTime 2019��4��10������10:22:23
   @package_name services
	@file_name UserServices.java
 */

public class UserServices {
	//�����û����ݽ��������
	private static UserDao userdao = new UserDao();
	//�û���¼
	public User login(String userId,String userPassword) {
		User user=userdao.queryByid_ps(userId, userPassword);
		return user;
	}
	//ͨ��userId��ѯ�û�
	public User queryById(String userId) {
		User user =userdao.queryById(userId);
		return user;
	}
	//����һ���û���ע�ᣩ
	public boolean insertUser(String userId,String userName,String userPassword,String userGender) {
		return userdao.insertUser(userId,userName,userPassword,userGender);
		
	}
	//�޸�����
	public boolean setNewPassword(String userId,String newPassword) {
		boolean success=false;
		try {
			success=userdao.setNewPassword(userId,newPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

}
