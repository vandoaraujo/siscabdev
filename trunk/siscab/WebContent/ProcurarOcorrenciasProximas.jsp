<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Atendimento" %>
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
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td style="padding-left:20px; padding-top:5px;" colspan="2">
			
					<h2>Atendimentos :: Registrar Chamado :: Procurar Ocorrências Próximas</h2>
			
						<fieldset style="width:50%"><legend>&nbsp;Resultado da Procura&nbsp;</legend>

							
								<%  ArrayList atendimento=(ArrayList<Atendimento>) request.getAttribute("listaAtendimentosProximos");
									%>
									
									<%if(atendimento.size()== 0){  %>
									    Nenhum atendimento não finalizado registrado em
									    <table border="0" cellpadding="4" cellspacing="1" width="100%">
										<tr>
											<td>
												Munic&iacute;pio: ${municipio}
											</td>
											<td>
												Bairro: ${bairro}</td>
											</td>
										</tr>										
										</table>
																				
									   
									<%} else{
										
										%>
										
										Atendimentos não finalizados registrados em:						
										<table border="0" cellpadding="4" cellspacing="1" width="100%">
										<tr>
											<td>
												Munic&iacute;pio: ${municipio}
											</td>
											<td>
												Bairro: ${bairro}</td>
											</td>
										</tr>										
										</table>
										
										<table border="0" cellpadding="4" cellspacing="1" width="100%" bgcolor="#000000">
										<tr bgcolor="#FFFFFF">
										  	<th>Tipo de Ocorrência</th>
										  	<th>Endereço</th>
										  	<th>OBM Responsável</th>
										  	<th>Status</th>
										</tr>
																			
										<%										
										for(int i=0;i<atendimento.size();i++){
											Atendimento atend =(Atendimento)atendimento.get(i);
									    
									    if (i%2==0){
									    %> 									    									  									    
									     <tr bgcolor="#EFEFEF">
									     <%}else{ %>
									     
									     <tr bgcolor="#F9D8D0">
									     <%} %>
									    	<td><%=atend.getTipoocorrencia().getTipoocorrencia_descricao()%></td>
									    	<td><%=atend.getLogradouro()%></td>
									    	<td><%=atend.getObm_id().getNome()%></td>
									    	<td><%=atend.getStatus_atendimento() %></td>
									       	
									    </tr>
									    								    										
									   <%} 
										
									}%>
									</table>
							<input type="button" value="Fechar" onclick="window.close()">
						</fieldset>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
						  												
								
</body>
</html>