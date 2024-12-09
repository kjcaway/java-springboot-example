package me.javaexample.javademo.util;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class ValidUtilsTest {

    @Test
    void validMobileNo() {
        String[] testData = {
            "01012345678",  // true
            "0101234567",   // true
            "010123456789", // false
            "01112345678",  // false
            "010-0000-1111",// true
            "010-123-1234", // true
            "010-123-123",  // false
            "011-1234-1234" // false
        };
        List<Boolean> actual = new ArrayList<>();
        Boolean[] expected = {true, true, false, false, true, true, false, false};
        for (String testDatum : testData) {
            boolean valid = ValidUtils.validMobileNo(testDatum);
            actual.add(valid);
        }
        Assertions.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    void validEmail() {
        String[] testData = {
            "kk@google.com",    // true
            "k12@google.com",   // true
            "k1@ccc",           // false
            "k1",               // false
            "k1_cc@google.com", // true
            "k1_@google.com",   // true
            "google.com",       // false
            "google.com@"       // false
        };
        List<Boolean> actual = new ArrayList<>();
        Boolean[] expected = {true, true, false, false, true, true, false, false};
        for (String testDatum : testData) {
            boolean valid = ValidUtils.validEmail(testDatum);
            actual.add(valid);
        }
        Assertions.assertArrayEquals(expected, actual.toArray());
    }
}