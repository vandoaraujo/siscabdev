<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Atendimentos" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Lista de Ocorrencias com este munícipio : <font color="Firebrick">${municipio}</font> e com este bairro: <font color="Firebrick">${bairro}</font> <br>


<% ArrayList atendimento=(ArrayList<Atendimentos>) request.getAttribute("listaAtendimentosProximos");
%>

<%if(atendimento.size()== 0){ %>
     <h2> Nenhuma ocorrencia cadastrada com tais parâmetros! <p>
     
     Retorne ao menu: <h4><a href="paginaPrincipal.jsp" title="Menu">Menu</a></h4>
     </h2>
   
<%} else{
	
	
	for(int i=0;i<atendimento.size();i++){
			Atendimentos atend =(Atendimentos)atendimento.get(i);
    %> 
    <br>	
    <td>
    <div>     
    <font color="darkgreen"> Número do Atendimento: <%= atend.getAtendimento_numero()%>, Bairro da Ocorrencia: <%= atend.getBairro()%>, Rua: <%= atend.getLogradouro()%>, Status do atendimento: <%= atend.getStatus_atendimento()%><br>
    </font>
    <br>
    </div>
    </td>
    </tr>
   <%} 
	
	
}%>
	 







</body>
</html>