package board;
/**
 * Excepcion lanzada por {@link Board} cuando el tablero recibido es invalido.
 * @author santi698
 *
 */
public class InvalidLevelException extends Exception {
	public String message;
	public InvalidLevelException(String string) {
		message = string;
	}
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
