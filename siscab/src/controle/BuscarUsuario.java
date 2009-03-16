package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.OBM;
import modelo.Usuario;
import dao.OBMDao;
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
                String nomeObm = request.getParameter("obm");
                String tipoPerfil = request.getParameter("perfil");
               
               
                //Verifica consulta conforme parametros
                if(registro1.equals("") && tipoPerfil.equals("") && nomeObm.equals("")){
                       
                        usu= UsuarioDao.getInstance().procurarUsuariosParametro1(nomeGuerra);

                }
                if (nomeGuerra.equals("") && tipoPerfil.equals("") && registro1.equals("")){
                       
                        OBM obm = OBMDao.getInstance().listarOBMNome(nomeObm);
                        usu= (List<Usuario>) obm.getUsuarios();
                       
                }
               
                if (nomeGuerra.equals("") && nomeObm.equals("") && registro1.equals("")){
                       
                        usu= UsuarioDao.getInstance().procurarUsuariosParametro4(tipoPerfil);

                }
                if (nomeGuerra.equals("") && nomeObm.equals("") && tipoPerfil.equals("")){
                {       
                    int numero = Integer.parseInt(registro1);
                	usu= UsuarioDao.getInstance().procurarUsuariosParametro2(numero);
                }
                               
                System.out.println("RESULTADO DA QUERY" + usu.size());
                               
                request.setAttribute("usuarios",usu);
                               
                RequestDispatcher view = request.getRequestDispatcher("/listaUsuarios.jsp");
                
                try {
                        view.forward(request, response);
                } catch (ServletException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
               
                       
        }
       
 
}

}

