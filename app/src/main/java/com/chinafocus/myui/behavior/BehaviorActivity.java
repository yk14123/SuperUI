package com.chinafocus.myui.behavior;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chinafocus.myui.R;

/**
 * @author
 * @date 2019/4/11
 * description：
 */
public class BehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_behavior);


        RecyclerView recyclerView = findViewById(R.id.recycler_view_behavior);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this));
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        private Context mContext;
        private Bitmap mBitmap;

        public MyAdapter(Context context) {
            mContext = context;
            mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.girl);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            myViewHolder.mImageView.setImageBitmap(mBitmap);
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder{

            private final ImageView mImageView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                mImageView = itemView.findViewById(R.id.img);
            }
        }
    }
}
