package domain;

import java.io.Serializable;

/**
 * 用户数据持久化类
 * @author 毛燕丰
 * @caeateTime 2019年4月9日下午8:26:01
   @package_name domain
	@file_name User.java
 */

public class User implements Serializable{
	private String userId;//身份证号
	private String userName;//用户姓名
	private String userPassword;//用户密码
	private String userGender;//用户性别
	
	public User() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserGender() {
		return userGender;
	}

	
	
	
	
	

}
