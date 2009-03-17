<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.Usuario,modelo.Atendimentos" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<% Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
 

%>		

Perfil: ${perfil}<br>


<fieldset style="width:1100px"><legend>&nbsp;Lista de Atendimentos Vinculadas a ${obm}&nbsp;</legend>
							<div style="color:red">Clique em um Atendimento para Transferência</div>
						
						
						<table>
						<% ArrayList atendimento=(ArrayList<Atendimentos>) request.getAttribute("atendimentos");
	
					%>
					
					<%if(atendimento.size()== 0){ %>
					     <h2> Nenhum Atendimento não finalizado no momento vinculado a sua ${obm}<p>
					     
					     Retorne ao menu: <h4><a href="paginaPrincipal.jsp" title="Menu">Menu</a></h4>
					     </h2>
					   
					<%} else{
						
						
						for(int i=0;i<atendimento.size();i++){
							Atendimentos atendimentos =(Atendimentos)atendimento.get(i);
					    %> 
					    
					    <tr>
					    <td>
					    <div>      
					    
					    <%= i+1 %> :: NúmeroAtendimento: <%=atendimentos.getAtendimento_numero()%> <a href="RepasseAtendimento?registro=<%=atendimentos.getId()%>&perfilUsuario=<%=usuario.getPerfil().getId()%>"> Municipio:<%= atendimentos.getMunicipio_id().getMunicipio_nome()%>, Bairro: <%=atendimentos.getBairro()%>,
					    Logradouro: <%=atendimentos.getLogradouro()%>, TipoOcorrencia: <%=atendimentos.getTipoocorrencia().getTipoocorrencia_descricao() %>, QtdVitimas: <%=atendimentos.getChamado_id().getNumaproxvitimas()%>, OBMAtendimento: <%=atendimentos.getObm_id().getNome()%>
					    
					  	</a><br>
					    </div>
					    </td>
					    </tr>
					   <%}  }%>
						
						</table>
						</fieldset>

</body>
</html>