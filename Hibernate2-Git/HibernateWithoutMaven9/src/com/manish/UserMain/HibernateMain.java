package com.manish.UserMain;

/* This Project demonstrates the  INHERITANCE WITH JOIN strategy,  Means separate tables are created for each parent and child classes but unlike
 * Table per class strategy Parent table will contain information(what all attributes present in the parent class) about the self and the child classes as well.
 * Child tables will only contain the class specific attributes apart from parent class.
 *The tables that we get is more normalised than TABLE PER CLASS STRATEGY */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.manish.hibernate.FourWheeler;
import com.manish.hibernate.TwoWheeler; 
import com.manish.hibernate.Vehicle;

public class HibernateMain {

	public static void main(String[] args) {


		
		Vehicle vehicle=new Vehicle();
		vehicle.setVehicleName("Car");
		
		TwoWheeler bike=new TwoWheeler();
		bike.setVehicleName("bike");
		bike.setSteeringHandle("bike steeringHandle");
		
		FourWheeler car = new FourWheeler();
		car.setVehicleName("porsche");
		car.setSteeringWheel("porsche steeringWheel");
		
		

		SessionFactory factory=new Configuration().configure().buildSessionFactory();

		Session session=factory.openSession();

		session.beginTransaction();
		
		session.save(vehicle);
		session.save(bike);
		session.save(car);
		
		

		session.getTransaction().commit();
		session.close();

		



	}

}
