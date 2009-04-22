<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Viatura,modelo.Atendimento" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - Relatório de Total de Atendimentos por Modo de Fechamento</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
</head>
<body>
<script language="JavaScript1.2">mmLoadMenus();</script>
<%! int controle,tamanhoModoFechamento,tamanhoNumeroAtendimento=0; %>
<%		List modoFechamento = (List)request.getAttribute("modosFechamento"); 
		List numeroAtendimentos = (List)request.getAttribute("qtdAtendimentos");
%>				
<%		tamanhoModoFechamento = modoFechamento.size();
%>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr style="height:139px;">
					<td align="right" style="padding-right:20px;">  	
					 		<img src="img/logo.png">	 	
					 </td>
				</tr>
				<tr>
					<fieldset style="width:900px"><legend>&nbsp; RELATÓRIO DE ATENDIMENTOS POR MODO DE FECHAMENTO&nbsp;</legend> </fieldset>
					<table border="0" cellpadding="0" cellspacing="3" width="100%">
					<td style="padding-left:20px; padding-top:20px;">
						<fieldset style="width:900px"><legend>&nbsp;Período de referênca: ${dataInicial} a ${dataFinal}.&nbsp;</legend>						
								<table border="0" cellpadding="0" cellspacing="3" width="100%">
								<tr>
									<td>
								
<table border=1 cellspacing=0 cellpadding=0>
 <tr>
  <td width=440 valign=top style='width:330.0pt;border:solid windowtext 1.0pt;
  '>
  <p class=MsoNormal style='margin-top:3.0pt;margin-right:0in;margin-bottom:
  3.0pt;margin-left:0in'><b style='mso-bidi-font-weight:normal'><span
  lang=PT-BR>Modo de Fechamento do Atendimento</span></b></p>
  </td>
  <td width=104 valign=top style='width:78.0pt;border:solid windowtext 1.0pt;
  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>

  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><b style='mso-bidi-font-weight:
  normal'><span lang=PT-BR>Total</span></b></p>
  </td>
  <td width=96 valign=top style='width:1.0in;border:solid windowtext 1.0pt;
  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><b style='mso-bidi-font-weight:
  normal'><span lang=PT-BR>Percentual</span></b></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:1'>
  <td width=440 valign=top style='width:330.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt'>

  <p class=MsoNormal style='margin-top:3.0pt;margin-right:0in;margin-bottom:
  3.0pt;margin-left:0in'><span lang=PT-BR><% if(controle < tamanhoModoFechamento){ out.print(modoFechamento.get(controle).toString());} else {out.print("&nbsp;");}%></span></p>
  </td>
  <td width=104 valign=top style='width:78.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR><% if(controle < tamanhoModoFechamento){ out.print(numeroAtendimentos.get(controle).toString()); controle++;} else {out.print("0 ");} %></span></p>
  </td>
  <td width=96 valign=top style='width:1.0in;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR>00.00%</span></p>

  </td>
 </tr>
 <tr style='mso-yfti-irow:2'>
  <td width=440 valign=top style='width:330.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal style='margin-top:3.0pt;margin-right:0in;margin-bottom:
  3.0pt;margin-left:0in'><span lang=PT-BR><% if(controle < tamanhoModoFechamento){ out.print(modoFechamento.get(controle).toString());} else {out.print("&nbsp;");}%> </span></p>
  </td>
  <td width=104 valign=top style='width:78.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR><% if(controle < tamanhoModoFechamento){ out.print(numeroAtendimentos.get(controle).toString()); controle++;} else {out.print("0");}%></span></p>

  </td>
  <td width=96 valign=top style='width:1.0in;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR>00.00%</span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:3'>
  <td width=440 valign=top style='width:330.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal style='margin-top:3.0pt;margin-right:0in;margin-bottom:
  3.0pt;margin-left:0in'><span lang=PT-BR><% if(controle < tamanhoModoFechamento){ out.print(modoFechamento.get(controle).toString());} else {out.print("&nbsp;");}%></span></p>

  </td>
  <td width=104 valign=top style='width:78.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR><%if(controle < tamanhoModoFechamento) { out.print(numeroAtendimentos.get(controle).toString()); controle++;} else {out.print("0");}%></span></p>
  </td>
  <td width=96 valign=top style='width:1.0in;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR>00.00%</span></p>
  </td>
 </tr>

 <tr style='mso-yfti-irow:4'>
  <td width=440 valign=top style='width:330.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal style='margin-top:3.0pt;margin-right:0in;margin-bottom:
  3.0pt;margin-left:0in'><span lang=PT-BR><% if(controle < tamanhoModoFechamento) { out.print(modoFechamento.get(controle).toString());} else {out.print("&nbsp;");}%></span></p>
  </td>
  <td width=104 valign=top style='width:78.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR><% if(controle < tamanhoModoFechamento){ out.print(numeroAtendimentos.get(controle).toString());controle++;} else {out.print("0");}%></span></p>
  </td>
  <td width=96 valign=top style='width:1.0in;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>

  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR>0.00%</span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:5'>
  <td width=440 valign=top style='width:330.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal style='margin-top:3.0pt;margin-right:0in;margin-bottom:
  3.0pt;margin-left:0in'><span lang=PT-BR><%if(controle < tamanhoModoFechamento) { out.print(modoFechamento.get(controle).toString());} else {out.print("&nbsp;");}%></span></p>
  </td>
  <td width=104 valign=top style='width:78.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>

  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR><% if(controle < tamanhoModoFechamento){ out.print(numeroAtendimentos.get(controle).toString()); controle++;} else {out.print("0");}%></span></p>
  </td>
  <td width=96 valign=top style='width:1.0in;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><span lang=PT-BR>0.00%</span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:6;mso-yfti-lastrow:yes'>
  <td width=440 valign=top style='width:330.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0in 5.4pt 0in 5.4pt'>

  <p class=MsoNormal style='margin-top:3.0pt;margin-right:0in;margin-bottom:
  3.0pt;margin-left:0in'><b style='mso-bidi-font-weight:normal'><span
  lang=PT-BR>Total de atendimentos registrados no período</span></b></p>
  </td>
  <td width=104 valign=top style='width:78.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><b style='mso-bidi-font-weight:
  normal'><span lang=PT-BR>0000</span></b></p>
  </td>
  <td width=96 valign=top style='width:1.0in;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0in 5.4pt 0in 5.4pt'>
  <p class=MsoNormal align=right style='margin-top:3.0pt;margin-right:0in;
  margin-bottom:3.0pt;margin-left:0in;text-align:right'><b style='mso-bidi-font-weight:
  normal'><span lang=PT-BR>100.00%</span></b></p>

  </td>
 </tr>
</table>
Relatório gerado pelo SISCAB em ${dataGeracao}							
									
							
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>

</body>
</html>