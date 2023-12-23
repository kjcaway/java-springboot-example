package me.javaexample.javademo.api.base;

public enum ApiResultCode {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    UNKNOWN("UNKNOWN");

    ApiResultCode(String value) {
        this.value = value;
    }

    private final String value;
}
