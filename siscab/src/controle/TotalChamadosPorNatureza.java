package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.ChamadoDao;

/**
 * Servlet implementation class TotalChamadosPorNatureza
 */
public class TotalChamadosPorNatureza extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestDispatcher view;
    static Logger logger = Logger.getLogger(TotalChamadosPorNatureza.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalChamadosPorNatureza() {
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
	Date formatadaInicial = null;
	Date formatadaFinal = null;

	List<String> naturezaChamado = null;
	List<Long> numeroChamados = null;
	List<String> percentualChamado = null;

	String dataInicial = request.getParameter("dataInicial");
	String dataFinal = request.getParameter("dataFinal");

	if (dataInicial.equals("") || dataFinal.equals("")) {

	    request.setAttribute("msg",
		    "Necessário informar data inicial e data final!");
	    view = request
		    .getRequestDispatcher("/informaParametrosTotalChamados.jsp");

	} else {

	    DateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
	    try {
		formatadaInicial = new java.sql.Date(formataData.parse(
			dataInicial).getTime());
	    } catch (ParseException e1) {
		e1.printStackTrace();
	    }

	    // Pega a data inicial para comparaçao de datas
	    StringTokenizer stInicial = new StringTokenizer(dataInicial, "/");
	    List<String> tokenInicial = new ArrayList<String>();
	    while (stInicial.hasMoreTokens()) {
		tokenInicial.add(stInicial.nextToken());
	    }

	    int comparaDataInicial = Integer.parseInt(tokenInicial.get(0))
		    + Integer.parseInt(tokenInicial.get(1))
		    + Integer.parseInt(tokenInicial.get(2));

	    logger.info("Valor da data Inicial" + comparaDataInicial);

	    // Quebra a data final ajustando para + 1
	    StringTokenizer st = new StringTokenizer(dataFinal, "/");
	    List<String> tokenFinal = new ArrayList<String>();
	    while (st.hasMoreTokens()) {
		tokenFinal.add(st.nextToken());
	    }

	    int comparaDataFinal = Integer.parseInt(tokenFinal.get(0))
		    + Integer.parseInt(tokenFinal.get(1))
		    + Integer.parseInt(tokenFinal.get(2));

	    logger.info("Valor da data Final" + comparaDataFinal);

	    if (comparaDataInicial > comparaDataFinal) {

		request.setAttribute("msg",
			"A data inicial é maior do que a data final!");
		view = request
			.getRequestDispatcher("/informaParametrosTotalChamados.jsp");
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

	    int dia = Integer.parseInt(tokenFinal.get(0));
	    dia++;
	    logger.info("data ajustada" + dia);

	    String dataFormatada = Integer.toString(dia) + "/"
		    + tokenFinal.get(1) + "/" + tokenFinal.get(2);

	    logger.info("Data final após ajustes" + dataFormatada);

	    DateFormat formataDataFinal = new SimpleDateFormat("dd/MM/yyyy");
	    try {
		formatadaFinal = new java.sql.Date(formataDataFinal.parse(
			dataFormatada).getTime());
	    } catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }

	    logger.info(" ############# Data inicial passada para o Banco"
		    + formatadaInicial);
	    logger.info(" ############# Data FORMATADA FINAL apos conversoes"
		    + formatadaFinal);

	    Iterator it = ChamadoDao.getInstance()
		    .ParametrosResultadosRelatorioChamadosPorNatureza(
			    formatadaInicial, formatadaFinal);

	    if (it.hasNext() == false) {

		request.setAttribute("msg",
			"Nenhum atendimento no período informado!");
		view = request
			.getRequestDispatcher("/informaParametrosTotalChamados.jsp");

	    } else {

		naturezaChamado = new ArrayList<String>();
		numeroChamados = new ArrayList<Long>();
		int count = 0;
		long somaChamados = 0;

		while (it.hasNext()) {

		    Object[] linhas = (Object[]) it.next();
		    naturezaChamado.add((String) linhas[0]);
		    numeroChamados.add((Long) linhas[1]);
		    numeroChamados.get(count);
		    somaChamados = somaChamados
			    + (long) numeroChamados.get(count);
		    count++;
		}

		logger.info("QTD " + somaChamados);
		percentualChamado = new ArrayList<String>();
		DecimalFormat formatador = new DecimalFormat("##,##.##");
		for (int i = 0; i < numeroChamados.size(); i++) {
		    double percentual = ((numeroChamados.get(i) * 100) / (double) somaChamados);
		    logger.info("Percentual " + i + " " + percentual);
		    percentualChamado.add(formatador.format(percentual));
		}

		GregorianCalendar calendar = new GregorianCalendar();
		request.setAttribute("percentualChamados", percentualChamado);
		request.setAttribute("dataInicial", dataInicial);
		request.setAttribute("dataFinal", dataFinal);
		request.setAttribute("dataGeracao", calendar.getTime()
			.getDate()
			+ "/"
			+ (calendar.getTime().getMonth() + 1)
			+ "/"
			+ (calendar.getTime().getYear() + 1900)
			+ " às "
			+ calendar.getTime().getHours()
			+ "h:"
			+ calendar.getTime().getMinutes()
			+ "min:"
			+ calendar.getTime().getSeconds() + "seg");
		request.setAttribute("chamadosPorNatureza", naturezaChamado);
		request.setAttribute("qtdChamados", numeroChamados);
		request.setAttribute("somaChamados", somaChamados);
		view = request
			.getRequestDispatcher("/relatorioTotalChamadosPorNatureza.jsp");

	    }

	}
	if (!response.isCommitted()) {
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
}
