package com.chinafocus.myui.widget.taobao;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chinafocus.myui.R;

public class HorizontalAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {

    private Context mContext;

    private LayoutHelper mLayoutHelper;

    private int position,xOffset;

    public HorizontalAdapter(Context context, LayoutHelper layoutHelper) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == 0) {
            return new BaseViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.activity_taobao_horizontal_recyclerview, parent, false));
//        }
//        return null;
    }


    @Override
    public void onViewDetachedFromWindow(BaseViewHolder holder) {
        if (holder.itemView instanceof RecyclerView) {
            RecyclerView recyclerView = ((RecyclerView) holder.itemView);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            position = manager.findFirstVisibleItemPosition();
            View view = manager.findViewByPosition(position);
            ViewGroup.MarginLayoutParams lp =
                    (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (view != null) {
                xOffset = view.getLeft() - lp.leftMargin; //如果你设置了margin则减去
            }
        }
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.itemView instanceof RecyclerView) {
            RecyclerView recyclerView = ((RecyclerView) holder.itemView);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            manager.scrollToPositionWithOffset(position, xOffset);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

//    @Override
//    public int getItemViewType(int position) {
//        return 0;
//    }

    @Override
    public int getItemCount() {
        return 1;
    }


}