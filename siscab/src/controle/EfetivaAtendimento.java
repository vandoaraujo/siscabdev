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

import modelo.Atendimentos;
import modelo.Chamado;
import modelo.Municipio;
import modelo.OBM;
import dao.AtendimentosDao;
import dao.ChamadoDao;
import dao.MunicipioDao;
import dao.OBMDao;

/**
 * Servlet implementation class EfetivaAtendimento
 */
public class EfetivaAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OBM obmAReceberSolicitacao = null;
	int proxIdAtendimento = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EfetivaAtendimento() {
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
		
		int idAtendimento = Integer.parseInt(request.getParameter("idAtendimento"));
		String obmAtendimento = request.getParameter("obmAtendimento");
		String bairro = request.getParameter("bairro");
		bairro.toLowerCase();
		String municipio = request.getParameter("municipio");
		String radioButton = request.getParameter("radiobutton");
		String obmRepassaAtendimento = request.getParameter("obmRepassaAtendimento");
		float coordX = Float.parseFloat(request.getParameter("coordX"));
		float coordY = Float.parseFloat(request.getParameter("coordY"));
		String logradouro = request.getParameter("logradouro");
		int numComplemento = Integer.parseInt(request.getParameter("numComplemento"));
		String tipoOcorrencia = request.getParameter("tipoOcorrencia");
		String status = request.getParameter("status");
		
		//Instancia objeto a ser salvo no BD 
		Atendimentos atendimento = new Atendimentos();
				
		GregorianCalendar calendar =  new GregorianCalendar();
		calendar.add(GregorianCalendar.YEAR, 0);
		DateFormat formata = new SimpleDateFormat("yyyy");
		String idAno = formata.format(calendar.getTime());
					
		List <Atendimentos> atendimentosBD = AtendimentosDao.getInstance().listarTodosAtendimentos();
		
		for(int i =0;i<atendimentosBD.size();i++){
			proxIdAtendimento = atendimentosBD.get(i).getId();
			
		}
		proxIdAtendimento++;
		
		String concatena = idAno + Integer.toString(proxIdAtendimento);
		
		int oid = Integer.parseInt(concatena);
		
		System.out.println("########### oid do atendimento" +  oid + "########################");
			
		
		//Verifica se o atendimento ser� analisado por outra OBM
		if(radioButton.equals("sim")){
			
			obmAReceberSolicitacao = OBMDao.getInstance().listarOBMNome(obmRepassaAtendimento);
			atendimento.setObm_id(obmAReceberSolicitacao);
			
		}
		else{
			obmAReceberSolicitacao = OBMDao.getInstance().listarOBMNome(obmAtendimento);
			atendimento.setObm_id(obmAReceberSolicitacao);
		}	
		
		//Busca ultimo chamado na transacao corrente 
		Chamado chamado = ChamadoDao.getInstance().BuscaChamadoId(idAtendimento);
		
		atendimento.setAtendimento_numero(oid);
		atendimento.setChamado_id(chamado);
		atendimento.setBairro(bairro);
		//Busca Municipio para instanciar o atributo do objeto
		Municipio m = MunicipioDao.getInstance().listarMunicipioNome(municipio);
		atendimento.setMunicipio_id(m);
		atendimento.setCoordx(coordX);
		atendimento.setCoordy(coordY);
		atendimento.setLogradouro(logradouro);
		atendimento.setNumcompl(numComplemento);
		atendimento.setTipoocorrencia(tipoOcorrencia);
		atendimento.setStatus_atendimento(status);
		
		AtendimentosDao.getInstance().salvar(atendimento);
		System.out.println("########### SALVO COM SUCESSO ##############");
		RequestDispatcher view = request.getRequestDispatcher("/atendimentoSalvo.jsp");
		
		request.setAttribute("numeroAtendimento",oid);
		view.forward(request, response);
		
		}
		
}