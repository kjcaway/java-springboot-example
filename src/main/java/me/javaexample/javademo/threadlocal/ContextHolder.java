package me.javaexample.javademo.threadlocal;

public class ContextHolder {
    private static final ThreadLocal<RequestInfo> REQ_INFO = ThreadLocal.withInitial(() -> null);

    public static void setRequestInfo(RequestInfo requestInfo) {
        REQ_INFO.set(requestInfo);
    }

    public static RequestInfo getRequestInfo() {
        return REQ_INFO.get();
    }

    public static void removeRequestInfo() {
        REQ_INFO.remove();
    }
}
