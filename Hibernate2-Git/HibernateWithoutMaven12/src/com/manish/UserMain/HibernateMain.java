package com.manish.UserMain;

/*In web applications , It is good idea to close the transactions after user fetches the data and start another transaction after user 
 * is done with the data  and wants to update now.
 * Means there should be two separate sessions for fetch and update
 * so, inorder to get the object and bring it to the PERSISTENT state, after it gets detached, we use session.update() and there is a fair
 * chance that the fetched object was different and the object to be updated will be different. Now since the obbject is coming from diffferen session
 * session doesnt know whether there is change in the given object or not , so for safer side it runs update query even if there is nothing to update.
 * To avoid such situation we can use "@SelectBeforeUpdate" annotation on Entity 
 * */

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
		
		 //user.setUserName("After sesssion close");     		//any objeect which is not tracked by hibernate(ie.After session close) is called "DETACHED" object. It is never reflected in the database.
		 
		 session=factory.openSession();
		 session.beginTransaction();
		 session.update(user);
		 session.getTransaction().commit();
		 session.close();

	}

}
