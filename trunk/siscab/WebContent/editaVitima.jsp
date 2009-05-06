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
<script>
function campoObrigatorio()
{
	if ( (document.form.nome.value != null) && (document.form.nome.value != "") &&
			(document.form.idade.value != null) && (document.form.idade.value != "")	)
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
<%! VitimaAtendida vitima=null; %>

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
					
					<h2>Atendimentos :: Acompanhar Atendimento :: Ficha de Atendimento :: Vítimas atendidas :: Editar Vítimas</h2>
					
					
						<fieldset style="width:520px"><legend>&nbsp;Dados da vítima selecionada&nbsp;</legend>
							
						<form name="form" action="CrudVitima" method="post">
								
								<table border="0" cellpadding="0" cellspacing="3" width="100%">
								<tr>
									<td><label>Nome:</label></td>
									<td colspan="8"><input name="nome" type="text" size=40 value="${vitima.nome}"/> * </td>
								</tr>
								<tr>	
									<td><label>Idade:</label></td>
									<td colspan="8"><input name="idade" type="text" size=3 maxlength="3" value="${vitima.idade}" onkeypress="return SomenteNumeros(event);"/> * </td>
								</tr>
								<tr>
									<td><label>Cor:</label></td>
		
									<%  vitima = (VitimaAtendida) request.getAttribute ("vitima"); 
											if(vitima.getCor() == 1){
									    	 %> <td width="10px"><input name="cor" type="radio" value="branca" checked/></td><td>Branca</td>
									    	 	<td width="10px"><input name="cor" type="radio" value="parda"/></td><td>Parda</td>
									    	 	<td width="10px"><input name="cor" type="radio" value="amarela"/></td><td>Amarela</td>
									    	 	<td width="10px"><input name="cor" type="radio" value="negra"/></td><td>Negra</td>	 
									     <%} else if (vitima.getCor() == 2){
									    	 %> <td width="10px"><input name="cor" type="radio" value="branca"/></td><td>Branca</td>
									    	 	<td width="10px"><input name="cor" type="radio" value="parda" checked/></td><td>Parda</td>
									    	 	<td width="10px"><input name="cor" type="radio" value="amarela"/></td><td>Amarela</td>
									    	 	<td width="10px"><input name="cor" type="radio" value="negra"/></td><td>Negra</td>	 
									    	 
									     <% }else if (vitima.getCor() == 3){
									    	 %> <td width="10px"><input name="cor" type="radio" value="branca"/></td><td>Branca</td>
									    	 	<td width="10px"><input name="cor" type="radio" value="parda"/></td><td>Parda</td>
									    	 	<td width="10px"><input name="cor" type="radio" value="amarela" checked/></td><td>Amarela</td>
									    	 	<td width="10px"><input name="cor" type="radio" value="negra"/></td><td>Negra</td>
									    								     
									     <% }else if (vitima.getCor() == 4){
									    	 %> <td width="10px"><input name="cor" type="radio" value="branca"/></td><td>Branca</td>
								    	 		<td width="10px"><input name="cor" type="radio" value="parda"/></td><td>Parda</td>
								    	 		<td width="10px"><input name="cor" type="radio" value="amarela"/></td><td>Amarela</td>
								    	 		<td width="10px"><input name="cor" type="radio" value="negra" checked/></td><td>Negra</td>	 
										    	 
											     <% } %>																
									</tr>
								<tr>
									<td><label>Sexo:</label></td>
									<% vitima = (VitimaAtendida) request.getAttribute ("vitima"); 
									     if(vitima.getSexo() == 'M'){
									    	 %> <td width="10px"><input name="sexo" type="radio" value="M" checked/></td><td>M</td>
									    	 	<td width="10px"><input name="sexo" type="radio" value="F" /></td><td colspan="5">F</td>
									     <%} else{
									    	 %> <td width="10px"><input name="sexo" type="radio" value="F" checked/></td><td>F</td>
									    	 	<td width="10px"><input name="sexo" type="radio" value="M" /></td><td colspan="5">M</td> 
									    	 
									     <% }%>
								</tr>
								
								<tr>	
									<td><label>Situacao Vitima:</label></td>
									<td colspan="8"><select name="situacaoVitima">
									<% vitima = (VitimaAtendida) request.getAttribute ("vitima"); 
									     if(vitima.getVitima_situacao() == 1){
									    	 %>
									    	 	<option selected>1 - Recusou Atendimento</option>
												<option>2 - Entregue ao hospital</option>
												<option>3 - Permaneceu no local após ser atendida</option>
												<option>4 - Encaminhada ao suporte aeromédico</option>
												<option>5 - Óbito no local</option>
												</select>
									  <% } else if (vitima.getVitima_situacao() == 2){
										  %>
										  		<option>1 - Recusou Atendimento</option>
												<option selected>2 - Entregue ao hospital</option>
												<option>3 - Permaneceu no local após ser atendida</option>
												<option>4 - Encaminhada ao suporte aeromédico</option>
												<option>5 - Óbito no local</option>
												</select>										  
									   <% } else if (vitima.getVitima_situacao() == 3){
										  %>
										 		<option>1 - Recusou Atendimento</option>
												<option>2 - Entregue ao hospital</option>
												<option selected>3 - Permaneceu no local após ser atendida</option>
												<option>4 - Encaminhada ao suporte aeromédico</option>
												<option>5 - Óbito no local</option>
												</select>
										 <% } else if (vitima.getVitima_situacao() == 4){
											 %>
												<option>1 - Recusou Atendimento</option>
												<option>2 - Entregue ao hospital</option>
												<option>3 - Permaneceu no local após ser atendida</option>
												<option selected>4 - Encaminhada ao suporte aeromédico</option>
												<option>5 - Óbito no local</option>
												</select>
											 
										<% } else if (vitima.getVitima_situacao() == 5){
											%>
												<option>1 - Recusou Atendimento</option>
												<option>2 - Entregue ao hospital</option>
												<option>3 - Permaneceu no local após ser atendida</option>
												<option>4 - Encaminhada ao suporte aeromédico</option>
												<option selected>5 - Óbito no local</option>
												</select>
										<% } %>
												
									* </td>	
								</tr>
								<tr>	
									<td><label>Hospital Destino:</label></td>
									<td colspan="8"><input name="hospital" type="text" size=40 value="${vitima.hospitaldestino}"/></td>
								</tr>
								<tr>
									<td colspan="9">
										<input type="submit" value="Salvar" onclick="return campoObrigatorio();"/>
										<input type="button" value="Voltar" onclick="history.back()"/>
										<input type="hidden" name="operacaoARealizar" value ="2">
								 		<input type="hidden" name="registroVitima" value ="${vitima.id}"/>
								 		<input type="hidden" name="atendimentoAtual" value="${atendimentoAtual}"/> 								 		
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