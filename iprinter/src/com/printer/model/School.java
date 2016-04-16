package com.printer.model;
//高校
public class School {
	private String id;
	private String shoolname;//学校名称
	private String city;//学校所在城市
	private String province;//学校所在身份
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShoolname() {
		return shoolname;
	}
	public void setShoolname(String shoolname) {
		this.shoolname = shoolname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
}
