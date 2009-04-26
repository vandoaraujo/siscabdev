package controle;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.CronoAtendimento;
import modelo.OBM;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.CronoAtendimentoDao;
import dao.OBMDao;

/**
 * Servlet implementation class EfetivaRepasseAtendimento
 */
public class EfetivaRepasseAtendimento extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Atendimento at = null;
    String nomeOBM = null;
    OBM obm = null;
    int numeroAtendimento = 0;

    static Logger logger = Logger.getLogger(EfetivaRepasseAtendimento.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EfetivaRepasseAtendimento() {
	super();
	// TODO Auto-generated constructor stub
    }

    private void despacha(HttpServletRequest request,
	    HttpServletResponse response, String string, String nomeOBM) {

	RequestDispatcher view;
	request.setAttribute("obm", nomeOBM);
	if (string.equals("salvar")) {

	    request.setAttribute("mensagem", "Alterado com sucesso!!");

	}

	view = request.getRequestDispatcher("repasseAtendimentoMensagem.jsp");

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
	numeroAtendimento = Integer.parseInt(request
		.getParameter("numeroAtendimento"));
	nomeOBM = request.getParameter("obm");

	finalizarRepasseAtendimento();

	repassarAtendimentoCronologia();

	logger.info(" ATUALIZADO COM SUCESSO!!");
	request.setAttribute("atendimentoNumero", at.getAtendimento_numero());
	despacha(request, response, "salvar", obm.getNome());

    }

    private void finalizarRepasseAtendimento() {

	obm = OBMDao.getInstance().listarOBMNome(nomeOBM);
	at = AtendimentoDao.getInstance().BuscaAtendimentoAtendimentoNumero(
		numeroAtendimento);
	at.setObm_id(obm);
	AtendimentoDao.getInstance().atualizar(at);

    }

    private void repassarAtendimentoCronologia() {

	// Iniciar cronologia do atendimento
	CronoAtendimento crono = new CronoAtendimento();
	crono.setAtendimento_id(at);
	crono.setCronoatendimento_tipoevento("repasse");
	crono.setCronoatendimento_horaevento(new Date());
	CronoAtendimentoDao.getInstance().salvar(crono);

    }

}
