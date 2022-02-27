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
import com.njcit.robotquery.bean.server.TrainBean;

import java.util.List;

/**
 * Created by youxuan on 2017/4/30 0030.
 */

public class TrainDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<TrainBean.TrainData> mDatas;

    public TrainDetailAdapter(Context context, List<TrainBean.TrainData> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_train_detail, parent, false);
        RecyclerView.ViewHolder viewHolder = new TrainDetailAdapter.EmployeeDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EmployeeDetailViewHolder viewHolder = (EmployeeDetailViewHolder) holder;
        if (viewHolder != null) {

            viewHolder.tvTrainName.setText(mDatas.get(position).getTrainName());
            viewHolder.tvTrainAim.setText(mDatas.get(position).getTrainTarget());
            viewHolder.tvTrainPeopleName.setText(mDatas.get(position).getTrainTrainer());
            viewHolder.tvTrainTime.setText(mDatas.get(position).getTrainDate() + "");
            viewHolder.tvClickMore.setVisibility(View.GONE);
            viewHolder.tvDataCardCount.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class EmployeeDetailViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTrainName;
        private TextView tvTrainAim;
        private TextView tvTrainPeopleName;
        private TextView tvTrainTime;
        private LinearLayout tvClickMore;
        private TextView tvDataCardCount;

        public EmployeeDetailViewHolder(View itemView) {
            super(itemView);

            tvTrainName = (TextView) itemView.findViewById(R.id.tv_train_name);
            tvTrainAim = (TextView) itemView.findViewById(R.id.tv_train_aim);
            tvTrainPeopleName = (TextView) itemView.findViewById(R.id.tv_train_people_name);
            tvTrainTime = (TextView) itemView.findViewById(R.id.tv_train_time);
            tvClickMore = (LinearLayout) itemView.findViewById(R.id.tv_click_more);
            tvDataCardCount = (TextView) itemView.findViewById(R.id.tv_data_card_count);
        }
    }
}
