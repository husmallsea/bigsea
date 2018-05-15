package com.bigsea.study.studythread.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 线程不安全标识
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {
    String value()default "";
}
