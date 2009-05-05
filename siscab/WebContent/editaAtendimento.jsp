<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.TipoOcorrencia,modelo.OBM,modelo.Atendimento,modelo.Usuario,modelo.NaturezaChamado,modelo.Municipio,dao.OBMDao,modelo.StatusAtendimento,dao.MunicipioDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dao.TipoOcorrenciaDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
<script>
function campoObrigatorio()
{
	if ( (document.form.registro.value != null) && (document.form.registro.value != "") && (document.form.nomeGuerra.value != null) && (document.form.nomeGuerra.value != "") && (document.form.senha.value != null) && (document.form.senha.value != "") )
		return true;
	else
	{
		alert("Favor preencher todos campos obrigatórios");	
		return false;
	}

}
</script>
</head>
<body>
<script language="JavaScript1.2">mmLoadMenus();</script>
<%Usuario usu =( Usuario) request.getSession().getAttribute("usuario"); %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr style="background-image:url(img/back_cabecalho.jpg); background-repeat:repeat-x;">
		<td style="background-image:url(img/cabecalho.jpg); background-repeat:no-repeat;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr style="height:139px;">
					<td align="right" style="padding-right:20px;" colspan="2">  	
					 		<img src="img/logo.png">	 	
					 </td>
				</tr>
				<tr>
					<td style="padding-left:20px;">						
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')" onMouseOut="MM_startTimeout();"><img src="img/bt_atendimento.gif" name="image1" width="109" height="17" border="0" id="image1"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="153" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<form action="Logoff" onsubmit="fechar()" style="display:inline"><input type="image" src="img/bt_sairsistema.gif" name="close"></form>										
					</td>
					<td align="right" style="padding-right:20px;">
						<strong>Usuário:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
					
					<h2>Atendimentos :: Acompanhar Atendimento :: Ficha do Atendimento :: Editar dados do Atendimento</h2>
					
					
						<fieldset style="width:450px"><legend>&nbsp;Dados do Atendimento&nbsp;</legend>
						
						<%! Atendimento atendimento=null; String logradouro=null; %>
						
						<% atendimento = (Atendimento)request.getSession().getAttribute("atendimentoAtual"); %>
						
						<% logradouro =  atendimento.getLogradouro(); %>
						
						<form action="AlteraAtendimento" method="post">											
						
						<table>
							<tr>
								<td>Número Atendimento:</td>
								<td><input name="numeroAtendimento" type="text" size =10 readonly="readonly" value="<%= atendimento.getAtendimento_numero() %>"></td>
							</tr>
							<tr>
								<td>Tipo da Ocorrência:</td>
								<td>
								<select name="tipoOcorrencia">
									<option selected> <%= atendimento.getTipoocorrencia().getTipoocorrencia_descricao() %>
									
										<% 
										List<TipoOcorrencia> tipos = TipoOcorrenciaDao.getInstance().listarTodosTiposOcorrencias();
										for(int i=0;i<tipos.size();i++){
											if(!atendimento.getTipoocorrencia().getTipoocorrencia_descricao().equals(tipos.get(i).getTipoocorrencia_descricao().toString())){
											out.print("<option>"+tipos.get(i).getTipoocorrencia_descricao().toString());
											}
					 				}
											out.println("</select>");
								%>
								</td>
							</tr>
							<tr>
								<td>Município:</td>
								<td><select name="municipio">
									<option selected><%=atendimento.getMunicipio_id().getMunicipio_nome() %></option>
									
									<!-- Popula a combo de municipios que aparecerá na tela -->
									<%
								    
									 List<Municipio> municipio = MunicipioDao.getInstance().listarTodosMunicipios();
								 	 for(Municipio m: municipio){
									 out.println("<option>"+m.getMunicipio_nome());
								 	}
									out.println("</select>");
									%>
								</td>
							</tr>
							<tr>
								<td>Bairro:</td>
								<td><input name="bairro" type="text" size =45 value="<%= atendimento.getBairro() %>"></td>
							</tr>
							<tr>
								<td>Logradouro:</td>
								<td><INPUT TYPE=TEXT NAME="logradouro" SIZE=30 MAXLENGHT=40 value= "<%= logradouro %>"></td>
							</tr>
							<tr>
								<td>Num. Complemento:</td>
								<td><input name="numComplemento" type="text" size=10 value=<%= atendimento.getNumcompl() %>></td>
							</tr>							
							<tr>
								<td>Coord X:</td>
								<td><input name="coordX" type="text" value=<%= atendimento.getCoordx() %>></td>
							</tr>
							<tr>
								<td>Coord Y:</td>
								<td><input name="coordY" type="text" value=<%= atendimento.getCoordy() %>></td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="submit" value="Alterar" onclick="this.form.operacaoARealizar.value=2"/>							
									<input type="hidden" name="operacaoARealizar" value ="">
									<input type="hidden" name="registroAtendimento" value = <%= atendimento.getId() %> >
								</form>
									<form action="AcompanharAtendimentos" method="post" style="display:inline;">
							 			<input type="submit" value="Voltar"/>
				 					</form>
								
								</td>
							</tr>
						</table>			
					</fieldset>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>