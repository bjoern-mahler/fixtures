package de.mahler.fixtures.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AnotherEntity {

    @Id
    @GeneratedValue
    Long id;


}
