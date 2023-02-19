package org.pausanchez.entities;


import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends PanacheEntity {
    private String code;
    private String name;
    private String description;
}
