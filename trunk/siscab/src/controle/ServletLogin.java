package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import dao.HibernateUtil;
import dao.UsuarioDao;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Usuario usu = null;
	HibernateUtil h = null;
	     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
    }
    
	   
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        h = HibernateUtil.getInstance();
			    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//Faz chamada ao Banco de Dados instanciando uma SessionFactory
		
		String registro = request.getParameter("numRegistro");
		String senha = request.getParameter("senha");
		
		boolean b = validaCampos(registro,senha,response,request);
		if(b == true){
			
			try{		
				int numRegistro = Integer.parseInt(registro);
				usu = UsuarioDao.getInstance().buscarUsuario(numRegistro, senha);
			}
			catch(Exception n){
				validaCampos(registro,senha,response,request);
			}
					

			getServletContext().setAttribute("usuarioCorrente", usu);
			validaUsuario(request,usu,response);
			
		}
		else {
			
			request.setAttribute("mensagem", "É necessário preencher usuário e senha");
			RequestDispatcher view = request.getRequestDispatcher("/dadosIncompletos.jsp");
			view.forward(request, response);
		}
		
	
	}
	
	private boolean validaCampos(String registro, String senha, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		if(registro.equals("") || senha.equals("")){
			
			return false;
		}
		
		return true;
				
					
	}

	public void validaUsuario(HttpServletRequest req, Usuario usu, HttpServletResponse response) throws ServletException, IOException{
		
		UsuarioDao.getInstance().fechaSession();		
		
		
		if(usu == null)
		{	
			req.setAttribute("mensagem", "Login e/ou senha inválido!");
			RequestDispatcher view = req.getRequestDispatcher("/home.jsp");
			view.forward(req, response);
			
		}
		
		else if(!usu.getStatus().equals("ATIVO")){
			  
			  HttpSession session = req.getSession();	
			  session.setAttribute("usuario", usu);
			  RequestDispatcher view = req.getRequestDispatcher("/statusUsuarioInativo.jsp");
			  view.forward(req, response);
		  
		  }
			
		else {
			
				HttpSession session = req.getSession();
				req.setAttribute("session", session);
				session.setAttribute("usuario", usu);
				RequestDispatcher view = req.getRequestDispatcher("/paginaPrincipal.jsp");
				view.forward(req, response);
		}
							
		}


			
	}
