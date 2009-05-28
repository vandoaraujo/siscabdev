<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Chamado,modelo.NaturezaChamado,dao.NaturezaChamadoDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date,java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
	<script id="api" type="text/javascript" src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAA1rJRd_d6NTBRsxYq3I7erBSVPiKh14QUveM1LzMKfwwniz5cMBRktlNQQD2Mh4zRyiEDe7djlt6huA"></script>
																									  
</head>
<body onload="init();" onunload="GUnload()">
<script language="JavaScript1.2">mmLoadMenus();</script>
<%Usuario usu =( Usuario) request.getSession().getAttribute("usuario"); %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr>
		<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td style="padding-left:20px; padding-top:5px;" colspan="2">
					
					<h2>Administração :: Cadastro de OBMs :: Editar OBM</h2>	
					
					
						<fieldset style="width:450px"><legend>&nbsp;Editar OBM&nbsp;</legend>
						<table width="100%">
							<tr>
								<td> 
									<div id="map_canvas">
										Map loading..
										<noscript>
											Habilite o uso de JavaScript em seu browser para exibir o Mapa da Google
										</noscript>
									</div>									
								</td>
							</tr>
						</table>
						
						<div style="display:inline">Latitude: <div id="CoordX" style="display:inline"></div></div>
						<div style="display:inline">Longitude: <div id="CoordY" style="display:inline"></div></div>
								  							
							<script>
							function Coordenadas(){
								var CoordX = document.getElementById('CoordX').innerHTML;
								var CoordY = document.getElementById('CoordY').innerHTML;
								document.getElementById('hiddenCoordY').value = CoordY;
								document.getElementById('hiddenCoordX').value = CoordX;
							}
							</script>
							
							<input type="hidden" name="hiddenCoordX" id="hiddenCoordX">
							<input type="hidden" name="hiddenCoordY" id="hiddenCoordY">
							<input type="hidden" name="q" id="q" value="><%=request.getParameter("endereco") %> <%=request.getParameter("numero") %> <%=request.getParameter("bairro") %> <%=request.getParameter("municipio") %> RJ Brasil ">
							
							<input type="submit" value="Confirmar Coordenadas Geográficas" onclick="window.close();" >																		
					</fieldset>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<script language="JavaScript" src="js/google.js"></script>

</body>
</html>