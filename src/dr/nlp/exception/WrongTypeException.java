package dr.nlp.exception;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public class WrongTypeException extends Exception {
	public WrongTypeException() {
		super();
	}

	public WrongTypeException(String message) {
		super(message);
	}

	public WrongTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongTypeException(Throwable cause) {
		super(cause);
	}
}
