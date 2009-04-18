package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.Chamado;
import modelo.Municipio;
import modelo.Usuario;
import dao.ChamadoDao;
import dao.MunicipioDao;
import dao.UsuarioDao;

/**
 * Servlet implementation class RegistrarChamado
 */
public class RegistrarChamado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher view;
	static Logger logger = Logger.getLogger(RegistrarChamado.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarChamado() {
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
		Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
		
		if((!(usuario.getPerfil().getId() == 2)) && (!(usuario.getPerfil().getId() == 3))){
			
			request.setAttribute("descricaoServico", "Registrar Chamado");
			request.setAttribute("perfil", "Atendente do COCB ou Operador da OBM");
			view = request.getRequestDispatcher("/acessoNegado.jsp");
			
		}
		
		else{
			
			
			String horaData = informaHoraChamado();
		
			Integer id = listaProximoId();
										  
			Usuario u =UsuarioDao.getUsuarioLogado();
			  		  
			List<Municipio> municipios = (List<Municipio>)MunicipioDao.getInstance().listarTodosMunicipios();
					  		  
			  //Artificio para guardar a hora inicial
			Chamado.setDataHoraInicioChamado();		  
			  
			request.setAttribute("municipios", municipios);
			request.setAttribute("usuario", u);
			request.setAttribute("idChamado", id);
			request.setAttribute("gravaData", horaData);
				 			
			view = request.getRequestDispatcher("/RegistrarChamado1.jsp");
		
			
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
		
		Integer id= ChamadoDao.getInstance().listaUltimoId();
		if(!(id == null)){
			id++;
		}
		else{
			id=1;
		}
		
		return id;
		
	}

	private String informaHoraChamado() {

		GregorianCalendar calendar =  new GregorianCalendar();
		calendar.add(GregorianCalendar.MONTH, 0);
		calendar.add(GregorianCalendar.HOUR_OF_DAY, 0);
		calendar.add(GregorianCalendar.MINUTE, 0);
		DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String grava = formata.format(calendar.getTime());
		String horaData = grava;
		return horaData;
	}
			
}
