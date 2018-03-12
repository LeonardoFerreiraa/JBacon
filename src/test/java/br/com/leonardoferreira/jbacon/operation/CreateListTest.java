package br.com.leonardoferreira.jbacon.operation;

import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.operation.impl.CreateListImpl;
import br.com.leonardoferreira.jbacon.factory.JBaconFunctionInstance;
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
public class CreateListTest {

    @Test
    public void createTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBaconFunctionInstance<SimpleClass> JBaconFunctionInstance = new JBaconFunctionInstance<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createTest");
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        CreateList<SimpleClass> create = new CreateListImpl<>(JBaconFunctionInstance.getFunctionList());

        List<SimpleClass> simpleClasses = create.create(5);

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(persistIsCalled.get())
                .isEqualTo(5);

        Assertions.assertThat(persisted)
                .isNotEmpty()
                .hasSize(5);

        Assertions.assertThat(persisted)
                .isEqualTo(simpleClasses);
    }

    @Test
    public void createWithExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBaconFunctionInstance<SimpleClass> JBaconFunctionInstance = new JBaconFunctionInstance<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @Override
            protected void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        CreateList<SimpleClass> create = new CreateListImpl<>(JBaconFunctionInstance.getFunctionList());
        SimpleClass example = new SimpleClass("createWithExampleTest_example");

        List<SimpleClass> simpleClasses = create.create(5, example);

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(persistIsCalled.get())
                .isEqualTo(5);

        Assertions.assertThat(persisted)
                .isNotEmpty()
                .hasSize(5)
                .isEqualTo(simpleClasses);

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertThat(simpleClass.getSimpleStr())
                    .isEqualTo(example.getSimpleStr());
            Assertions.assertThat(simpleClass.getSimpleInteger())
                    .isEqualTo(1);
        }
    }

    @Test
    public void createWithTemplateTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBaconFunctionInstance<SimpleClass> JBaconFunctionInstance = new JBaconFunctionInstance<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateTest_template");
            }

            @Override
            protected void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        CreateList<SimpleClass> create = new CreateListImpl<>(JBaconFunctionInstance.getFunctionList());

        List<SimpleClass> simpleClasses = create.create(5, "template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isZero();
        Assertions.assertThat(persistIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(5);

        Assertions.assertThat(persisted)
                .isNotEmpty()
                .hasSize(5)
                .isEqualTo(simpleClasses);

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertThat(simpleClass.getSimpleStr())
                    .isEqualTo("createWithTemplateTest_template");
        }
    }

    @Test
    public void createWithTemplateAndExampleTest() {
        AtomicInteger emptyIsCalled = new AtomicInteger();
        AtomicInteger defaultIsCalled = new AtomicInteger();
        AtomicInteger persistIsCalled = new AtomicInteger();
        AtomicInteger templateIsCalled = new AtomicInteger();

        List<SimpleClass> persisted = new ArrayList<>();

        JBaconFunctionInstance<SimpleClass> JBaconFunctionInstance = new JBaconFunctionInstance<SimpleClass>() {
            @Override
            protected SimpleClass getDefault() {
                defaultIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateAndExampleTest", 1);
            }

            @Override
            protected SimpleClass getEmpty() {
                emptyIsCalled.incrementAndGet();
                return new SimpleClass();
            }

            @JBaconTemplate("template")
            protected SimpleClass template() {
                templateIsCalled.incrementAndGet();
                return new SimpleClass("createWithTemplateAndExampleTest_template", 2);
            }

            @Override
            protected void persist(SimpleClass simpleClass) {
                persistIsCalled.incrementAndGet();
                persisted.add(simpleClass);
            }
        };

        CreateList<SimpleClass> create = new CreateListImpl<>(JBaconFunctionInstance.getFunctionList());
        SimpleClass example = new SimpleClass("createWithTemplateAndExampleTest_example");

        List<SimpleClass> simpleClasses = create.create(5, example, "template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isZero();
        Assertions.assertThat(persistIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(templateIsCalled.get())
                .isEqualTo(5);

        Assertions.assertThat(persisted)
                .isNotEmpty()
                .hasSize(5)
                .isEqualTo(simpleClasses);

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertThat(simpleClass.getSimpleStr())
                    .isEqualTo(example.getSimpleStr());
            Assertions.assertThat(simpleClass.getSimpleInteger())
                    .isEqualTo(2);
        }
    }
}
