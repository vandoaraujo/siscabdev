<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.Usuario,modelo.Atendimentos" %>
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
<fieldset style="width:1100px"><legend>&nbsp;Lista de Atendimentos Nao Finalizados&nbsp;</legend>
							<div style="color:red">Clique em um Atendimento para alteraçöes</div>
						
						
						<table>
						<% ArrayList atendimento=(ArrayList<Atendimentos>) request.getAttribute("atendimentos");
	
					%>
					
					<%if(atendimento.size()== 0){ %>
					     <h2> Nenhum Atendimento com Status = "Pendente" cadastrado <p>
					     
					     Retorne ao menu: <h4><a href="paginaPrincipal.jsp" title="Menu">Menu</a></h4>
					     </h2>
					   
					<%} else{
						
						
						for(int i=0;i<atendimento.size();i++){
							Atendimentos atendimentos =(Atendimentos)atendimento.get(i);
					    %> 
					    
					    <tr>
					    <td>
					    <div>      
					    
					    <%= i+1 %> :: NúmeroAtendimento: <%=atendimentos.getAtendimento_numero()%> <a href="EditaAtendimento?registro=<%=atendimentos.getAtendimento_numero()%>"> Municipio:<%= atendimentos.getMunicipio_id().getMunicipio_nome()%>, Bairro: <%=atendimentos.getBairro()%>,
					    Logradouro: <%=atendimentos.getLogradouro()%>, TipoOcorrencia: <%=atendimentos.getTipoocorrencia()%>, QtdVitimas: <%=atendimentos.getChamado_id().getNumaproxvitimas()%>, OBMAtendimento: <%=atendimentos.getObm_id().getNome()%>
					    
					  	</a><br>
					    </div>
					    </td>
					    </tr>
					   <%}  }%>
						
						</table>
						</fieldset>


</body>
</html>