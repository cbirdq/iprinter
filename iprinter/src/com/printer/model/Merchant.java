package com.printer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

//联系人信息
@Entity(name="merchant")
public class Merchant extends BaseUser {

  private static final long serialVersionUID = 1L;
    @Column(name="shopid")
	private String shopid;
  @OneToOne
	private Shop shop;
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public String getShopid() {
		return shopid;
	}
	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	

	
	
}
