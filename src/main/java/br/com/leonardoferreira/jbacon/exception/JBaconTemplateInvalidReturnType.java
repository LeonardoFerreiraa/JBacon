package br.com.leonardoferreira.jbacon.exception;

/**
 * Created by lferreira on 2/4/18
 */
public class JBaconTemplateInvalidReturnType extends RuntimeException {
    private static final long serialVersionUID = -8976882490842323947L;

    public JBaconTemplateInvalidReturnType(final String msg) {
        super(msg);
    }

}
