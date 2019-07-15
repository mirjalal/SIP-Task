package com.talmir.sip.task.githubpublicrepositories

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
//    @Test fun formatDate() {
//        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
//        format.timeZone = TimeZone.getTimeZone("GMT")
//        println(format.format(format.parse("2011-08-13T00:17:46Z")))
//    }

    private val apiDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        //timeZone = TimeZone.getTimeZone("GMT")
    }

    private val localDateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    }

    /**
     * A test to format the given date.
     */
    @Test fun formatDate() {
//        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
//        format.timeZone = TimeZone.getTimeZone("GMT")
        println(localDateFormat.format(apiDateFormat.parse("2011-08-13T00:17:46Z")!!/*?.formatTo("dd MMMM yyyy"*/))
    }

    private fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        formatter.timeZone = timeZone
        return formatter.format(this)
    }
}
