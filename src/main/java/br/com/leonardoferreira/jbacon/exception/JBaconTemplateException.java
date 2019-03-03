package br.com.leonardoferreira.jbacon.exception;

/**
 * Throwed when template method violate validations of {@link br.com.leonardoferreira.jbacon.annotation.JBaconTemplate}
 */
public class JBaconTemplateException extends RuntimeException {
    private static final long serialVersionUID = -6134181642432696324L;

    public JBaconTemplateException(final String msg) {
        super(msg);
    }

}
