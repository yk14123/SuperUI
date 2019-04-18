package com.chinafocus.myui.widget.taobao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

public class BaseDelegateAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {

    private LayoutHelper mLayoutHelper;
    private int mCount = -1;
    private int mLayoutId = -1;
    private Context mContext;
    private int mViewTypeItem = -1;

    public BaseDelegateAdapter(Context context,
                               LayoutHelper layoutHelper,
                               int layoutId, int count, int viewTypeItem) {
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
        this.mLayoutId = layoutId;
        this.mContext = context;
        this.mViewTypeItem = viewTypeItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == mViewTypeItem){
            return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, viewGroup, false));
        }
        return new BaseViewHolder(new View(mContext));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        if (mViewTypeItem == -1) {
            return 0;
        }
        return mViewTypeItem;
    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
