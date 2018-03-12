package br.com.leonardoferreira.jbacon.exception;

/**
 * Created by lferreira on 2/10/18
 */
public class JBaconTemplateInvalidVisibility extends RuntimeException {
    private static final long serialVersionUID = -3102369853766977125L;

    public JBaconTemplateInvalidVisibility(final String msg) {
        super(msg);
    }

}
