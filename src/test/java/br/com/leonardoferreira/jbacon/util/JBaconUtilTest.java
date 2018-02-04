package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateNotFound;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by lferreira on 2/4/18
 */
@RunWith(JUnit4.class)
public class JBaconUtilTest {

    @Test(expected = JBaconTemplateNotFound.class)
    public void findTemplateByNameWithoutTemplatesTest() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                return null;
            }

            @Override
            public SimpleClass getEmpty() {
                return null;
            }

            @Override
            public void persist(SimpleClass simpleClass) {

            }
        };

        JBaconUtil.findTemplateByName("notFound", jBacon);
    }

    @Test
    public void findTemplateByNameWithOneTemplateTest() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                return null;
            }

            @Override
            public SimpleClass getEmpty() {
                return null;
            }

            @Override
            public void persist(SimpleClass simpleClass) {

            }

            @JBaconTemplate("myTemplate")
            public SimpleClass myTemplate() {
                return new SimpleClass("findTemplateByNameWithOneTemplateTest");
            }
        };

        SimpleClass myTemplate = JBaconUtil.findTemplateByName("myTemplate", jBacon);
        Assertions.assertThat(myTemplate)
                .isNotNull();
        Assertions.assertThat(myTemplate.getSimpleStr())
                .isEqualTo("findTemplateByNameWithOneTemplateTest");
    }

    @Test
    public void findTemplateByNameWithManyTemplatesTest() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                return null;
            }

            @Override
            public SimpleClass getEmpty() {
                return null;
            }

            @Override
            public void persist(SimpleClass simpleClass) {

            }

            @JBaconTemplate("template1")
            public SimpleClass template1() {
                return new SimpleClass("findTemplateByNameWithManyTemplatesTest_1");
            }

            @JBaconTemplate("template2")
            public SimpleClass template2() {
                return new SimpleClass("findTemplateByNameWithManyTemplatesTest_2");
            }

            @JBaconTemplate("template3")
            public SimpleClass template3() {
                return new SimpleClass("findTemplateByNameWithManyTemplatesTest_3");
            }
        };

        SimpleClass template1 = JBaconUtil.findTemplateByName("template1", jBacon);
        Assertions.assertThat(template1)
                .isNotNull();
        Assertions.assertThat(template1.getSimpleStr())
                .isEqualTo("findTemplateByNameWithManyTemplatesTest_1");

        SimpleClass template2 = JBaconUtil.findTemplateByName("template2", jBacon);
        Assertions.assertThat(template2)
                .isNotNull();
        Assertions.assertThat(template2.getSimpleStr())
                .isEqualTo("findTemplateByNameWithManyTemplatesTest_2");

        SimpleClass template3 = JBaconUtil.findTemplateByName("template3", jBacon);
        Assertions.assertThat(template3)
                .isNotNull();
        Assertions.assertThat(template3.getSimpleStr())
                .isEqualTo("findTemplateByNameWithManyTemplatesTest_3");
    }
}
