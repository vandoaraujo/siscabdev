<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Chamado,modelo.NaturezaChamados,dao.NaturezaChamadosDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date, java.text.*" %>
<html  xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
<head>
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="http://maps.google.com/maps?file=api&amp;v=2.x&amp;key=ABQIAAAA1rJRd_d6NTBRsxYq3I7erBSVPiKh14QUveM1LzMKfwwniz5cMBRktlNQQD2Mh4zRyiEDe7djlt6huA" type="text/javascript"></script>
    <script type="text/javascript">

    var map = null;
    var geocoder = null;

    function initialize() {
      if (GBrowserIsCompatible()) {
        map = new GMap2(document.getElementById("map_canvas"));
        map.setCenter(new GLatLng(37.4419, -122.1419), 13);
        geocoder = new GClientGeocoder();
      }
    }

    function showAddress(address) {
      if (geocoder) {
        geocoder.getLatLng(
          address,
          function(point) {
            if (!point) {
              alert(address + " not found");
            } else {
              map.setCenter(point, 13);
              var marker = new GMarker(point);
              map.addOverlay(marker);
              marker.openInfoWindowHtml(address);
            }
          }
        );
      }
    }
    </script>
</head>
<body onload="initialize()" onunload="GUnload()">
<h4> Configurar esta tela pra visualizar um mapa do Google Maps</h4><br>

<div id="map_canvas" style="width: 500px; height: 300px"></div>

<%! String municipio=null; %>

<% municipio = (String)request.getAttribute("municipio"); %>

	<form action="FinalizarChamadoIniciarAtendimento" onsubmit="showAddress(this.address.value); return false" method="post">
		<table>
		<tr><td>Origem do Chamado: <input name="origemChamado" type="text" readonly="readonly" value=${origemChamado}></td></tr>
		<tr><td>Nome do Solicitante: <input name="nomeSolicitante" type="text" readonly="readonly" value=${nomeSolicitante}></td></tr>
		<tr><td>Telefone: <input name="telefone" type="text" readonly="readonly" value=${telefone}></td></tr>
		<tr><td>Número Aproximado de Vitimas: <input name="numAproxVitimas" type="text" readonly="readonly" value=${aproxVitimas}></td></tr>
		<tr><td>Obm Solicitada: <input name="obmSolicitada" type="text" readonly="readonly" value=${nomeObmUsuario}></td></tr>
		<tr><td> <input name="infoComplementares" type="hidden" readonly="readonly" value=${infoComplementares}></td></tr>
		<tr><td> <input name="numeroChamado" type="hidden" readonly="readonly" value=${numeroChamado}></td></tr>
		<tr><td>Município: <select name="municipio">
		 <option><%= municipio %></option>
		</select>
		<tr><td> <input name="bairro" type="hidden" readonly="readonly" value=${bairro}></td></tr>
	
		
		
			<tr><td>Natureza Chamado:</td>
									<td>
										<select name="naturezaChamado">
		 								<% 
											List<NaturezaChamados> tipos = NaturezaChamadosDao.getInstance().listarTodasNaturezasChamado();
											for(int i=0;i<tipos.size();i++){
												out.print("<option>"+tipos.get(i).getNaturezachamado_descricao());
						 				}
												out.println("</select>");
									%></td></tr>			
		
	<br>
			<input type="submit" value="Finalizar Chamado" onclick="this.form.operacaoARealizar.value=1" >
			<input type="hidden" name="operacaoARealizar" value ="">
			<input type="hidden" name="registroOcorrencia" value ="1"> 
			
			</form>

</body>
</html>