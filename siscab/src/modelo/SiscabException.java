package modelo;

public class SiscabException extends Throwable {
	
	private static String mensagem;

	public SiscabException(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public static String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	

}
