<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Municipio" %>
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
					</td>				
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:350px"><legend>&nbsp;Editar de OBM&nbsp;</legend>
							<form action="CrudOBM" method="post">
							<table border="0" cellpadding="0" cellspacing="3" width="100%">
								<tr>
									<td><label>Nome:</label></td>
									<td><input name="nome" type="text" value=${obmAtual.nome} /></td>
								</tr>
								<tr>								
									<td><label>Municipio:</label></td>
									<td><select name="municipio">
									<option selected>${obmAtual.municipio.municipio_nome}							
									<!-- Popula a combo que aparecerá na tela -->
									<%
									OBM obmVO = (OBM)request.getAttribute("obmAtual");
								 	ArrayList<Municipio> municipio = (ArrayList)request.getAttribute("municipios");
								 	 for(Municipio m: municipio){
									 out.println("<option>"+m.getMunicipio_nome());
								 	}
									out.println("</select>");
									%>
									</td>
								</tr>
								<tr>	
									<td><label>Bairro:</label></td>
									<td><input name="bairro" type="text" value=${obmAtual.bairro} /></td>
								</tr>
								<tr>
									<td><label>Logradouro:</label></td>
									<td><input name="logradouro" type="text" value=${obmAtual.logradouro} /></td>
								</tr>
								<tr>
									<td><label>Num. Complemento:</label></td>
									<td><input name="numComplemento" type="text" value="${obmAtual.numCompl}" /></td>
								</tr>
								<tr>	
									<td><label>Coord. X:</label></td>
									<td><input name="coordX" type="text" value=${obmAtual.coordX} /></td>
								</tr>
								<tr>	
									<td><label>Coord. Y:</label></td>
									<td><input name="coordY" type="text" value=${obmAtual.coordY} /></td>
								</tr>
								<tr>	
									<td><label>Status:</label></td>
									<td>Ativa <input name="statusObm" type="radio" value="ativa" <%= (obmVO.getStatus()!=1)? " checked" : "" %> > &nbsp;&nbsp;&nbsp;
							  			Inativa: <input name="statusObm" type="radio" value="inativa" <%= (obmVO.getStatus()!=0)? " checked" : "" %></td>
								</tr>
								<tr>	
									<td colspan="2">
										<input type="submit" value="Alterar" onclick="this.form.operacaoARealizar.value=2"/>
										<input type="submit" value="Deletar" onclick="this.form.operacaoARealizar.value=3"/>							
										<input type="hidden" name="operacaoARealizar" value ="">
							 			<input type="hidden" name="registroOBM" value =${obmAtual.id}>
							 			</form>
							 			 		
							 			<form action="ObmControle" method="post" style="display:inline;">
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
