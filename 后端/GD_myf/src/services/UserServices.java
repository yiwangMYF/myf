package services;

import java.sql.SQLException;

import dao.UserDao;
import domain.User;

/**
 * 用户服务业务层，包括用户登录，用户注册
 * @author 毛燕丰
 * @caeateTime 2019年4月10日上午10:22:23
   @package_name services
	@file_name UserServices.java
 */

public class UserServices {
	//创建用户数据交互类对象
	private static UserDao userdao = new UserDao();
	//用户登录
	public User login(String userId,String userPassword) {
		User user=userdao.queryByid_ps(userId, userPassword);
		return user;
	}
	//通过userId查询用户
	public User queryById(String userId) {
		User user =userdao.queryById(userId);
		return user;
	}
	//插入一个用户（注册）
	public boolean insertUser(String userId,String userName,String userPassword,String userGender) {
		return userdao.insertUser(userId,userName,userPassword,userGender);
		
	}
	//修改密码
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
