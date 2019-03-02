package br.com.leonardoferreira.jbacon.lazy.domain;

import lombok.Data;

@Data
public class Phone {

    private String number;

    private Contact contact;

}