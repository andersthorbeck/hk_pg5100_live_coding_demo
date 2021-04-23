package org.tsdes.intro.spring.demojsfselenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class CounterService {

    private final EntityManager entityManager;

    @Autowired
    public CounterService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public long createCounter() {
        CounterEntity entity = new CounterEntity();
        entity.setValue(0L);

        entityManager.persist(entity);

        return entity.getId();
    }

    public long getValue(long id) {
        CounterEntity entity = entityManager.find(CounterEntity.class, id);
        return entity.getValue();
    }

    @Transactional
    public void incrementCounter(long id) {
        CounterEntity entity = entityManager.find(CounterEntity.class, id);
        if (entity == null) {
            throw new IllegalArgumentException("No counter found id " + id);
        }
        entity.setValue(entity.getValue() + 1);
    }

    @Transactional
    public void resetCounter(String id) {
        Query query = entityManager.createQuery(
                "UPDATE CounterEntity c SET c.value = 0 WHERE c.id = " + id
        );
        query.executeUpdate();
    }
}
