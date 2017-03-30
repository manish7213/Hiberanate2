package com.manish.UserMain;

/*CRUD OPERATION DEMONSTRATION*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.manish.hibernate.UserDetails;

public class HibernateMain {

	public static void main(String[] args) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();

		Session session=factory.openSession();

		session.beginTransaction();
//****************************************************************CREATE OPERATION*************************************************
		for(int i=1;i<=10;i++){
			UserDetails user=new UserDetails();
			user.setUserName("USER_NAME_"+i);
			session.save(user);
		}
//***************************************************************READ OPERATION***************************************************
	/*	UserDetails user=session.get(UserDetails.class, 5);
		System.out.println("user name for id: " +user.getUserId()+" is "+user.getUserName());*/
		
//**************************************************************UPDATE OPEARTION**************************************************
		/*UserDetails user=session.get(UserDetails.class, 5);
		user.setUserName("UPDATED USER_NAME_5");
		session.update(user);*/
		
//************************************************************DELETE OPERATION**************************************************
		
		/*UserDetails user= session.get(UserDetails.class, 6);
		session.delete(user);*/
		
//*****************************************************************************************************************************
		session.getTransaction().commit();
		session.close();

		





	}

}
