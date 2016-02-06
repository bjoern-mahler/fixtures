package de.mahler.fixtures.entities

import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

/**
 * Entity class with an association
 */
@ToString
@Entity
class SomeEntity {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String property;

    @OneToMany
    List<AnotherEntity> others;

}
