<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.Usuario,modelo.Atendimentos" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
</head>
<body>


<%! Atendimentos atendimento=null; %>

<% atendimento = (Atendimentos) request.getSession().getAttribute("atendimentoAtual"); 
request.getSession().setAttribute("atendimentoAtual", atendimento);
%>


<form action="AplicaAcaoAtendimentoDiversasOpcoes" method="post">


Atendimento:

Municipio: <%= atendimento.getMunicipio_id().getMunicipio_nome() %><br>

Bairro: <%= atendimento.getBairro() %><br>

Logradouro: <%= atendimento.getLogradouro() %><br>


<input type="submit" value="Editar" onclick="this.form.operacaoARealizar.value=2"/>
<input type="submit" value="Sugestão de Rota" onclick="this.form.operacaoARealizar.value=3"/>
<input type="submit" value="Viaturas Empenhadas" onclick="this.form.operacaoARealizar.value=4"/>
<input type="submit" value="Vitimas" onclick="this.form.operacaoARealizar.value=5"/>
<input type="submit" value="Servicos Executados" onclick="this.form.operacaoARealizar.value=6"/>							
<input type="hidden" name="operacaoARealizar" value ="">
<input type="hidden" name="registroAtendimento" value = <%= atendimento.getAtendimento_numero() %>>

</form>
</body>
</html>