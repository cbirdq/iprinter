package com.printer.model;

import java.sql.Timestamp;

//支付单
public class Payment {
private String id;
private String orderid;
private Timestamp paytime;
private float paymoney;
private String sourcealipaynum;
private String aimalipaynum;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public Timestamp getPaytime() {
	return paytime;
}
public void setPaytime(Timestamp paytime) {
	this.paytime = paytime;
}
public float getPaymoney() {
	return paymoney;
}
public void setPaymoney(float paymoney) {
	this.paymoney = paymoney;
}
public String getSourcealipaynum() {
	return sourcealipaynum;
}
public void setSourcealipaynum(String sourcealipaynum) {
	this.sourcealipaynum = sourcealipaynum;
}
public String getAimalipaynum() {
	return aimalipaynum;
}
public void setAimalipaynum(String aimalipaynum) {
	this.aimalipaynum = aimalipaynum;
}
}
