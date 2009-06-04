package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.Chamado;
import modelo.Municipio;
import modelo.OBM;
import modelo.SiscabException;
import modelo.Usuario;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.ChamadoDao;
import dao.MunicipioDao;
import dao.OBMDao;
import dao.UsuarioDao;
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
	
	logger.info("Valor do Status recebido " + status);

	if (status.equals("ATIVA"))
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
	
	if(viaturas.size() != 0){
	    despacha(request, response, "viaturas", obm.getNome());
	}
	else if (atendimento.size() != 0) {
	    despacha(request, response, "excecao", obm.getNome());
	}
	 
	
	else {

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
		.listarAtendimentosOBM(registro);
	
	List<Usuario> usuarios = UsuarioDao.getInstance().buscarUsuariosOBM(obm.getId());
	
	List<Chamado> chamados= ChamadoDao.getInstance().buscarChamadosOBM(obm.getId());

	logger.info("Qtd Atendimentos" + atendimento.size());
	
	logger.info("Qtd Usuarios" + usuarios.size());
	
	logger.info("Qtd Chamados" + chamados.size());

	if (atendimento.size() > 0) {
	    despacha(request, response, "existeAtendimento", nome);
	}
	
	else if(chamados.size() > 0){
	    despacha(request, response, "existeChamado", nome);
	}
	
	else if (usuarios.size() > 0){
	    despacha(request, response, "existeUsuario", nome);
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

	    request.setAttribute("mensagem", "OBM foi incluída no cadastro.");

	}
	
	else if (acao.equals("existeUsuario")){
	    request.setAttribute("mensagem", "OBM possui usuários e não pode ser deletada.");
	}
	else if (acao.equals("existeChamado")){
	    request.setAttribute("mensagem", "OBM possui chamados e não pode ser deletada.");
	}
	else if (acao.equals("excecao")) {
	    request
		    .setAttribute(
			    "mensagem",
			    "OBM não pode ser desativada no momento, pois está associada a atendimentos(s) não finalizado(s)");
	}
	else if (acao.equals("viaturas")) {
	    request
		    .setAttribute(
			    "mensagem",
			    "OBM não pode ser desativada no momento, pois possui viaturas associada(s)");
	}
	else if (acao.equals("obmIgual")) {
	    request.setAttribute("mensagem",
		    "OBM já cadastrada.");
	}

	else if (acao.equals("existeAtendimento")) {
	    request.setAttribute("mensagem",
		    "OBM não pode ser excluída do cadastro, pois existe(m) registro(s) de atendimento(s) associado(s) a esta.\nSe desejar que ela não seja exibida nas caixas de listagem, altere seu status para Inativa");
	}

	else if (acao.equals("alterar")) {
	    request.setAttribute("mensagem", "Dados da OBM foram alterados.");

	} else if (acao.equals("deletar")) {
	    request.setAttribute("mensagem", "OBM foi excluída do cadastro.");
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
	int registroObm = 0;
	int operacao = Integer.parseInt(request
		.getParameter("operacaoARealizar"));
	registroObm = Integer.parseInt(request.getParameter("registroOBM"));

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

	    alterar(request, response, registroObm);
	}

	else {

	    deletar(request, response, registroObm);
	}

    }

    protected void salvar(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	coordX = Double.parseDouble(request.getParameter("coordX"));
	coordY = Double.parseDouble(request.getParameter("coordY"));
	status = request.getParameter("statusObm");

	if (status.equals("ATIVA"))
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
