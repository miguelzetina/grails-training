package com.training

import spock.lang.Specification

class ConstraintUnitSpec extends Specification {

    String getString(Integer len) {
        "s"*len
    }

    String getEmail(Integer len) {
        if (len <= 72) "${'s'*(len - 8)}@exp.com"
        else "${'s'*64}@${'s'*(len - 69)}.com"
    }

    Date getDateOnly() {
        new Date().clearTime()
    }

    String getNumericString(Integer len) {
        new Random().with {
            (1..len).collect { nextInt( 10 ) }.join()
        }
    }

}
