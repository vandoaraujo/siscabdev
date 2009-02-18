package controle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Municipio;
import modelo.NaturezaChamados;
import modelo.OBM;
import modelo.TiposOcorrencia;
import modelo.Usuario;
import dao.ChamadoDao;
import dao.MunicipioDao;
import dao.OBMDao;
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
		
		  String data = "dd/MM/yyyy";  
		  String hora = "HH:mm";  
		  String data1, hora1;  
		     
		  Date agora = new java.util.Date();;  
		  SimpleDateFormat formata = new SimpleDateFormat(data);  
		  data1 = formata.format(agora);  
		  formata = new SimpleDateFormat(hora);  
		  hora1 = formata.format(agora);  
		     
		  System.out.print(data1);	
		  System.out.print(hora1);  
		  
		  //Fazer demais lógicas deste caso de USO ---
		  //Verifica próximo ID
		  int id= ChamadoDao.getInstance().listaUltimoId();	
		  id++;
			  
		  Usuario u =UsuarioDao.getUsuarioLogado();
		  System.out.println("######################" + UsuarioDao.getUsuarioLogado().getNomeGuerra()+ "##############");
		  System.out.println(UsuarioDao.getUsuarioLogado().getObm().getNome());
		  
		  List<Municipio> municipios = (List<Municipio>)MunicipioDao.getInstance().listarTodosMunicipios();
		
		  
		  //Cria objeto NaturezaChamados
		  NaturezaChamados n = new NaturezaChamados();
		  ArrayList<String> nChamados =  n.getAr();
		  
		  TiposOcorrencia tipo = new TiposOcorrencia();
		  ArrayList<String> nTiposOcorrencia = tipo.getAr();
		  
		  ArrayList<OBM> obms = (ArrayList<OBM>) OBMDao.getInstance().listarTodasOBMs();
		  		  
		  //Parametros utilizados na próxima página
		  request.setAttribute("obms", obms);
		  request.setAttribute("municipios", municipios);
		  request.setAttribute("tipoOcorrencia", nTiposOcorrencia);
		  request.setAttribute("NaturezaChamados", nChamados);
		  request.setAttribute("usuario", u);
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
