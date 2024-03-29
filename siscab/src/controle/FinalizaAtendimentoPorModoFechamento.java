package controle;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Atendimento;
import modelo.CronoAtendimento;
import modelo.ModoFechamento;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.CronoAtendimentoDao;
import dao.ModoFechamentoDao;
import dao.MovimentaViaturaDao;

/**
 * Servlet implementation class FinalizaAtendimentoPorModoFechamento
 */
public class FinalizaAtendimentoPorModoFechamento extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger
	    .getLogger(FinalizaAtendimentoPorModoFechamento.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizaAtendimentoPorModoFechamento() {
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
	String modoFechamento = request.getParameter("modoFechamento");
	int numeroAtendimento = Integer.parseInt(request
		.getParameter("numeroAtendimento"));
	int idAtendimento = Integer.parseInt(request
		.getParameter("idAtendimento"));

	List<Viatura> viatura = MovimentaViaturaDao.getInstance()
		.ListarViaturaNaoRepetidasEmAtendimento(idAtendimento);

	if (viatura.isEmpty()) {

	    logger.info("Numero de viaturas Classe Finaliza Atendimento: "
		    + viatura.size());

	    efetivaFinalizacaoAtendimento(request, response, modoFechamento,
		    idAtendimento);

	} else {

	    logger.info("TEM VIATURA - CLASSE FINALIZA ATENDIMENTO");
	    informarUsuarioSobreAlocacaoViaturasAtendimento(request, response,
		    modoFechamento, numeroAtendimento, idAtendimento, viatura);
	}
    }

    private void efetivaFinalizacaoAtendimento(HttpServletRequest request,
	    HttpServletResponse response, String modoFechamento,
	    int idAtendimento) {

	Atendimento atendimento = AtendimentoDao.getInstance()
		.BuscaAtendimentoId(idAtendimento);
	ModoFechamento m = ModoFechamentoDao.getInstance()
		.listarModoFechamentoNome(modoFechamento);
	atendimento.setModofechamento_id(m.getId());
	atendimento.setStatus_atendimento("Finalizado");
	logger.info(" ############### Setou o modoFechamento");

	AtendimentoDao.getInstance().atualizar(atendimento);

	logger.info(" ############# Atendimento atualizado  e finalizado com sucesso");

	finalizarCronologiaAtendimento(atendimento);

	logger.info(" ############# Seta Cronologia do Atendimento - Finalizacao");

	RequestDispatcher view;
	request.setAttribute("mensagem", "Finalizado com sucesso");
	request.setAttribute("numeroAtendimento", atendimento
		.getAtendimento_numero());
	view = request.getRequestDispatcher("/finalizaChamadoMensagem.jsp");

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

    private void finalizarCronologiaAtendimento(Atendimento atendimento) {

	// Iniciar cronologia do atendimento
	CronoAtendimento crono = new CronoAtendimento();
	crono.setAtendimento_id(atendimento);
	crono.setCronoatendimento_tipoevento("finalização");
	crono.setCronoatendimento_horaevento(new Date());
	CronoAtendimentoDao.getInstance().salvar(crono);

    }

    private void informarUsuarioSobreAlocacaoViaturasAtendimento(
	    HttpServletRequest request, HttpServletResponse response,
	    String modoFechamento, int numeroAtendimento, int idAtendimento,
	    List<Viatura> viatura) {

	RequestDispatcher view;
	HttpSession sessao = request.getSession();
	sessao.setAttribute("modos", modoFechamento);
	sessao.setAttribute("numeroAtendimento", numeroAtendimento);
	sessao.setAttribute("idAtendimento", idAtendimento);
	sessao.setAttribute("modoFechamento", modoFechamento);
	sessao.setAttribute("viaturas", viatura);
	view = request
		.getRequestDispatcher("/avisoLiberacaoViaturasFechaAtendimento.jsp");

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
