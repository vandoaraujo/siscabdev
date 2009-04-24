package controle;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.Atendimento;
import modelo.CronoAtendimento;
import modelo.ModoFechamento;
import modelo.Viatura;
import dao.AtendimentoDao;
import dao.CronoAtendimentoDao;
import dao.ModoFechamentoDao;
import dao.MovimentaViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class LiberarViaturasFecharAtendimento
 */
public class LiberarViaturasFecharAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<Viatura> viaturas = null;
    Atendimento atendimento = null;
	static Logger logger = Logger.getLogger(LiberarViaturasFecharAtendimento.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LiberarViaturasFecharAtendimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info(getServletName());
		String modoFechamento = request.getParameter("modoFechamento");
		int numeroAtendimento = Integer.parseInt(request.getParameter("numeroAtendimento"));
		int idAtendimento = Integer.parseInt(request.getParameter("idAtendimento"));
		
		viaturas = MovimentaViaturaDao.getInstance().ListarViaturaNaoRepetidasEmAtendimento(idAtendimento);
		
		//Inicia transacao
		ViaturaDao.getInstance().iniciaTransacao();
		
		trocarStatusViaturas();
		//Finaliza Transacao
		ViaturaDao.getInstance().finalizaTransacao();
		logger.info("Finalizou a transacao");
		
		efetivaFinalizacaoAtendimento(request,response,modoFechamento,idAtendimento);

	}
	
	private void trocarStatusViaturas() {
		
		for(int i=0;i< viaturas.size(); i++){
			Viatura v = viaturas.get(i);
			v.setViatura_status("Em prontidão");
			ViaturaDao.getInstance().atualizarDiversasViaturas(v);
			logger.info(" ############ Atualizou " + " viatura!   ################");
		}
		
	}

	private void efetivaFinalizacaoAtendimento(HttpServletRequest request,
			HttpServletResponse response, String modoFechamento,
			int idAtendimento) {

		atendimento = AtendimentoDao.getInstance().BuscaAtendimentoId(idAtendimento);
		ModoFechamento m = ModoFechamentoDao.getInstance().listarModoFechamentoNome(modoFechamento);
		atendimento.setModofechamento_id(m.getId());
		atendimento.setStatus_atendimento("Finalizado");
				
		AtendimentoDao.getInstance().atualizar(atendimento);
		
		logger.info("############# Atendimento atualizado e finalizado com sucesso ##############");
		
		finalizaCronologiaAtendimento();
			
		logger.info("############# Cronologia do Atendimento - Finalizacao");
			
		RequestDispatcher view;
		request.setAttribute("mensagem", "Finalizado com sucesso");
		request.setAttribute("numeroAtendimento",atendimento.getAtendimento_numero());
		view = request.getRequestDispatcher("/finalizaChamadoMensagem.jsp");
		
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

	private void finalizaCronologiaAtendimento() {
		
		//Finaliza cronologia do atendimento
		CronoAtendimento crono =  new CronoAtendimento();
		crono.setAtendimento_id(atendimento);
		crono.setCronoatendimento_tipoevento("finalização");
		crono.setCronoatendimento_horaevento(new Date());
		CronoAtendimentoDao.getInstance().salvar(crono);
		
	}

}
