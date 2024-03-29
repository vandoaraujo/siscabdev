package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.OBM;
import modelo.TipoViatura;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.MovimentaViaturaDao;
import dao.OBMDao;
import dao.TipoViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class CRUDViatura
 */
public class CRUDViatura extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String numeroViatura;
    private String obm;
    //private String statusViatura;
    //private String obsViatura;
    private int registroViatura;
    private String tipoViatura;
    static Logger logger = Logger.getLogger(CRUDViatura.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDViatura() {
	super();
	// TODO Auto-generated constructor stub
    }

    private void alterar(HttpServletRequest request,
	    HttpServletResponse response, int registroViatura) {

	Viatura via = ViaturaDao.getInstance().BuscaViaturaId(registroViatura);
	// Viatura em atendimento n�o pode ser modificada
	if (via.getViatura_status().equals("Em atendimento")) {

	    despacha(request, response, "Em atendimento", via.getNumero());

	} else {
	    OBM obmAtual = OBMDao.getInstance().listarOBMNome(obm);
	    TipoViatura tipoV = TipoViaturaDao.getInstance().listarTipoViatura(
		    tipoViatura);
	    via.setNumero(numeroViatura);
	    via.setObm(obmAtual);
	    via.setTipo_viatura(tipoV);
	    via.setViatura_obs(null);
	    via.setViatura_status("Inoperante - Sem tripula��o");
	    ViaturaDao.getInstance().atualizar(via);
	    despacha(request, response, "alterar", via.getNumero());
	}
    }

    private void deletar(HttpServletRequest request,
	    HttpServletResponse response, int registroViatura) {

	Viatura via = ViaturaDao.getInstance().BuscaViaturaId(registroViatura);
	// Viatura que realizou atendimento n�o pode ser excluida
	List<Viatura> viaturas = MovimentaViaturaDao.getInstance()
		.ListarViaturasRealizaramAtendimento(via.getId());
	if (!viaturas.isEmpty()) {
	    despacha(request, response, "Em atendimento Exclusao",
		    numeroViatura);
	} else {
	    String numeroViatura = via.getNumero();
	    ViaturaDao.getInstance().deletar(via);
	    despacha(request, response, "deletar", numeroViatura);
	}
    }

    /*
     * Recebe como parametro HttpRequest, response, a acao a ser executada e o
     * nome do objeto
     */
    private void despacha(HttpServletRequest request,
	    HttpServletResponse response, String string, String numeroViatura) {

	RequestDispatcher view;
	request.setAttribute("numeroViatura", numeroViatura);
	if (string.equals("salvar")) {
	    request.setAttribute("mensagem", "Viatura foi inclu�da no cadastro");
	}
	else if (string.equals("alterar")) {
	    request.setAttribute("mensagem", "Dados da viatura foram alterados.");

	} else if (string.equals("viaturaRepetida")) {
	    request
		    .setAttribute("mensagem",
			    "Viatura j� cadastrada.");

	} else if (string.equals("Em atendimento")) {
	    request
		    .setAttribute("mensagem",
			    "Dados da viatura n�o podem ser alterados no momento, pois est� em atendimento.");

	} else if (string.equals("Em atendimento Exclusao")) {
	    request
		    .setAttribute("mensagem",
			    "Viatura n�o pode ser exclu�da do cadastro, pois existe(m) registro(s) de atendimento(s) associado(s) a esta.");

	} else if (string.equals("numeroViaturaException")) {
	    request
	    .setAttribute("mensagem",
		    "O n�mero da viatura deve conter no m�ximo 3 caracteres!");
	}
	else {
	    request.setAttribute("mensagem", "Viatura foi exclu�da do cadastro.");
	}

	view = request.getRequestDispatcher("/mensagemViatura.jsp");

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

	logger.info(getServletName().toString());
	numeroViatura = request.getParameter("numero");
	obm = request.getParameter("obm");
	//statusViatura = request.getParameter("status");
	//obsViatura = request.getParameter("obsViatura");
	tipoViatura = request.getParameter("tipoViatura");
	registroViatura = Integer.parseInt(request
		.getParameter("registroViatura"));
	int operacao = Integer.parseInt(request
		.getParameter("operacaoARealizar"));

	if (operacao == 1) {

	    salvar(request, response);

	} else if (operacao == 2) {

	    alterar(request, response, registroViatura);
	}

	else {

	    deletar(request, response, registroViatura);
	}
    }

    protected void salvar(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	OBM cobm = OBMDao.getInstance().listarOBMNome(obm);
	TipoViatura tipoV = TipoViaturaDao.getInstance().listarTipoViatura(
		tipoViatura);
	Viatura via = new Viatura();
	numeroViatura.toUpperCase();
	via.setNumero(numeroViatura);

	Viatura v = ViaturaDao.getInstance()
		.listarViaturasNumero(numeroViatura);
	if (v != null) {
	    despacha(request, response, "viaturaRepetida", via.getNumero());

	}
	else if(via.getNumero().length() > 3){
	    despacha(request, response, "numeroViaturaException", via.getNumero());
	}
	else {

	    via.setObm(cobm);
	    via.setTipo_viatura(tipoV);
	    via.setViatura_obs(null);
	    via.setViatura_status("Inoperante - Sem tripula��o");
	    ViaturaDao.getInstance().salvar(via);
	    request.setAttribute("viatura", via);
	    despacha(request, response, "salvar", via.getNumero());
	}
    }

}
