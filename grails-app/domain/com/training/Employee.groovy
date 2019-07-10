package com.training

class Employee extends CommonProfile  {

    String email

    Date entryDate

    Date leaveDate

    static hasMany = [ contacts: Contact ]

    static constraints = {
        email       nullable:false, blank:false, email:true, unique:true
        entryDate   nullable:false
        leaveDate   nullable:true, validator:{ Date leaveDate, Employee employee ->
                        if (employee.leaveDate && employee.leaveDate < employee.entryDate) {
                            return 'min.notmet.entryDate'
                        }
                    }
    }

}
