package br.com.leonardoferreira.jbacon.exception;

/**
 * Created by lferreira on 2/4/18
 */
public class JBaconInvocationException extends RuntimeException {
    private static final long serialVersionUID = 8357880288563052949L;

    public JBaconInvocationException(final Exception e) {
        super(e);
    }

}
