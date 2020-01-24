package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.domain.SimpleInheritorClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CopyPropertiesTest {

    @Test
    public void copyTest() {
        SimpleClass source = SimpleClass.buildExample();
        SimpleClass target = new SimpleClass();

        CopyProperties.copy(target, source);

        Assertions.assertAll("target content",
                () -> Assertions.assertEquals(source.getSimpleBigDecimal(), target.getSimpleBigDecimal()),
                () -> Assertions.assertEquals(source.getSimpleInteger(), target.getSimpleInteger()),
                () -> Assertions.assertEquals(source.getSimpleStr(), target.getSimpleStr()));
    }

    @Test
    public void copyWithSuperClass() {
        SimpleInheritorClass source = SimpleInheritorClass.build();
        SimpleInheritorClass target = new SimpleInheritorClass();

        CopyProperties.copy(target, source);

        Assertions.assertAll(
                () -> Assertions.assertEquals(source.getSimpleStr(), target.getSimpleStr()),
                () -> Assertions.assertEquals(source.getSimpleSuperStr(), target.getSimpleSuperStr()));
    }

    @Test
    public void copyNonNullTest() {
        SimpleClass source = SimpleClass.buildExample();
        SimpleClass target = new SimpleClass();
        target.setSimpleInteger(123321);

        CopyProperties.copy(target, source);

        Assertions.assertAll("target content",
                () -> Assertions.assertEquals(source.getSimpleBigDecimal(), target.getSimpleBigDecimal()),
                () -> Assertions.assertEquals(source.getSimpleStr(), target.getSimpleStr()),
                () -> Assertions.assertEquals(Integer.valueOf(123321), target.getSimpleInteger()),
                () -> Assertions.assertNotEquals(source.getSimpleInteger(), target.getSimpleInteger()));
    }

    @Test
    public void copyNonNullWithSuperClass() {
        SimpleInheritorClass source = SimpleInheritorClass.build();
        SimpleInheritorClass target = new SimpleInheritorClass();
        target.setSimpleStr("qwe");

        CopyProperties.copy(target, source);

        Assertions.assertAll("target content",
                () -> Assertions.assertNotEquals(source.getSimpleStr(), target.getSimpleSuperStr()),
                () -> Assertions.assertEquals("qwe", target.getSimpleStr()),
                () -> Assertions.assertNotEquals(source.getSimpleStr(), target.getSimpleStr()));
    }

}
