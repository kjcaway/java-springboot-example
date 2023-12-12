package me.javaexample.javademo.threadlocal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestInfo {
    private String id;
    private String path;
    private String method;
}
