package com.manish.UserMain;

import java.util.List;

import org.hibernate.Query;

/*Caching in Hibernate, There are Two types of caching 1> Hibernate implements First level caching by default as session 2> Second level caching to
 * implement the caching across different sessions in a application or across different applications or across different clusters & 3> Query cache which keeps the result of the queries;
 * 
 * Query CACHE DEMONSTRATION
 * Annotations have been added to model and Query cache is configured in hibernate.cfg.xml
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
		
		
		Query query1=session.createQuery("from UserDetails where userId=1");
		query1.setCacheable(true);         // Enabling the query1 to cache
		List<UserDetails> users=query1.list();
	
		session.getTransaction().commit();

		session.close();
		
		//When we open a new session and query for the same object, both the select statement will be executed becz sessions(cache) are different and hence it hits the DB twice.It can be overcome by implementing second level cache.
		// Now in this program since we have implemented "Second level caching" , SELECT statement will be executed only once.
		//Even if we  have implemented second level caching , select query is executing twice why??
		//Because, query is different level of caching, query data is not stored in second level cache, for that we need to add Query cache configuration in hibernate.cfg.xml
		Session session2 = factory.openSession();
		session2.beginTransaction();
		
		Query query2 = session2.createQuery("from UserDetails where userId=1");
		query2.setCacheable(true);       //Enabling query cache
		users=query2.list();
		
		session2.getTransaction().commit();
		
		session2.close();

		









	}

}
