package com.printer.model;

public class Price {
private String id;
private String papersize;//纸张类型
private float price;//单价
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getPapersize() {
	return papersize;
}
public void setPapersize(String papersize) {
	this.papersize = papersize;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}
}
