package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimentos;
import modelo.MovimentaViatura;
import modelo.MovimentaViaturaPK;
import modelo.Viatura;
import modelo.VitimaAtendida;
import dao.AtendimentosDao;
import dao.MovimentaViaturaDao;
import dao.ViaturaDao;
import dao.VitimaAtendidaDao;

/**
 * Servlet implementation class CrudTipoEvento
 */
public class CrudTipoEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int registroEventoAtendimento;
	private int registroEventoViatura;
	private String tipoEventoDescricao;
	private String registroNumeroViatura;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudTipoEvento() {
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
		
		registroNumeroViatura = request.getParameter("numeroViatura");
		registroEventoAtendimento =Integer.parseInt(request.getParameter("registroEventoAtendimento"));
		registroEventoViatura = Integer.parseInt(request.getParameter("registroEventoViatura"));
		int operacao = Integer.parseInt(request.getParameter("operacaoARealizar"));
		tipoEventoDescricao = request.getParameter("tipoEventoDescricao");
		
				
		if(operacao == 1){
				
		 	salvar(request,response);	
			
		}			
		else if(operacao == 2){
				
			alterar(request, response);
		}
		    
		else{
				
			deletar(request, response,registroEventoViatura, registroEventoAtendimento);
			
			// Analisar se a viatura mudará seu status após a ultima deleção de tipo EVENTO
		}
		    
		    
						
		}
		
		

		private void deletar(HttpServletRequest request,
			HttpServletResponse response, int registroEventoViatura2,
			int registroEventoAtendimento2) {
		
			Atendimentos at = AtendimentosDao.getInstance().BuscaAtendimentoId(registroEventoAtendimento);
			
			Viatura v = ViaturaDao.getInstance().BuscaViaturaId(registroEventoViatura);
						
			//Define relacionamento
			MovimentaViatura tipoEvento = MovimentaViaturaDao.getInstance().listaUmTipoEventoDaViatura(at.getId(), v.getId());
			String evento = tipoEvento.getMovimentaviatura_tipoevento();
					
			MovimentaViaturaDao.getInstance().deletar(tipoEvento);
			request.setAttribute("movimentaViatura", tipoEvento);
			despacha(request, response,"deletar", evento);
		
	}

		private void alterar(HttpServletRequest request,
			HttpServletResponse response) {
		
					
			Atendimentos at = AtendimentosDao.getInstance().BuscaAtendimentoId(registroEventoAtendimento);
			
			Viatura v = ViaturaDao.getInstance().BuscaViaturaId(registroEventoViatura);
						
			//Define relacionamento
			MovimentaViatura tipoEvento = MovimentaViaturaDao.getInstance().listaUmTipoEventoDaViatura(at.getId(), v.getId());
			tipoEvento.setMovimentaviatura_tipoevento(tipoEventoDescricao);
			
					
			MovimentaViaturaDao.getInstance().atualizar(tipoEvento);
			request.setAttribute("movimentaViatura", tipoEvento);
			despacha(request, response,"alterar", tipoEvento.getMovimentaviatura_tipoevento());
			
			
	}

		protected void salvar (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
				
					
			Atendimentos at = AtendimentosDao.getInstance().BuscaAtendimentoId(registroEventoAtendimento);
			
			Viatura v = ViaturaDao.getInstance().BuscaViaturaId(registroEventoViatura);
			
			MovimentaViaturaPK mov = new MovimentaViaturaPK();
			mov.setAtendimentos(at);
			mov.setViatura(v);
			
			//Define relacionamento
			MovimentaViatura tipoEvento = new MovimentaViatura();
			tipoEvento.setChaveComposta(mov);
			tipoEvento.setMovimentaviatura_tipoevento(tipoEventoDescricao);
			
			//ver
			
			GregorianCalendar calendar =  new GregorianCalendar();
			calendar.add(GregorianCalendar.MONTH, 0);
			calendar.add(GregorianCalendar.HOUR_OF_DAY, 0);
			calendar.add(GregorianCalendar.MINUTE, 0);
			DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Date data = new Date(formata.format(calendar.getTime()));
						
			tipoEvento.setMovimentaviatura_horaEvento(data);
			
			
			MovimentaViaturaDao.getInstance().salvar(tipoEvento);
			request.setAttribute("movimentaViatura", tipoEvento);
			despacha(request, response,"salvar", tipoEvento.getMovimentaviatura_tipoevento());
			
		
				
			
		}
		
		/*
		 * Recebe como parametro HttpRequest, response, a acao a ser executada e o nome do objeto
		 */
		private void despacha(HttpServletRequest request,
				HttpServletResponse response, String string, String nomeEvento) {
			
				RequestDispatcher view;
				request.setAttribute("nomeEvento", nomeEvento);
				if(string.equals("salvar")){
					
					request.setAttribute("mensagem", "salvo com sucesso!!");
					
				}
				
				else if(string.equals("alterar")){
					
					request.setAttribute("mensagem", "alterado com sucesso!!");

				}
				else{
					request.setAttribute("mensagem", "deletado com sucesso!!");
				}
				
				view = request.getRequestDispatcher("/mensagemTipoEvento.jsp");
				
			
				
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
