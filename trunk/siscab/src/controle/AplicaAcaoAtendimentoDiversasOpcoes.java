package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Atendimento;
import modelo.ModoFechamento;
import modelo.ServicoRealizado;
import modelo.Usuario;
import modelo.Viatura;
import modelo.VitimaAtendida;

import org.apache.log4j.Logger;

import dao.ModoFechamentoDao;
import dao.MovimentaViaturaDao;
import dao.ServicoRealizadoDao;
import dao.VitimaAtendidaDao;

/**
 * Servlet implementation class AplicaAcaoAtendimentoDiversasOpcoes
 */
public class AplicaAcaoAtendimentoDiversasOpcoes extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Atendimento at = null;

    static Logger logger = Logger
	    .getLogger(AplicaAcaoAtendimentoDiversasOpcoes.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AplicaAcaoAtendimentoDiversasOpcoes() {
	super();

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
	at = (Atendimento) request.getSession()
		.getAttribute("atendimentoAtual");
	int registroAtendimento = 0;
	int operacao = Integer.parseInt(request
		.getParameter("operacaoARealizar"));
	registroAtendimento = Integer.parseInt(request
		.getParameter("registroAtendimento"));

	// operacao que será realizada
	if (operacao == 2) {

	    editar(request, response, registroAtendimento);
	} else if (operacao == 3) {

	    sugestaoRota(request, response, registroAtendimento);
	} else if (operacao == 4) {

	    viaturasEmpenhadasAtendimento(request, response,
		    registroAtendimento);
	} else if (operacao == 5) {

	    vitimasAtendimento(request, response, registroAtendimento);
	} else if (operacao == 6) {

	    servicosExecutadosAtendimento(request, response,
		    registroAtendimento);
	} else if (operacao == 7) {

	    finalizarAtendimento(request, response, registroAtendimento);
	}

    }

    public void editar(HttpServletRequest request,
	    HttpServletResponse response, int registroAtendimento) {

	RequestDispatcher view;
	HttpSession sessao = request.getSession();
	sessao.setAttribute("atendimentoAtual", at);
	view = request.getRequestDispatcher("/editaAtendimento.jsp");

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

    private void finalizarAtendimento(HttpServletRequest request,
	    HttpServletResponse response, int registroAtendimento) {

	List<ModoFechamento> modo = ModoFechamentoDao.getInstance()
		.listarTodosModosFechamento();

	RequestDispatcher view;
	HttpSession sessao = request.getSession();
	sessao.setAttribute("modos", modo);
	sessao.setAttribute("registroAtendimento", at.getId());
	sessao.setAttribute("numeroAtendimento", at.getAtendimento_numero());
	view = request.getRequestDispatcher("/informaModoFechamento.jsp");

	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    private void servicosExecutadosAtendimento(HttpServletRequest request,
	    HttpServletResponse response, int registroAtendimento) {

	List<ServicoRealizado> servicos = ServicoRealizadoDao.getInstance()
		.listaServicosReferenteUmAtendimento(at.getId());

	RequestDispatcher view;
	HttpSession sessao = request.getSession();
	sessao.setAttribute("servicos", servicos);
	sessao.setAttribute("numeroAtendimento", at.getAtendimento_numero());
	sessao.setAttribute("registroAtendimento", at.getId());
	sessao.setAttribute("atendimentoAtual", at);
	view = request.getRequestDispatcher("/iniciarServico.jsp");

	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    private void sugestaoRota(HttpServletRequest request,
	    HttpServletResponse response, int registroAtendimento) {

	at = (Atendimento) request.getSession()
		.getAttribute("atendimentoAtual");
	Usuario usuario = (Usuario) getServletContext().getAttribute(
		"usuarioCorrente");
	request.setAttribute("CoordX_Operador", usuario.getObm().getCoordX());
	request.setAttribute("CoordY_Operador", usuario.getObm().getCoordX());
	request.setAttribute("CoordX_Acidente", at.getCoordx());
	request.setAttribute("CoordY_Acidente", at.getCoordy());

	request.setAttribute("NomeOrigem", usuario.getObm().getNome());
	request
		.setAttribute("EnderecoOrigem", usuario.getObm()
			.getLogradouro());
	request.setAttribute("NumeroOrigem", usuario.getObm().getNumCompl());
	request.setAttribute("BairroOrigem", usuario.getObm().getBairro());
	request.setAttribute("MunicipioOrigem", usuario.getObm().getMunicipio()
		.getMunicipio_nome());

	request.setAttribute("NumeroAtendimento", at.getAtendimento_numero());
	request.setAttribute("EnderecoDestino", at.getLogradouro());
	request.setAttribute("NumeroDestino", at.getNumcompl());
	request.setAttribute("BairroDestino", at.getBairro());
	request.setAttribute("MunicipioDestino", at.getMunicipio_id()
		.getMunicipio_nome());

	RequestDispatcher view;
	HttpSession sessao = request.getSession();
	sessao.setAttribute("atendimentoAtual", at);
	view = request.getRequestDispatcher("/iniciarSugestaoRota.jsp");

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

    private void viaturasEmpenhadasAtendimento(HttpServletRequest request,
	    HttpServletResponse response, int registroAtendimento) {

	List<Viatura> tiposEventosViatura = MovimentaViaturaDao.getInstance()
		.ListarViaturaNaoRepetidasEmAtendimento(at.getId());

	HttpSession sessao = request.getSession();
	sessao.setAttribute("viaturas", tiposEventosViatura);
	RequestDispatcher view;
	sessao.setAttribute("atendimentoAtual", at);
	view = request.getRequestDispatcher("/iniciarViaturasEmpenhadas.jsp");

	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {

	    e.printStackTrace();
	}

    }

    private void vitimasAtendimento(HttpServletRequest request,
	    HttpServletResponse response, int registroAtendimento) {

	List<VitimaAtendida> vitimas = VitimaAtendidaDao.getInstance()
		.listaVitimasReferenteUmAtendimento(at.getId());

	RequestDispatcher view;
	HttpSession sessao = request.getSession();
	sessao.setAttribute("vitimas", vitimas);
	sessao.setAttribute("atendimentoAtual", at);
	view = request.getRequestDispatcher("/iniciarTelaVitimas.jsp");

	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
