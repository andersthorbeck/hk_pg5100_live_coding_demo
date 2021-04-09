package org.tsdes.intro.spring.demojsfselenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named
@SessionScope
public class SessionCounter {

    @Autowired
    private CounterService counterService;

    private long counterId;

    @PostConstruct
    private void initializeCounter() {
        counterId = counterService.createCounter();
    }

    public void increment() {
        counterService.incrementCounter(counterId);
    }

    public long getValue() {
        return counterService.getValue(counterId);
    }

}
