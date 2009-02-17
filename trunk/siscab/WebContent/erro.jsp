<%@ page isErrorPage="true" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">  
<html>  
<head>  
<title>Erro</title>  
<link href="css/estilo.css" rel="stylesheet" type="text/css" />  
</head>  
<body>
<h2><font color="blue">SISCAB EXCEPTION!</font> </h2>

  
<div>
ERRO:<p>  
<%exception.printStackTrace(new java.io.PrintWriter(out, true));%>  
</div>  
</body>  
</html>  