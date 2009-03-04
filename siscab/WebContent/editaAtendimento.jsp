<%@ page import="java.util.*,modelo.TiposOcorrencia,modelo.OBM,modelo.Atendimentos,modelo.Usuario,modelo.NaturezaChamados,modelo.Municipio,dao.OBMDao,modelo.StatusAtendimento,dao.MunicipioDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
</head>
<body>

Obs: O presente Chamado possui uma Natureza de Solicitação de Socorro.<br>

Tela de Tranferência de Atendimento de Obms<br>


<%! Atendimentos atendimento=null; String logradouro=null; %>

<% atendimento = (Atendimentos)request.getSession().getAttribute("atendimentoAtual"); %>

<% logradouro =  atendimento.getLogradouro(); %>

<%= logradouro %>

<form action="AlteraAtendimento" method="post">

Informacoes do Atendimento<br>

Obm Atual do Atendimento: <select name="obmAtendimento">
<option selected><%=atendimento.getObm_id().getNome() %></option>
	<!-- Popula a combo que aparecerá na tela -->
	<%
	  List<OBM> obms = OBMDao.getInstance().listarTodasOBMs();
	  for(OBM s: obms){
		 out.println("<option>"+s.getNome()+ "</option>");
	  }
	out.println("</select>");
	%><br>

Bairro: <input name="bairro" type="text" size =45 value="<%= atendimento.getBairro() %>"><br>

Município: <select name="municipio">
	<option selected><%=atendimento.getMunicipio_id().getMunicipio_nome() %></option>
	
	<!-- Popula a combo de municipios que aparecerá na tela -->
	<%
    
	 List<Municipio> municipio = MunicipioDao.getInstance().listarTodosMunicipios();
 	 for(Municipio m: municipio){
	 out.println("<option>"+m.getMunicipio_nome());
 	}
	out.println("</select>");
	%><br>

Coord X: <input name="coordX" type="text" value=<%= atendimento.getCoordx() %>><br>
Coord Y: <input name="coordY" type="text" value=<%= atendimento.getCoordy() %>><br>
Logradouro: <INPUT TYPE=TEXT NAME="logradouro" SIZE=30 MAXLENGHT=40 value= "<%= logradouro %>"><br>
Num. Complemento: <input name="numComplemento" type="text" size=10 value=<%= atendimento.getNumcompl() %>><br>

Tipo da Ocorrência:

atendimento
	<select name="tipoOcorrencia">
	<option selected> <%=atendimento.getTipoocorrencia() %></option>
	<% 
	TiposOcorrencia tipo = new TiposOcorrencia();
	ArrayList<String> nTiposOcorrencia = tipo.getAr();
	for(int i=0;i<nTiposOcorrencia.size();i++){
	out.print("<option>"+nTiposOcorrencia.get(i));
	 	}
	out.println("</select>");
	%><br>	
	
	Status do Atendimento:	
	<select name="status">
	<option> <%= atendimento.getStatus_atendimento() %></option>
	<option>Em andamento</option>
	<option>Finalizado</option>
    </select><br>
		
	
	<input type="submit" value="Alterar" onclick="this.form.operacaoARealizar.value=2"/>
	<input type="submit" value="Deletar" onclick="this.form.operacaoARealizar.value=3"/>							
	<input type="hidden" name="operacaoARealizar" value ="">
	<input type="hidden" name="registroAtendimento" value = <%= atendimento.getId() %> >
</form>
														
	<form action="AcompanharAtendimentos" method="post" style="display:inline;">
			 	<input type="submit" value="Voltar"/>
 	</form>	

</body>
</html>