package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lferreira on 2/4/18
 */
@RunWith(JUnit4.class)
public class BuildTest {

    @Test
    public void buildTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildTest");
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        SimpleClass simpleClass = jBacon.build();
        Assertions.assertThat(simpleClass)
                .isNotNull();

        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo("buildTest");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(persistIsCalled.get())
                .isZero();
    }

    @Test
    public void buildWithTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateTest");
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("buildWithTemplateTest_template");
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        SimpleClass simpleClass = jBacon.build("template");
        Assertions.assertThat(simpleClass)
                .isNotNull();

        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo("buildWithTemplateTest_template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isZero();
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(persistIsCalled.get())
                .isZero();
    }

    @Test
    public void buildWithExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithExampleTest", 10);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        SimpleClass example = new SimpleClass("buildWithExampleTest_example");

        SimpleClass simpleClass = jBacon.build(example);
        Assertions.assertThat(simpleClass)
                .isNotNull();

        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo(example.getSimpleStr());

        Assertions.assertThat(simpleClass.getSimpleInteger())
                .isEqualTo(10);

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(persistIsCalled.get())
                .isZero();
    }

    @Test
    public void buildWithExampleAndTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("buildWithExampleAndTemplateTest", 10);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("buildWithExampleAndTemplateTest_template", 20);
            }

            @Override
            protected void persist(final SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
            }
        };

        SimpleClass example = new SimpleClass("buildWithExampleAndTemplateTest_example");

        SimpleClass simpleClass = jBacon.build(example, "template");
        Assertions.assertThat(simpleClass)
                .isNotNull();

        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo(example.getSimpleStr());

        Assertions.assertThat(simpleClass.getSimpleInteger())
                .isEqualTo(20);

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isZero();
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(persistIsCalled.get())
                .isZero();
    }

}
