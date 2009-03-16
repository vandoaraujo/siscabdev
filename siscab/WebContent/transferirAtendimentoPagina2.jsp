<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.Usuario,modelo.Atendimentos,modelo.OBM" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<% Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
 	Atendimentos at = (Atendimentos) request.getAttribute("atendimentos");

%>	

<form action="EfetivaRepasseAtendimento" method="post">						
						<table>
							<tr>
								<td valign="top">
									<fieldset style="width:450px"><legend>&nbsp;Repassar Atendimento&nbsp;</legend>
									<table>						
											
										
										<tr>
											<td width="100px" nowrap="nowrap">Seu perfil: ${perfil}</td>
										</tr>
										<tr>
											<td>Numero Atendimento:</td>
											<td><input name="numeroAtendimento" type="text" size=20 value="<%= at.getAtendimento_numero() %>"></td>							
										</tr>						
										<tr>
										
										<% if(usuario.getPerfil().getId() == 4){%>
										  	<td>Obm de Transferencia:</td>
										  	<td><select name="obm">													
											<!-- Popula a combo de obm que aparecerá na tela - CONTROLADOR DO COCB -->
											<%
										    
										 	ArrayList<OBM> obms = (ArrayList)request.getAttribute("obm");
											%><option selected> 
											<% 
											for(OBM o: obms){
											 out.println("<option>"+o.getNome());
										 	}
											out.println("</select>");
											%>
											</td>
											<%} %>
											
											
										<% else if(usuario.getPerfil().getId() == 3){%>
										  	<td>Obm de Transferencia:</td>
										  	<td><select name="obm">													
											<!-- Popula a combo de obm que aparecerá na tela - OPERADOR DA OBM-->
											<option selected>COCB 
											</td>
											<%} %>
																								
										</tr>
										<tr>
											<td></td>
											<td><input type="submit" value="Efetivar Repasse"></td>
										</tr>	
									</table>
									</fieldset>



		if(perfilUsuario == 4){
			
						
		}
		//2 caso - Controlador do COCB transfere atendimento para uma determinada OBM
		else{
			List<OBM> obm = OBMDao.getInstance().listarTodasOBMsExcetoCOCB();
			request.setAttribute("obm", obm);
			
		}
		
		request.setAttribute("atendimentos", at );
		request.setAttribute("perfil","Operador da OBM");				 			
		view = request.getRequestDispatcher("/transferirAtendimentoPagina2.jsp");
		
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}




</body>
</html>