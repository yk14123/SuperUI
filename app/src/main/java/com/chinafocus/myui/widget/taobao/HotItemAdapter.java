package com.chinafocus.myui.widget.taobao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chinafocus.myui.R;

public class HotItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private int[] mImgs;
    private String[] mNames;

    public HotItemAdapter(Context applicationContext, int[] img_urls, String[] item_names) {
        this.mContext = applicationContext;
        this.mImgs = img_urls;
        this.mNames = item_names;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BaseViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.taobao_horizontal_recyclerview_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Log.e("HotItemAdapter", "HotItemAdapter  position == " + position);
//        if (position < mImgs.length) {
        holder.setText(R.id.tv_menu_title_home, mNames[position % 9] + "");
        holder.setImageResource(R.id.iv_menu_home, mImgs[position % 9]);
        holder.getView(R.id.ll_menu_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, mNames[position % 9] + position , Toast.LENGTH_SHORT).show();
            }
        });
//        }

    }

    @Override
    public int getItemCount() {
        return mImgs.length * 10;
    }

}