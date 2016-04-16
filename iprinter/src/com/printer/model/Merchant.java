package com.printer.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//联系人信息
@Entity
@Table(name="merchant")
public class Merchant extends BaseUser {

  private static final long serialVersionUID = 1L;
    
    @OneToOne(optional=false)
    @JoinColumn(name="shopid", referencedColumnName="id", unique=true)
	private Shop shop;
    
    
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	

	
	
}
