package br.com.leonardoferreira.jbacon.domain;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleInheritorClass extends SimpleSuperClass {

    private String simpleStr;

    private boolean simpleBool;

    public static SimpleInheritorClass build() {
        Faker faker = new Faker();
        SimpleInheritorClass simpleInheritorClass = new SimpleInheritorClass();
        simpleInheritorClass.simpleStr = faker.gameOfThrones().character();
        simpleInheritorClass.simpleSuperStr = faker.gameOfThrones().dragon();
        simpleInheritorClass.simpleBool = true;

        return simpleInheritorClass;
    }

}
