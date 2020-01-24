package br.com.leonardoferreira.jbacon.domain;

import com.github.javafaker.Faker;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SimpleClass {

    private static final String CONSTRAINT_VALUE = "str";

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
        simpleClass.simpleStr = faker.bool().bool() ? faker.gameOfThrones().character() : CONSTRAINT_VALUE;

        return simpleClass;
    }

}
