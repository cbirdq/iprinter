package com.printer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="file")
public class Files implements Serializable {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="filename")
	private String filename;//文件名称
	
	@Column(name="originalname")
	private String originalname; //文件上传之前的名字
	
	@Column(name="value")
	private int value;//文件价值
	
	@Column(name="remark")
	private String remark;//文件说明
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOriginalname() {
		return originalname;
	}
	public void setOriginalname(String originalname) {
		this.originalname = originalname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
