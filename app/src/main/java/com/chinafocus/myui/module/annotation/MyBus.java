package com.chinafocus.myui.module.annotation;

import com.chinafocus.myui.module.MyThread;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author
 * @date 2019/3/27
 * description：
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBus {
    MyThread thread() default MyThread.MAIN;
}
