package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.VitimaAtendida;

import org.apache.log4j.Logger;

import dao.AtendimentoDao;
import dao.VitimaAtendidaDao;

/**
 * Servlet implementation class CrudVitima
 */
public class CrudVitima extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int idade=0;
    private String sexo=null;
    private String cor=null;
    private String situacaoVitima= null;
    private String hospitalDestino= null;
    private int registroVitima= 0;
    private int atendimentoAtualId;
    private char sexoChar;
    private int corNumero;
    private int situacaoVitimaNumero;

    static Logger logger = Logger.getLogger(CrudVitima.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrudVitima() {
	super();
	// TODO Auto-generated constructor stub
    }

    private void alterar(HttpServletRequest request,
	    HttpServletResponse response, int registroVitima) {

	Atendimento atendimento = AtendimentoDao.getInstance()
		.BuscaAtendimentoId(atendimentoAtualId);

	VitimaAtendida vitima = VitimaAtendidaDao.getInstance().BuscaVitimaId(
		registroVitima);
	vitima.setAtendimento(atendimento);
	vitima.setNome(nome);
	
	if(idade > 150){
	    despacha(request, response, "erro", vitima.getNome());
	}
	else{

	vitima.setIdade(idade);

	if (sexo.equals("F")) {
	    sexoChar = 'F';
	} else {
	    sexoChar = 'M';
	}

	// 1 - branca, 2 - parda, 3- amarela, 4 - negra
	if (cor.equals("branca")) {
	    corNumero = 1;
	} else if (cor.equals("parda")) {
	    corNumero = 2;
	} else if (cor.equals("amarela")) {
	    corNumero = 3;
	} else if (cor.equals("negra")) {
	    corNumero = 4;
	}

	if (situacaoVitima.equals("1 - Recusou Atendimento")) {
	    situacaoVitimaNumero = 1;
	} else if (situacaoVitima.equals("2 - Entregue ao hospital")) {
	    situacaoVitimaNumero = 2;
	} else if (situacaoVitima
		.equals("3 - Permaneceu no local após ser atendida")) {
	    situacaoVitimaNumero = 3;
	} else if (situacaoVitima
		.equals("4 - Encaminhada ao suporte aeromédico")) {
	    situacaoVitimaNumero = 4;
	} else if (situacaoVitima.equals("5 - Óbito no local")) {
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

    private void deletar(HttpServletRequest request,
	    HttpServletResponse response, int registroVitima) {

	VitimaAtendida vitima = VitimaAtendidaDao.getInstance().BuscaVitimaId(
		registroVitima);
	String nomeVitima = vitima.getNome();
	VitimaAtendidaDao.getInstance().deletar(vitima);
	despacha(request, response, "deletar", nomeVitima);
    }

    /*
     * Recebe como parametro HttpRequest, response, a acao a ser executada e o
     * nome do objeto
     */
    private void despacha(HttpServletRequest request,
	    HttpServletResponse response, String string, String nomeVitima) {

	RequestDispatcher view;
	request.setAttribute("nomeVitima", nomeVitima);
	if (string.equals("salvar")) {

	    request.setAttribute("mensagem", "Vítima foi incluída na lista.");

	}

	else if (string.equals("erro")) {
	    request.setAttribute("mensagem", "Idade inválida!!");

	}
	else if (string.equals("alterar")) {
	    request.setAttribute("mensagem", "Dados da vítima foram alterados.");

	} 
	else {
	    request.setAttribute("mensagem", "Vítima foi excluída da lista.");
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
	registroVitima = Integer.parseInt(request
		.getParameter("registroVitima"));
	int operacao = Integer.parseInt(request
		.getParameter("operacaoARealizar"));

	
	//Se for diferente da acao de deletar recebe os parâmetros necessários
	if(operacao != 3){
	    
	    atendimentoAtualId = Integer.parseInt(request
			.getParameter("atendimentoAtual"));
	    nome = request.getParameter("nome");
	    sexo = request.getParameter("sexo");
	    cor = request.getParameter("cor");
	    situacaoVitima = request.getParameter("situacaoVitima");
	    hospitalDestino = request.getParameter("hospital");
	    
	    if(request.getParameter("idade").equals("")){
		    idade = 0;
		    logger.info("Idade não preenchida - Atribui zero a idade");
	    }
	    else{
		    idade = Integer.parseInt(request.getParameter("idade"));
	    }
		    
	    if (operacao == 1) {	
		salvar(request, response);
	    } else {	
		alterar(request, response, registroVitima);
	    }

	    
	}
	
	else{
	    deletar(request, response, registroVitima);
	}

    }

    protected void salvar(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	Atendimento atendimento = AtendimentoDao.getInstance()
		.BuscaAtendimentoId(atendimentoAtualId);

	VitimaAtendida vitima = new VitimaAtendida();
	vitima.setAtendimento(atendimento);
	vitima.setNome(nome);
	vitima.setIdade(idade);
	
	if(vitima.getIdade() > 150){
	    despacha(request, response, "erro", vitima.getNome());
	}
	
	else {

	if (sexo.equals("F")) {
	    sexoChar = 'F';
	} else {
	    sexoChar = 'M';
	}

	// 1 - branca, 2 - parda, 3- amarela, 4 - negra
	if (cor.equals("branca")) {
	    corNumero = 1;
	} else if (cor.equals("parda")) {
	    corNumero = 2;
	} else if (cor.equals("amarela")) {
	    corNumero = 3;
	} else if (cor.equals("negra")) {
	    corNumero = 4;
	}

	if (situacaoVitima.equals("1 - Recusou Atendimento")) {
	    situacaoVitimaNumero = 1;
	} else if (situacaoVitima.equals("2 - Entregue ao hospital")) {
	    situacaoVitimaNumero = 2;
	} else if (situacaoVitima
		.equals("3 - Permaneceu no local após ser atendida")) {
	    situacaoVitimaNumero = 3;
	} else if (situacaoVitima
		.equals("4 - Encaminhada ao suporte aeromédico")) {
	    situacaoVitimaNumero = 4;
	} else if (situacaoVitima.equals("5 - Óbito no local")) {
	    situacaoVitimaNumero = 5;
	}

	vitima.setCor(corNumero);
	vitima.setSexo(sexoChar);
	vitima.setVitima_situacao(situacaoVitimaNumero);
	vitima.setHospitaldestino(hospitalDestino);

	VitimaAtendidaDao.getInstance().salvar(vitima);
	request.setAttribute("vitima", vitima);
	despacha(request, response, "salvar", vitima.getNome());
	
	}

    }

}