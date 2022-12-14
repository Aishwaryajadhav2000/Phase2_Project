package com.simplilearn.B;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.simplilearn.C.ClassRoom;
import com.simplilearn.C.Student;

public class Database {

	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			
			try {
				Configuration configuration = new Configuration();
				
				Properties hibernateProperties = new Properties();
				hibernateProperties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				hibernateProperties.put(Environment.URL, "jdbc:mysql://localhost:3306/SecondPhaseProject");
				hibernateProperties.put(Environment.USER, "root");
				hibernateProperties.put(Environment.PASS, "Aishwarya1608");
				hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.MYSQL5InnoDBDialect");
				hibernateProperties.put(Environment.SHOW_SQL, "true");
				hibernateProperties.put(Environment.FORMAT_SQL, "true");
				hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");
				
				configuration.setProperties(hibernateProperties);
				
				configuration.addAnnotatedClass(Student.class);
				configuration.addAnnotatedClass(ClassRoom.class);
				
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return sessionFactory;
	}
	
	
}
