function mmLoadMenus() {
  if (window.mm_menu_0217221104_0) return;
  window.mm_menu_0217221104_0 = new Menu("root",250,19,"Trebuchet MS",13,"#FFFFFF","#000000","#5557A7","#BABBDC","left","middle",5,1,1000,-5,7,true,false,true,0,true,true);
  mm_menu_0217221104_0.addMenuItem("Painel&nbsp;de&nbsp;Situação&nbsp;dos&nbsp;Atendimentos","location='situacaoAtendimento.jsp'");
  mm_menu_0217221104_0.addMenuItem("Registrar&nbsp;Chamado","location='RegistrarChamado'");
  mm_menu_0217221104_0.addMenuItem("Transferir&nbsp;Atendimento","location='TransferirAtendimento'");
  mm_menu_0217221104_0.addMenuItem("Acompanhar&nbsp;Atendimento","location='AcompanharAtendimentos'");  
  mm_menu_0217221104_0.addMenuItem("Cadastro&nbsp;de&nbsp;Viaturas","location='ViaturasControle'");
  mm_menu_0217221104_0.hideOnMouseOut=true;
  mm_menu_0217221104_0.bgColor='#555555';
  mm_menu_0217221104_0.menuBorder=1;
  mm_menu_0217221104_0.menuLiteBgColor='#FFFFFF';
  mm_menu_0217221104_0.menuBorderBgColor='#777777';
  
  window.mm_menu_0217221434_0 = new Menu("root",155,23,"Trebuchet MS",13,"#FFFFFF","#000000","#5557A7","#BABBDC","left","middle",5,1,1000,-5,7,true,false,true,0,true,true);
  mm_menu_0217221434_0.addMenuItem("Cadastro&nbsp;de&nbsp;Usuários","location='AdministracaoUsuario'");
  mm_menu_0217221434_0.addMenuItem("Cadastro&nbsp;de&nbsp;OBMs","location='ObmControle'");
  mm_menu_0217221434_0.hideOnMouseOut=true;
  mm_menu_0217221434_0.bgColor='#555555';
  mm_menu_0217221434_0.menuBorder=1;
  mm_menu_0217221434_0.menuLiteBgColor='#FFFFFF';
  mm_menu_0217221434_0.menuBorderBgColor='#777777';

  window.mm_menu_0217221648_0 = new Menu("root",300,19,"Trebuchet MS",13,"#FFFFFF","#000000","#5557A7","#BABBDC","left","middle",5,1,1000,-5,7,true,false,true,0,true,true);
  
  mm_menu_0217221648_0.addMenuItem("Total&nbsp;de&nbsp;Chamados&nbsp;por&nbsp;Natureza","location='IniciaTotalChamadosPorNatureza'");
  mm_menu_0217221648_0.addMenuItem("Total&nbsp;de&nbsp;Atendimentos&nbsp;por&nbsp;Modo&nbsp;de&nbsp;Fechamento","location='iniciaTotalAtendimentosPorModoFechamento.jsp'");
  mm_menu_0217221648_0.addMenuItem("Atendimentos&nbsp;por&nbsp;Tipo&nbsp;Ocorrencia","location='iniciaTotalAtendimentosPorTipoOcorrencia.jsp'");
  mm_menu_0217221648_0.hideOnMouseOut=true;
  mm_menu_0217221648_0.bgColor='#555555';
  mm_menu_0217221648_0.menuBorder=1;
  mm_menu_0217221648_0.menuLiteBgColor='#FFFFFF';
  mm_menu_0217221648_0.menuBorderBgColor='#777777';
  

  mm_menu_0217221648_0.writeMenus();
} // mmLoadMenus()



function fechar(){
	window.close();
}


function SomenteNumeros(e)
{
    var tecla=(window.event)?event.keyCode:e.which;
    if((tecla > 47 && tecla < 58) || (tecla == 9 || tecla == 8 || tecla == 46)) 
		return true;
    else
		return false;
}

function SomenteLetras(e)
{
	var tecla=(window.event)?event.keyCode:e.which;
	if((tecla > 65 && tecla < 90) || (tecla > 97 && tecla < 122)  || (tecla == 8 || tecla == 0)) 
		return true; 
	else
		return false;
}

function mascaraData(campoData, e){
	var tecla=(window.event)?event.keyCode:e.which;
	if ((tecla == 8 || tecla == 0))
		return true;

	if((tecla > 47 && tecla < 58))
	{
		var data = campoData.value;
		if (data.length == 2)
		{
			data = data + '/';
			document.forms[0].data.value = data;
			return true;              
		}
		if (data.length == 5){
			  data = data + '/';
			  document.forms[0].data.value = data;
			  return true;
		}
	}
	else
		return false;		

	return true;
}


