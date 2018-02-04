package br.com.leonardoferreira.jbacon.domain;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by lferreira on 6/16/17.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleInheritorClass extends SimpleSuperClass {

    private String simpleStr;


    public static SimpleInheritorClass build() {
        Faker faker = new Faker();
        SimpleInheritorClass simpleInheritorClass = new SimpleInheritorClass();
        simpleInheritorClass.simpleStr = faker.gameOfThrones().character();
        simpleInheritorClass.simpleSuperStr = faker.gameOfThrones().dragon();

        return simpleInheritorClass;
    }

}
