package com.manish.UserMain;

import java.util.List;

import org.hibernate.Query;

/*HQL(Hibernate Query Language) DEMONSTRATION
 * Class behaves as Table and member variables behaves as columns
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
		
		/*Query query=session.createQuery("from UserDetails");
		
		query.setFirstResult(5);     //sets the first record to be fetched (argument index starts with 0 hence 5 means ,it starts with 6th record)
		query.setMaxResults(4);			// sets the maximum number of records to be fetched
		
		List<UserDetails> users=(List<UserDetails>)query.list();
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(users.size());
		
		for(int i=0;i<users.size();i++){
			
			System.out.println(users.get(i).getUserName());
		}*/
		
		//***********************Second way is to use the quer as simple sql*********************************
		Query query=session.createQuery(" select userName from UserDetails");
		query.setFirstResult(5);     //sets the first record to be fetched (argument index starts with 0 hence 5 means ,it starts with 6th record)
		query.setMaxResults(4);			// sets the maximum number of records to be fetched
		
		List<String> users=(List<String>)query.list();
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(users.size());
		
		for(int i=0;i<users.size();i++){
			
			System.out.println(users.get(i));
		}
		
		//We can use select max(userId) ie. all the aggregate functions of sql
		//Even we can select userId,userName from UserDetails. this will return List of lists
		//we can also select map(userId,userName),this will return list of map.

		





	}

}
