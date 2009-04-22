package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;

/**
 * Servlet implementation class TotalAtendimentosPorModoFechamento
 */
public class TotalAtendimentosPorModoFechamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Long atendida = null;
	private Long naoAtendida= null;
	private Long canceladaSolicitante= null;
	private Long trote= null;
	private Long semAtuação= null;
	static Logger logger = Logger.getLogger(TotalAtendimentosPorModoFechamento.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalAtendimentosPorModoFechamento() {
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
		Date formatadaInicial=null;
		Date formatadaFinal=null;
				
		List<String> modosFechamento = null;
		List<Long> numeroAtendimentos = null;
		
		String dataInicial = request.getParameter("dataInicial");
		String dataFinal = request.getParameter("dataFinal");

		//Quebra a data ajustando para + 1
		StringTokenizer st = new StringTokenizer(dataFinal,"/");
		List<String> token = new ArrayList();
		while (st.hasMoreTokens()) {
          token.add(st.nextToken()); 
		}
		int dia = Integer.parseInt(token.get(0));
		dia++;
		logger.info("data ajustada" + dia);
		
		String dataFormatada = Integer.toString(dia) + "/" + token.get(1) + "/" + token.get(2);

		logger.info("Data final após ajustes" + dataFormatada);
		
		DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
		try {
			formatadaInicial = new java.sql.Date(formataData.parse(dataInicial).getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		DateFormat formataDataFinal = new SimpleDateFormat("dd/MM/yyyy");
		try {
			formatadaFinal = new java.sql.Date(formataDataFinal.parse(dataFormatada).getTime());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		logger.info(" ############# Data inicial passada para o Banco" + formatadaInicial);	
		logger.info(" ############# Data FORMATADA FINAL apos conversoes" + formatadaFinal);	

		
		
		Iterator it = AtendimentoDao.getInstance().ParametrosResultadosRelatorio(formatadaInicial, formatadaFinal);
				modosFechamento = new ArrayList<String>();
		numeroAtendimentos = new ArrayList<Long>();
		
		while (it.hasNext()){
			
			Object [] linhas = (Object[]) it.next();
			modosFechamento.add((String) linhas[0]);
			numeroAtendimentos.add((Long) linhas [1]);
			
			if(linhas[0].equals("Atendida")){
				atendida=((Long) linhas [1]);
			}
			else if(linhas[0].equals("Não atendida")){
				naoAtendida = ((Long) linhas [1]);
			}
			else if(linhas[0].equals("Cancelada pelo solicitante")){
				canceladaSolicitante = ((Long) linhas [1]); 
			}
			else if(linhas[0].equals("Falso aviso (trote)")){
				trote = ((Long) linhas [1]);
			}
			else if(linhas[0].equals("Sem atuação")){
				semAtuação = ((Long) linhas [1]);
			}
		}
		
		logger.info("MODO FECHAMENTO" + modosFechamento.get(0).toString());
		logger.info("NUMERO" + numeroAtendimentos.get(0).toString());
		
		logger.info("MODO FECHAMENTO" + modosFechamento.get(1).toString());
		logger.info("NUMERO" + numeroAtendimentos.get(1).toString());
		
		RequestDispatcher view;
		request.setAttribute("dataInicial", dataInicial);
		request.setAttribute("dataFinal", dataFinal);
		request.setAttribute("QtdAtendida", atendida);
		request.setAttribute("QtdNaoAtendida", naoAtendida);
		request.setAttribute("QtdCanceladaSolicitante", canceladaSolicitante);
		request.setAttribute("QtdTrote", trote);
		request.setAttribute("QtdSemAtuacao", semAtuação);
		request.setAttribute("dataGeracao", new Date());
		request.setAttribute("modosFechamento", modosFechamento);
		request.setAttribute("qtdAtendimentos", numeroAtendimentos);
		view = request.getRequestDispatcher("/relatorioTotalAtendimentosPorModoFechamento.jsp");
		
			
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
