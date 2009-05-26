package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Chamado;
import modelo.Municipio;
import modelo.Usuario;

import org.apache.log4j.Logger;

import dao.ChamadoDao;
import dao.MunicipioDao;
import dao.UsuarioDao;

/**
 * Servlet implementation class PopulaGeoRegistrarChamado
 */
public class PopulaGeoRegistrarChamado extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestDispatcher view;
    static Logger logger = Logger.getLogger(PopulaGeoRegistrarChamado.class);
    Integer id;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulaGeoRegistrarChamado() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	logger.info(getServletName());
	Usuario usuario = (Usuario) getServletContext().getAttribute(
		"usuarioCorrente");
	
	String acao = request.getParameter("acao");

	if ((!(usuario.getPerfil().getId() == 2))
		&& (!(usuario.getPerfil().getId() == 3))) {

	    request.setAttribute("descricaoServico", "Registrar Chamado");
	    request.setAttribute("perfil","Atendente do COCB ou Operador da OBM");
	    view = request.getRequestDispatcher("/acessoNegado.jsp");

	}

	else {
	    
		
	    id = listaProximoId();

	    Usuario u = UsuarioDao.getUsuarioLogado();

	    List<Municipio> municipios = MunicipioDao.getInstance()
		    .listarTodosMunicipios();

	    // Artificio para guardar a hora inicial
	    
	    request.setAttribute("municipios", municipios);
	    request.setAttribute("usuario", u);
	    request.setAttribute("gravaData", Chamado.getDataHoraInicioChamado());

    	    request.setAttribute("bairro",request.getParameter("bairro"));
    	    request.setAttribute("infoComplementares",request.getParameter("infoComplementares"));
    	    request.setAttribute("idChamado",request.getParameter("numeroChamado"));
    	    request.setAttribute("endereco",request.getParameter("endereco"));
    	    request.setAttribute("telefone",request.getParameter("telefone"));
    	    request.setAttribute("nomeSolicitante",request.getParameter("nomeSolicitante"));
    	    request.setAttribute("numeroGeradoChamado",request.getParameter("numeroGeradoChamado"));
    	    request.setAttribute("municipio",request.getParameter("municipio"));
    	    request.setAttribute("numero",request.getParameter("numero"));
    	    request.setAttribute("hiddenCoordX",request.getParameter("hiddenCoordX"));
    	    request.setAttribute("hiddenCoordY",request.getParameter("hiddenCoordY"));
  	    request.setAttribute("idChamado", id--);
    	    
    	    view = request.getRequestDispatcher("/registrarChamado2.jsp");
  


	}

	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }


    private Integer listaProximoId() {

	Integer id = ChamadoDao.getInstance().listaUltimoId();
	if (!(id == null)) {
	    id++;
	} else {
	    id = 1;
	}

	return id;

    }

}
