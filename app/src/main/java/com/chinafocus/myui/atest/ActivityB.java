package com.chinafocus.myui.atest;

public class ActivityB implements IGiveString{
    @Override
    public void give(String str) {
        System.out.println("B 收到了 " + str);
    }
}
