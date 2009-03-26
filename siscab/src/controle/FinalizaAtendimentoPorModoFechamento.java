package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Atendimento;
import modelo.ModoFechamento;
import modelo.Viatura;
import dao.AtendimentoDao;
import dao.ModoFechamentoDao;
import dao.MovimentaViaturaDao;

/**
 * Servlet implementation class FinalizaAtendimentoPorModoFechamento
 */
public class FinalizaAtendimentoPorModoFechamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizaAtendimentoPorModoFechamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String modoFechamento = request.getParameter("modoFechamento");
		int numeroAtendimento = Integer.parseInt(request.getParameter("numeroAtendimento"));
		int idAtendimento = Integer.parseInt(request.getParameter("idAtendimento"));
		
		List<Viatura> viatura = MovimentaViaturaDao.getInstance().ListarViaturaNaoRepetidasEmAtendimento(idAtendimento);
		
		if(viatura.isEmpty()){
			
			efetivaFinalizacaoAtendimento(request,response,modoFechamento,idAtendimento);
			
		}
		else{
			
			System.out.println("TEM VIATURA - CLASSE FINALIZA ATENDIMENTO");
			informaUsuarioSobreAlocacaoViaturasAtendimento(request,response,modoFechamento,numeroAtendimento,idAtendimento,viatura);
		}
	}

	private void informaUsuarioSobreAlocacaoViaturasAtendimento(
			HttpServletRequest request, HttpServletResponse response,
			String modoFechamento, int numeroAtendimento,int idAtendimento, List<Viatura> viatura) {
		
		
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("modos", modoFechamento);
		sessao.setAttribute("numeroAtendimento",numeroAtendimento);
		sessao.setAttribute("idAtendimento",idAtendimento);
		sessao.setAttribute("modoFechamento", modoFechamento);
		sessao.setAttribute("viaturas", viatura);
		view = request.getRequestDispatcher("/avisoLiberacaoViaturasFechaAtendimento.jsp");
		
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

	private void efetivaFinalizacaoAtendimento(HttpServletRequest request,
			HttpServletResponse response, String modoFechamento,
			int idAtendimento) {

		Atendimento atendimento = AtendimentoDao.getInstance().BuscaAtendimentoId(idAtendimento);
		ModoFechamento m = ModoFechamentoDao.getInstance().listarModoFechamentoNome(modoFechamento);
		atendimento.setModofechamento_id(m.getId());
		atendimento.setStatus_atendimento("Finalizado");
		System.out.println(" ############### Setou o modoFechamento");
		
		AtendimentoDao.getInstance().atualizar(atendimento);
		
		System.out.println(" ############# Atendimento atualizado com sucesso");
		
			
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

}
