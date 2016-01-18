package de.mahler.fixtures.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Some ordinary yet simple jpa entity to test against
 */
@Entity
class TestEntity {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String name

    @Column
    int value

}
