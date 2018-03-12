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

    @Test
    public void buildTest() {
        JBaconInstance jBacon = new JBaconInstance();
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
        JBaconInstance jBacon = new JBaconInstance();
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
        JBaconInstance jBacon = new JBaconInstance();
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
        JBaconInstance jBacon = new JBaconInstance();
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
        JBaconInstance jBacon = new JBaconInstance();
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
        JBaconInstance jBacon = new JBaconInstance();
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
        JBaconInstance jBacon = new JBaconInstance();
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
        JBaconInstance jBacon = new JBaconInstance();
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
        JBaconInstance jBacon = new JBaconInstance();

        List<SimpleClass> simpleClasses = jBacon.create(5, "template");

        Assertions.assertThat(jBacon.getEmptyIsCalled())
                .isEqualTo(5);
        Assertions.assertThat(jBacon.getDefaultIsCalled())
                .isZero();
        Assertions.assertThat(jBacon.getPersistIsCalled())
                .isEqualTo(5);
        Assertions.assertThat(jBacon.getTemplateIsCalled())
                .isEqualTo(5);

        Assertions.assertThat(jBacon.getDatabase())
                .isNotEmpty()
                .hasSize(5)
                .isEqualTo(simpleClasses);

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertThat(simpleClass.getSimpleStr())
                    .isEqualTo("JBaconTemplate");
        }
    }

    @Test
    public void createListWithTemplateAndExampleTest() {
        JBaconInstance jBacon = new JBaconInstance();

        SimpleClass example = new SimpleClass("createWithTemplateAndExampleTest_example");

        List<SimpleClass> simpleClasses = jBacon.create(5, example, "template");

        Assertions.assertThat(jBacon.getEmptyIsCalled())
                .isEqualTo(5);
        Assertions.assertThat(jBacon.getDefaultIsCalled())
                .isZero();
        Assertions.assertThat(jBacon.getPersistIsCalled())
                .isEqualTo(5);
        Assertions.assertThat(jBacon.getTemplateIsCalled())
                .isEqualTo(5);

        Assertions.assertThat(jBacon.getDatabase())
                .isNotEmpty()
                .hasSize(5)
                .isEqualTo(simpleClasses);

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertThat(simpleClass.getSimpleStr()).isEqualTo("createWithTemplateAndExampleTest_example");
            Assertions.assertThat(simpleClass.getSimpleInteger()).isEqualTo(2);
        }
    }

    @Test
    public void createWithTemplateAndExampleTest() {
        JBaconInstance jBacon = new JBaconInstance();
        SimpleClass example = new SimpleClass("createWithTemplateAndExampleTest_example");

        SimpleClass simpleClass = jBacon.create(example, "template");

        Assertions.assertThat(jBacon.getDatabase())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(jBacon.getDatabase().get(0))
                .isEqualTo(simpleClass);
        Assertions.assertThat(simpleClass.getSimpleStr())
                .isEqualTo("createWithTemplateAndExampleTest_example");
        Assertions.assertThat(simpleClass.getSimpleInteger())
                .isEqualTo(2);
    }

    @Test
    public void buildWithExampleAndTemplateTest() {
        JBaconInstance jBacon = new JBaconInstance();
        SimpleClass example = new SimpleClass("buildWithExampleAndTemplateTest_example");

        SimpleClass simpleClass = jBacon.build(example, "template");
        Assertions.assertThat(simpleClass).isNotNull();

        Assertions.assertThat(simpleClass.getSimpleStr()).isEqualTo("buildWithExampleAndTemplateTest_example");

        Assertions.assertThat(simpleClass.getSimpleInteger()).isEqualTo(2);
    }

    @Test
    public void buildListTest() {
        JBaconInstance jBacon = new JBaconInstance();
        List<SimpleClass> buildList = jBacon.build(5);
        for (SimpleClass build : buildList) {
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

        Assertions.assertThat(jBacon.getDatabase())
                .isEmpty();
        Assertions.assertThat(jBacon.getPersistIsCalled())
                .isZero();
    }

    @Test
    public void buildListWithExampleTest() {
        JBaconInstance jBacon = new JBaconInstance();
        SimpleClass example = new SimpleClass();
        example.setSimpleStr("Example");

        List<SimpleClass> buildList = jBacon.build(5, example);
        for (SimpleClass build : buildList) {
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
        }

        Assertions.assertThat(jBacon.getDatabase())
                .isEmpty();

        Assertions.assertThat(example.getSimpleBigDecimal())
                .isNull();

        Assertions.assertThat(example.getSimpleInteger())
                .isNull();

        Assertions.assertThat(jBacon.getPersistIsCalled())
                .isZero();
    }

    @Test
    public void buildListWithTemplateAndExampleTest() {
        JBaconInstance jBacon = new JBaconInstance();

        SimpleClass example = new SimpleClass("buildWithTemplateAndExampleTest_example");

        List<SimpleClass> simpleClasses = jBacon.build(5, example, "template");

        Assertions.assertThat(jBacon.getEmptyIsCalled())
                .isEqualTo(5);
        Assertions.assertThat(jBacon.getTemplateIsCalled())
                .isEqualTo(5);
        Assertions.assertThat(jBacon.getDefaultIsCalled())
                .isZero();
        Assertions.assertThat(jBacon.getPersistIsCalled())
                .isZero();

        Assertions.assertThat(jBacon.getDatabase())
                .isEmpty();

        for (SimpleClass simpleClass : simpleClasses) {
            Assertions.assertThat(simpleClass.getSimpleStr()).isEqualTo("buildWithTemplateAndExampleTest_example");
            Assertions.assertThat(simpleClass.getSimpleInteger()).isEqualTo(2);
        }
    }
}
