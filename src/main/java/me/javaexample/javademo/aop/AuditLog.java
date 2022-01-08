package me.javaexample.javademo.aop;

import lombok.Data;

@Data
public class AuditLog {
    private String time;
    private String action;
    private String path;
    private String remoteAddr;
    private String method;
    private Long spendTime;
    private String params;
    private int result;
}
