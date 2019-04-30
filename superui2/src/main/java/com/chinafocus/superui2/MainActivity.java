package com.chinafocus.superui2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.chinafocus.bookshelf.model.base.sdkconfig.BookShelfSdkConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv_home);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookShelfSdkConfig.initialize(MainActivity.this, "expressreader", "12312313");
            }
        });// expressreader
    }
}
