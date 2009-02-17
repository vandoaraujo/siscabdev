<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Municipio" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ediçao de OBMS</title>
</head>
<body>
<div id="texto">

	<h2>Editar OBM</h2>
	
	
	<form action="CrudOBM" method="post">
	<fieldset>
	
	<label>Nome: <input name="nome" type="text" value=${obmAtual.nome} /></label><br>
	
	<label>Municipio: <select name="municipio">
	<option selected>${obmAtual.municipio.municipio_nome}
	
	<!-- Popula a combo que aparecerá na tela -->
	<%
	OBM obmVO = (OBM)request.getAttribute("obmAtual");
 	ArrayList<Municipio> municipio = (ArrayList)request.getAttribute("municipios");
 	 for(Municipio m: municipio){
	 out.println("<option>"+m.getMunicipio_nome());
 	}
	out.println("</select>");
	%>
	
	 </label><br>
	 <label>Bairro: <input name="bairro" type="text" value=${obmAtual.bairro} /></label><br>
	 <label>Logradouro: <input name="logradouro" type="text" value=${obmAtual.logradouro} /></label><br>
	 <label>Num. Complemento: <input name="numComplemento" type="text" value="${obmAtual.numCompl}" /></label><br>
	 <label>Coord. X: <input name="coordX" type="text" value=${obmAtual.coordX} /></label><br>
	 <label>Coord. Y: <input name="coordY" type="text" value=${obmAtual.coordY} /></label><br>
	 <label>Status:<br>
	 
	  Ativa <input name="statusObm" type="radio" value="ativa" <%= (obmVO.getStatus()!=1)? " checked" : "" %> ><br>
	  Inativa: <input name="statusObm" type="radio" value="inativa" <%= (obmVO.getStatus()!=0)? " checked" : "" %> ></label><br>
	 </fieldset>
	<input type="submit" value="Alterar" onclick="this.form.operacaoARealizar.value=2"/>
	<input type="submit" value="Deletar" onclick="this.form.operacaoARealizar.value=3"/>
	<input type="hidden" name="operacaoARealizar" value ="">
	 <input type="hidden" name="registroOBM" value =${obmAtual.id}> 
	</form>
	</div>
		


</body>
</html>