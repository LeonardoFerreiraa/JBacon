package br.com.leonardoferreira.jbacon.domain;

import lombok.Setter;

/**
 * Created by lferreira on 2/4/18
 */
public class ExplosiveClass {

    @Setter
    private String str;

    public String getStr() {
        throw new RuntimeException("inaccessible property");
    }
}
