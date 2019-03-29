package com.chinafocus.myui.module;

import android.os.Handler;
import android.os.Looper;

import com.chinafocus.myui.module.annotation.MyBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author
 * @date 2019/3/27
 * description：
 */
public class MyEventBus {

    private Map<Object, List<MethodParams>> mCachePool;

    private Handler mHandler;

    private static volatile MyEventBus instance;

    private MyEventBus() {
        mCachePool = new HashMap<>();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static MyEventBus getDefault() {
        if (instance == null) {
            synchronized (MyEventBus.class) {
                if (instance == null) {
                    instance = new MyEventBus();
                }
            }
        }
        return instance;
    }

    /**
     * 注册activity，回调方法
     *
     * @param obj
     */
    public void register(Object obj) {

        List<MethodParams> methodLists = mCachePool.get(obj);
        // 没有注册过，或者注册过，但是方法集合为空
        if (methodLists != null && !methodLists.isEmpty()) {
            return;
        }

        List<MethodParams> methodResult = findAllMethodList(obj);
        mCachePool.put(obj, methodResult);
    }

    /**
     * 通过对象，拿到该对象下，所有注解的方法
     *
     * @param obj activity对象
     * @return 方法集合
     */
    private List<MethodParams> findAllMethodList(Object obj) {
        List<MethodParams> methodTemp = new ArrayList<>();

        //通过对象，拿到该对象下，所有注解的方法
        Class<?> objClass = obj.getClass();

        //一直循环找父类，查看所有类的方法
        while (objClass != null) {

            //过滤系统级别的父类，直接省略
            String name = objClass.getName();
            if (name.startsWith("java.") || name.startsWith("javax.")
                    || name.startsWith("android.")) {
                break;
            }

            // 获取当前对象的所有方法
            Method[] methods = objClass.getDeclaredMethods();
            for (Method method : methods) {
                // 获取注解为MyBus的方法
                MyBus annotation = method.getAnnotation(MyBus.class);
                if (annotation == null) {
                    continue;
                }
                //判断使用注解的方法的参数，有几个！
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new IllegalArgumentException("使用注解的方法有且只能有1个参数");
                }
                // 获取回调方法在哪个线程运行
                MyThread threadType = annotation.thread();

                MethodParams methodParams = new MethodParams(method, parameterTypes[0], threadType);
                methodTemp.add(methodParams);
            }
            objClass = objClass.getSuperclass();
        }


        return methodTemp;
    }

    /**
     * 传递数据
     *
     * @param target 目标数据
     */
    public void post(Object target) {

        Set<Object> objects = mCachePool.keySet();

        for (Object temp : objects) {
            List<MethodParams> methodParams = mCachePool.get(temp);

            if (methodParams == null) {
                throw new IllegalArgumentException("一个注册activity都没有！");
            }

            for (MethodParams methodTemp : methodParams) {
                //  比较一下，传递的类型和注册方法里面的类型是否一致
                if (methodTemp.getParams().isAssignableFrom(target.getClass())) {
                    switch (methodTemp.getMyThread()) {
                        case MAIN:
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                // 主 --》 主
                                postNow(target, temp, methodTemp);
                            } else {
                                // 子 --》 主
                                mHandler.post(() -> postNow(target, temp, methodTemp));
                            }
                            break;
                        case OTHER:
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                // 主 --》 子
                                new Thread(() -> postNow(target, temp, methodTemp)).start();
                            } else {
                                // 子 --》 子
//                                mHandler.post(()->postNow(target, temp, methodTemp));
                                new Thread(() -> postNow(target, temp, methodTemp)).start();
                            }
                            break;
                    }
                }
            }
        }

    }

    private void postNow(Object target, Object temp, MethodParams methodTemp) {
        try {
            methodTemp.getMethod().invoke(temp, target);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void unRegister(Object obj) {
        List<MethodParams> methodParams = mCachePool.get(obj);
        if (methodParams != null) {
            methodParams.clear();
        }
        mCachePool.remove(obj);
    }
}
