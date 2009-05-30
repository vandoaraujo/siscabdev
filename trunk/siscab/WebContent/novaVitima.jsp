<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.VitimaAtendida,dao.VitimaAtendidaDao" %>
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
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="109" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<form action="Logoff" onsubmit="fechar()" style="display:inline"><input type="image" src="img/bt_sairsistema.gif" name="close"></form>										
					</td>
					<td align="right" style="padding-right:20px;">
						<strong>Usuário:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
						<fieldset style="width:450px"><legend>&nbsp;Incluir Vitima&nbsp;</legend>
							
							<form action="CrudVitima" method="post">
								
								<table border="0" cellpadding="0" cellspacing="3" width="100%">
								<tr>
									<td><label>Nome:</label></td>
									<td colspan="8"><input name="nome" type="text" size=40/></td>
								</tr>
								<tr>	
									<td><label>Idade:</label></td>
									<td colspan="8"><input name="idade" type="text" size=3 maxlength="3"/></td>
								</tr>
								<tr>	
									<td><label>Sexo:</label></td>
									<td width="10px"><input name="sexo" type="radio" value="F" checked/></td>
									<td>F</td>
									<td width="10px"><input name="sexo" type="radio" value="M"/></td>
									<td colspan="5">M</td>
									
								</tr>
								<tr>	
									<td><label>Cor:</label></td>
									<td width="10px"><input name="cor" type="radio" value="branca" checked/></td>
									<td>Branca</td>
									
									<td width="10px"><input name="cor" type="radio" value="parda"/></td>
									<td>Parda</td>
									
									<td width="10px"><input name="cor" type="radio" value="amarela"/></td>
									<td>Amarela</td>
									
									<td width="10px"><input name="cor" type="radio" value="negra"/></td>
									<td>Negra</td>									
								</tr>
								<tr>	
									<td><label>Situacao Vitima:</label></td>
									<td colspan="8"><select name="situacaoVitima">
									<option>1 - Recusou Atendimento</option>
									<option>2 - Entregue ao hospital</option>
									<option>3 - Permaneceu no local após ser atendida</option>
									<option>4 - Encaminhada ao suporte aeromédico</option>
									<option>5 - Óbito no local</option>
									</select>
								</td>	
								</tr>
								<tr>	
									<td><label>Hospital Destino:</label></td>
									<td colspan="8"><input name="hospital" type="text" size=40/></td>
								</tr>
								<tr>
									<td colspan="9">
										<input type="submit" value="Incluir" onclick="this.form.operacaoARealizar.value=1"/>
										<input type="hidden" name="operacaoARealizar" value ="">
								 		<input type="hidden" name="registroVitima" value ="1">
								 		<input type="hidden" name="atendimentoAtual" value="${atendimentoAtual.id}"> 
								 		</form>
								 		<form action="paginaPrincipal.jsp" method="post" style="display:inline;">
							 				<input type="submit" value="Voltar"/>
							 			</form>							 										
									</td>								
								</tr>
								<tr>																																							
						</fieldset>	
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>




</body>
</html>