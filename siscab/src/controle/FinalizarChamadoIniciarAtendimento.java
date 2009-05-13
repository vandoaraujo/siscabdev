package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.Chamado;
import modelo.CronoAtendimento;
import modelo.Municipio;
import modelo.NaturezaChamado;
import modelo.OBM;
import modelo.TipoOcorrencia;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.ChamadoDao;
import dao.CronoAtendimentoDao;
import dao.MunicipioDao;
import dao.NaturezaChamadoDao;
import dao.OBMDao;
import dao.TipoOcorrenciaDao;

/**
 * Servlet implementation class FinalizarChamadoIniciarAtendimento
 */
public class FinalizarChamadoIniciarAtendimento extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Date dataFim = null;
    private Date dataInicio = null;
    int identificadorChamado, numAproxVitimas, oid = 0;
    String infoComplementares, nomeSolicitante, obmSolicitada, naturezaChamado,origemChamado, telefone, nomeObmUsuario = null;
    HttpServletRequest request = null;
    HttpServletResponse response = null;
    Chamado chamado = null;
    int numeroGeradoChamado = 0;
    OBM obmAReceberSolicitacao = null;
    String municipio = null;
    String bairro = null;
    String endereco = null;
    int numero = 0;
    String idAno = null;
    String radioButton = null;
    private TipoOcorrencia tipoO;
    private String obmAtendimento;
    private String obmRepassaAtendimento;
    private float coordX;
    private float coordY;
    private String status;
    private Atendimento atendimento;
    static Logger logger = Logger.getLogger(FinalizarChamadoIniciarAtendimento.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarChamadoIniciarAtendimento() {
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
	doPost(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	logger.info(getServletName());
	this.request = request;
	this.response = response;
	
	int operacaoARealizar = Integer.parseInt(request
		.getParameter("operacaoARealizar"));
	numeroGeradoChamado = Integer.parseInt(request
		.getParameter("idChamado")); // cast
	nomeSolicitante = request.getParameter("nomeSolicitante");
	telefone = request.getParameter("telefone");
	String aproxVitimas = request.getParameter("numAproximadoVitimas");
	if (aproxVitimas.equals(""))
	    aproxVitimas = "0";
	numAproxVitimas = Integer.parseInt(aproxVitimas);
	obmSolicitada = request.getParameter("obmSolicitada");
	infoComplementares = request.getParameter("infoComplementares");
	origemChamado = request.getParameter("origemChamado");
	naturezaChamado = request.getParameter("naturezaChamado");
	nomeObmUsuario = request.getParameter("obmUsuario");
	municipio = request.getParameter("municipio");
	bairro = request.getParameter("bairro");
	bairro.toLowerCase();
	endereco = request.getParameter("endereco");
	numero = Integer.parseInt(request.getParameter("numero"));

	request.setAttribute("municipio", municipio);
	request.setAttribute("bairro", bairro);

	
	if (operacaoARealizar == 1) {
	
    	getHoraFinalChamado();
    
    	// Artificio para setar a hora Inicial do Chamado no guardada no Servlet
    	// RegistrarChamado
    	dataInicio = Chamado.getDataHoraInicioChamado();
    
    	salvarChamado();
    
    	if (naturezaChamado.equals("Solicitação de socorro")) {
            	    
	      	buscaChamadoAtual();
	    
	    	obmAtendimento = request.getParameter("obmUsuario");
		radioButton = request.getParameter("radiobutton");
		obmRepassaAtendimento = request.getParameter("obmRepassaAtendimento");
		String latitude = request.getParameter("CoordX");
		if (latitude.equals("")) {
		    latitude = "0";
		}
		String longitude = request.getParameter("CoordY");
		if (longitude.equals("")) {
		    longitude = "0";
		}
		coordX = Float.parseFloat(latitude);
		coordY = Float.parseFloat(longitude);
		String tipoOcorrencia = request.getParameter("tipoOcorrencia");
		status = "Pendente";

		// Instancia objeto a ser salvo no BD
		atendimento = new Atendimento();

		getAnoVigente();

		iniciaProximoIdAtendimento();

		verificarRepasseAtendimento();

		buscaChamadoAtual();

		tipoO = TipoOcorrenciaDao.getInstance().listarTiposOcorrenciaNome(
			tipoOcorrencia);

		salvarAtendimento();

		iniciaCronologiaAtendimento();

		RequestDispatcher view = request
			.getRequestDispatcher("/atendimentoSalvo.jsp");

		request.setAttribute("numeroAtendimento", oid);
		view.forward(request, response);

	} else {

	    RequestDispatcher view = request
		    .getRequestDispatcher("/FinalizarChamado.jsp");
	    view.forward(request, response);

	}
	}
	
	if((municipio.equals("") || bairro.equals("")) && operacaoARealizar == 2)
	{
	    
	    	RequestDispatcher view = request
	    	.getRequestDispatcher("/msgOcorrenciasProximas.jsp");
	    	view.forward(request, response);
	}
	//Campos obrigatórios vazios
	else if((!municipio.equals("") || !bairro.equals("")) && operacaoARealizar == 2)
	{	    
	    buscaChamadosProximos();
	}
	
	// Ver este campo --- JSP passa o valor 1
	int registroOcorrencia = Integer.parseInt(request
		.getParameter("registroOcorrencia"));

	ArrayList<OBM> obms = (ArrayList<OBM>) OBMDao.getInstance()
		.listarTodasOBMs();

	request.setAttribute("numeroChamado", numeroGeradoChamado);
	request.setAttribute("obms", obms);
	request.setAttribute("origemChamado", origemChamado);
	request.setAttribute("nomeSolicitante", nomeSolicitante);
	request.setAttribute("telefone", telefone);
	request.setAttribute("aproxVitimas", numAproxVitimas);
	request.setAttribute("infoComplementares", infoComplementares);
	request.setAttribute("nomeObmUsuario", nomeObmUsuario);
	request.setAttribute("endereco", endereco);
	request.setAttribute("numero", numero);

	if (operacaoARealizar == 3) {
	    RequestDispatcher view = request
		    .getRequestDispatcher("/MostraMapaLocalOcorrencia.jsp");
	    view.forward(request, response);
	}
	    

    }

    private void getHoraFinalChamado() {

	GregorianCalendar calendar = new GregorianCalendar();
	calendar.add(Calendar.MONTH, 0);
	calendar.add(Calendar.HOUR_OF_DAY, 0);
	calendar.add(Calendar.MINUTE, 0);
	DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	String grava = formata.format(calendar.getTime());
	dataFim = new Date(formata.format(calendar.getTime()));
    }
    
    private void getAnoVigente() {

	GregorianCalendar calendar = new GregorianCalendar();
	calendar.add(Calendar.YEAR, 0);
	DateFormat formata = new SimpleDateFormat("yyyy");
	idAno = formata.format(calendar.getTime());
    }

    private void iniciaCronologiaAtendimento() {

	// Iniciar cronologia do atendimento
	CronoAtendimento crono = new CronoAtendimento();
	crono.setAtendimento_id(atendimento);
	crono.setCronoatendimento_tipoevento("acionamento");
	crono.setCronoatendimento_horaevento(new Date());
	CronoAtendimentoDao.getInstance().salvar(crono);

    }

    private void iniciaProximoIdAtendimento() {
	
	Integer numero = null;
	int maior = 0;
	String anoUltimoAtendimento = null;
	Iterator proxIdAtendimento = AtendimentoDao.getInstance()
		.listaUltimoId();
	
	if (proxIdAtendimento.hasNext() == false) {
	    maior=1;
	    logger.info("Setou o próximo atendimento como 1 ");
	    
	} else {
	    Object[] linhas = (Object[]) proxIdAtendimento.next();
	    numero = (Integer) linhas[0];
	    maior =(Integer) linhas[1];
	    anoUltimoAtendimento = Integer.toString(numero).substring(0, 4);
	    logger.info("Ano Ultimo Atendimento " + anoUltimoAtendimento);
	
	    if(!anoUltimoAtendimento.equals(idAno)){
		maior=1;
		logger.info("Setou o próximo atendimento como 1 ");
	    }
	    else{
		maior++;
		logger.info("Incrementou o atendimento ");
	    }
	
	}
	
	//if o ano mudou referente ao ultimo atendimento, reinicia a numeracao
	String concatena = idAno + maior;
	oid = Integer.parseInt(concatena);

    }

    private void salvarAtendimento() {

	atendimento.setAtendimento_numero(oid);
	atendimento.setChamado_id(chamado);
	atendimento.setBairro(bairro);
	// Busca Municipio para instanciar o atributo do objeto
	Municipio m = MunicipioDao.getInstance().listarMunicipioNome(municipio);
	atendimento.setMunicipio_id(m);
	atendimento.setCoordx(coordX);
	atendimento.setCoordy(coordY);
	atendimento.setLogradouro(endereco);
	atendimento.setNumcompl(numero);
	atendimento.setTipoocorrencia(tipoO);
	atendimento.setStatus_atendimento(status);

	AtendimentoDao.getInstance().salvar(atendimento);

    }

    private void verificarRepasseAtendimento() {

	// Verifica se o atendimento será analisado por outra OBM
	if (radioButton.equals("sim")) {

	    obmAReceberSolicitacao = OBMDao.getInstance().listarOBMNome(
		    obmRepassaAtendimento);
	    atendimento.setObm_id(obmAReceberSolicitacao);

	} else {
	    obmAReceberSolicitacao = OBMDao.getInstance().listarOBMNome(
		    obmAtendimento);
	    atendimento.setObm_id(obmAReceberSolicitacao);
	}
    }

    private void salvarChamado() {

	// Criacao do Objeto Chamado com os campos da tela
	Chamado chamado = new Chamado();
	chamado.setHorainicio(dataInicio);
	chamado.setHoratermino(dataFim);
	// chamado.setHoratermino(horatermino);
	chamado.setId(identificadorChamado);
	chamado.setInfocomplementares(infoComplementares);
	// chamado.setNaturezaChamado(naturezaChamado);
	chamado.setNomeSolicitante(nomeSolicitante);
	chamado.setNumaproxvitimas(numAproxVitimas);
	// Buscar no Banco a OBM
	OBM obmUsuario = OBMDao.getInstance().listarOBMNome(obmSolicitada);
	// OBM que recebeu aquele chamado, não o atendimento

	// Busca no banco a Natureza do chamado
	NaturezaChamado natureza = NaturezaChamadoDao.getInstance()
		.listarTipoNaturezaNome(naturezaChamado);
	chamado.setObm(obmUsuario);
	chamado.setOrigem(origemChamado);
	chamado.setTelefoneSolicitante(telefone);
	chamado.setNaturezaChamado(natureza);

	ChamadoDao.getInstance().salvar(chamado);

    }
    
    private void buscaChamadoAtual() {

	// Busca ultimo chamado na transacao corrente
	chamado = ChamadoDao.getInstance().BuscaChamadoId(numeroGeradoChamado);
    }
    
    
    private void buscaChamadosProximos() throws ServletException, IOException {

	Municipio mun = MunicipioDao.getInstance().listarMunicipioNome(
		municipio);
	List<Atendimento> at = AtendimentoDao.getInstance()
		.listarOcorrenciasProximas(mun.getId(), bairro);
	request.setAttribute("municipio", municipio);
	request.setAttribute("listaAtendimentosProximos", at);
	RequestDispatcher view = request
		.getRequestDispatcher("/ProcurarOcorrenciasProximas.jsp");
	view.forward(request, response);

    }

}
