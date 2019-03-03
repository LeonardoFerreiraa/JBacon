package br.com.leonardoferreira.jbacon.domain;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SimpleClass {

    private String simpleStr;

    private Integer simpleInteger;

    private BigDecimal simpleBigDecimal;

    public SimpleClass(final String simpleStr) {
        this.simpleStr = simpleStr;
    }

    public SimpleClass(final String simpleStr, final Integer simpleInteger) {
        this.simpleStr = simpleStr;
        this.simpleInteger = simpleInteger;
    }

    public static SimpleClass buildExample() {
        Faker faker = new Faker();
        SimpleClass simpleClass = new SimpleClass();

        simpleClass.simpleBigDecimal = BigDecimal.valueOf(faker.number().randomDouble(2, 0, 30));
        simpleClass.simpleInteger = faker.number().randomDigit();
        simpleClass.simpleStr = faker.gameOfThrones().character();

        return simpleClass;
    }

}
