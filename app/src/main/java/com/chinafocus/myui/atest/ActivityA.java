package com.chinafocus.myui.atest;

public class ActivityA {

    private IGiveString mIGiveString;

    public static void main(String[] args) {

        Router.getInstance().setIGiveString(str -> System.out.println("B 收到了 -- > " + str));

        Router.getInstance().getIGiveString().give("abc");


    }


}
