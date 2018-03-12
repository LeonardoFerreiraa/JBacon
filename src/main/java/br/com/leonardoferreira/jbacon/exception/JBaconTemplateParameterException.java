package br.com.leonardoferreira.jbacon.exception;

/**
 * Created by lferreira on 2/4/18
 */
public class JBaconTemplateParameterException extends RuntimeException {
    private static final long serialVersionUID = 437822298231767961L;

    public JBaconTemplateParameterException(final String msg) {
        super(msg);
    }
}
