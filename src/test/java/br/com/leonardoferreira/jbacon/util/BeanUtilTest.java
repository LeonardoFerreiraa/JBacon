package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.domain.SimpleInheritorClass;
import br.com.leonardoferreira.jbacon.util.BeanUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by lferreira on 6/16/17.
 */
@RunWith(JUnit4.class)
public class BeanUtilTest {

    @Test
    public void copyTest() {
        SimpleClass source = SimpleClass.buildExample();
        SimpleClass target = new SimpleClass();

        BeanUtils.copyPropertiesNotNull(source, target);

        Assertions.assertThat(target.getSimpleBigDecimal())
                .isEqualTo(source.getSimpleBigDecimal());

        Assertions.assertThat(target.getSimpleInteger())
                .isEqualTo(source.getSimpleInteger());

        Assertions.assertThat(target.getSimpleStr())
                .isEqualTo(source.getSimpleStr());
    }

    @Test
    public void copyWithSuperClas() {
        SimpleInheritorClass source = SimpleInheritorClass.build();
        SimpleInheritorClass target = new SimpleInheritorClass();

        BeanUtils.copyPropertiesNotNull(source, target);

        Assertions.assertThat(target.getSimpleStr())
                .isEqualTo(source.getSimpleStr());

        Assertions.assertThat(target.getSimpleSuperStr())
                .isEqualTo(source.getSimpleSuperStr());
    }

    @Test
    public void copyNonNullTest() {
        SimpleClass source = SimpleClass.buildExample();
        SimpleClass target = new SimpleClass();
        target.setSimpleInteger(123321);

        BeanUtils.copyPropertiesNotNull(source, target);

        Assertions.assertThat(target.getSimpleBigDecimal())
                .isEqualTo(source.getSimpleBigDecimal());

        Assertions.assertThat(target.getSimpleInteger())
                .isEqualTo(123321)
                .isNotEqualTo(source.getSimpleInteger());

        Assertions.assertThat(target.getSimpleStr())
                .isEqualTo(source.getSimpleStr());
    }

    @Test
    public void copyNonNullWithSuperClass() {
        SimpleInheritorClass source = SimpleInheritorClass.build();
        SimpleInheritorClass target = new SimpleInheritorClass();
        target.setSimpleStr("qwe");

        BeanUtils.copyPropertiesNotNull(source, target);

        Assertions.assertThat(target.getSimpleStr())
                .isEqualTo("qwe")
                .isNotEqualTo(source.getSimpleStr());

        Assertions.assertThat(target.getSimpleSuperStr())
                .isEqualTo(source.getSimpleSuperStr());
    }

}
