package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
		HibernateUtil h = HibernateUtil.getInstance();
		    
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
		
		int numRegistro = Integer.parseInt(request.getParameter("numRegistro"));
		String senha = request.getParameter("senha");
		Usuario usu = UsuarioDao.getInstance().buscarUsuario(numRegistro, senha);
		validaUsuario(request,usu,response);
	}
	
	public void validaUsuario(HttpServletRequest req, Usuario usu, HttpServletResponse response) throws ServletException, IOException{
		
		UsuarioDao.getInstance().fechaSession();
		
		if(usu == null)
		{	
			RequestDispatcher view = req.getRequestDispatcher("/usuarioNaoEncontrado.jsp");
			view.forward(req, response);
			
		}
		
		else{
			
			if(usu.getPerfil().equals("ADMIN")){
				HttpSession session = req.getSession();
				req.setAttribute("session", session);
				session.setAttribute("usuario", usu);
				RequestDispatcher view = req.getRequestDispatcher("/paginaPrincipal.jsp");
				view.forward(req, response);
			}
			
			else if(!usu.getPerfil().equals("ADMIN")){
				
				HttpSession session = req.getSession();
				req.setAttribute("session", session);
				session.setAttribute("usuario", usu);
				req.setAttribute("mensagem", "Seu perfil não é de administrador!");
				RequestDispatcher view = req.getRequestDispatcher("/perfilNotAdmin.jsp");
				view.forward(req, response);

			}
			
			
			
		}
			
	}

}
