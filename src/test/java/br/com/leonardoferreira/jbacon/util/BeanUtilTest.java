package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.domain.SimpleInheritorClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeanUtilTest {

    @Test
    public void copyTest() {
        SimpleClass source = SimpleClass.buildExample();
        SimpleClass target = new SimpleClass();

        BeanUtils.copyPropertiesNotNull(source, target);

        Assertions.assertAll("target content",
                () -> Assertions.assertEquals(source.getSimpleBigDecimal(), target.getSimpleBigDecimal()),
                () -> Assertions.assertEquals(source.getSimpleInteger(), target.getSimpleInteger()),
                () -> Assertions.assertEquals(source.getSimpleStr(), target.getSimpleStr()));
    }

    @Test
    public void copyWithSuperClass() {
        SimpleInheritorClass source = SimpleInheritorClass.build();
        SimpleInheritorClass target = new SimpleInheritorClass();

        BeanUtils.copyPropertiesNotNull(source, target);

        Assertions.assertAll(
                () -> Assertions.assertEquals(source.getSimpleStr(), target.getSimpleStr()),
                () -> Assertions.assertEquals(source.getSimpleSuperStr(), target.getSimpleSuperStr()));
    }

    @Test
    public void copyNonNullTest() {
        SimpleClass source = SimpleClass.buildExample();
        SimpleClass target = new SimpleClass();
        target.setSimpleInteger(123321);

        BeanUtils.copyPropertiesNotNull(source, target);

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

        BeanUtils.copyPropertiesNotNull(source, target);

        Assertions.assertAll("target content",
                () -> Assertions.assertNotEquals(source.getSimpleStr(), target.getSimpleSuperStr()),
                () -> Assertions.assertEquals("qwe", target.getSimpleStr()),
                () -> Assertions.assertNotEquals(source.getSimpleStr(), target.getSimpleStr()));
    }

}
