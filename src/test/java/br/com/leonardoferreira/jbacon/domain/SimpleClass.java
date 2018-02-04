package br.com.leonardoferreira.jbacon.domain;

import com.github.javafaker.Faker;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by lferreira on 6/16/17.
 */
@Data
public class SimpleClass {

    private String simpleStr;

    private Integer simpleInteger;

    private BigDecimal simpleBigDecimal;

    public static SimpleClass buildExample() {
        Faker faker = new Faker();
        SimpleClass simpleClass = new SimpleClass();

        simpleClass.simpleBigDecimal = BigDecimal.valueOf(faker.number().randomDouble(2, 0, 30));
        simpleClass.simpleInteger = faker.number().randomDigit();
        simpleClass.simpleStr = faker.gameOfThrones().character();

        return simpleClass;
    }

}
