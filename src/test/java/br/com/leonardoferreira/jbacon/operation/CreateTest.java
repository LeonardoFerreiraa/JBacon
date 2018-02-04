package br.com.leonardoferreira.jbacon.operation;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.operation.impl.CreateImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lferreira on 2/4/18
 */
@RunWith(JUnit4.class)
public class CreateTest {

    @Test
    public void createTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createTest");
            }

            @Override
            public SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            public void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        Create<SimpleClass> create = new CreateImpl<>(jBacon);

        SimpleClass simpleClass = create.create();

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(persistIsCalled.get())
                .isEqualTo(1);

        Assertions.assertThat(persisted)
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(persisted.get(0))
                .isEqualTo(simpleClass);
    }

    @Test
    public void createWithExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithExampleTest", 1);
            }

            @Override
            public SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            public void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        Create<SimpleClass> create = new CreateImpl<>(jBacon);
        SimpleClass example = new SimpleClass("createWithExampleTest_example");

        SimpleClass simpleClass = create.create(example);

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(persistIsCalled.get())
                .isEqualTo(1);

        Assertions.assertThat(persisted)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(persisted.get(0))
                .isEqualTo(simpleClass);
        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo(example.getSimpleStr());
        Assertions.assertThat(simpleClass.getSimpleInteger())
                .isEqualTo(1);
    }

    @Test
    public void createWithTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateTest", 1);
            }

            @Override
            public SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            public SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateTest_template");
            }

            @Override
            public void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        Create<SimpleClass> create = new CreateImpl<>(jBacon);

        SimpleClass simpleClass = create.create("template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(0);
        Assertions.assertThat(persistIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(1);

        Assertions.assertThat(persisted)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(persisted.get(0))
                .isEqualTo(simpleClass);
        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo("createWithTemplateTest_template");
    }

    @Test
    public void createWithTemplateAndExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBacon<SimpleClass> jBacon = new JBacon<SimpleClass>() {
            @Override
            public SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateAndExampleTest", 1);
            }

            @Override
            public SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            public SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateAndExampleTest_template", 2);
            }

            @Override
            public void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        Create<SimpleClass> create = new CreateImpl<>(jBacon);
        SimpleClass example = new SimpleClass("createWithTemplateAndExampleTest_example");

        SimpleClass simpleClass = create.create(example, "template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(0);
        Assertions.assertThat(persistIsCalled.get())
                .isEqualTo(1);
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(1);

        Assertions.assertThat(persisted)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(persisted.get(0))
                .isEqualTo(simpleClass);
        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo(example.getSimpleStr());
        Assertions.assertThat(simpleClass.getSimpleInteger())
                .isEqualTo(2);
    }
}
