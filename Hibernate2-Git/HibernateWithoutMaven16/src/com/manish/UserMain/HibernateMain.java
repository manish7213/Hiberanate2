package com.manish.UserMain;

/*Using CRITERIA  API to fetch data*/

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import com.manish.hibernate.UserDetails;

public class HibernateMain {

	public static void main(String[] args) {

		SessionFactory factory=new Configuration().configure().buildSessionFactory();

		Session session=factory.openSession();

		session.beginTransaction();

		Criteria criteria=session.createCriteria(UserDetails.class);

		/*criteria.add(Restrictions.eq("userName", "USER_NAME_10"));*/


		/*This is the way to add criteria with AND logic means we will get the results fulfilled by both the restrictions, 
		 * we can add any number of restrictions like this
		 */
		/*criteria.add(Restrictions.eq("userName","USER_NAME_9"))    
				.add(Restrictions.gt("userId", 5));*/


		//More where clause Restrictions

		/*criteria.add(Restrictions.like("userName", "%USER_NAME_5%"))
				.add(Restrictions.between("userId", 3, 8));*/

		/*The above add chaining is work on AND logic if both the values are satisfied then result is displayed */

		/*Now, HOW TO DEAL WITH "OR" LOGIC , MEANS IF ANY ONE RESTRICTION IS SATISFIED ,THE RESULT SHOULD BE DISPLAYED*/

		criteria.add(Restrictions.or(Restrictions.between("userId",0,3), Restrictions.between("userId", 7, 10)));


		List<UserDetails> users = (List<UserDetails>)criteria.list();

		session.getTransaction().commit();

		session.close();

		for(UserDetails user:users)
			System.out.println(user.getUserName());






	}

}
