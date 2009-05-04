<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "java.util.Vector" %>
<%@ page import="org.apache.log4j.BasicConfigurator,org.apache.log4j.Level,org.apache.log4j.Logger" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
</head>

<body>
 
<script language="JavaScript">
function validate()
{	
	if(document.frm.numRegistro.value=="" || document.frm.numRegistro.value==null || document.frm.senha.value=="" || document.frm.senha.value==null)
	{
		alert("É necessário digitar nome e senha!!");
		return false;
	}
	  	//document.frm.action="index.jsp";
		//document.frm.submit;
		return true;
	}
</script>

<form action ="Login" name="frm" method="post">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr style="background-image:url(img/back_cabecalho.jpg); background-repeat:repeat-x;">
		<td style="background-image:url(img/cabecalho.jpg); background-repeat:no-repeat;">
			<center>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td align="right" style="padding-right:20px;">  	
					 	<img src="img/logo.png">
					 </td>
				</tr>
				<tr>
					<td>
						<center>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr style="height:110px" valign="top">
								<td colspan="2" style="padding-top:10px">
									<center><h3>Seja bem-vindo. Para acessar o sistema, entre com o número de registro e senha.</h3></center>
								</td>
							</tr>
							<tr>
								<td>
									<center><img src="img/logo.gif"></center>									
								</td>
								<td>							
									<table width="210" border="0" cellpadding="5" cellspacing="0">
										<tr>
											<td class="texto" width="80" nowrap="nowrap">Nº Registro:</td>
											<td class="campo" width="130"><input name="numRegistro" type="text" size="15"></td>
										</tr>
										<tr>				
											<td class="texto" width="80">Senha:</td>
											<td class="campo" width="130"><input name="senha" type="password" size="15"></td>
										</tr>
										<tr>
											<td colspan="2" class="campo_centralizado">
												<input type="submit" name="evento" value="Login" onclick="return validate();">
											</td>
										</tr>
										<tr>
											<td colspan="2" class="campo_centralizado">
												<font color=red> ${mensagem}</font>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						</center>
					</td>
				</tr>
			</table>
			</center>
		</td>
	</tr>
</table>

</form>
		

 </body> 
</html>