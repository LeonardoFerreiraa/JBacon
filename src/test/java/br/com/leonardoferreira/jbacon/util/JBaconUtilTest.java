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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by lferreira on 2/4/18
 */
public class JBaconUtilTest {

    @Test
    public void validateWithInvalidReturnType() {
        Assertions.assertThrows(JBaconTemplateInvalidReturnType.class, () ->
                new JBacon<SimpleClass>() {
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
                });
    }

    @Test
    public void validateWithInvalidParameterNumber() {
        Assertions.assertThrows(JBaconTemplateParameterException.class, () ->
                new JBacon<SimpleClass>() {
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
                });
    }

    @Test
    public void validateTemplateNotProtected() {
        Assertions.assertThrows(JBaconTemplateInvalidVisibility.class, () ->
                new JBacon<SimpleClass>() {
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
                });
    }

    @Test
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
        Assertions.assertThrows(JBaconInvocationException.class,
                () -> jBaconUtil.findTemplateByName("template"));
    }

    @Test
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
        Assertions.assertThrows(JBaconTemplateNotFound.class,
                () -> jBaconUtil.findTemplateByName("notFound"));
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

        Assertions.assertNotNull(myTemplate);
        Assertions.assertEquals("findTemplateByNameWithOneTemplateTest", myTemplate.getSimpleStr());
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
        Assertions.assertNotNull(template1);
        Assertions.assertEquals("findTemplateByNameWithManyTemplatesTest_1", template1.getSimpleStr());

        SimpleClass template2 = jBaconUtil.findTemplateByName("template2");
        Assertions.assertNotNull(template2);
        Assertions.assertEquals("findTemplateByNameWithManyTemplatesTest_2", template2.getSimpleStr());

        SimpleClass template3 = jBaconUtil.findTemplateByName("template3");
        Assertions.assertNotNull(template3);
        Assertions.assertEquals("findTemplateByNameWithManyTemplatesTest_3", template3.getSimpleStr());
    }

}
