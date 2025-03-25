package com.qfleaf.aggregatesearch.adapter.anno;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface SearchAdapter {
    String value() default "";
}
