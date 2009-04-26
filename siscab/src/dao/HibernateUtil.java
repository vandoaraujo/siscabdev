package dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static SessionFactory factory;

    private static Session session;

    private static HibernateUtil singleton = null;

    private static Logger logger = Logger.getLogger(HibernateUtil.class);

    public static void closeFactory() {

	closeFactory(factory);

    }

    public static void closeFactory(SessionFactory factory) {
	if (factory != null) {
	    factory.close();
	    logger.info("factory Session closed - Hibernate Util Class!!");
	}
    }

    public static HibernateUtil getInstance() {
	if (singleton == null) {
	    singleton = new HibernateUtil();
	}
	return singleton;
    }

    private HibernateUtil() {

	logger.info(this.getClass());
	AnnotationConfiguration conf = new AnnotationConfiguration();
	conf.configure("hibernate.cfg.xml");
	factory = conf.buildSessionFactory();

    }

    public Session AbreUmaSession() {

	if (session == null || !session.isOpen() || !session.isConnected()) {
	    session = (factory).openSession();
	}
	return session;
    }

}
