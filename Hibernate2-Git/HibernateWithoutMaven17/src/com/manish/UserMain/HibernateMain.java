package com.manish.UserMain;

/*Using CRITERIA  API 's PROJECTIONS and querying by example to fetch data*/

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

		/*Criteria criteria=session.createCriteria(UserDetails.class)
								  .setProjection(Projections.property("userId"));*/   //return list of user Ids ie. list of Integers


		//Returinig aggregate functions
		/*Criteria criteria=session.createCriteria(UserDetails.class)
				  .setProjection(Projections.max("userId"));*/              //return list userId ie. list of Integers

		/*Criteria criteria=session.createCriteria(UserDetails.class)		//return list userId ie. list of String
				  .setProjection(Projections.count("userId"));*/

		//Sorting
		/*Criteria criteria=session.createCriteria(UserDetails.class)    //return UserDetails object
				  .addOrder(Order.desc("userId"));*/


		//USING QUERYING BY EXAMPLE

		UserDetails exampelUser = new UserDetails();

		//exampelUser.setUserId(5);
//		exampelUser.setUserName("USER_NAME_5");    //Example accepts everything except null values and Ids. So commenting userid doesnt impact the program
		exampelUser.setUserName("USER_NAME_1%");    //to use enableLike()

		/*Example example = Example.create(exampelUser);

		Criteria criteria = session.createCriteria(UserDetails.class)
									.add(example);*/

		/*Example example = Example.create(exampelUser).excludeProperty("userName");  //excluseProperty is used to tell hibernate explicitely to ignore that property

		Criteria criteria = session.createCriteria(UserDetails.class)
				.add(example);*/
		
		Example example = Example.create(exampelUser).enableLike();  //excluseProperty is used to tell hibernate explicitely to ignore that property

		Criteria criteria = session.createCriteria(UserDetails.class)
				.add(example);








		List<UserDetails> users = (List<UserDetails>)criteria.list();

		/*List<Integer> userIds= (List<Integer>)criteria.list();*/

		session.getTransaction().commit();

		session.close();

		for(UserDetails user:users)
			System.out.println(user.getUserName());









	}

}
