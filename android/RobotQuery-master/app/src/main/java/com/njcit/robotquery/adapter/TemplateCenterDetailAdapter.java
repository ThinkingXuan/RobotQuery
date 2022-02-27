package com.njcit.robotquery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.njcit.robotquery.R;

/**
 * Created by youxuan on 2017/4/29 0029.
 */

public class TemplateCenterDetailAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private Context mContext;

    public TemplateCenterDetailAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LinearLayout.inflate(mContext, R.layout.item_template_detail,null);
        RecyclerView.ViewHolder viewHolder = new TemplateCenterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TemplateCenterViewHolder extends RecyclerView.ViewHolder{

        public TemplateCenterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
