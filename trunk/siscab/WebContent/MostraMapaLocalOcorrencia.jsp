<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Chamado" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4> Configurar esta tela pra visualizar um mapa do Google Maps</h4><br>



	<form action="FinalizarChamadoIniciarAtendimento" method="post">
		<table>
		<tr><td>Origem do Chamado: <input name="origemChamado" type="text" readonly="readonly" value=${origemChamado}></td></tr>
		<tr><td>Nome do Solicitante: <input name="nomeSolicitante" type="text" readonly="readonly" value=${nomeSolicitante}></td></tr>
		<tr><td>Telefone: <input name="telefone" type="text" readonly="readonly" value=${telefone}></td></tr>
		<tr><td>Número Aproximado de Vitimas: <input name="numAproxVitimas" type="text" readonly="readonly" value=${aproxVitimas}></td></tr>
		<tr><td>Obm Solicitada: <input name="obmSolicitada" type="text" readonly="readonly" value=${nomeObmUsuario}></td></tr>
		<tr><td> <input name="infoComplementares" type="hidden" readonly="readonly" value=${infoComplementares}></td></tr>
		<tr><td> <input name="numeroChamado" type="hidden" readonly="readonly" value=${numeroChamado}></td></tr>
	

		</table>
		
				
		
		<tr>NaturezaChamado: <select name="naturezaChamado">
			
			<!-- Popula a combo de natureza de Chamados que aparecerá na tela Verificar após o clique do botao-->
			<%
				 
		 	ArrayList<String> nChamados = (ArrayList)request.getAttribute("naturezaChamados");
			for(int i=0;i<nChamados.size();i++){
			 out.print("<option>"+nChamados.get(i));
		 	}
			out.println("</select>");
			%></tr>
			
			
			<input type="submit" value="Finalizar Chamado" onclick="this.form.operacaoARealizar.value=1" >
			<input type="hidden" name="operacaoARealizar" value ="">
			<input type="hidden" name="registroOcorrencia" value ="1"> 
			</form>

</body>
</html>