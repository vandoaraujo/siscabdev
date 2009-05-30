package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.MovimentaViatura;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.MovimentaViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class PreparaNovaMovimentacaoViatura
 */
public class PreparaNovaMovimentacaoViatura extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int viaturaAtual, atendimentoAtual;
    private List<String> opcaoEvento = null;
    static Logger logger = Logger
	    .getLogger(PreparaNovaMovimentacaoViatura.class);
    private Atendimento at = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreparaNovaMovimentacaoViatura() {
	super();
    }

    private void despacha(HttpServletRequest request,
	    HttpServletResponse response) {
	
	at = (Atendimento) request.getSession().getAttribute("atendimentoAtual");
	
	Viatura v = ViaturaDao.getInstance().BuscaViaturaId(viaturaAtual);

	RequestDispatcher view;
	request.setAttribute("viaturaAtual", viaturaAtual);
	request.setAttribute("numeroViatura", v.getNumero());	
	request.setAttribute("tipoViatura", v.getTipo_viatura().getTipoviatura_abreviacao());	
	request.setAttribute("atendimentoAtual", atendimentoAtual);
	request.setAttribute("atendimento", at.getAtendimento_numero());
	request.setAttribute("tipoEvento", opcaoEvento);

	view = request.getRequestDispatcher("/novaMovimentacao.jsp");

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
	opcaoEvento = new ArrayList<String>();
	viaturaAtual = Integer.parseInt(request.getParameter("viaturaAtual"));
	atendimentoAtual = Integer.parseInt(request
		.getParameter("atendimentoAtual"));
	filtraTipoEvento(request, response, atendimentoAtual, viaturaAtual);
	despacha(request, response);

    }

    private void filtraTipoEvento(HttpServletRequest request,
	    HttpServletResponse response, int atendimentoAtual, int viaturaAtual) {

	Integer i = MovimentaViaturaDao.getInstance().listaUltimoEvento(
		atendimentoAtual, viaturaAtual);

	MovimentaViatura m = MovimentaViaturaDao.getInstance()
		.BuscaMovimentacaoViaturaId(i);

	if (m.getMovimentaviatura_tipoevento().equals("Saída da OBM")) {

	    opcaoEvento.add("Chegada à Cena");
	    opcaoEvento.add("Saída da Cena");
	    opcaoEvento.add("Chegada ao Hospital");
	    opcaoEvento.add("Saída do Hospital");
	    opcaoEvento.add("Retorno à OBM");
	} else if (m.getMovimentaviatura_tipoevento().equals("Chegada à Cena")) {

	    opcaoEvento.add("Saída da Cena");
	    opcaoEvento.add("Chegada ao Hospital");
	    opcaoEvento.add("Saída do Hospital");
	    opcaoEvento.add("Retorno à OBM");
	} else if (m.getMovimentaviatura_tipoevento().equals("Saída da Cena")) {

	    opcaoEvento.add("Chegada ao Hospital");
	    opcaoEvento.add("Saída do Hospital");
	    opcaoEvento.add("Retorno à OBM");
	} else if (m.getMovimentaviatura_tipoevento().equals("Chegada ao Hospital")) {

	    opcaoEvento.add("Saída do Hospital");
	    opcaoEvento.add("Retorno à OBM");
	} else if (m.getMovimentaviatura_tipoevento().equals("Saída do Hospital")) {

	    opcaoEvento.add("Retorno à OBM");
	}

    }

}
