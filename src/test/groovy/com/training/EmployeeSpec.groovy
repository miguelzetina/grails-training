package com.training

import com.training.ConstraintUnitSpec
import grails.testing.gorm.DomainUnitTest
import spock.lang.Unroll

class EmployeeSpec extends ConstraintUnitSpec implements DomainUnitTest<Employee> {

    @Unroll("Validation returns #expectedErrorCount errors because #reason")
    void "Test constraints"() {
        given: "A new employee"
            Employee employee = new Employee(name:name,
                                             phone:phone,
                                             email:email,
                                             entryDate: entryDate,
                                             leaveDate: leaveDate)

        when: "Validating the instance"
            Boolean validation = employee.validate()

        then: "Validation result is expected"
            validation == expectedValidation
        and: "Error count is expected"
            employee.errors.errorCount == expectedErrorCount

        where:
            name            |phone                  |email          |entryDate          |leaveDate          ||expectedValidation |expectedErrorCount |reason
            getString(255)  |getNumericString(10)   |getEmail(130)  |getDateOnly() - 30 |null               ||true               |0                  |"all fields are valid"
            null            |null                   |null           |null               |null               ||false              |4                  |"all fields are null"

            //name
            null            |getNumericString(10)   |getEmail(130)  |getDateOnly() - 30 |null               ||false              |1                  |"name is null"
            ''              |getNumericString(10)   |getEmail(130)  |getDateOnly() - 30 |null               ||false              |1                  |"name is blank"
            getString(256)  |getNumericString(10)   |getEmail(130)  |getDateOnly() - 30 |null               ||false              |1                  |"name is too long"

            //phone
            getString(255)  |'456434356'            |getEmail(130)  |getDateOnly() - 30 |null               ||false              |2                  |"phone is incorrect"
            getString(255)  |'456434356S'           |getEmail(130)  |getDateOnly() - 30 |null               ||false              |1                  |"phone is incorrect with alphabet"
            getString(255)  |null                   |getEmail(130)  |getDateOnly() - 30 |null               ||false              |1                  |"phone is null"

            //entryDate
            getString(255)  |getNumericString(10)   |getEmail(255)  |getDateOnly() - 30 |null               ||false              |1                  |"entryDate is null"

            //leaveDate
            getString(255)  |getNumericString(10)   |getEmail(130)  |getDateOnly()      |getDateOnly() - 1  ||false              |1                  |"leaveDate is less than entryDate"
    }
}