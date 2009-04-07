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
import modelo.Viatura;
import dao.ViaturaDao;

/**
 * Servlet implementation class DespacharViatura
 */
public class DespacharViatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DespacharViatura() {
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
		
		
		Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
		
		int obm_Id = usuario.getObm().getId();
		
		List<Viatura> v = (List<Viatura>) ViaturaDao.getInstance().listaViaturasOBMStatusPrecisao(obm_Id);	
	    //Pegar atendimento atual através do HttpSession
	    //atendimentoAtual
	    String obm = usuario.getObm().getNome();
		
		HttpSession sessao = request.getSession();
		sessao.setAttribute("obm", obm);
		sessao.setAttribute("viaturasEmProntidao", v);
		RequestDispatcher view = request.getRequestDispatcher("/listarViaturasProntidao.jsp");
		
		try {
		view.forward(request, response);
		} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
}

}
