<%@ page import="java.util.*,modelo.TiposOcorrencia,modelo.OBM,modelo.Atendimentos,modelo.Usuario,modelo.NaturezaChamados,modelo.Municipio,dao.OBMDao,modelo.StatusAtendimento,dao.MunicipioDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dao.TiposOcorrenciaDao"%><html>
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
<script language="JavaScript1.2">mmLoadMenus();</script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr style="background-image:url(img/back_cabecalho.jpg); background-repeat:repeat-x;">
		<td style="background-image:url(img/cabecalho.jpg); background-repeat:no-repeat;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr style="height:139px;">
					<td align="right" style="padding-right:20px;">  	
					 		<img src="img/logo.png">	 	
					 </td>
				</tr>
				<tr>
					<td style="padding-left:20px;">						
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')" onMouseOut="MM_startTimeout();"><img src="img/bt_atendimento.gif" name="image1" width="109" height="17" border="0" id="image1"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="153" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<a href="#" onclick="window.close();"><img src="img/bt_sairsistema.gif" name="close" width="109" height="17" border="0" id="close"></a>
					</td>				
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
					<% Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");	%>		
					   Perfil: ${perfil}<br>
						
						
						<fieldset style="width:780px"><legend>&nbsp;Lista de Atendimentos Vinculadas a ${obm}&nbsp;</legend>

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

								
						<select name="tipoOcorrencia">
						<option selected> <%= atendimento.getTipoocorrencia().getTipoocorrencia_descricao() %>
						
							<% 
							List<TiposOcorrencia> tipos = TiposOcorrenciaDao.getInstance().listarTodosTiposOcorrencias();
							for(int i=0;i<tipos.size();i++){
								if(!atendimento.getTipoocorrencia().getTipoocorrencia_descricao().equals(tipos.get(i).getTipoocorrencia_descricao().toString())){
								out.print("<option>"+tipos.get(i).getTipoocorrencia_descricao().toString());
								}
		 				}
								out.println("</select>");
					%>	


						<br>	
							
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