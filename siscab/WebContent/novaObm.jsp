<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Municipio,dao.MunicipioDao" %>
<%@ page import="java.util.List" %>
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
	var indiceMunicipio = document.form.municipio.selectedIndex;
	var indiceStatus = document.form.statusObm.selectedIndex;			
		
	if ( (document.form.nome.value != null) && (document.form.nome.value != "") && 
			(document.form.municipio.value != null) && (document.form.municipio.value != "") && 
				(document.form.bairro.value != null) && (document.form.bairro.value != "") &&
					(document.form.logradouro.value != null) && (document.form.logradouro.value != "") &&
						(document.form.numComplemento.value != null) && (document.form.numComplemento.value != "") &&
							(document.form.coordX.value != null) && (document.form.coordX.value != "") &&
								(document.form.coordY.value != null) && (document.form.coordY.value != "") && 
									(document.form.municipio.options[indiceMunicipio].text != "- - -") && 
										(document.form.statusObm.options[indiceStatus].text != "- - -" ) )
		return true;
	else
	{
		alert("Favor preencher todos campos obrigat�rios");	
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
					
					<h2>Administra��o :: Cadastro de OBMs :: Incluir OBM</h2>
					
					
						<fieldset style="width:450px"><legend>&nbsp;Dados da nova OBM&nbsp;</legend>
							
							<form name="form" action="CrudOBM" method="post">
								
								<table border="0" cellpadding="0" cellspacing="3" width="100%">
								<tr>
									<td><label>Nome:</label></td>
									<td><input name="nome" type="text" size=40 maxlength="40"/> * </td>
								</tr>
								<tr>	
									<td><label>Municipio:</label></td>
									<td><select name="municipio">
										<!-- Popula a combo que aparecer� na tela -->
										<%										
									 	 List<Municipio> municipio =(List<Municipio>) MunicipioDao.getInstance().listarTodosMunicipios();
										%><option selected>- - -</option><%
										for(Municipio m: municipio){
									 	
									 		 out.println("<option>"+m.getMunicipio_nome());
									 	}
										out.println("</select> * ");
										%>
									</td>
								</tr>
								<tr>	
									<td><label>Bairro:</label></td>
									<td><input name="bairro" type="text" size=40 maxlength="40"/> * </td>
								</tr>
								<tr>	
									<td><label>Logradouro:</label></td>
									<td><input name="logradouro" type="text" size=40 maxlength="65"/> * </td>
								</tr>
								<tr>	
									<td><label>N�/Complemento:</label></td>
									<td><input name="numComplemento" type="text" size=40 maxlength="30"/> * </td>
								</tr>
								<tr>	
									<td><label>Latitude:</label></td>
									<td><input name="coordX" type="text" size=40  onkeydown="return SomenteNumeros(event)"/> * </td>
								</tr>
								<tr>	
									<td><label>Longitude:</label></td>
									<td><input name="coordY" type="text" size=40 onkeydown="return SomenteNumeros(event)"/> * </td>
								</tr>
								<tr>	
									<td><label>Status:</label></td>
									<td><select name="statusObm">
											<option selected>- - -</option>
											<option>ATIVA</option>
											<option>INATIVA</option>
										</select> *									
									</td>	
								</tr>
								<tr>
									<td colspan="2">
										<input type="submit" value="Salvar" onclick="return campoObrigatorio()"/>
										<input type="hidden" name="operacaoARealizar" value ="1">
								 		<input type="hidden" name="registroOBM" value ="1">
								 		</form>
								 		<form action="ObmControle" method="post" style="display:inline;">
							 				<input type="submit" value="Cancelar"/>
							 			</form>							 										
									</td>								
								</tr>	
								</table>																																													
						</fieldset>	
						( * ) Campos obrigat�rios
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>


</body>
</html>