package com.manish.UserMain;

/*Named Query and Native Sql queries*/

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.manish.hibernate.UserDetails;

public class HibernateMain {

	public static void main(String[] args) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();

		Session session=factory.openSession();

		session.beginTransaction();
		
		Query query=session.getNamedQuery("UserDetails.byId");   //Named query
		
		/*Query query2 =session.getNamedQuery("UserDetails.byId");  // This is for Native SQL query*/	
		
		query.setInteger(0,6);     //We could also use named parameter binding instead ? ie. :userId
							
		List<UserDetails> users = (List<UserDetails>)query.list();
		
		session.getTransaction().commit();
		
		session.close();
		
		for(UserDetails user:users)
			System.out.println(user.getUserName());
		
		




	}

}
