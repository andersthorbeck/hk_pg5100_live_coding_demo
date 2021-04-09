package org.tsdes.intro.spring.demojsfselenium;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CounterServiceTest {

    @Autowired
    private CounterService counterService;

    @Test
    void test_that_newly_created_counter_has_value_0() {
        long counterId = counterService.createCounter();
        long actualValue = counterService.getValue(counterId);
        assertThat(actualValue, is(0L));
    }

    @Test
    void incrementCounter_should_increment_counter_value_by_1() {
        long counterId = counterService.createCounter();
        counterService.incrementCounter(counterId);
        long actualValue = counterService.getValue(counterId);
        assertThat(actualValue, is(1L));
    }

    @Test
    void incrementing_counter_multiple_times_works() {
        long counterId = counterService.createCounter();
        counterService.incrementCounter(counterId);
        counterService.incrementCounter(counterId);
        counterService.incrementCounter(counterId);
        long actualValue = counterService.getValue(counterId);
        assertThat(actualValue, is(3L));
    }

}
