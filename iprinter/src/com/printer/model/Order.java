package com.printer.model;

import java.sql.Timestamp;
import java.util.List;

public class Order {
private String id;
private List<Entry> entry;//文件明细
private String userid;//用户
private String shopid;//取货点
private Timestamp  createtime;//下单时间
private Timestamp  fetchtime;//取货时间
private Timestamp  confirmtime;//订单确认时间
private String status;//打印状态
private float money;//打印费用
private float pointpay;//积分支付
private float payment;//实际支付金额
private  int filenumber;//文件数量
private  String receipient;//签收人
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public List<Entry> getEntry() {
	return entry;
}
public void setEntry(List<Entry> entry) {
	this.entry = entry;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getShopid() {
	return shopid;
}
public void setShopid(String shopid) {
	this.shopid = shopid;
}
public Timestamp getCreatetime() {
	return createtime;
}
public void setCreatetime(Timestamp createtime) {
	this.createtime = createtime;
}
public Timestamp getFetchtime() {
	return fetchtime;
}
public void setFetchtime(Timestamp fetchtime) {
	this.fetchtime = fetchtime;
}
public Timestamp getConfirmtime() {
	return confirmtime;
}
public void setConfirmtime(Timestamp confirmtime) {
	this.confirmtime = confirmtime;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public float getMoney() {
	return money;
}
public void setMoney(float money) {
	this.money = money;
}
public float getPointpay() {
	return pointpay;
}
public void setPointpay(float pointpay) {
	this.pointpay = pointpay;
}
public float getPayment() {
	return payment;
}
public void setPayment(float payment) {
	this.payment = payment;
}
public int getFilenumber() {
	return filenumber;
}
public void setFilenumber(int filenumber) {
	this.filenumber = filenumber;
}
public String getReceipient() {
	return receipient;
}
public void setReceipient(String receipient) {
	this.receipient = receipient;
}
}
