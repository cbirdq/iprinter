package com.printer.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="entity")
public class Entry implements Serializable {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="orderid")
	private String orderid;//订单号
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="fileid", referencedColumnName="id")
	private File file;
	
	
	@Column(name="printcount")
	private int printcount;//打印份数
	
	@Column(name="pagenumber")
	private int pagenumber;//文档页数
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="priceid", referencedColumnName="id")
	private Price price; //价格
	
	@Column(name="money")
	private float money;
	
	
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
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	


}
