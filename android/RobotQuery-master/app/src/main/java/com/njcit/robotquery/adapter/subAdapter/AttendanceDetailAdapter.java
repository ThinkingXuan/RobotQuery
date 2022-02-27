package com.njcit.robotquery.adapter.subAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.bean.server.AttendanceBean;
import com.njcit.robotquery.bean.server.EmployeeBean;
import com.njcit.robotquery.util.DateUtil;

import java.util.List;

/**
 * Created by youxuan on 2017/4/30 0030.
 */

public class AttendanceDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<AttendanceBean.AttendanceData> mDatas;


    public AttendanceDetailAdapter(Context context, List<AttendanceBean.AttendanceData> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_attendance_detail, parent, false);
        RecyclerView.ViewHolder viewHolder = new AttendanceDetailAdapter.AttendanceDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AttendanceDetailViewHolder viewHolder = (AttendanceDetailViewHolder) holder;
        if (viewHolder != null) {
            viewHolder.tvAttendanceTime.setText(DateUtil.getDateToString(mDatas.get(position).getAttendance_date()));
            viewHolder.tvTvAttendanceAccount.setText(mDatas.get(position).getAttendanceAll() + "");
            viewHolder.tvAttendanceRealAccount.setText(mDatas.get(position).getAttendanceReal() + "");
            viewHolder.tvClickMore.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class AttendanceDetailViewHolder extends RecyclerView.ViewHolder {

        TextView tvAttendanceTime;
        TextView tvTvAttendanceAccount;
        TextView tvAttendanceRealAccount;
        LinearLayout tvClickMore;
        TextView tvDataCardCount;

        public AttendanceDetailViewHolder(View itemView) {
            super(itemView);
            tvDataCardCount = (TextView) itemView.findViewById(R.id.tv_data_card_count);
            tvAttendanceTime = (TextView) itemView.findViewById(R.id.tv_attendance_time);
            tvTvAttendanceAccount = (TextView) itemView.findViewById(R.id.tv_tv_attendance_account);
            tvAttendanceRealAccount = (TextView) itemView.findViewById(R.id.tv_attendance_real_account);
            tvClickMore = (LinearLayout) itemView.findViewById(R.id.tv_click_more);

        }
    }
}
