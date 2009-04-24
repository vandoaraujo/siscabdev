package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.OBM;
import modelo.TipoViatura;
import modelo.Viatura;
import dao.OBMDao;
import dao.TipoViaturaDao;
import dao.UsuarioDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class BuscarViatura
 */
public class BuscarViatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher view = null;
	List<Viatura> viaturas = null;
	
	static Logger logger = Logger.getLogger(BuscarViatura.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarViatura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	logger.info(getServletName());
		String statusViatura = request.getParameter("status");
		String tipoViatura = request.getParameter("tipoViatura");
		
		procurarViatura(statusViatura,tipoViatura,request,response);
	
		
	}
	
	private void procurarViatura(String status, String tipoViatura,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//Verifica consulta conforme parametros
        if(status.equals("") && tipoViatura.equals("")){
               
        		logger.info("Caiu aqui");
				request.setAttribute("parametrosVazios","Necessário preencher um dos valores para a busca!");
				view = request.getRequestDispatcher("/procurarViaturas.jsp");
				try {
                        view.forward(request, response);
                } catch (ServletException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        else if (!(tipoViatura.equals("")) && !(status.equals("")))
        {   
            
            TipoViatura tv = TipoViaturaDao.getInstance().listarTipoViatura(tipoViatura);
            viaturas = ViaturaDao.getInstance().listaViaturasPorTipoStatus(tv.getId(),status);
            
        }
        
        else if (status.equals("") && !(tipoViatura.equals(""))){
               
        	  TipoViatura tv = TipoViaturaDao.getInstance().listarTipoViatura(tipoViatura);
              viaturas = ViaturaDao.getInstance().listaViaturasPorTipo(tv.getId());
        }
        else if ((tipoViatura.equals("")) && !(status.equals("")))
        {
              viaturas = ViaturaDao.getInstance().listaViaturasPorStatus(status);
        }
                       
        if (!response.isCommitted()){  
        	 
        	request.setAttribute("viaturas",viaturas);
             
             view = request.getRequestDispatcher("/listaViaturasDescritivo.jsp");
             
             try {
                     view.forward(request, response);
             } catch (ServletException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
             }
        	
        }  
	

}
	
}
