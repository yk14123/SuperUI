package com.chinafocus.myui.module;

import com.chinafocus.myui.module.MyThread;

import java.lang.reflect.Method;

/**
 * @author
 * @date 2019/3/27
 * description：
 */
public class MethodParams {

    // 该方法对象
    private Method mMethod;

    // 标识当前方法中的参数类型
    private Class<?> mParams;

    // 标识当前线程
    private MyThread mMyThread;

    public MethodParams(Method method, Class<?> params, MyThread myThread) {
        mMethod = method;
        mParams = params;
        mMyThread = myThread;
    }

    public Class<?> getParams() {
        return mParams;
    }

    public void setParams(Class<?> params) {
        mParams = params;
    }

    public Method getMethod() {
        return mMethod;
    }

    public void setMethod(Method method) {
        mMethod = method;
    }

    public MyThread getMyThread() {
        return mMyThread;
    }

    public void setMyThread(MyThread myThread) {
        mMyThread = myThread;
    }
}
