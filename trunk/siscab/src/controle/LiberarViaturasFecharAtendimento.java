package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.ModoFechamento;
import modelo.Viatura;
import dao.AtendimentoDao;
import dao.ModoFechamentoDao;
import dao.MovimentaViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class LiberarViaturasFecharAtendimento
 */
public class LiberarViaturasFecharAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		String modoFechamento = request.getParameter("modoFechamento");
		int numeroAtendimento = Integer.parseInt(request.getParameter("numeroAtendimento"));
		int idAtendimento = Integer.parseInt(request.getParameter("idAtendimento"));
		List<Viatura> viaturas = MovimentaViaturaDao.getInstance().ListarViaturaNaoRepetidasEmAtendimento(numeroAtendimento);
		
		//Inicia transacao
		ViaturaDao.getInstance().iniciaTransacao();
		System.out.println("Abriu a transacao");
		
		for(int i=0;i< viaturas.size(); i++){
			Viatura v = viaturas.get(i);
			v.setViatura_status("Em prontidão");
			ViaturaDao.getInstance().atualizarDiversasViaturas(v);
			System.out.println("Atualizou" + i+1 + "viatura");
		}
		
		//Finaliza Transacao
		ViaturaDao.getInstance().finalizaTransacao();
		System.out.println("Finalizou a transacao");
		
		efetivaFinalizacaoAtendimento(request,response,modoFechamento,idAtendimento);

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
