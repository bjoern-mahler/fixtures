package fixtures.example1.sub

import de.mahler.fixtures.entities.TestEntity

build {
    test1(TestEntity, property: 'test')
}


build {
    test2(TestEntity, property: 'test2')
}



