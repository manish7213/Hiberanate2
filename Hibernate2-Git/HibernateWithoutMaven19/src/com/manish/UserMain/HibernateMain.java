package com.manish.UserMain;

/*Caching in Hibernate, There are Two types of caching 1> Hibernate implements First level caching by default as session 2> Second level caching to
 * implement the caching across different sessions in a application or across different applications or across different clusters & 3> Query cache which keeps the result of the queries;
 * 
 * SECOND LEVEL CACHE DEMONSTRATION
 * Annotations have been added to model and cache is configured in hibernate.cfg.xml
 * */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.manish.hibernate.UserDetails;

public class HibernateMain {
 
	public static void main(String[] args) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();

		Session session=factory.openSession();

		session.beginTransaction();
		
		
		UserDetails user1= session.get(UserDetails.class,1);
	
		session.getTransaction().commit();

		session.close();
		
		//When we open a new session and query for the same object, both the select statement will be executed becz sessions(cache) are different and hence it hits the DB twice.It can be overcome by implementing second level cache.
		// Now in this program since we have implemented "Second level caching" , SELECT statement will be executed only once.
		Session session2 = factory.openSession();
		session2.beginTransaction();
		
		UserDetails user2=session2.get(UserDetails.class, 1);
		
		session2.getTransaction().commit();
		
		session2.close();

		









	}

}
