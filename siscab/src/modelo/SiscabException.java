package modelo;

public class SiscabException extends Throwable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static String mensagem;

    public static String getMensagem() {
	return mensagem;
    }

    public SiscabException(String mensagem) {
	super();
	SiscabException.mensagem = mensagem;
    }

    public void setMensagem(String mensagem) {
	SiscabException.mensagem = mensagem;
    }

}
