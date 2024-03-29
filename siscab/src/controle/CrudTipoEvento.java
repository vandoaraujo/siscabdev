package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.MovimentaViatura;
import modelo.MovimentaViaturaPK;
import modelo.Viatura;
import dao.AtendimentoDao;
import dao.MovimentaViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class CrudTipoEvento
 */
public class CrudTipoEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int registroEventoAtendimento;
	private int registroEventoViatura;
	private String tipoEventoDescricao;
       
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
		
		registroEventoAtendimento =Integer.parseInt(request.getParameter("registroEventoAtendimento"));
		registroEventoViatura = Integer.parseInt(request.getParameter("registroEventoViatura"));
		int operacao = Integer.parseInt(request.getParameter("operacaoARealizar"));
		tipoEventoDescricao = request.getParameter("registroEventoDescricao");
		
		if(operacao == 1){
				
		 	salvar(request,response);	
			
		}			
		else if(operacao == 2){
				
			//alterar(request, response);
		}
		    
		else{
				
			//deletar(request, response,registroVitima);
		}
		    
		}
		
		protected void salvar (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
				
			Atendimento at = AtendimentoDao.getInstance().BuscaAtendimentoId(registroEventoAtendimento);
			
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
			despacha(request, response,"salvar", tipoEvento.getMovimentaviatura_tipoevento(),at.getId());
			
		
				
			
		}
		
		/*
		 * Recebe como parametro HttpRequest, response, a acao a ser executada e o nome do objeto
		 */
		private void despacha(HttpServletRequest request,
				HttpServletResponse response, String string, String nomeEvento,int idAtendimento) {
			
		    RequestDispatcher view;
		    request.setAttribute("nomeEvento", nomeEvento);
		    request.setAttribute("idAtendimento", idAtendimento);
		    if(string.equals("salvar")){
			
			request.setAttribute("mensagem", "Movimentação registrada no sistema.");
			
		    }
    				
		    else if(string.equals("alterar")){
			request.setAttribute("mensagem", "alterado com sucesso!!");

		    }
		    else{
			request.setAttribute("mensagem", "deletado com sucesso!!");
		    }
		    
		    view = request.getRequestDispatcher("/mensagemEventoViatura.jsp");
				
    			
				
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

		/*private void deletar(HttpServletRequest request,
				HttpServletResponse response, int registroVitima) {
			
			VitimaAtendida vitima = VitimaAtendidaDao.getInstance().BuscaVitimaId(registroVitima);
			String nomeVitima = vitima.getNome();
			VitimaAtendidaDao.getInstance().deletar(vitima);
			despacha(request, response, "deletar", nomeVitima);
		}

		private void alterar(HttpServletRequest request,
				HttpServletResponse response, int registroVitima) {
			
			Atendimentos atendimento = AtendimentosDao.getInstance().BuscaAtendimentoId(atendimentoAtualId);
			
		
			VitimaAtendidaDao.getInstance().atualizar(vitima);
			despacha(request, response, "alterar", vitima.getNome());		
		}*/

}
