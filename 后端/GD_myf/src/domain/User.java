package domain;

import java.io.Serializable;

/**
 * �û����ݳ־û���
 * @author ë���
 * @caeateTime 2019��4��9������8:26:01
   @package_name domain
	@file_name User.java
 */

public class User implements Serializable{
	private String userId;//���֤��
	private String userName;//�û�����
	private String userPassword;//�û�����
	private String userGender;//�û��Ա�
	
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
