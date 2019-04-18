package com.chinafocus.myui.widget.taobao;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import java.util.List;

/**
 * @author
 * @date 2019/4/17
 * description：
 */
public class BaseVirtualLayoutAdapter extends VirtualLayoutAdapter {

    public BaseVirtualLayoutAdapter(@NonNull VirtualLayoutManager layoutManager) {
        super(layoutManager);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public List<LayoutHelper> getLayoutHelpers() {
        return super.getLayoutHelpers();
    }

    @Override
    public void setLayoutHelpers(List list) {
        super.setLayoutHelpers(list);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
