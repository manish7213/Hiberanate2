package com.manish.UserMain;

/*TRANSIENT PERSISTENT AND DETACHED OBJECTS  DEMONSTRATION*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.manish.hibernate.UserDetails;

public class HibernateMain {

	public static void main(String[] args) {
		
		UserDetails user=new UserDetails();
		
		user.setUserName("username Before Saving");      // Any object which is not yet handed over to hibernate ie.(saving) is TRANSIENT object 

		SessionFactory factory=new Configuration().configure().buildSessionFactory();

		Session session=factory.openSession();

		session.beginTransaction();
		
		session.save(user);
		
		user.setUserName("updated username, after save");  //Any object which is tracked by hibernate is called "PERSISTENT" object. ie. the state of object after save and before commit.
		user.setUserName("latest updated username");      //Any latest update on object which is tracked by hibernate will be reflected in the DB.
		session.getTransaction().commit();
		session.close();
		
		 user.setUserName("After sesssion close");     		//any objeect which is not tracked by hibernate(ie.After session close) is called "DETACHED" object. It is never reflected in the database.


	}

}
