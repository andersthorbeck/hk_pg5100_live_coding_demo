package org.tsdes.intro.spring.demojsfselenium;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("postgres")
public class ResetCounterTest {

    @Autowired
    private CounterService counterService;

    @Test
    void test_that_reset_sets_value_to_0() {
        long id = counterService.createCounter();
        counterService.incrementCounter(id);

        long valueBefore = counterService.getValue(id);
        assertThat(valueBefore, is(1L));

        counterService.resetCounter(Long.toString(id));

        long valueAfter = counterService.getValue(id);
        assertThat(valueAfter, is(0L));
    }

    @Test
    void test_that_reset_is_not_susceptible_to_sql_injection() {
        long id = counterService.createCounter();
        counterService.incrementCounter(id);

        long valueBefore = counterService.getValue(id);
        assertThat(valueBefore, is(1L));

        assertThrows(IllegalArgumentException.class, () -> {
            counterService.resetCounter(id + " OR 5=5");
        });

        long valueAfter = counterService.getValue(id);
        assertThat(valueAfter, is(1L));
    }
}
