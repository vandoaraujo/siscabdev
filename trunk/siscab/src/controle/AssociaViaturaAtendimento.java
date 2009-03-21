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

import modelo.Atendimentos;
import modelo.CronoAtendimento;
import modelo.MovimentaViatura;
import modelo.MovimentaViaturaPK;
import modelo.Viatura;
import dao.AtendimentosDao;
import dao.CronoAtendimentoDao;
import dao.MovimentaViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class AssociaViaturaAtendimento
 */
public class AssociaViaturaAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssociaViaturaAtendimento() {
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
		
		
		int idViatura = Integer.parseInt(request.getParameter("viatura"));
		
		System.out.println("Id da viatura" + idViatura);
		
		Viatura v = ViaturaDao.getInstance().BuscaViaturaId(idViatura);		
		Atendimentos at = (Atendimentos) request.getSession().getAttribute("atendimentoAtual"); 
		
		v.setViatura_status("Em atendimento");
		
		MovimentaViaturaPK m = new MovimentaViaturaPK();
		m.setAtendimentos(at);
		m.setViatura(v);
		
		MovimentaViatura mov = new MovimentaViatura();
		mov.setChaveComposta(m);
		mov.setMovimentaviatura_tipoevento("Sa�da da OBM");
		mov.setMovimentaviatura_horaEvento(new Date());
		
		//Atualiza o status da viatura de em prontid�o para em atendimento
		ViaturaDao.getInstance().atualizar(v);
		System.out.println("ATUALIZOU O STATUS DA VIATURA!!!!");
		
		//Salva na tabela associativa a movimenta��o da Viatura
		MovimentaViaturaDao.getInstance().salvar(mov);
		
		System.out.println("SALVOU O TIPO EVENTO NA TABELA MOVIMENTAVIATURA");
		
		//Cria um registro na tabela CronoAtendimento
		CronoAtendimento crono =  new CronoAtendimento();
		crono.setAtendimento_id(at);
		crono.setCronoatendimento_tipoevento("in�cio");
		crono.setCronoatendimento_horaevento(new Date());
		CronoAtendimentoDao.getInstance().salvar(crono);
		System.out.println("SALVO a CronologiaAtendimento como in�cio");
		
		//Modifica o Status do Atendimento
		at.setStatus_atendimento("Em andamento");
		AtendimentosDao.getInstance().atualizar(at);
		
		System.out.println("Atualizou o Atendimento para situacao Status Em andamento");
		
		//fazer uma busca de viaturas empenhadas...
		//L�gica de na pr�xima p�gina popular uma lista de viaturas j� associadas e com tipo evento ao atendimento
		List <Viatura> viaturas = new ArrayList();
		
		List <Viatura> tiposEventosViatura = MovimentaViaturaDao.getInstance().listaViaturasDeUmAtendimento(at.getId());
		
		for(int i=0;i<tiposEventosViatura.size();i++){
					
			Viatura via = ViaturaDao.getInstance().BuscaViaturaId(tiposEventosViatura.get(i).getId());
			viaturas.add(via);
			
		}
				
		HttpSession sessao = request.getSession();
		sessao.setAttribute("viaturas", viaturas);
		RequestDispatcher view;
		sessao.setAttribute("atendimentoAtual", at);
		request.setAttribute("mensagem", "Viatura despachada com sucesso");
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
		
		
		
}

