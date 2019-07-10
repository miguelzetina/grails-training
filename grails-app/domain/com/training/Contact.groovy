package com.training

class Contact extends CommonProfile {

    String relation

    static constraints = {
        relation        nullable:false, blank:false, maxSize:255
    }

}
