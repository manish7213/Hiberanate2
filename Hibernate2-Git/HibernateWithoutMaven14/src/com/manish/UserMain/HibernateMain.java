package com.manish.UserMain;

/*Parameter Binding and SQL Injection in HQL*/

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
		/*int userId=5;
		Query query=session.createQuery("from UserDetails where userId > "+userId);  //prone to SQL Injection,NOT RECOMMENDED
*/		
		/*It may be modified like this and it will give all the values instead of filtered by where clause*/
		
		/*String userId="5 or 1 = 1";
		Query query=session.createQuery("from UserDetails where userId > "+userId); // where clause is fulfilled and we can get all the values by modifying the variable usiing "OR" operator
*/	
		
		
		//To avoid such vulnerability we use the below method called parameter binding
		
//*************************************First Method using parameter position********************************************************		
		/*String userId="5";
		String userName="USER_NAME_7";
		
		Query query=session.createQuery("from UserDetails where userId>? and userName=?");
		
		query.setInteger(0, Integer.parseInt(userId));  		//The first parameter denotes the parameter position 
		
		query.setString(1, userName);							//The first parameter denotes the parameter position
		
*/
//***************************************Second Method using named parameter **************************************************	
		String userId="5";
		String userName="USER_NAME_7";
		
		Query query=session.createQuery("from UserDetails where userId > :userId and userName = :userName"); //instead of ? we use :and field name
		
		query.setInteger("userId", Integer.parseInt(userId));  		//here first parameter is always string which is the placeholder name
		query.setString("userName", userName);						
		List<UserDetails> users = (List<UserDetails>)query.list();
		
		session.getTransaction().commit();
		
		session.close();
		
		for(UserDetails user:users)
			System.out.println(user.getUserName());
		
		




	}

}
