package org.tsdes.intro.spring.demojsfselenium;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class CounterEntity {

    @Id @GeneratedValue(generator = "entity_id_sequence")
    private Long id;

    @NotNull
    @Min(0)
    @Max(10)
    private Long value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
