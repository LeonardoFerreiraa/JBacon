package br.com.leonardoferreira.jbacon.exception;

/**
 * Created by lferreira on 2/4/18
 */
public class JBaconInvocationException extends RuntimeException {

    public JBaconInvocationException(Exception e) {
        super(e);
    }

}
