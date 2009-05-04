package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.Municipio;
import modelo.OBM;
import modelo.SiscabException;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.MunicipioDao;
import dao.OBMDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class CrudOBM
 */
public class CrudOBM extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String municipio;
    private String bairro;
    private String logradouro;
    private String numComplemento;
    private double coordX;
    private double coordY;
    private String status;
    private int statusObm;

    static Logger logger = Logger.getLogger(CrudOBM.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudOBM() {
	super();
	// TODO Auto-generated constructor stub
    }

    private void alterar(HttpServletRequest request,
	    HttpServletResponse response, int registro) {

	coordX = Double.parseDouble(request.getParameter("coordX"));
	coordY = Double.parseDouble(request.getParameter("coordY"));
	status = request.getParameter("statusObm");

	if (status.equals("ativa"))
	    statusObm = 1;
	else
	    statusObm = 0;
	
	Municipio municipioDao = MunicipioDao.getInstance()
		.listarMunicipioNome(municipio);

	OBM obm = OBMDao.getInstance().BuscaOBMId(registro);

	// Analisa se existem viaturas alocadas a esta OBM ou atendimentos não
	// finalizados
	List<Viatura> viaturas = ViaturaDao.getInstance().listaViaturasObm(
		registro);
	logger.info(viaturas.size());
	List<Atendimento> atendimento = AtendimentoDao.getInstance()
		.listarAtendimentosNaoFinalizadosOBM(registro);
	logger.info("HASH " + atendimento);
	if ((viaturas.size() != 0) || (atendimento.size() != 0)) {
	    despacha(request, response, "excecao", obm.getNome());
	} else {

	    obm.setNome(nome);
	    obm.setMunicipio(municipioDao);
	    obm.setBairro(bairro);
	    obm.setLogradouro(logradouro);
	    obm.setNumCompl(numComplemento);
	    obm.setCoordX(coordX);
	    obm.setCoordY(coordY);
	    obm.setStatus(statusObm);

	    OBMDao.getInstance().atualizar(obm);
	    request.setAttribute("obm", obm);
	    despacha(request, response, "alterar", obm.getNome());
	}

    }

    private void deletar(HttpServletRequest request,
	    HttpServletResponse response, int registro) {

	OBM obm = OBMDao.getInstance().BuscaOBMId(registro);
	String nome = obm.getNome();
	// Analisa se já prestou atendimento
	List<Atendimento> atendimento = AtendimentoDao.getInstance()
		.listarAtendimentosNaoFinalizadosOBM(registro);

	logger.info("Qtd Atendimentos" + atendimento.size());

	if (atendimento.size() > 0) {
	    despacha(request, response, "existeAtendimento", nome);
	}

	else {

	    try {
		OBMDao.getInstance().deletar(obm);
	    } catch (SiscabException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    despacha(request, response, "deletar", nome);
	}
    }

    /*
     * Recebe como parametro HttpRequest, response, a acao a ser executada e o
     * nome do objeto
     */
    private void despacha(HttpServletRequest request,
	    HttpServletResponse response, String acao, String nomeOBM) {

	RequestDispatcher view;
	request.setAttribute("obm", nomeOBM);
	if (acao.equals("salvar")) {

	    request.setAttribute("mensagem", "salvo com sucesso!!");

	}

	else if (acao.equals("excecao")) {
	    request
		    .setAttribute(
			    "mensagem",
			    "Não é possível mudar seu status para Inativa,"
				    + " pois a OBM presente possui viaturas alocadas ou existem atendimentos não finalizados");
	} else if (acao.equals("obmIgual")) {
	    request.setAttribute("mensagem",
		    "OBM não salva! Já existe uma OBM com este nome!");
	}

	else if (acao.equals("existeAtendimento")) {
	    request.setAttribute("mensagem",
		    "Esta OBM prestou atendimento e não pode ser deletada!");
	}

	else if (acao.equals("alterar")) {
	    request.setAttribute("mensagem", "alterado com sucesso!!");

	} else if (acao.equals("deletar")) {
	    request.setAttribute("mensagem", "deletado com sucesso!!");
	}

	view = request.getRequestDispatcher("OBMMensagem.jsp");

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
	int registroUsuario = 0;
	int operacao = Integer.parseInt(request
		.getParameter("operacaoARealizar"));
	registroUsuario = Integer.parseInt(request.getParameter("registroOBM"));

	// Parametros do JSP
	nome = request.getParameter("nome");
	municipio = request.getParameter("municipio");
	bairro = request.getParameter("bairro");
	logradouro = request.getParameter("logradouro");
	numComplemento = request.getParameter("numComplemento");
	

	// Controle de qual operacao será realizada

	if (operacao == 1) {

	    salvar(request, response);

	} else if (operacao == 2) {

	    alterar(request, response, registroUsuario);
	}

	else {

	    deletar(request, response, registroUsuario);
	}

    }

    protected void salvar(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	coordX = Double.parseDouble(request.getParameter("coordX"));
	coordY = Double.parseDouble(request.getParameter("coordY"));
	status = request.getParameter("statusObm");

	if (status.equals("ativa"))
	    statusObm = 1;
	else
	    statusObm = 0;
	
	Municipio municipioDao = MunicipioDao.getInstance()
		.listarMunicipioNome(municipio);

	OBM obm = new OBM();
	obm.setNome(nome);

	// Analisa se existe OBM com mesmo nome
	OBM obmIgual = OBMDao.getInstance().listarOBMNome(obm.getNome());
	if (obmIgual != null) {
	    despacha(request, response, "obmIgual", obm.getNome());

	} else {

	    obm.setMunicipio(municipioDao);
	    obm.setBairro(bairro);
	    obm.setLogradouro(logradouro);
	    obm.setNumCompl(numComplemento);
	    obm.setCoordX(coordX);
	    obm.setCoordY(coordY);
	    obm.setStatus(statusObm);

	    OBMDao.getInstance().salvar(obm);
	    request.setAttribute("obm", obm);
	    despacha(request, response, "salvar", obm.getNome());

	}

    }
}
