package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Atendimento;
import modelo.MovimentaViatura;
import modelo.MovimentaViaturaPK;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.MovimentaViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class NovaMovimentacaoViatura
 */
public class NovaMovimentacaoViatura extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(NovaMovimentacaoViatura.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovaMovimentacaoViatura() {
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
	int registroViatura = (Integer.parseInt(request
		.getParameter("registro")));
	int numeroAtendimento = (Integer.parseInt(request
		.getParameter("numeroAtendimento")));
	int operacaoARealizar = Integer.parseInt(request
		.getParameter("operacaoARealizar"));

	if (operacaoARealizar == 1) {

	    listaEventosViatura(registroViatura, numeroAtendimento, request,
		    response);
	}
	// Libera status da viatura de em atendimento, para em prontidão
	else {

	    mudaStatusViaturaAtendimento(registroViatura, request, response,
		    numeroAtendimento);

	}

    }

    private void listaEventosViatura(int registroViatura,
	    int numeroAtendimento, HttpServletRequest request,
	    HttpServletResponse response) {

	Viatura via = ViaturaDao.getInstance().BuscaViaturaId(registroViatura);
	Atendimento atendimento = AtendimentoDao.getInstance()
		.BuscaAtendimentoId(numeroAtendimento);

	List<MovimentaViatura> tiposEventosViaturaAtendimento = MovimentaViaturaDao
		.getInstance().listaTiposEventosViaturaAtendimento(
			atendimento.getId(), via.getId());

	// Artificio para telaNova Movimentacao.jsp
	String numeroViatura = via.getNumero();

	HttpSession sessao = request.getSession();
	sessao.setAttribute("tiposeventosViaturaAtendimento",
		tiposEventosViaturaAtendimento);
	sessao.setAttribute("viaturaAtual", via);
	sessao.setAttribute("numeroViatura", numeroViatura);
	sessao.setAttribute("atendimento", atendimento);
	RequestDispatcher view = request
		.getRequestDispatcher("/listaEventosViaturaAtendimento.jsp");

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

    private void mudaStatusViaturaAtendimento(int registroViatura,
	    HttpServletRequest request, HttpServletResponse response,
	    int atendimento) {

	Viatura via = ViaturaDao.getInstance().BuscaViaturaId(registroViatura);
	via.setViatura_status("Em prontidão");

	ViaturaDao.getInstance().atualizar(via);

	logger.info("######### Atualizou o STATUS DA VIATURA!!!   ##############");
	
	analisaMovimentacoesViatura(atendimento,registroViatura);
	
	// Atualiza lista de viaturas

	List<Viatura> viaturas = new ArrayList<Viatura>();

	List<Viatura> tiposEventosViatura = MovimentaViaturaDao.getInstance()
		.ListarViaturaNaoRepetidasEmAtendimento(atendimento);

	for (int i = 0; i < tiposEventosViatura.size(); i++) {

	    Integer viatura_id = tiposEventosViatura.get(i).getId();
	    Viatura v = ViaturaDao.getInstance().BuscaViaturaId(viatura_id);
	    viaturas.add(v);

	}

	Atendimento atendimentoAtual = AtendimentoDao.getInstance()
		.BuscaAtendimentoId(atendimento);

	HttpSession sessao = request.getSession();
	sessao.setAttribute("viaturas", viaturas);
	RequestDispatcher view;
	sessao.setAttribute("atendimentoAtual", atendimentoAtual);
	view = request.getRequestDispatcher("/iniciarViaturasEmpenhadas.jsp");

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

    private void analisaMovimentacoesViatura(int atendimento, int registroViatura) {
	
	//Retorna a ultima movimentacao da viatura  
	Integer id = MovimentaViaturaDao.getInstance().listaUltimoEvento(
		atendimento, registroViatura);
	//Verifica se é diferente de "Retorno à OBM"
	MovimentaViatura m = MovimentaViaturaDao.getInstance()
		.BuscaMovimentacaoViaturaId(id);
	
	if (!(m.getMovimentaviatura_tipoevento().equals("Retorno à OBM"))) {

	    	Atendimento at = AtendimentoDao.getInstance().BuscaAtendimentoId(atendimento);
		
		Viatura v = ViaturaDao.getInstance().BuscaViaturaId(registroViatura);
		
		MovimentaViaturaPK mov = new MovimentaViaturaPK();
		mov.setAtendimentos(at);
		mov.setViatura(v);
		
		//Define relacionamento
		MovimentaViatura tipoEvento = new MovimentaViatura();
		tipoEvento.setChaveComposta(mov);
		tipoEvento.setMovimentaviatura_tipoevento("Retorno à OBM");
		
			
		GregorianCalendar calendar =  new GregorianCalendar();
		calendar.add(GregorianCalendar.MONTH, 0);
		calendar.add(GregorianCalendar.HOUR_OF_DAY, 0);
		calendar.add(GregorianCalendar.MINUTE, 0);
		DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date data = new Date(formata.format(calendar.getTime()));
					
		tipoEvento.setMovimentaviatura_horaEvento(data);
		
		MovimentaViaturaDao.getInstance().salvar(tipoEvento);
		
		logger.info("Salvo a movimentação da Viatura Retorno à OBM");
	    
	}
	
    }
}
