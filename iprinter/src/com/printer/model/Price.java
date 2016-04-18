package com.printer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="price")
public class Price implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="papersize")
	private String papersize;//纸张类型
	
	@Column(name="price")
	private float price;//单价
	
	@Column(name="biside")
	private int biside = 0; //是否双面打印(默认单面打印)
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getBiside() {
		return biside;
	}
	public void setBiside(int biside) {
		this.biside = biside;
	}
	
	
}
