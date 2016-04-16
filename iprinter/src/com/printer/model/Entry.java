package com.printer.model;
public class Entry {
private String id;
private String orderid;//订单号
private String fileid;//文件ID
private String filename;//文件名
private int printcount;//打印份数
private int pagenumber;//文档页数
private String papersize;//纸张类型
private int dupprint;//是否双面打印；
private float money;//单价/张
private String fileremark;//文件说明
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
public int getPrintcount() {
	return printcount;
}
public void setPrintcount(int printcount) {
	this.printcount = printcount;
}
public int getPagenumber() {
	return pagenumber;
}
public void setPagenumber(int pagenumber) {
	this.pagenumber = pagenumber;
}
public int getDupprint() {
	return dupprint;
}
public void setDupprint(int dupprint) {
	this.dupprint = dupprint;
}

public String getPapersize() {
	return papersize;
}
public void setPapersize(String papersize) {
	this.papersize = papersize;
}

public float getMoney() {
	return money;
}
public void setMoney(float money) {
	this.money = money;
}
public String getFileid() {
	return fileid;
}
public void setFileid(String fileid) {
	this.fileid = fileid;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getFileremark() {
	return fileremark;
}
public void setFileremark(String fileremark) {
	this.fileremark = fileremark;
}



}
