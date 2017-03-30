package com.manish.UserMain;

/*Caching in Hibernate, There are Two types of caching 1> Hibernate implements First level caching by default as session 2> Second level caching to
 * implement the caching across different sessions in a application or across different applications or across different clusters*/

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.manish.hibernate.UserDetails;

public class HibernateMain {

	public static void main(String[] args) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();

		Session session=factory.openSession();

		session.beginTransaction();
		
		/*UserDetails user1= session.get(UserDetails.class,1);
		UserDetails user2 = session.get(UserDetails.class,1);*/  //Since both objects are same,hibernate will not query for the object again ,hence select query will be executed only once,Hibernate maintains the minimum hit on the database
		
		/*UserDetails user1= session.get(UserDetails.class,1);
		user1.setUserName("UPDATED_USER_NAME_1");
		UserDetails user2 = session.get(UserDetails.class,1);*/ // In this case one select and one update statement will be executed, second select will not be executed becz its querying for the same object.
		UserDetails user1= session.get(UserDetails.class,1);
	
		session.getTransaction().commit();

		session.close();
		
		//When we open a new session and query for the same object, both the select statement will be executed becz sessions(cache) are different and hence it hits the DB twice.It can be overcome by implementing second level cache.
		
		Session session2 = factory.openSession();
		session2.beginTransaction();
		
		UserDetails user2=session2.get(UserDetails.class, 1);
		
		session2.getTransaction().commit();
		
		session2.close();

		









	}

}
