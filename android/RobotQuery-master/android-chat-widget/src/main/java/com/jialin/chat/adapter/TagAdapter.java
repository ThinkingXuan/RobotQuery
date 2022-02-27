package com.jialin.chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.njcit.chat.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by youxuan on 2017/3/19 0019.
 */

public class TagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mdatas;

    public TagAdapter(Context context) {
        mContext = context;
        mdatas = Arrays.asList("text1","text2","text3","text4","text5","text6","text7","text8","text9","text10");

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.tag_item,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder!=null){
            ((ViewHolder)holder).tagName.setText(mdatas.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tagName;

        public ViewHolder(View itemView) {
            super(itemView);
            tagName = (TextView) itemView.findViewById(R.id.tag_name);
        }
    }
}
