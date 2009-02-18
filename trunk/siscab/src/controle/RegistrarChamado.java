package controle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import dao.ChamadoDao;
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


		/*Locale locale = new Locale("pt","BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'",locale);
		System.out.println(formatador.format(calendar.getTime())); */
		
		  String data = "dd/MM/yyyy";  
		  String hora = "h:mm - a";  
		  String data1, hora1;  
		     
		  Date agora = new java.util.Date();;  
		  SimpleDateFormat formata = new SimpleDateFormat(data);  
		  data1 = formata.format(agora);  
		  formata = new SimpleDateFormat(hora);  
		  hora1 = formata.format(agora);  
		     
		  System.out.print(data1);	
		  System.out.println("##########################");
		  System.out.print(hora1);  
		  
		  //Fazer demais lógicas deste caso de USO ---
		  
		  
		  //Verifica próximo ID
		  int id= ChamadoDao.getInstance().listaUltimoId();
	
		  
		  id++;
		  System.out.println("#####################" + id + "############################");
		  
		  UsuarioDao.getUsuarioLogado();
		  System.out.println("######################" + UsuarioDao.getUsuarioLogado().getNomeGuerra()+ "##############");
		  System.out.println(UsuarioDao.getUsuarioLogado().getObm().getNome());
		  
		  
		  
		  //Verifica Nome do Usuario e OBM Atual 
		  
		 
		  request.setAttribute("idChamado", id);
		  request.setAttribute("data", data1);
		  request.setAttribute("hora", hora1);
		 
			
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
