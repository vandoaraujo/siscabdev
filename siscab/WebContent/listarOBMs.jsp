<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OBms-Siscab</title>
</head>
<body>

<a href="novaObm.jsp">Nova OBM</a>

<p>Lista de OBMS cadastradas: Clique em uma OBM para alteraçöes.


<% ArrayList obms=(ArrayList<OBM>) request.getAttribute("obms");
	
%>

<%if(obms.size()== 0){ %>
     <h2> Nenhuma OBM cadastrada <p>
     
     Retorne ao menu: <h4><a href="paginaPrincipal.jsp" title="Menu">Menu</a></h4>
     </h2>
   
<%} else{
	
	
	for(int i=0;i<obms.size();i++){
		OBM obm =(OBM)obms.get(i);
    %> 
    
    <tr>
    <td>
    <div>      
    
    <%= i+1 %>. =<a href="EditaOBM?registro=<%=i+1%>"> Nome:<%= obm.getNome()%>, Cidade: <%=obm.getMunicipio().getMunicipio_nome()%>
    
       ,<%= obm.getBairro()%>,Status:
    
    <!-- Informa status da OBM -->
    <%
    	if(obm.getStatus() == 1 ){
    	       out.println("Ativa");
    	}
    	else{
    	       out.println("Inativa");
    	} 
    %> </a><br>
    </div>
    </td>
    </tr>
   <%}  }%>
</body>
</html>