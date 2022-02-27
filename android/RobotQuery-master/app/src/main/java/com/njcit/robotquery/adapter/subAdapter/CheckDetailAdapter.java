package com.njcit.robotquery.adapter.subAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.bean.server.CheckBean;

import java.util.List;

/**
 * Created by youxuan on 2017/4/30 0030.
 */

public class CheckDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<CheckBean.CheckData> mDatas;

    public CheckDetailAdapter(Context context, List<CheckBean.CheckData> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_check_detail,parent,false);
        RecyclerView.ViewHolder viewHolder = new CheckDetailAdapter.CheckDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CheckDetailViewHolder viewHolder = (CheckDetailViewHolder) holder;
        if (viewHolder!=null){

            viewHolder.tvCheckAttitudeDetail.setText(mDatas.get(position).getUserAttitude()+"");
            viewHolder.tvCheckResponsibilityDetail.setText(mDatas.get(position).getUserResponsibility()+"");
            viewHolder.tvCheckWorkAbilityDetail.setText(mDatas.get(position).getUserWorkAbility()+"");
            viewHolder.tvCheckTrainAbilityDetail.setText(mDatas.get(position).getUserTrainingAbility()+"");
            viewHolder.tvCheckAllDetail.setText(mDatas.get(position).getUserTotal()+"");
            viewHolder.tvCheckUsernameDetail.setText(mDatas.get(position).getUserId()+"");
            viewHolder.tvClickMore.setVisibility(View.GONE);
            viewHolder.tvDataCardCount.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class CheckDetailViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCheckAttitudeDetail;
        private TextView tvCheckResponsibilityDetail;
        private TextView tvCheckWorkAbilityDetail;
        private TextView tvCheckTrainAbilityDetail;
        private TextView tvCheckAllDetail;
        private LinearLayout tvClickMore;
        private TextView tvCheckUsernameDetail;

        TextView tvDataCardCount;
        public CheckDetailViewHolder(View itemView) {
            super(itemView);


            tvCheckUsernameDetail = (TextView) itemView.findViewById(R.id.tv_check_username_detail);

            tvCheckAttitudeDetail = (TextView) itemView.findViewById(R.id.tv_check_attitude_detail);
            tvCheckResponsibilityDetail = (TextView) itemView.findViewById(R.id.tv_check_responsibility_detail);
            tvCheckWorkAbilityDetail = (TextView) itemView.findViewById(R.id.tv_check_workAbility_detail);
            tvCheckTrainAbilityDetail = (TextView) itemView.findViewById(R.id.tv_check_trainAbility_detail);
            tvCheckAllDetail = (TextView) itemView.findViewById(R.id.tv_check_all_detail);
            tvClickMore = (LinearLayout) itemView.findViewById(R.id.tv_click_more);
            tvDataCardCount = (TextView) itemView.findViewById(R.id.tv_data_card_count);


        }
    }
}
