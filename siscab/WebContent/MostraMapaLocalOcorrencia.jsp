<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario" %>
<%@ page import="java.util.ArrayList" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4> Configurar esta tela pra visualizar um mapa do Google Maps</h4><br>

		<form action="" method="post">
		
		
		
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
			<input type="submit" value="Procurar Ocorrências Próximas" onclick="this.form.operacaoARealizar.value=2"/>
			<input type="hidden" name="operacaoARealizar" value ="">
			<input type="hidden" name="registroOcorrencia" value ="1"> 
			</form>

</body>
</html>