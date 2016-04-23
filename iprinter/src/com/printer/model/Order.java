package com.printer.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Order")
public class Order implements Serializable {
	
	
	@Id
	@Column(name="id")
	private String id;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="entryid", referencedColumnName="id")
	private Collection<Entry> entries;//订单明细
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userid", referencedColumnName="id")
	private User user;//用户

	@Column(name="shopid")
	private String shopid;//取货点
	
	@Column(name="createtime")
	private Timestamp  createtime;//下单时间
	
	@Column(name="fetchtime")
	private Timestamp  fetchtime;//取货时间
	
	@Column(name="confirmtime")
	private Timestamp  confirmtime;//订单确认时间
	
	@Column(name="status")
	private String status;//打印状态
	
	@Column(name="money")
	private float money;//打印费用
	
	@Column(name="pointpay")
	private float pointpay;//积分支付
	
	@Column(name="payment")
	private float payment;//实际支付金额
	
	@Column(name="filenumber")
	private  int filenumber;//文件数量
	
	@Column(name="receipient")
	private  String receipient;//签收人
	
	@Column(name="payway")
	private int payway; //支付方式
	
	@Column(name="comment")
	private String comment; //备注
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public Collection<Entry> getEntries() {
		return entries;
	}
	public void setEntries(Collection<Entry> entries) {
		this.entries = entries;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getPayway() {
		return payway;
	}
	public void setPayway(int payway) {
		this.payway = payway;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
		
	
}
