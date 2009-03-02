<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.TiposOcorrencia,modelo.OBM,modelo.Usuario,modelo.NaturezaChamados,modelo.Municipio,dao.OBMDao,modelo.StatusAtendimento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
<script>

function habilitaCombo(priID, prstHabilita){
    if (prstHabilita == 's')
        document.getElementById(priID).disabled = false;
    else
        document.getElementById(priID).disabled = true;
}

</script>
<head> 
</head>
<body>

Obs: O presente Chamado possui uma Natureza de Solicitação de Socorro.<br>

Tela de Tranferência de Atendimento de Obms<br>


<%! String municipio=null; %>

<% municipio = (String)request.getAttribute("municipio"); %>

<form action="EfetivaAtendimento" method="post">



<tr>Numero gerado: <input name="idAtendimento" type="text" readonly="readonly" value=${idNumeroAtendimento}></tr><br>
<tr>OBM a Prestar Atendimento: <input name="obmAtendimento" type="text" readonly="readonly" value=${obmAtendimento}></tr><br>
<tr>Bairro: <input name="bairro" type="text" readonly="readonly" value=<%= request.getAttribute("bairro") %>> </tr><br>
<tr>Municipio <select name="municipio">
<option><%= municipio %></option>
</select>
	
	<font color="blue">Você deseja repassar o atendimento da OBM Atual? </font>	Sim: <input name="radiobutton" type="radio" value="sim" onClick="habilitaCombo('seleciona','s');">
	Não: <input name="radiobutton" type="radio" value="nao" checked onClick="habilitaCombo('seleciona','n');">
		
	<select name="obmRepassaAtendimento" id="seleciona" disabled="disabled">
	<!-- Popula a combo que aparecerá na tela -->
	<%
	  List<OBM> obms = OBMDao.getInstance().listarTodasOBMs();
	  for(OBM s: obms){
		 out.println("<option>"+s.getNome());
	  }
	out.println("</select>");
	%><br>

	<tr>Coord X: <input name="coordX" type="text" ></tr><br>
	<tr>Coord Y: <input name="coordY" type="text"></tr><br>
	<tr>Logradouro: <input name="logradouro" type="text" size=30><p><br>
	<tr>Num. Complemento: <input name="numComplemento" type="text" size=10></tr><br>
		
	<br>Tipo da Ocorrência:
	<select name="tipoOcorrencia">
		 <% 
	TiposOcorrencia tipo = new TiposOcorrencia();
	ArrayList<String> nTiposOcorrencia = tipo.getAr();
	for(int i=0;i<nTiposOcorrencia.size();i++){
	out.print("<option>"+nTiposOcorrencia.get(i));
	 	}
	out.println("</select>");
		%></tr><br>	
	
	Status do Atendimento:<br>	
	<select name="status">
	<option> Pendente
    </select><br>
			
	<input type="submit" value="Registrar Atendimento">
	
</form>

</body>
</html>