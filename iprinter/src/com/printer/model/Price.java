package com.printer.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;


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
	
	@Column(name="color")
	private int color = 0; //是否彩印（默认否）
	
	
	public Price() {
		
	}
	
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
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	
	
	public static void main(String[] args) {
		Price  price = new Price();
		price.setBiside(0);
		price.setColor(0);
		price.setId(0);
		price.setPapersize("A4");
		price.setPrice(3);
		
		/*JSONObject jo = new JSONObject();
		jo.put("id", price.getId());
		jo.put("biside", price.getBiside());
		jo.put("color", price.getColor());
		jo.put("price", price.getPrice());
		jo.put("papersize", price.getPapersize());
		System.out.println(jo.toString());
		
		JSONObject jos = new JSONObject(jo);
		System.out.println(jos.toString());
		*/
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", price.getId());
		map.put("biside", price.getBiside());
		map.put("color", price.getColor());
		map.put("price", price.getPrice());
		map.put("papersize", price.getPapersize());
		

		JSONObject json = new JSONObject();
		json.put("price", map);
		System.out.println(json.toString());
	}
	
}
