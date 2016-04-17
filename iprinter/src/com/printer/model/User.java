package com.printer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User extends BaseUser {

	private static final long serialVersionUID = 1L;
	
	@Column(name="enrollyear")
	private String enrollyear; //入学时间
	
	@Column(name="major")
	private String major; //所学专业
	
	@Column(name="field")
	private String field; //感兴趣的领域
	
	@Column(name="points")
	private int points; //积分
	
	@Column(name="q")
	private String qq; //qq号码
	
	@Column(name="openid")
	private String openid; //qq检测到的id
	
	@Column(name="token")
	private String token;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="schoolid", referencedColumnName="id", unique=true)
	private School school;
	
	
	public String getEnrollyear() {
		return enrollyear;
	}
	public void setEnrollyear(String enrollyear) {
		this.enrollyear = enrollyear;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	
}
