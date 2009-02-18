<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.NaturezaChamados,modelo.Municipio" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  	
Cadastro de Chamados<br>



<form action="LocalizaOcorrencia" method="post">
<table>

<tr>Numero gerado: <input name="idChamado" type="text" readonly="readonly" value=${idChamado}></tr>
<tr>Usuário: <input name="usuario" type="text" readonly="readonly" value=${usuario.nomeGuerra}></tr>
<tr>Obm do Usuário: <input name="obmUsuario" type="text" readonly="readonly" value=${usuario.obm.nome}></tr>
<tr>Data registrada: <input name="dataRegistrada" type="text" readonly="readonly" value=${data}></tr>
<tr>Hora registrada: <input name="horaRegistrada" type="text" readonly="readonly" value=${hora}></tr>
  	  	  	
	<tr>Origem: <select name="origem">
	<option>Telefone 
	<option>Pessoalmente
	</select></tr>		

	<tr>Nome Solicitante: <input name="nomeSolicitante" type="text" ></tr>
	<tr>Telefone Solicitante: <input name="telefoneSolicitante" type="text"></tr>
	<tr>Num aproximado vítimas: <input name="numAproximadoVitimas" type="text" size=8><p>
	<tr>Info Complementares: <textArea NAME="infoComplementares" COLS=30 ROWS=4></textArea></tr>
	
		
	<tr>Tipo de Ocorrencia: <select name="tipoOcorrencia">
	
	<!-- Popula a combo de tipos de Ocorrencia que aparecerá na tela -->
	<%
		 
 	ArrayList<String> tipoOcorrencia = (ArrayList)request.getAttribute("tipoOcorrencia");
	for(int i=0;i<tipoOcorrencia.size();i++){
	 out.print("<option>"+tipoOcorrencia.get(i));
 	}
	out.println("</select>");
	%></tr>
	
	<tr>Município: <select name="municipio">
	
	<!-- Popula a combo de municipios que aparecerá na tela -->
	<%
    
 	ArrayList<Municipio> municipio = (ArrayList)request.getAttribute("municipios");
 	 for(Municipio m: municipio){
	 out.println("<option>"+m.getMunicipio_nome());
 	}
	out.println("</select>");
	%>
	
	<tr>Bairro: <input name="bairro" type="text" size=20></tr><p>
	<tr>Logradouro: <input name="logradouro" type="text" size=20></tr><p>
	<tr>num.Complemento: <input name="numComplemento" type="text" size=6></tr><p>
	<tr>CoordX: <input name="coordX" type="text" size=20></tr><p>
	<tr>CoordY: <input name="coordY" type="text" size=20></tr><p>
	
	<tr>OBM: <select name="obmReceberSolicitacao">
	
		
	<!-- Popula a combo de OBMS que aparecerá na tela -->
	<%
 	ArrayList<OBM> obms = (ArrayList)request.getAttribute("obms");
 	 for(OBM s: obms){
	 out.println("<option>"+s.getNome());
 	}
	out.println("</select>");
	%></tr>
	
		//Repassa os atributos de Atendimentos, pois pode haver Ocorrencias Próximas
		request.setAttribute("tipoOcorrencia", tipoOcorrencia);
		request.setAttribute("municipio", municipio);
		request.setAttribute("bairro", bairro);
		request.setAttribute("logradouro", logradouro);
		request.setAttribute("numComplemento", numComplemento);
		request.setAttribute("coordY", coordY);
		request.setAttribute("coordX", coordX);
		request.setAttribute("obmSolicitacao", obmReceberSolicitacao);
		//parametros de chamado
		request.setAttribute("naturezaChamados", nChamados);
		request.setAttribute("objChamado", chamado);
	
	
			
	<input type="submit" value="Localizar no Mapa" onclick="this.form.operacaoARealizar.value=1" >
	<input type="hidden" name="operacaoARealizar" value ="">
	<input type="hidden" name="registroOcorrencia" value ="1"> 
	</table>
</form>

</body>
</html>