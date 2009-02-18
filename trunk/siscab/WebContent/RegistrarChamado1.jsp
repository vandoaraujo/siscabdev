<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.NaturezaChamados" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  	
Cadastro de Chamados<br>



<form action="registraChamado" method="post">
<table>
<tr>Usuário: <input name="usuario" type="text" readonly="readonly" value=${u.nomeGuerra}></tr>
<tr>Obm do Usuário: <input name="obmUsuario" type="text" readonly="readonly" value=${u.obm.nome}></tr>
<tr>Data registrada: <input name="dataRegistrada" type="text" readonly="readonly" value=${data}></tr>
<tr>Hora registrada: <input name="horaRegistrada" type="text" readonly="readonly" value=${hora}></tr>
<tr>Natureza Chamado: <input name="obmUsuario" type="text" readonly="readonly" value=${u.obm.nome}></tr>
  	  	  	
<tr>NaturezaChamado: <select name="naturezaChamado">
	
	<!-- Popula a combo que aparecerá na tela -->
	<%
		 
 	ArrayList<NaturezaChamados> nChamados = (ArrayList)request.getAttribute("nChamados");
	
	for(int i=0;i<nChamados.size();i++){
	 out.println("<option>"+nChamados.get(i));
 	}
	out.println("</select>");
	%></tr>

<tr>Origem: <select name="origem">
	<option>Telefone 
	<option>Pessoalmente
	</select></tr>		

	<tr>Nome Solicitante: <input name="nomeSolicitante" type="text" ></tr>
	<tr>Telefone Solicitante: <input name="telefoneSolicitante" type="text"></tr>
	<tr>Num aproximado vítimas: <input name="numAproximadoVitimas" type="text"></tr>
	<tr>Info Complementares: <textArea NAME="infoComplementares" COLS=30 ROWS=4></textArea></tr>
	<input type="submit" value="Registrar" onclick="this.form.operacaoARealizar.value=1"/>
	<input type="submit" value="Procurar Ocorrências Próximas" onclick="this.form.operacaoARealizar.value=2"/>
	<input type="hidden" name="operacaoARealizar" value ="">
	 <input type="hidden" name="registroOcorrencia" value ="1"> 
	</table>
</form>

</body>
</html>