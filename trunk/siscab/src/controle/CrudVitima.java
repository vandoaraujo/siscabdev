package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.VitimaAtendida;
import dao.AtendimentoDao;
import dao.VitimaAtendidaDao;

/**
 * Servlet implementation class CrudVitima
 */
public class CrudVitima extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String nome; 
	private int idade;
	private String sexo;
	private String cor;
	private String situacaoVitima;
	private String hospitalDestino;
	private int registroVitima;
	private int atendimentoAtualId;
	private char sexoChar;
	private int corNumero;
	private int situacaoVitimaNumero;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudVitima() {
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
		

		nome = request.getParameter("nome");
		idade =Integer.parseInt(request.getParameter("idade"));
		sexo = request.getParameter("sexo");
		cor = request.getParameter("cor");
		situacaoVitima = request.getParameter("situacaoVitima");
		hospitalDestino = request.getParameter("hospital");
		registroVitima = Integer.parseInt(request.getParameter("registroVitima"));
		int operacao = Integer.parseInt(request.getParameter("operacaoARealizar"));
		atendimentoAtualId = Integer.parseInt(request.getParameter("atendimentoAtual"));
		
		if(operacao == 1){
				
		 	salvar(request,response);	
			
		}			
		else if(operacao == 2){
				
			alterar(request, response, registroVitima);
		}
		    
		else{
				
			deletar(request, response,registroVitima);
		}
		    
		    
						
		}
		
		

		protected void salvar (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
				
					
				Atendimento atendimento = AtendimentoDao.getInstance().BuscaAtendimentoId(atendimentoAtualId);
					
				VitimaAtendida vitima = new VitimaAtendida();
				vitima.setAtendimento(atendimento);
				vitima.setNome(nome);
				vitima.setIdade(idade);
				
				if(sexo.equals("F")){
					sexoChar = 'F';
				}
				else{
					sexoChar = 'M';
				}
				
				//1 - branca, 2 - parda, 3- amarela, 4 - negra
				if(cor.equals("branca")){
					corNumero = 1;
				}
				else if(cor.equals("parda")){
					corNumero = 2;
				}
				else if(cor.equals("amarela")){
					corNumero = 3;
				}
				else if(cor.equals("negra")){
					corNumero = 4;
				}
				
				if(situacaoVitima.equals("1 - Recusou Atendimento")){
					situacaoVitimaNumero = 1;
				}
				else if(situacaoVitima.equals("2 - Entregue ao hospital")){
					situacaoVitimaNumero = 2;
				}
				else if(situacaoVitima.equals("3 - Permaneceu no local após ser atendida")){
					situacaoVitimaNumero = 3;
				}
				else if(situacaoVitima.equals("4 - Encaminhada ao suporte aeromédico")){
					situacaoVitimaNumero = 4;
				}
				else if(situacaoVitima.equals("5 - Óbito no local")){
					situacaoVitimaNumero = 5;
				}
			
				vitima.setCor(corNumero);
				vitima.setSexo(sexoChar);
				vitima.setVitima_situacao(situacaoVitimaNumero);
				vitima.setHospitaldestino(hospitalDestino);
				
				VitimaAtendidaDao.getInstance().salvar(vitima);
				request.setAttribute("vitima", vitima);
				despacha(request, response,"salvar", vitima.getNome());
				
		
				
			
		}
		
		/*
		 * Recebe como parametro HttpRequest, response, a acao a ser executada e o nome do objeto
		 */
		private void despacha(HttpServletRequest request,
				HttpServletResponse response, String string, String nomeVitima) {
			
				RequestDispatcher view;
				request.setAttribute("nomeVitima", nomeVitima);
				if(string.equals("salvar")){
					
					request.setAttribute("mensagem", "salva com sucesso!!");
					
				}
				
				else if(string.equals("alterar")){
					request.setAttribute("mensagem", "alterado com sucesso!!");

				}
				else{
					request.setAttribute("mensagem", "deletado com sucesso!!");
				}
				
				view = request.getRequestDispatcher("/mensagemVitima.jsp");
				
			
				
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

		private void deletar(HttpServletRequest request,
				HttpServletResponse response, int registroVitima) {
			
			VitimaAtendida vitima = VitimaAtendidaDao.getInstance().BuscaVitimaId(registroVitima);
			String nomeVitima = vitima.getNome();
			VitimaAtendidaDao.getInstance().deletar(vitima);
			despacha(request, response, "deletar", nomeVitima);
		}

		private void alterar(HttpServletRequest request,
				HttpServletResponse response, int registroVitima) {
			
			Atendimento atendimento = AtendimentoDao.getInstance().BuscaAtendimentoId(atendimentoAtualId);
			
			VitimaAtendida vitima = VitimaAtendidaDao.getInstance().BuscaVitimaId(registroVitima);
			vitima.setAtendimento(atendimento);
			vitima.setNome(nome);
			vitima.setIdade(idade);
			
			if(sexo.equals("F")){
				sexoChar = 'F';
			}
			else{
				sexoChar = 'M';
			}
			
			//1 - branca, 2 - parda, 3- amarela, 4 - negra
			if(cor.equals("branca")){
				corNumero = 1;
			}
			else if(cor.equals("parda")){
				corNumero = 2;
			}
			else if(cor.equals("amarela")){
				corNumero = 3;
			}
			else if(cor.equals("negra")){
				corNumero = 4;
			}
			
			if(situacaoVitima.equals("1 - Recusou Atendimento")){
				situacaoVitimaNumero = 1;
			}
			else if(situacaoVitima.equals("2 - Entregue ao hospital")){
				situacaoVitimaNumero = 2;
			}
			else if(situacaoVitima.equals("3 - Permaneceu no local após ser atendida")){
				situacaoVitimaNumero = 3;
			}
			else if(situacaoVitima.equals("4 - Encaminhada ao suporte aeromédico")){
				situacaoVitimaNumero = 4;
			}
			else if(situacaoVitima.equals("5 - Óbito no local")){
				situacaoVitimaNumero = 5;
			}

			vitima.setCor(corNumero);
			vitima.setSexo(sexoChar);
			vitima.setVitima_situacao(situacaoVitimaNumero);
			vitima.setHospitaldestino(hospitalDestino);
			VitimaAtendidaDao.getInstance().atualizar(vitima);
			despacha(request, response, "alterar", vitima.getNome());		
		}
	
	
}
