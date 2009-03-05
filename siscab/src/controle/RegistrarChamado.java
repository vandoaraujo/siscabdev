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
import dao.ChamadoDao;
import dao.MunicipioDao;
import dao.UsuarioDao;

/**
 * Servlet implementation class RegistrarChamado
 */
public class RegistrarChamado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
				
			GregorianCalendar calendar =  new GregorianCalendar();
			calendar.add(GregorianCalendar.MONTH, 0);
			calendar.add(GregorianCalendar.HOUR_OF_DAY, 0);
			calendar.add(GregorianCalendar.MINUTE, 0);
			DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String grava = formata.format(calendar.getTime());
			String horaData = grava;
			
			System.out.println("HORA ATUAL NO SERVLET REGISTRAR CHAMADO" + grava);
			
			System.out.println("HORA ATUAL" + grava.toString());

		
		  //Fazer demais l�gicas deste caso de USO ---
		  //Verifica pr�ximo ID
		  Integer id= ChamadoDao.getInstance().listaUltimoId();	
		  id++;
			  
		  Usuario u =UsuarioDao.getUsuarioLogado();
		  System.out.println("######################" + UsuarioDao.getUsuarioLogado().getNomeGuerra()+ "##############");
		  System.out.println(UsuarioDao.getUsuarioLogado().getObm().getNome());
		  
		  List<Municipio> municipios = (List<Municipio>)MunicipioDao.getInstance().listarTodosMunicipios();
				  		  
		  //Parametros utilizados na pr�xima p�gina
		  
		  //Artificio para guardar a hora inicial
		  Chamado.pegaDataInicialChamado();		  
		  
		  request.setAttribute("municipios", municipios);
		  request.setAttribute("usuario", u);
		  request.setAttribute("idChamado", id);
		  request.setAttribute("gravaData", horaData);
			 			
		  RequestDispatcher view = request.getRequestDispatcher("/RegistrarChamado1.jsp");
				
			
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
