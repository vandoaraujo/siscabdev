<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,modelo.OBM,modelo.Usuario,modelo.Viatura,modelo.Atendimento,modelo.MovimentaViatura,dao.MovimentaViaturaDao" %>
<%@ page import="java.util.ArrayList" %>
<%@	page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SISCAB - SISTEMA DE CONTROLE DE ATENDIMENTOS DE BOMBEIROS</title>
	<link href="css/current.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="js/mm_menu.js"></script>
	<script language="JavaScript" src="js/script.js"></script>
</head>
<body>
<% Atendimento at = (Atendimento)  request.getSession().getAttribute("atendimentoAtual");
%>

<script language="JavaScript1.2">mmLoadMenus();</script>
<%Usuario usu =( Usuario) request.getSession().getAttribute("usuario"); 
  String FORMATACAO_DATA = "dd/MM/yyyy HH:mm:ss";

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
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221648_0,0,17,null,'image3')" onMouseOut="MM_startTimeout();"><img src="img/bt_consultas.gif" name="image3" width="109" height="17" border="0" id="image3"></a> 
						<a href="javascript:;" onMouseOver="MM_showMenu(window.mm_menu_0217221434_0,0,17,null,'image2')" onMouseOut="MM_startTimeout();"><img src="img/bt_administrador.gif" name="image2" width="109" height="17" border="0" id="image2"></a>
						<form action="Logoff" onsubmit="fechar()" style="display:inline"><input type="image" src="img/bt_sairsistema.gif" name="close"></form>										
					</td>
					<td align="right" style="padding-right:20px;">
						<strong>Usuário:</strong> <%=usu.getNomeGuerra().toUpperCase() %>&nbsp;&nbsp;<strong>Perfil:</strong> <%=usu.getPerfil().getPerfil_descricao().toUpperCase() %>
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;" colspan="2">
					
					<h2>Atendimentos :: Acompanhar Atendimento :: Ficha do Atendimento :: Viaturas Empenhadas</h2>
				
				
					Número atendimento: <strong>${atendimentoID}</strong>
					<p></p>
				
					
						<fieldset style="width:200px"><legend>&nbsp;Opções&nbsp;</legend>
							<a href="DespacharViatura?numeroAtendimento<%= at.getId() %>">Despachar Viatura</a>
						</fieldset>										
					</td>
				</tr>
				<tr>
					<td style="padding-left:20px; padding-top:20px;">
				
					
					<fieldset style="width:800px"><legend>&nbsp;Viaturas empenhadas neste atendimento&nbsp;</legend>						
								
								<form action="NovaMovimentacaoViatura" method="post">
								<table border="0" cellpadding="4" cellspacing="1" width="100%" bgcolor="#000000">
								<% ArrayList viatura=(ArrayList<Viatura>) request.getSession().getAttribute("viaturas");
									%>
									
									<%if(viatura.size()== 0){ %>
									     <div style="color:red"> Nenhuma viatura empenhada no momento</div>
									   
									<%} else{
										
										%>
										
										<tr bgcolor="#FFFFFF">
										  	<th nowrap="nowrap">Tipo/Número</th>
										  	<th width="350px" nowrap="nowrap">Movimentações</th>
										  	<th width="500px">Opções</th>
										</tr>
									
										<%										
										for(int i=0;i<viatura.size();i++){
											Viatura via =(Viatura)viatura.get(i);
									    
									    if (i%2==0){
									    %> 									    									  									    
									     <tr bgcolor="#EFEFEF">
									     <%}else{ %>
									     
									     <tr bgcolor="#F9D8D0">
									     <%} %>
									    	<td nowrap="nowrap"><%=via.getTipo_viatura().getTipoviatura_abreviacao()%>-<%=via.getNumero()%></td>
									    	<td nowrap="nowrap"><% List <MovimentaViatura> movimentacoes =  MovimentaViaturaDao.getInstance().listaTiposEventosViaturaAtendimento(at.getId(),via.getId());
									    	for(int j = 0 ; j<movimentacoes.size();j++){
									    		  
									    		out.println(new SimpleDateFormat(FORMATACAO_DATA).format(movimentacoes.get(j).getMovimentaviatura_horaEvento()) + " - "  + movimentacoes.get(j).getMovimentaviatura_tipoevento() + "<br>");
									    	}
									    	%>
									    	</td>
									    	<!--  Aqui deveria entrar a lista de movimentações  -->
									    	<td>									    						    
									    		<a href="PreparaNovaMovimentacaoViatura?viaturaAtual=<%=via.getId()%>&atendimentoAtual=<%= at.getId() %>&operacaoARealizar=1"><img src="img/btnRegistrarMovimentacao.gif" border="0"></a>&nbsp;
									    		<a href="NovaMovimentacaoViatura?registro=<%=via.getId()%>&numeroAtendimento=<%= at.getId() %>&operacaoARealizar=2" onclick="if (!confirm('Confirma a liberação?')) return false"><img src="img/btnLiberarViatura.gif" border="0"></a>
									       	</td>
									    </tr>								    										
									   <%} 
									}%>
							</table>
						   </form>
						   </fieldset> 
									<br/>																
							 		<form action="EditaAtendimento" method="post">
										<input type="hidden" value="${atendimentoID}" name="registro">
										<input type="submit" value="Voltar para a ficha do atendimento">
									</form>
					</td>
				</tr>				
			</table>
		</td>
	</tr>
</table>

</body>
</html>