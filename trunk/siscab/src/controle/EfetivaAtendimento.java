package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.Chamado;
import modelo.CronoAtendimento;
import modelo.Municipio;
import modelo.OBM;
import modelo.TipoOcorrencia;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.ChamadoDao;
import dao.CronoAtendimentoDao;
import dao.MunicipioDao;
import dao.OBMDao;
import dao.TipoOcorrenciaDao;

/**
 * Servlet implementation class EfetivaAtendimento
 */
public class EfetivaAtendimento extends HttpServlet {
    private static final long serialVersionUID = 1L;
    OBM obmAReceberSolicitacao = null;
    int proxIdAtendimento, oid, idAtendimento = 0;
    String idAno = null;
    String radioButton, obmRepassaAtendimento, obmAtendimento = null;
    Atendimento atendimento = null;
    Chamado chamado = null;
    private String bairro;
    private double coordX;
    private double coordY;
    private String logradouro;
    private int numComplemento;
    private TipoOcorrencia tipoO;
    private String status;
    private String municipio;

    static Logger logger = Logger.getLogger(EfetivaAtendimento.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EfetivaAtendimento() {
	super();
	// TODO Auto-generated constructor stub
    }

    private void buscaChamadoAtual() {

	// Busca ultimo chamado na transacao corrente
	chamado = ChamadoDao.getInstance().BuscaChamadoId(idAtendimento);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	logger.info(getServletName());
	idAtendimento = Integer.parseInt(request.getParameter("idAtendimento"));
	obmAtendimento = request.getParameter("obmAtendimento");
	bairro = request.getParameter("bairro");
	bairro.toLowerCase();
	municipio = request.getParameter("municipio");
	radioButton = request.getParameter("radiobutton");
	obmRepassaAtendimento = request.getParameter("obmRepassaAtendimento");
	String latitude = request.getParameter("coordX");
	if (latitude.equals("")) {
	    latitude = "0";
	}
	String longitude = request.getParameter("coordY");
	if (longitude.equals("")) {
	    longitude = "0";
	}
	coordX = Float.parseFloat(latitude);
	coordY = Float.parseFloat(longitude);
	logradouro = request.getParameter("logradouro");
	numComplemento = Integer.parseInt(request
		.getParameter("numComplemento"));
	String tipoOcorrencia = request.getParameter("tipoOcorrencia");
	status = request.getParameter("status");

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
	
	//Se o ano mudou referente ao ultimo atendimento, reinicia a numeracao
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
	atendimento.setLogradouro(logradouro);
	atendimento.setNumcompl(numComplemento);
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

}
