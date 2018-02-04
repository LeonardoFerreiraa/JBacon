package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.factory.JBaconInstance;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lferreira on 6/16/17.
 */
@RunWith(JUnit4.class)
public class JBaconTest {

    private JBaconInstance jBacon;

    @Before
    public void setup() {
        this.jBacon = new JBaconInstance();
    }

    @Test
    public void buildTest() {
        SimpleClass build = jBacon.build();
        Assertions.assertThat(build)
                .isNotNull();

        Assertions.assertThat(build.getSimpleStr())
                .isNotNull()
                .isEqualTo("JBacon");

        Assertions.assertThat(build.getSimpleInteger())
                .isNotNull()
                .isEqualTo(123321);

        Assertions.assertThat(build.getSimpleBigDecimal())
                .isNotNull()
                .isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void buildWithExampleTest() {
        SimpleClass example = new SimpleClass();
        example.setSimpleStr("Example");

        SimpleClass build = jBacon.build(example);
        Assertions.assertThat(build)
                .isNotNull();

        Assertions.assertThat(build.getSimpleStr())
                .isNotNull()
                .isEqualTo(example.getSimpleStr());

        Assertions.assertThat(build.getSimpleInteger())
                .isNotNull()
                .isEqualTo(123321);

        Assertions.assertThat(build.getSimpleBigDecimal())
                .isNotNull()
                .isEqualTo(BigDecimal.ZERO);

        Assertions.assertThat(example.getSimpleBigDecimal())
                .isNull();

        Assertions.assertThat(example.getSimpleInteger())
                .isNull();
    }

    @Test
    public void createTest() {
        SimpleClass create = jBacon.create();
        Assertions.assertThat(create)
                .isNotNull();

        Assertions.assertThat(create.getSimpleStr())
                .isNotNull()
                .isEqualTo("JBacon");

        Assertions.assertThat(create.getSimpleInteger())
                .isNotNull()
                .isEqualTo(123321);

        Assertions.assertThat(create.getSimpleBigDecimal())
                .isNotNull()
                .isEqualTo(BigDecimal.ZERO);

        Assertions.assertThat(jBacon.getDatabase())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(jBacon.getDatabase().get(0))
                .isEqualTo(create);
    }

    @Test
    public void createWithExampleTest() {
        SimpleClass example = new SimpleClass();
        example.setSimpleStr("Example");

        SimpleClass create = jBacon.create(example);
        Assertions.assertThat(create)
                .isNotNull();

        Assertions.assertThat(create.getSimpleStr())
                .isNotNull()
                .isEqualTo(example.getSimpleStr());

        Assertions.assertThat(create.getSimpleInteger())
                .isNotNull()
                .isEqualTo(123321);

        Assertions.assertThat(create.getSimpleBigDecimal())
                .isNotNull()
                .isEqualTo(BigDecimal.ZERO);

        Assertions.assertThat(jBacon.getDatabase())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(jBacon.getDatabase().get(0))
                .isEqualTo(create);

        Assertions.assertThat(example.getSimpleBigDecimal())
                .isNull();

        Assertions.assertThat(example.getSimpleInteger())
                .isNull();
    }

    @Test
    public void createListTest() {
        List<SimpleClass> createList = jBacon.create(5);
        for (SimpleClass create : createList) {
            Assertions.assertThat(create)
                    .isNotNull();

            Assertions.assertThat(create.getSimpleStr())
                    .isNotNull()
                    .isEqualTo("JBacon");

            Assertions.assertThat(create.getSimpleInteger())
                    .isNotNull()
                    .isEqualTo(123321);

            Assertions.assertThat(create.getSimpleBigDecimal())
                    .isNotNull()
                    .isEqualTo(BigDecimal.ZERO);
        }

        Assertions.assertThat(jBacon.getDatabase())
                .isNotNull()
                .hasSize(5)
                .isEqualTo(createList);
    }

    @Test
    public void createListWithExampleTest() {
        SimpleClass example = new SimpleClass();
        example.setSimpleStr("Example");

        List<SimpleClass> createList = jBacon.create(5, example);
        for (SimpleClass create : createList) {
            Assertions.assertThat(create)
                    .isNotNull();

            Assertions.assertThat(create.getSimpleStr())
                    .isNotNull()
                    .isEqualTo(example.getSimpleStr());

            Assertions.assertThat(create.getSimpleInteger())
                    .isNotNull()
                    .isEqualTo(123321);

            Assertions.assertThat(create.getSimpleBigDecimal())
                    .isNotNull()
                    .isEqualTo(BigDecimal.ZERO);
        }

        Assertions.assertThat(jBacon.getDatabase())
                .isNotNull()
                .hasSize(5)
                .isEqualTo(createList);

        Assertions.assertThat(example.getSimpleBigDecimal())
                .isNull();

        Assertions.assertThat(example.getSimpleInteger())
                .isNull();
    }

    @Test
    public void buildInvalidTemplateTest() {
        SimpleClass invalid = jBacon.build("invalid");

        Assertions.assertThat(invalid)
                .isNotNull();

        Assertions.assertThat(invalid.getSimpleStr())
                .isEqualTo("INVALID");

        Assertions.assertThat(invalid.getSimpleInteger())
                .isEqualTo(-1);

        Assertions.assertThat(invalid.getSimpleBigDecimal())
                .isEqualTo(new BigDecimal("-1.00"));
    }

    @Test
    public void createInvalidTemplateTest() {
        SimpleClass invalid = jBacon.create("invalid");

        Assertions.assertThat(invalid)
                .isNotNull();

        Assertions.assertThat(invalid.getSimpleStr())
                .isEqualTo("INVALID");

        Assertions.assertThat(invalid.getSimpleInteger())
                .isEqualTo(-1);

        Assertions.assertThat(invalid.getSimpleBigDecimal())
                .isEqualTo(new BigDecimal("-1.00"));

        Assertions.assertThat(jBacon.getDatabase())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(jBacon.getDatabase().get(0))
                .isEqualTo(invalid);

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

        List<SimpleClass> simpleClasses = jBacon.create(5, "template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(0);
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

        SimpleClass example = new SimpleClass("createWithTemplateAndExampleTest_example");

        List<SimpleClass> simpleClasses = jBacon.create(5, example, "template");

        Assertions.assertThat(emptyIsCalled.get())
                .isEqualTo(5);
        Assertions.assertThat(defaultIsCalled.get())
                .isEqualTo(0);
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
