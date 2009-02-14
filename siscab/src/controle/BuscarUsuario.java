package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import dao.UsuarioDao;

/**
 * Servlet implementation class BuscarUsuario
 */
public class BuscarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Usuario> usu;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int registro=0;
		String registro1 = request.getParameter("registro");
		String nomeGuerra = request.getParameter("nomeGuerra");
				
		registro = Integer.parseInt(registro1);
		if(registro1.equals("")){
			registro=0;
		}
			
		 usu= UsuarioDao.getInstance().procurarUsuariosParametro2(registro, nomeGuerra);
		UsuarioDao.getInstance().fechaSession();
		
		System.out.println("RESULTADO DA QUERY" + usu.size());
				
		request.setAttribute("usuarios",usu);
				
		RequestDispatcher view = request.getRequestDispatcher("listaUsuarios.jsp");
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
   public List<Usuario> getUsuarios() {  
	     return this.usu;  
} 

}
