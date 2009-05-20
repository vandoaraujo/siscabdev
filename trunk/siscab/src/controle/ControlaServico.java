package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.ServicoRealizado;
import modelo.TipoServico;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.ServicoRealizadoDao;
import dao.TipoServicoDao;

/**
 * Servlet implementation class ControlaServico
 */
public class ControlaServico extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int registroAtendimento;
    private String tipoServico;
    private int registroServico;
    private int numeroAtendimento;

    static Logger logger = Logger.getLogger(ControlaServico.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlaServico() {
	super();
	// TODO Auto-generated constructor stub
    }

    private void deletar(HttpServletRequest request,
	    HttpServletResponse response, int registroServico) {

	ServicoRealizado servico = ServicoRealizadoDao.getInstance()
		.BuscaServicoId(registroServico);
	String nomeServico = servico.getTiposervico()
		.getTiposervico_descricao();
	ServicoRealizadoDao.getInstance().deletar(servico);
	despacha(request, response, "deletar", nomeServico);
    }

    /*
     * Recebe como parametro HttpRequest, response, a acao a ser executada e o
     * nome do objeto
     */
    private void despacha(HttpServletRequest request,
	    HttpServletResponse response, String string, String descricaoServico) {

	RequestDispatcher view;
	request.setAttribute("descricaoServico", descricaoServico);
	if (string.equals("salvar")) {

	    request.setAttribute("mensagem", "salvo com sucesso!!");

	} else if(string.equals("tipoServicoRepetido")){
	    
	    request.setAttribute("mensagem", "Este serviço já foi cadastrado para este Atendimento!!");

	}
	else {
	    request.setAttribute("mensagem", "deletado com sucesso!!");
	}
	
	

	view = request.getRequestDispatcher("/mensagemServico.jsp");

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

	tipoServico = request.getParameter("tipoServico");
	registroAtendimento = Integer.parseInt(request
		.getParameter("idAtendimento"));
	registroServico = Integer.parseInt(request
		.getParameter("registroServico"));
	int operacao = Integer.parseInt(request
		.getParameter("operacaoARealizar"));

	if (operacao == 1) {

	    salvar(request, response);

	} else if (operacao == 3) {

	    deletar(request, response, registroServico);
	}

    }

    protected void salvar(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	Atendimento at = AtendimentoDao.getInstance().BuscaAtendimentoId(
		registroAtendimento);
	TipoServico tipoS = TipoServicoDao.getInstance().listarTipoServicoNome(
		tipoServico);
	ServicoRealizado servico = new ServicoRealizado();
	servico.setAtendimentos(at);
	servico.setTiposervico(tipoS);
	
	List<ServicoRealizado> s  = ServicoRealizadoDao.getInstance().listaNomeServicosReferenteUmAtendimento(tipoS.getId(),registroAtendimento);
	if(s.isEmpty()){
	    
	    ServicoRealizadoDao.getInstance().salvar(servico);
	    request.setAttribute("servico", servico);
	    despacha(request, response, "salvar", servico.getTiposervico()
		.getTiposervico_descricao());
	}
	else{
	
	    despacha(request, response, "tipoServicoRepetido", servico.getTiposervico()
			.getTiposervico_descricao());
	}

    }
}
