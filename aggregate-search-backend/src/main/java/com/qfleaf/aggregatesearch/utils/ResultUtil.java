package com.qfleaf.aggregatesearch.utils;

import com.qfleaf.aggregatesearch.common.CommonResponse;
import com.qfleaf.aggregatesearch.common.ResponseCode;

public final class ResultUtil {
    private ResultUtil() {
    }

    public static <T> CommonResponse<T> success(ResponseCode responseCode, T data) {
        return new CommonResponse<>(responseCode.getCode(), responseCode.getMsg(), data);
    }

    public static <T> CommonResponse<T> failure(ResponseCode responseCode, String desc) {
        return new CommonResponse<>(responseCode.getCode(), desc, null);
    }
}
