<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Municipio,dao.MunicipioDao" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NOVA OBM</title>
</head>
<body>

<form action="CrudOBM" method="post">
	<fieldset>
	
	<label>Nome: <input name="nome" type="text" size=40/></label><br>
	
	<label>Municipio: <select name="municipio">
	
	<!-- Popula a combo que aparecerá na tela -->
	<%
	
 	 List<Municipio> municipio =(List<Municipio>) MunicipioDao.getInstance().listarTodosMunicipios();
 	 for(Municipio m: municipio){
	 out.println("<option>"+m.getMunicipio_nome());
 	}
	out.println("</select>");
	%>
	
	 </label><br>
	 <label>Bairro: <input name="bairro" type="text" size=40/></label><br>
	 <label>Logradouro: <input name="logradouro" type="text" size=40/></label><br>
	 <label>Num. Complemento: <input name="numComplemento" type="text" size=40/></label><br>
	 <label>Coord. X: <input name="coordX" type="text" size=40/></label><br>
	 <label>Coord. Y: <input name="coordY" type="text" size=40/></label><br>
	 <label>Status:<br> Ativa:<input name="statusObm" type="radio" value="ativa" checked><br>
	 Inativa: <input name="statusObm" type="radio" value="inativa"/></label><br>
	 </fieldset>
	<input type="submit" value="Incluir" onclick="this.form.operacaoARealizar.value=1"/>
	<input type="hidden" name="operacaoARealizar" value ="">
	 <input type="hidden" name="registroOBM" value ="1"> 
	</form>
	</div>
		



</body>
</html>