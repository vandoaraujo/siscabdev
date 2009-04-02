package controle;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Chamado;

import org.hibernate.Session;

import dao.ChamadoDao;
import dao.HibernateUtil;

/**
 * Servlet implementation class TotalChamados
 */
public class TotalChamados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalChamados() {
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
		
		/*Fazer uma query buscando um chamado com tais naturezas de atendimento
		para a estatistica...buscando por estes id`s
		
		1, 'Solicitação de socorro'
		2, 'Trote'
		3, 'Engano/desistência'
		4, 'Queda de ligação'
		5, 'Orientação'
		6, 'Informação sobre atendimento'*/			
		
		List <Chamado> chamados = ChamadoDao.getInstance().listarTodosChamados();
		
		request.setAttribute("listaChamados", chamados);
		
	
		/*Session session = HibernateUtil.getInstance().AbreUmaSession();
		Connection c = session.connection();
		
		String[] vlParametro = request.getParameterValues("nome");
        
        
        // gera o relatório
        ServletContext context = getServletContext();
        byte[] bytes = null;
        try {
            
            
            // carrega os arquivos jasper
            JasperReport relatorioJasper = (JasperReport)JRLoader.loadObject(
                    context.getRealPath("/WEB-INF/reports/Cadastro.jasper"));
            
            // parâmetros, se houverem
            Map parametros = new HashMap();
            parametros.put("nr_op", vlParametro[0]);
            
            // direciona a saída do relatório para um stream
            bytes = JasperRunManager.runReportToPdf(relatorioJasper,parametros,"PASSAR BANCO DE DADOS OU UM SUBCONJUTNO DE UMA QUERY");
            
        } catch (JRException e) {
            e.printStackTrace();
        }
        if (bytes != null && bytes.length > 0) {
            // envia o relatório em formato PDF para o browser
            response.setContentType("application/pdf");
            
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();
        }
        
    }*/
 
}
	
}

		



