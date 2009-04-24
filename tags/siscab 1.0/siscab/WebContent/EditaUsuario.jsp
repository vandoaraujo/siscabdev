<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,dao.OBMDao,modelo.Usuario" %>
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
<%! Usuario usuario; %>
<% usuario = (Usuario)request.getAttribute("usuario");%>

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
										<td style="padding-left:20px;" colspan="2">						
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')" onMouseOut="MM_startTimeout();"><img src="img/bt_atendimento.gif" name="image1" width="109" height="17" border="0" id="image1"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="153" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<a href="#" onclick="fechar()"><img src="img/bt_sairsistema.gif" name="close" width="109" height="17" border="0" id="close"></a>											
					</td>
</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:450px"><legend>&nbsp;Alterar Usuário&nbsp;</legend>						
								<form action="NovoUsuarioServlet" method="get">
								<table border="0" cellpadding="0" cellspacing="3" width="100%">
								<tr>
									<td><label>Nº de Registro:</label></td>
									<td><input name="registro" type="text" value=${usuario.numRegistro} readonly="readonly" /></td>								
								</tr>
								<tr>
									<td><label>Nome de Guerra:</label></td>
									<td><input name="nomeGuerra" type="text" value=${usuario.nomeGuerra} /></td>								
								</tr>
								<tr>
									<td><label>OBM:</label></td>
									<td>
										<select name="obm">
										
										<!-- Popula a combo que aparecerá na tela -->
										<%
											  List<OBM> obms = OBMDao.getInstance().listarTodasOBMs();
											 for(OBM s: obms){
										 		out.println("<option>"+s.getNome());
											}
										out.println("</select>");
										%>
										
								</tr>
									<tr><td><label>perfil:</label></td>
									<td> <select name="perfil" >
										<option>ADMINISTRADOR DO SISTEMA 
										<option selected ><%= usuario.getPerfil().getPerfil_descricao() %> 
										<option>OPERADOR DA OBM
										<option>CONTROLADOR DA OBM
										<option>COMANDANTE
										<option>ATENDENTE DO COCB
										</select>									
																	
								</tr>
								<tr>
								<tr>
									<td><label>Status:</label></td>
									<td><%  
									     if(usuario.getStatus().equals("ATIVO")){
									    %>
									      	   O perfil atual deste Usuário é Ativo. No momento seu status será trocado para Inativo
									    	 	Ativo  <input name="status" type="radio" value="ATIVO" />
									    	 	Inativo <input name="status" type="radio" value="INATIVO" checked/>
									    	 	 
									     <%} else if (usuario.getStatus().equals("INATIVO")){
									    	 %>
									    	 O perfil atual deste Usuário é Inativo. No momento seu status será trocado para Ativo
									    	  	Ativo <input name="status" type="radio" value="ATIVO" checked/>
									    	 	Inativo <input name="status" type="radio" value="INATIVO" /> 
									    	 
									     <% }%></td>
								</tr>
								<tr>
									<td><label>Email:</label></td>
									<td><input name="email" type="text" size="30" value=${usuario.email} /></td>								
								</tr>
								<tr>
									<td><label>Senha:</label></td>
									<td><input name="senha" type="text" size="30" value=${usuario.senha} /></td>								
								</tr>
								<tr>
									<td colspan="2">								
										<input type="submit" value="Alterar" onclick="this.form.operacaoARealizar.value=2"/>
										<input type="submit" value="Deletar" onclick="this.form.operacaoARealizar.value=3"/>	
										<input type="hidden" name="operacaoARealizar" value ="">
									    <input type="hidden" name="registroUsuario" value =${usuario.id} > 
										</form>	
										
										<form action="BuscarUsuario" method="post" style="display:inline;">							 				
							 				<input type="hidden" name="registro" value=${usuario.numRegistro} >
							 				<input type="hidden" name="nomeGuerra" value=${usuario.nomeGuerra} >
							 				<input type="submit" name="evento" value="Voltar"/>
							 			</form>	
									</td>								
								</tr>
						</fieldset>	
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>

</body>
</html>
	