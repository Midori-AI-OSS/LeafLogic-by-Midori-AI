package com.midoriai.leaflogic

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    
    @Test
    fun leaflogic_package_exists() {
        val packageName = "com.midoriai.leaflogic"
        assertEquals(packageName, this::class.java.`package`?.name)
    }
}