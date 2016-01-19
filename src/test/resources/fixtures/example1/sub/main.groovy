package fixtures.example1.sub

import de.mahler.fixtures.entities.SomeEntity

build {
    test1(SomeEntity, property: 'test')
}

build {
    test2(SomeEntity, property: 'test2')
}



