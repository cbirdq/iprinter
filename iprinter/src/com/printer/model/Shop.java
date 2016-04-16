package com.printer.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity(name="shop")
public class Shop {
@Id
@Column(name="id")
private String id;
@Column(name="shopname")
private String shopname;//打印店名称
@Column(name="address")
private String address;//打印店地址
@Column(name="phone")
private String phone;//电话
@Column(name="shoolid")
private String shoolid;//所属学校编号
@Column(name="setuptime")
private Timestamp setuptime;//打印店建立时间
@Column(name="opentime")
private String opentime;//营业时间



public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getShopname() {
	return shopname;
}
public void setShopname(String shopname) {
	this.shopname = shopname;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getShoolid() {
	return shoolid;
}
public void setShoolid(String shoolid) {
	this.shoolid = shoolid;
}
public Timestamp getSetuptime() {
	return setuptime;
}
public void setSetuptime(Timestamp setuptime) {
	this.setuptime = setuptime;
}
public String getOpentime() {
	return opentime;
}
public void setOpentime(String opentime) {
	this.opentime = opentime;
}
}
