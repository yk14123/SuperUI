package com.chinafocus.myui.pool;

/**
 * @author
 * @date 2019/4/9
 * description：
 */
public class RecycleBin {

    public static final Object sObject = new Object();

    private MyView mMyView;

    public static RecycleBin sPool;

    private RecycleBin next;

    public static int sPoolSize = 0;

    public static RecycleBin obtain() {
        synchronized (sObject) {
            if (sPool != null) {
                RecycleBin temp = sPool;
                sPool = temp.next;
                temp.next = null;
                sPoolSize--;
                return temp;
            }
        }
        return new RecycleBin();
    }

    public void setMyView(MyView myView) {
        mMyView = myView;
    }

    public void addTargetView(MyView myView) {
        RecycleBin recycleBin = sPool;
        RecycleBin temp = new RecycleBin();
        temp.setMyView(myView);
        if (recycleBin != null) {
            temp.next = recycleBin;
        }
        sPool = temp;
        sPoolSize++;
    }

    public static void main(String[] args) {

        MyView a = new MyView("a");
        MyView b = new MyView("b");
        MyView c = new MyView("c");


    }

}
