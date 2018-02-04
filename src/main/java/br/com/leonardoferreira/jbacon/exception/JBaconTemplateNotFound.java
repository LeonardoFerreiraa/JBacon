package br.com.leonardoferreira.jbacon.exception;

/**
 * Created by lferreira on 2/4/18
 */
public class JBaconTemplateNotFound extends RuntimeException {

    public JBaconTemplateNotFound(final String templateName) {
        super(templateName + " template not found");
    }

}
