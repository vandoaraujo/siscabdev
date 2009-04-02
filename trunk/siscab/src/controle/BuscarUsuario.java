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
                String nomeObm = request.getParameter("obm");
         
               
                //Verifica consulta conforme parametros
                if(registro1.equals("") && nomeObm.equals("")){
                       
						request.setAttribute("parametrosVazios","Necess�rio preencher um dos valores para a busca!");
						RequestDispatcher view = request.getRequestDispatcher("/buscarUsuario.jsp");
						try {
		                        view.forward(request, response);
		                } catch (ServletException e) {
		                        // TODO Auto-generated catch block
		                        e.printStackTrace();
		                }
                }
                //Traz OBM
                else if (registro1.equals("") && (!nomeObm.equals(""))){
                       
                        OBM obm = OBMDao.getInstance().listarOBMNome(nomeObm);
                        usu= UsuarioDao.getInstance().buscarUsuariosOBM(obm.getId());
                        
                       
                }
                else if ((nomeObm.equals("")) && (!registro1.equals("")))
                {       
                    int numero = Integer.parseInt(registro1);
                	usu= UsuarioDao.getInstance().buscarUsuariosNumRegistro(numero);
                }
                else
                {
                   	usu= UsuarioDao.getInstance().listar();

                }
                               
                System.out.println("RESULTADO DA BUSCA QTD USU�RIOS " + usu.size());
                               
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
 

