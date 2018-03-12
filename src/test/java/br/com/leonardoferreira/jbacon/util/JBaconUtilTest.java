package br.com.leonardoferreira.jbacon.util;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.domain.SimpleSuperClass;
import br.com.leonardoferreira.jbacon.exception.JBaconInvocationException;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateInvalidReturnType;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateInvalidVisibility;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateNotFound;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateParameterException;
import br.com.leonardoferreira.jbacon.exception.ShouldNotBeCalled;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by lferreira on 2/4/18
 */
@RunWith(JUnit4.class)
public class JBaconUtilTest {

    @Test(expected = JBaconTemplateInvalidReturnType.class)
    public void validateWithInvalidReturnType() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                return null;
            }

            @Override
            public SimpleClass getEmpty() {
                return null;
            }

            @JBaconTemplate("template")
            public SimpleSuperClass template() {
                return new SimpleSuperClass();
            }

            @Override
            public void persist(final SimpleClass simpleClass) {
                throw new ShouldNotBeCalled();
            }
        };

        new JBaconUtil<>(jBacon);
    }

    @Test(expected = JBaconTemplateParameterException.class)
    public void validateWithInvalidParameterNumber() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                return null;
            }

            @Override
            public SimpleClass getEmpty() {
                return null;
            }

            @JBaconTemplate("template")
            public SimpleClass template(final String s) {
                return new SimpleClass();
            }

            @Override
            public void persist(final SimpleClass simpleClass) {
                throw new ShouldNotBeCalled();
            }
        };

        new JBaconUtil<>(jBacon);
    }

    @Test(expected = JBaconTemplateInvalidVisibility.class)
    public void validateTemplateNotProtected() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                return null;
            }

            @Override
            protected SimpleClass getEmpty() {
                return null;
            }

            @JBaconTemplate("template")
            public SimpleClass template() {
                return null;
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                throw new ShouldNotBeCalled();
            }
        };
        new JBaconUtil<>(jBacon);
    }

    @Test(expected = JBaconInvocationException.class)
    public void findTemplateByNameTemplateMethodThrowsException() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                return null;
            }

            @Override
            protected SimpleClass getEmpty() {
                return null;
            }

            @JBaconTemplate("template")
            protected SimpleClass template() throws Exception {
                throw new Exception();
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                throw new ShouldNotBeCalled();
            }
        };

        JBaconUtil<SimpleClass> jBaconUtil = new JBaconUtil<>(jBacon);
        jBaconUtil.findTemplateByName("template");
    }

    @Test(expected = JBaconTemplateNotFound.class)
    public void findTemplateByNameWithoutTemplatesTest() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                return null;
            }

            @Override
            protected SimpleClass getEmpty() {
                return null;
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                throw new ShouldNotBeCalled();
            }
        };

        JBaconUtil<SimpleClass> jBaconUtil = new JBaconUtil<>(jBacon);
        jBaconUtil.findTemplateByName("notFound");
    }

    @Test
    public void findTemplateByNameWithOneTemplateTest() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                return null;
            }

            @Override
            protected SimpleClass getEmpty() {
                return null;
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                throw new ShouldNotBeCalled();
            }

            @JBaconTemplate("myTemplate")
            protected SimpleClass myTemplate() {
                return new SimpleClass("findTemplateByNameWithOneTemplateTest");
            }
        };

        JBaconUtil<SimpleClass> jBaconUtil = new JBaconUtil<>(jBacon);
        SimpleClass myTemplate = jBaconUtil.findTemplateByName("myTemplate");
        Assertions.assertThat(myTemplate)
                .isNotNull();
        Assertions.assertThat(myTemplate.getSimpleStr())
                .isEqualTo("findTemplateByNameWithOneTemplateTest");
    }

    @Test
    public void findTemplateByNameWithManyTemplatesTest() {
        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                return null;
            }

            @Override
            protected SimpleClass getEmpty() {
                return null;
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                throw new ShouldNotBeCalled();
            }

            @JBaconTemplate("template1")
            protected SimpleClass template1() {
                return new SimpleClass("findTemplateByNameWithManyTemplatesTest_1");
            }

            @JBaconTemplate("template2")
            protected SimpleClass template2() {
                return new SimpleClass("findTemplateByNameWithManyTemplatesTest_2");
            }

            @JBaconTemplate("template3")
            protected SimpleClass template3() {
                return new SimpleClass("findTemplateByNameWithManyTemplatesTest_3");
            }
        };

        JBaconUtil<SimpleClass> jBaconUtil = new JBaconUtil<>(jBacon);
        SimpleClass template1 = jBaconUtil.findTemplateByName("template1");
        Assertions.assertThat(template1)
                .isNotNull();
        Assertions.assertThat(template1.getSimpleStr())
                .isEqualTo("findTemplateByNameWithManyTemplatesTest_1");

        SimpleClass template2 = jBaconUtil.findTemplateByName("template2");
        Assertions.assertThat(template2)
                .isNotNull();
        Assertions.assertThat(template2.getSimpleStr())
                .isEqualTo("findTemplateByNameWithManyTemplatesTest_2");

        SimpleClass template3 = jBaconUtil.findTemplateByName("template3");
        Assertions.assertThat(template3)
                .isNotNull();
        Assertions.assertThat(template3.getSimpleStr())
                .isEqualTo("findTemplateByNameWithManyTemplatesTest_3");
    }

}
