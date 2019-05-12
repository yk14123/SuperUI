package com.chinafocus.myui.atest;

public class Router {

    private IGiveString mIGiveString;

    private static Router instance = new Router();

    private Router() {
    }

    public static Router getInstance() {
        return instance;
    }

    public IGiveString getIGiveString() {
        return mIGiveString;
    }

    public void setIGiveString(IGiveString IGiveString) {
        mIGiveString = IGiveString;
    }
}
