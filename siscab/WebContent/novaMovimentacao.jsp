<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Municipio,dao.MunicipioDao,modelo.TipoEventoViatura" %>
<%@ page import="java.util.List" %>
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
						<strong>Usu�rio:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
					
					<h2>Atendimentos :: Acompanhar Atendimento :: Ficha do Atendimento :: Viaturas Empenhadas :: Registrar Movimenta��o</h2>
					
					
					N�mero atendimento: <strong>${atendimento}</strong>
					<p></p>
					
						<fieldset style="width:450px"><legend>&nbsp;Nova movimenta��o&nbsp;</legend>
							
							<form action="CrudTipoEvento" method="post">
								
								<table border="0" cellpadding="0" cellspacing="3" width="100%">								
								<tr>
									<td><label>Tipo/N�mero:</label></td>
									<td>${tipoViatura}-${numeroViatura}</td>
								</tr>
								<tr>	
									<td><label>Tipo de evento:</label></td>
									<td><select name="registroEventoDescricao">
										<!-- Popula a combo que aparecer� na tela -->
										<%
										 List<String> t = (ArrayList)request.getAttribute("tipoEvento");	
										 //TipoEventoViatura t = new TipoEventoViatura();
									 	 for(int i=0;i<t.size();i++){
										 String tipo = t.get(i).toString();										 	 		 
										 out.println("<option>"+tipo);
									 	}
										out.println("</select>");
										%>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<input name="registroEventoAtendimento" type="hidden" size=10  readonly="readonly" value="${atendimentoAtual}"/>
										<input name="registroEventoViatura" type="hidden" size=10 readonly="readonly" value="${viaturaAtual}"/>
										<input type="submit" value="Salvar" onclick="this.form.operacaoARealizar.value=1"/>
										<input type="hidden" name="operacaoARealizar" value ="">
										<input type="button" value="Cancelar" onclick="history.back()">										
								 	</td>								
								</tr>
								</table>
							</form>																																						
						</fieldset>	
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>


</body>
</html>

