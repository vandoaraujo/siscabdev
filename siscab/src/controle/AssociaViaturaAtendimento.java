package controle;

import java.io.IOException;
import java.util.ArrayList;
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
import modelo.MovimentaViatura;
import modelo.MovimentaViaturaPK;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.CronoAtendimentoDao;
import dao.MovimentaViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class AssociaViaturaAtendimento
 */
public class AssociaViaturaAtendimento extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Viatura viatura = null;
    MovimentaViatura mov = null;
    Atendimento atendimento = null;

    static Logger logger = Logger.getLogger(AssociaViaturaAtendimento.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssociaViaturaAtendimento() {
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

	int idViatura = Integer.parseInt(request.getParameter("viatura"));
	logger.info("Id da viatura" + idViatura);

	viatura = ViaturaDao.getInstance().BuscaViaturaId(idViatura);
	atendimento = (Atendimento) request.getSession().getAttribute(
		"atendimentoAtual");

	logger.info("Atendimento vindo do request " + atendimento);

	viatura.setViatura_status("Em atendimento");

	movimentaViaturaAoAtendimento();

	// Atualiza o status da viatura de em prontid�o para em atendimento
	ViaturaDao.getInstance().atualizar(viatura);

	salvarCronologiaAtendimentoPrimeiraViatura();

	// Modifica o Status do Atendimento
	atendimento.setStatus_atendimento("Em andamento");
	AtendimentoDao.getInstance().atualizar(atendimento);

	logger.info("Atualizou o Atendimento para situacao Status Em andamento");

	// fazer uma busca de viaturas empenhadas...
	// L�gica de na pr�xima p�gina popular uma lista de viaturas j�
	// associadas e com tipo evento ao atendimento
	List<Viatura> viaturas = new ArrayList<Viatura>();

	List<Viatura> tiposEventosViatura = MovimentaViaturaDao.getInstance()
		.listaViaturasDeUmAtendimento(atendimento.getId());

	// Abre uma nova Session em AssociaViaturaAtendimento
	ViaturaDao.getInstance().abreUmaSessionFluxosExcecao();

	for (int i = 0; i < tiposEventosViatura.size(); i++) {

	    Viatura via = ViaturaDao.getInstance().BuscaViaturaId(
		    tiposEventosViatura.get(i).getId());
	    viaturas.add(via);

	}

	HttpSession sessao = request.getSession();
	sessao.setAttribute("viaturas", viaturas);
	RequestDispatcher view;
	sessao.setAttribute("atendimentoAtual", atendimento);
	request.setAttribute("mensagem", "Viatura despachada com sucesso");
	view = request.getRequestDispatcher("/iniciarViaturasEmpenhadas.jsp");

	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    private void movimentaViaturaAoAtendimento() {

	MovimentaViaturaPK m = new MovimentaViaturaPK();
	m.setAtendimentos(atendimento);
	m.setViatura(viatura);
	mov = new MovimentaViatura();
	mov.setChaveComposta(m);
	mov.setMovimentaviatura_tipoevento("Sa�da da OBM");
	mov.setMovimentaviatura_horaEvento(new Date());

	// Salva na tabela associativa a movimenta��o da Viatura
	MovimentaViaturaDao.getInstance().salvar(mov);

    }

    private void salvarCronologiaAtendimentoPrimeiraViatura() {

	CronoAtendimento cr = CronoAtendimentoDao.getInstance()
		.verificaCronologiaAtendimentoInicio(atendimento.getId());
	// Se a query retorna null, indica que nenhuma viatura ainda foi
	// despachada para este atendimento
	if (cr == null) {
	    // Cria um registro na tabela CronoAtendimento
	    CronoAtendimento crono = new CronoAtendimento();
	    crono.setAtendimento_id(atendimento);
	    crono.setCronoatendimento_tipoevento("in�cio");
	    crono.setCronoatendimento_horaevento(new Date());
	    CronoAtendimentoDao.getInstance().salvar(crono);
	    logger.info("SALVO a CronologiaAtendimento como in�cio");
	} else {
	    logger.info("TESTE - CLASSE ASSOCIA VIATURA ATENDIMENTO -- NAO ASSOCIOU UMA CRONOLOGIA REPETIDA");
	}

    }

}
