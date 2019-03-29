package com.chinafocus.myui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.chinafocus.myui.bean.Person;
import com.chinafocus.myui.global.Constants;
import com.chinafocus.myui.module.MyEventBus;
import com.chinafocus.myui.module.MyThread;
import com.chinafocus.myui.module.annotation.MyBus;

/**
 * @author
 * @date 2019/3/27
 * description：
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        MyEventBus.getDefault().register(this);
    }

    public void sendData(View view) {
//        MyEventBus.getDefault().post(new Person("yangke","34"));

        new Thread(() -> MyEventBus.getDefault().post(new Person("yangke", "34"))).start();

//        MyEventBus.getDefault().post("来自SecondActivity的爱");
    }

    @MyBus(thread = MyThread.OTHER)
    public void getDate(Person person) {
        Log.e(Constants.MYBUS_TAG, person + "SecondActivity获取数据成功  from receivePersonData" + "当前线程是：" + Thread.currentThread().getName());
    }

    @MyBus(thread = MyThread.MAIN)
    public void getDate2(String person) {
        Log.e(Constants.MYBUS_TAG, person + "SecondActivity获取数据成功" + "  from receivePersonData2");
    }

}
