package com.training

import grails.gorm.dirty.checking.DirtyCheck

/** Represents common profile data for every user type */
@DirtyCheck
abstract class CommonProfile {

    /** User full name */
    String name

    /** User phone number */
    String phone

    /** Creation date */
    Date dateCreated

    /** Date of last change */
    Date lastUpdated

    static constraints = {
        phone       size:10..10, matches:/\d{10}/
        name        nullable:false, blank:false, maxSize:255
    }

}
