<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,dao.TipoServicoDao,dao.VitimaAtendidaDao,modelo.ServicoRealizado,modelo.TipoServico" %>
<%@ page import="java.util.ArrayList" %>
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

	document.form.operacaoARealizar.value = 1;

	var indicetipoServico = document.form.tipoServico.selectedIndex;			
		
	if (document.form.tipoServico.options[indicetipoServico].text != "- - -" )
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
						
						<h2>Atendimentos :: Acompanhar Atendimento :: Ficha do Atendimento :: Servi�os Executados :: Incluir Servi�o</h2>
						
						N�mero atendimento: <strong>${numeroAtendimento}</strong>
						<p>&nbsp;</p>
						
						<fieldset style="width:450px"><legend>&nbsp;Dados do novo servi�o&nbsp;</legend>
							
							<form action="ControlaServico" method="post" name="form">
								
								<table border="0" cellpadding="0" cellspacing="3" width="100%">
								<tr>
									<td><label>Tipo de servi�o:</label></td>
									<td>
										<select name="tipoServico">
		 								<%
		 									List<TipoServico> tipos = TipoServicoDao.getInstance().listarTodosTiposServicos();
											%><option selected>- - -</option><%
		 									for(int i=0;i<tipos.size();i++){
												out.print("<option>"+tipos.get(i).getTiposervico_descricao());
						 					}
												out.println("</select> *");
		 								%>
		 							</td>
		 						</tr>	
									<input name="idAtendimento" type="hidden" value="${registroAtendimento}"/>
									<input name="numeroAtendimento" type="hidden" value="${numeroAtendimento}"/>
								<tr>
									<td colspan="2">
									<br/>
										<input type="submit" value="Salvar" onclick="return campoObrigatorio()"/>
										<input type="button" value="Cancelar" onclick="history.back()"/>
										<input type="hidden" name="operacaoARealizar" value ="">
								 		<input type="hidden" name="registroServico" value ="1">
									</td>								
								</tr>
								</table>
							</form>
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