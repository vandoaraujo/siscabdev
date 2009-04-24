package controle;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.HibernateUtil;

/**
 * Servlet implementation class Logoff
 */
public class Logoff extends HttpServlet{
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(Logoff.class);
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logoff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(getServletName());
		logger.info("Aplication closed!");
		getServletContext().removeAttribute("usuarioCorrente"); 
		HibernateUtil.closeFactory();	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
