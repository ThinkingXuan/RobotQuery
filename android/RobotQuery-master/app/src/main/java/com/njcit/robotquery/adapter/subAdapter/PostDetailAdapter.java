package com.njcit.robotquery.adapter.subAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.bean.server.EmployeeBean;
import com.njcit.robotquery.bean.server.PostBean;

import java.util.List;

/**
 * Created by youxuan on 2017/4/30 0030.
 */

public class PostDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<PostBean.Postdata> mDatas;

    public PostDetailAdapter(Context context, List<PostBean.Postdata> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_post_detail,parent,false);
        RecyclerView.ViewHolder viewHolder = new PostDetailAdapter.PostDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PostDetailViewHolder viewHolder = (PostDetailViewHolder) holder;
        if (viewHolder!=null){

            viewHolder.tvPostName.setText(mDatas.get(position).getPostName());
            int count = mDatas.get(position).getPostNumber() - mDatas.get(position).getPostRealNumber();
            viewHolder.tvPostPeopleAccount.setText(count+"");
            viewHolder.tvClickMore.setVisibility(View.GONE);
            viewHolder.tvDataCardCount.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class PostDetailViewHolder extends RecyclerView.ViewHolder{

        private TextView tvPostName;
        private TextView tvPostPeopleAccount;
        private LinearLayout tvClickMore;

        TextView tvDataCardCount;

        public PostDetailViewHolder(View itemView) {
            super(itemView);
            tvPostName = (TextView) itemView.findViewById(R.id.tv_post_name);
            tvPostPeopleAccount = (TextView) itemView.findViewById(R.id.tv_post_people_account);
            tvClickMore = (LinearLayout) itemView.findViewById(R.id.tv_click_more);
            tvDataCardCount = (TextView) itemView.findViewById(R.id.tv_data_card_count);

        }
    }
}
