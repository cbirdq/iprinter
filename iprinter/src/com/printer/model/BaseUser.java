package com.printer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author ThinkPad
 *
 */
@MappedSuperclass  //该注解声明了这个类是一个父类
public class BaseUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private String id; //���
	
	@Column(name="name")
	private String username; //用户姓名
	
	@Column(name="passwork")
	private String password; //密码
	
	@Column(name="gender")
	private String gender = "男"; //性别
	
	@Column(name="phone")
	private String phone; //电话
	
	@Column(name="email")
	private String email; //邮箱
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
