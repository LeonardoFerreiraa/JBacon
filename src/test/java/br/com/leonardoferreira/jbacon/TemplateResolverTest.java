package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.domain.SimpleSuperClass;
import br.com.leonardoferreira.jbacon.exception.JBaconInvocationException;
import br.com.leonardoferreira.jbacon.exception.JBaconTemplateException;
import br.com.leonardoferreira.jbacon.exception.ShouldNotBeCalled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by lferreira on 2/4/18
 */
public class TemplateResolverTest {

    @Test
    public void validateWithInvalidReturnType() {
        JBaconTemplateException exception = Assertions.assertThrows(JBaconTemplateException.class, () ->
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
                    protected SimpleSuperClass template() {
                        return new SimpleSuperClass();
                    }

                    @Override
                    protected void persist(final SimpleClass simpleClass) {
                        throw new ShouldNotBeCalled();
                    }
                });
        Assertions.assertEquals("Template methods should return the same type of factory", exception.getMessage());
    }

    @Test
    public void validateWithInvalidParameterNumber() {
        JBaconTemplateException exception = Assertions.assertThrows(JBaconTemplateException.class, () ->
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
                    protected SimpleClass template(final String s) {
                        return new SimpleClass();
                    }

                    @Override
                    protected void persist(final SimpleClass simpleClass) {
                        throw new ShouldNotBeCalled();
                    }
                });
        Assertions.assertEquals("Template methods should not have parameters", exception.getMessage());
    }

    @Test
    public void validateTemplateNotProtected() {
        JBaconTemplateException exception = Assertions.assertThrows(JBaconTemplateException.class, () ->
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
        Assertions.assertEquals("Template methods should be protected", exception.getMessage());
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

        TemplateResolver<SimpleClass> templateResolver = new TemplateResolver<>(jBacon);
        Assertions.assertThrows(JBaconInvocationException.class,
                () -> templateResolver.findTemplateByName("template"));
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

        TemplateResolver<SimpleClass> templateResolver = new TemplateResolver<>(jBacon);
        Assertions.assertThrows(JBaconTemplateException.class,
                () -> templateResolver.findTemplateByName("notFound"));
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

        TemplateResolver<SimpleClass> templateResolver = new TemplateResolver<>(jBacon);
        SimpleClass myTemplate = templateResolver.findTemplateByName("myTemplate");

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

        TemplateResolver<SimpleClass> templateResolver = new TemplateResolver<>(jBacon);

        SimpleClass template1 = templateResolver.findTemplateByName("template1");
        Assertions.assertNotNull(template1);
        Assertions.assertEquals("findTemplateByNameWithManyTemplatesTest_1", template1.getSimpleStr());

        SimpleClass template2 = templateResolver.findTemplateByName("template2");
        Assertions.assertNotNull(template2);
        Assertions.assertEquals("findTemplateByNameWithManyTemplatesTest_2", template2.getSimpleStr());

        SimpleClass template3 = templateResolver.findTemplateByName("template3");
        Assertions.assertNotNull(template3);
        Assertions.assertEquals("findTemplateByNameWithManyTemplatesTest_3", template3.getSimpleStr());
    }

    @Test
    public void templateWithMethodName() {
        JBacon<SimpleClass> factory = new JBacon<SimpleClass>() {

            @Override
            protected SimpleClass getDefault() {
                return new SimpleClass("default");
            }

            @Override
            protected SimpleClass getEmpty() {
                return new SimpleClass();
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                throw new ShouldNotBeCalled();
            }

            @JBaconTemplate
            protected SimpleClass etset() {
                return new SimpleClass("testSC");
            }
        };

        SimpleClass simpleClass = factory.build("etset");
        Assertions.assertEquals("testSC", simpleClass.getSimpleStr());
    }

}
