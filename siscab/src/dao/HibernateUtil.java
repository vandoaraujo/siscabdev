package dao;

import modelo.OBM;
import modelo.Usuario;

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
		conf.addAnnotatedClass(OBM.class);
		conf.addAnnotatedClass(Usuario.class);
		factory = conf.buildSessionFactory();	
		
	}
	
	public static void closeSession() {
		session.close();
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
	}
	}
	
	
	
	public static void closeFactory() {
	   
		closeFactory(factory);
	}
	
	

}
