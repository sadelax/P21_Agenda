package agenda.excepciones;

@SuppressWarnings("serial")
public class PersistenciaExcepcion extends RuntimeException {

	public PersistenciaExcepcion() {
		super();
	}

	public PersistenciaExcepcion(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PersistenciaExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenciaExcepcion(String message) {
		super(message);
	}

	public PersistenciaExcepcion(Throwable cause) {
		super(cause);
	}

	
}
