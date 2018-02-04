package br.com.leonardoferreira.jbacon;

import br.com.leonardoferreira.jbacon.domain.SimpleClass;
import br.com.leonardoferreira.jbacon.factory.MultiTemplateJBacon;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

/**
 * Created by lferreira on 2/4/18
 */
@RunWith(JUnit4.class)
public class MultiTemplateJBaconTest {

    private MultiTemplateJBacon jBacon;

    @Before
    public void setup() {
        this.jBacon = new MultiTemplateJBacon();
    }

    @Test
    public void invalidTemplateTest() {
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

}
