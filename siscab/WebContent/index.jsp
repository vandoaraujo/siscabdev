<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  
  
 <SCRIPT language="JavaScript">
  	function validate()
  	{
	  		
		 if(document.frm.nome.value==""|| document.frm.senha.value=="")
		{
  			alert("É necessário digitar nome e senha!!");
			return;
  		}
  	  	document.frm.action="index.jsp";
  		document.frm.submit;
	
  	}
	</SCRIPT>
	
 	
 	<h2>Sistema de Controle de Atendimento de Bombeiros - SISCAB</h2>
	
	
	
	<td valign="middle" align="right">
	
	
	 	<img src="img/cobm.jpg" border=0>
	 	<p>Tela Login</p>
	 	
	      <form action ="ServletLogin" method="post"> 
	    	            
	    	            
     			
		<table class="bloco5" width="200" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td class="texto" width="70"> Login: </td>
			<td class="campo" width="130"><input name="nome" type="text" size="15"></td>
		</tr>
		<tr>

			<td class="texto" width="70"> Senha: </td>
			<td class="campo" width="130"><input name="senha" type="password" size="15"></td>
		</tr>
		<tr>
			<td colspan="2" class="campo_centralizado">
				<input type="submit" name="evento" value="Login" class="botao">
			</td>
		</tr>
		</table>
		</form>
		

 </body> 
</html>
