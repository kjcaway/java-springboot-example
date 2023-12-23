package me.javaexample.javademo.api.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    private ApiResultCode code;
    private T data;
    private String error;

    public ApiResult(ApiResultCode code) {
        this.code = code;
        this.data = null;
        this.error = null;
    }

    public ApiResult(ApiResultCode code, T data) {
        this.code = code;
        this.data = data;
        this.error = null;
    }

    public ApiResult(ApiResultCode code, String error) {
        this.code = code;
        this.data = null;
        this.error = error;
    }

    public static ApiResult ok() {
        return new ApiResult(ApiResultCode.SUCCESS);
    }

    public static <T> ApiResult<T> ok(T data) {
        return new ApiResult(ApiResultCode.SUCCESS, data);
    }

    public static ApiResult error(String error) {
        return new ApiResult(ApiResultCode.FAIL, error);
    }
}
