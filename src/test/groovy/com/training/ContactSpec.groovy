package com.training

import com.training.ConstraintUnitSpec
import grails.testing.gorm.DomainUnitTest
import spock.lang.Unroll

class ContactSpec extends ConstraintUnitSpec implements DomainUnitTest<Contact> {

    @Unroll("Validation returns #expectedErrorCount errors because #reason")
    void "Test constraints"() {
        given: "A new contact"
            Contact contact = new Contact(name:name, phone:phone, relation:relation)

        when: "Validating the instance"
            Boolean validation = contact.validate()

        then: "Validation result is expected"
            validation == expectedValidation
        and: "Error count is expected"
            contact.errors.errorCount == expectedErrorCount

        where:
            name            |phone                  |relation       ||expectedValidation |expectedErrorCount |reason
            getString(255)  |getNumericString(10)   |getString(130) ||true               |0                  |"all fields are valid"
            null            |null                   |null           ||false              |3                  |"all fields are null"

            //name
            null            |getNumericString(10)   |getString(130) ||false              |1                  |"name is null"
            ''              |getNumericString(10)   |getString(130) ||false              |1                  |"name is blank"
            getString(256)  |getNumericString(10)   |getString(130) ||false              |1                  |"name is too long"

            //phone
            getString(255)  |'456434356'            |getString(130) ||false              |2                  |"phone is incorrect"
            getString(255)  |'456434356S'           |getString(130) ||false              |1                  |"phone is incorrect with alphabet"
            getString(255)  |null                   |getString(130) ||false              |1                  |"phone is null"

    }

}