package br.com.leonardoferreira.jbacon.exception;

/**
 * Throwed when invocation of any method in factory fail
 */
public class JBaconInvocationException extends RuntimeException {
    private static final long serialVersionUID = 8357880288563052949L;

    public JBaconInvocationException(final Exception e) {
        super(e);
    }

}
