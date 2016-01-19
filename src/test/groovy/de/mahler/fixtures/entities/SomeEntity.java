package de.mahler.fixtures.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Entity class with an association
 */
@Entity
public class SomeEntity {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String property;

    @OneToMany
    List<AnotherEntity> others;

}
