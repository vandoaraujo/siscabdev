package modelo;

import java.util.Date;
import java.util.Locale;

import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;

 
public class Hoje {

  public static void main(String[] args) {

    Date hoje = new Date();

    System.out.println("Hoje e Agora, para depuração:\n" + hoje);

     DateFormat dataCurta = DateFormat.getDateInstance();

    DateFormat horaCurta = DateFormat.getTimeInstance();

    DateFormat dataHoraCurta = DateFormat.getDateTimeInstance();

    System.out.println("Hoje:\t" + dataCurta.format(hoje));

    System.out.println("Agora:\t" + horaCurta.format(hoje));

    System.out.println("Hoje e Agora, curtos:\n" +

      dataHoraCurta.format(hoje));

 

    DateFormat dataHoraLongo = DateFormat.getDateTimeInstance(

      DateFormat.LONG, DateFormat.LONG);

    System.out.println("Hoje e Agora, longos:\n" +

      dataHoraLongo.format(hoje));

 

    DateFormat dataHoraCustomizado = new SimpleDateFormat(

      "EEEE, dd/MMM/yyyy ? hh:mm a");

    System.out.println("Hoje e Agora, customizado:\n" +

      dataHoraCustomizado.format(hoje));
    
    Date dataAtual = null;	
	//Faz o parse de String em data		
	String str = "Apr 19, 2005";  
	DateFormat dt = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());  
	try {
		dataAtual = dt.parse(str);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
			
	System.out.println("#########################");
	
	System.out.println("data" + dataAtual);
	
	System.out.println("#########################");

  }

}

