package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	
	private static SessionFactory factory;
		
	private static Session session;
	
	private static HibernateUtil singleton = null;
	
	public static HibernateUtil getInstance(){
		if(singleton==null){
			singleton= new HibernateUtil();
		}
			return singleton;
	}
	
	private HibernateUtil(){
		
		AnnotationConfiguration conf = new AnnotationConfiguration();
		conf.configure("hibernate.cfg.xml");
		factory = conf.buildSessionFactory();	
		
	}
	
	public Session AbreUmaSession(){
		
		if (session == null || !session.isOpen() || !session.isConnected()) {
			session = ((SessionFactory) factory).openSession();
		}
			return session;
	}
	
	
	public static void closeFactory(SessionFactory factory) {
	if (factory != null) {
		factory.close();
		System.out.println("factory Session closed - Hibernate Util Class!!");
	}
	}
	
	
	
	public static void closeFactory() {
	   
		closeFactory(factory);
		
	}
	
	

}
