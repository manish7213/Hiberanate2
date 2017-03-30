package com.manish.hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity 
@NamedQuery(name="UserDetails.byId",query="from UserDetails where userId > ?")   //HQL Queries
/*@NamedNativeQuery(name="UserDetails.byid",query="from User_Details where userid > ?",resultClass=UserDetails.class)  //Native SQL queries ...it performs opertaions on tables and column rather than Entity and attributes 
*/public class UserDetails {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	private String userName;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}





}
