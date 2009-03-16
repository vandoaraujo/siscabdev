package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimentos;
import modelo.OBM;
import dao.AtendimentosDao;
import dao.OBMDao;

/**
 * Servlet implementation class EfetivaRepasseAtendimento
 */
public class EfetivaRepasseAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EfetivaRepasseAtendimento() {
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
		
		int numeroAtendimento = Integer.parseInt(request.getParameter("numeroAtendimento"));
		String nomeOBM = request.getParameter("obm"); 
		
		OBM obm = OBMDao.getInstance().listarOBMNome(nomeOBM);
		Atendimentos at = AtendimentosDao.getInstance().BuscaAtendimentoAtendimentoNumero(numeroAtendimento);
		
		at.setObm_id(obm);
		
		AtendimentosDao.getInstance().atualizar(at);
		System.out.println(" ATUALIZADO COM SUCESSO!!");
		request.setAttribute("atendimentoNumero", at.getAtendimento_numero());
		despacha(request, response,"salvar", obm.getNome());
		
		
		
		
	}
	
	
	private void despacha(HttpServletRequest request,
			HttpServletResponse response, String string, String nomeOBM) {
		
			RequestDispatcher view;
			request.setAttribute("obm", nomeOBM);
			if(string.equals("salvar")){
				
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

	

}
