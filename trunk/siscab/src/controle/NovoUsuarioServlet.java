package controle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.OBM;
import modelo.Usuario;
import dao.OBMDao;
import dao.UsuarioDao;

/**
 * Servlet implementation class NovoUsuarioServlet
 */
public class NovoUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovoUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		salva(request,response,response.getWriter());		
				
	}
	
	protected void salva (HttpServletRequest request, HttpServletResponse response, PrintWriter out) 
	{
			int numRegistro = Integer.parseInt(request.getParameter("registro"));
			String nomeGuerra = request.getParameter("nomeGuerra");
			String obm = request.getParameter("obm");
			String perfil = request.getParameter("perfil");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String status = "ativo";
			
			OBM cobm = OBMDao.getInstance().listarOBMNome(obm);
			
			OBMDao.getInstance().fechaSession();
					
			Usuario usu = new Usuario();
			usu.setNomeGuerra(nomeGuerra);
			usu.setNumRegistro(numRegistro);
			usu.setObm(cobm);
			usu.setPerfil(perfil);
			usu.setEmail(email);
			usu.setStatus(status);
			usu.setSenha(senha);
			
			UsuarioDao.getInstance().salvar(usu);
					
			request.setAttribute("usuario", usu);
			RequestDispatcher view = request.getRequestDispatcher("paginas/administracao/usuarioSalvo.jsp");
			
		
	}

}
