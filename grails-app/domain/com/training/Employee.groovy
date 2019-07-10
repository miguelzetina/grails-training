package com.training

/** Represents Employee data */
class Employee extends CommonProfile  {

    String email

    Date entryDate

    Date leaveDate

    static hasMany = [contacts:Contact]

    static constraints = {
        email       blank:false, email:true, maxSize:255, unique:true
        leaveDate   nullable:true, validator:{ Date leaveDate, Employee employee ->
                        if (employee.leaveDate && employee.leaveDate < employee.entryDate) {
                            return 'min.notmet.entryDate'
                        }
                    }
    }

}
