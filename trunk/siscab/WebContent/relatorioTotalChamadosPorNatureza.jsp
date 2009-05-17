<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Viatura,modelo.Atendimento"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
	<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css" media="screen"></LINK>
	<SCRIPT type="text/javascript" src="js/dhtmlgoodies_calendar.js"></script>
	
	
</head>

<body>
<script language="JavaScript1.2">mmLoadMenus();</script>
<%Usuario usu =( Usuario) request.getSession().getAttribute("usuario"); %>
<%! int controle,tamanhochamadosPorNatureza,tamanhoNumeroAtendimento=0; %>
<%		List chamadosPorNatureza = (List)request.getAttribute("chamadosPorNatureza"); 
		List numeroAtendimentos = (List)request.getAttribute("qtdChamados");
		List percentualAtendimento = (List)request.getAttribute("percentualChamados");
%>
<%		tamanhochamadosPorNatureza = chamadosPorNatureza.size();
%>



<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr style="background-image:url(img/back_cabecalho.jpg); background-repeat:repeat-x;">
		<td style="background-image:url(img/cabecalho.jpg); background-repeat:no-repeat;">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr style="height:139px;">
					<td align="right" style="padding-right:20px;" colspan="2">  	
					 		<img src="img/logo.png">	 	
					 </td>
				</tr>
				<tr>
					<td style="padding-left:20px;">						
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221104_0,0,17,null,'image1')" onMouseOut="MM_startTimeout();"><img src="img/bt_atendimento.gif" name="image1" width="109" height="17" border="0" id="image1"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="153" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<form action="Logoff" onsubmit="fechar()" style="display:inline"><input type="image" src="img/bt_sairsistema.gif" name="close" /></form>										
					</td>
					<td align="right" style="padding-right:20px;">
						<strong>Usuário:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
					
					<h2>Relatórios :: Total de Chamados por Natureza</h2>

				<fieldset style="width: 900px"><legend>&nbsp;Período
				de referênca: ${dataInicial} a ${dataFinal}.&nbsp;</legend>
				<table border="0" cellpadding="0" cellspacing="3" width="100%">
					<tr>
						<td>

						<table border=1 cellspacing=0 cellpadding=0>
							<tr>
								<td width=440 valign=top
									style='width: 330.0pt; border: solid windowtext 1.0pt;'>
								<p class=MsoNormal
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 9px'><b
									style='mso-bidi-font-weight: normal'><span lang=PT-BR>Natureza do Chamado</span></b></p>
								</td>
								<td width=104 valign=top
									style='width: 78.0pt; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>

								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><b
									style='mso-bidi-font-weight: normal'><span lang=PT-BR>Total</span></b></p>
								</td>
								<td width=96 valign=top
									style='width: 1.0in; border: solid windowtext 1.0pt; border-left: none; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><b
									style='mso-bidi-font-weight: normal'><span lang=PT-BR>Percentual</span></b></p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 1'>
								<td width=440 valign=top
									style='width: 330.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>

								<p class=MsoNormal
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(chamadosPorNatureza.get(controle).toString());} else {out.print("&nbsp;");}%>
								</span></p>
								</td>
								<td width=104 valign=top
									style='width: 78.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(numeroAtendimentos.get(controle).toString()); } else {out.print("&nbsp;");} %>
								</span></p>
								</td>
								<td width=96 valign=top
									style='width: 1.0in; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(percentualAtendimento.get(controle).toString() + "%"); controle++; } else {out.print("&nbsp;");} %>
								</span></p>

								</td>
							</tr>
							<tr style='mso-yfti-irow: 2'>
								<td width=440 valign=top
									style='width: 330.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(chamadosPorNatureza.get(controle).toString());} else {out.print("&nbsp;");}%>
								</span></p>
								</td>
								<td width=104 valign=top
									style='width: 78.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(numeroAtendimentos.get(controle).toString()); } else {out.print("&nbsp;");}%>
								</span></p>

								</td>
								<td width=96 valign=top
									style='width: 1.0in; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(percentualAtendimento.get(controle).toString() + "%"); controle++;} else {out.print("&nbsp;");} %>
								</span></p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 3'>
								<td width=440 valign=top
									style='width: 330.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(chamadosPorNatureza.get(controle).toString());} else {out.print("&nbsp;");}%>
								</span></p>

								</td>
								<td width=104 valign=top
									style='width: 78.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<%if(controle < tamanhochamadosPorNatureza) { out.print(numeroAtendimentos.get(controle).toString()); } else {out.print("&nbsp;");}%>
								</span></p>
								</td>
								<td width=96 valign=top
									style='width: 1.0in; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(percentualAtendimento.get(controle).toString() + "%"); controle++;} else {out.print("&nbsp;");} %>
								</span></p>
								</td>
							</tr>

							<tr style='mso-yfti-irow: 4'>
								<td width=440 valign=top
									style='width: 330.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza) { out.print(chamadosPorNatureza.get(controle).toString());} else {out.print("&nbsp;");}%>
								</span></p>
								</td>
								<td width=104 valign=top
									style='width: 78.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(numeroAtendimentos.get(controle).toString());} else {out.print("&nbsp;");}%>
								</span></p>
								</td>
								<td width=96 valign=top
									style='width: 1.0in; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>

								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(percentualAtendimento.get(controle).toString() + "%"); controle++;} else {out.print("&nbsp;");} %>
								</span></p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 5'>
								<td width=440 valign=top
									style='width: 330.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in'><span
									lang=PT-BR>
								<%if(controle < tamanhochamadosPorNatureza) { out.print(chamadosPorNatureza.get(controle).toString());} else {out.print("&nbsp;");}%>
								</span></p>
								</td>
								<td width=104 valign=top
									style='width: 78.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>

								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(numeroAtendimentos.get(controle).toString()); } else {out.print("&nbsp;");}%>
								</span></p>
								</td>
								<td width=96 valign=top
									style='width: 1.0in; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><span
									lang=PT-BR>
								<% if(controle < tamanhochamadosPorNatureza){ out.print(percentualAtendimento.get(controle).toString() + "%"); controle++;} else {out.print("&nbsp;");} %>
								</span></p>
								</td>
							</tr>
							<tr style='mso-yfti-irow: 6; mso-yfti-lastrow: yes'>
								<td width=440 valign=top
									style='width: 330.0pt; border: solid windowtext 1.0pt; border-top: none; mso-border-top-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>

								<p class=MsoNormal
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in'><b
									style='mso-bidi-font-weight: normal'><span lang=PT-BR>Total de chamados registrados no período</span></b></p>
								</td>
								<td width=104 valign=top
									style='width: 78.0pt; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><b
									style='mso-bidi-font-weight: normal'><span lang=PT-BR>${somaChamados}</span></b></p>
								</td>
								<td width=96 valign=top
									style='width: 1.0in; border-top: none; border-left: none; border-bottom: solid windowtext 1.0pt; border-right: solid windowtext 1.0pt; mso-border-top-alt: solid windowtext .5pt; mso-border-left-alt: solid windowtext .5pt; mso-border-alt: solid windowtext .5pt; padding: 0in 5.4pt 0in 5.4pt'>
								<p class=MsoNormal align=right
									style='margin-top: 3.0pt; margin-right: 0in; margin-bottom: 3.0pt; margin-left: 0in; text-align: right'><b
									style='mso-bidi-font-weight: normal'><span lang=PT-BR>100.00%</span></b></p>

								</td>
								</tr>
						</table>
						<br /><input type="button" name="imprimir" value="Imprimir" onclick="window.print()"/>&nbsp;
						<input type="button" name="Voltar" value="Voltar" onclick="window.back()"/>
						</td>
						</tr>
						</table>
						</fieldset>
						<br />Relatório gerado pelo SISCAB em ${dataGeracao}</td>
					</tr>
				</table>
				</td>
				</tr>
			</table>
</body>
</html>