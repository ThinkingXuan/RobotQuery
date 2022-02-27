package com.njcit.robotquery.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.bean.server.Model;
import com.njcit.robotquery.constant.Constants;
import com.njcit.robotquery.moudle.modelchoose.ModelChooseActivity;
import com.njcit.robotquery.moudle.modelconfigure.AttendanceActivity;
import com.njcit.robotquery.moudle.modelconfigure.CheckConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.EmployeeModelConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.OrderConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.PostConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.ProductModelConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.SalaryQueryConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.SalasConfigureActivity;
import com.njcit.robotquery.moudle.modelconfigure.TrainActivity;
import com.njcit.robotquery.util.DateUtil;

import java.util.List;

/**
 * Created by youxuan on 2017/4/9 0009.
 */

public class ModelListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mModels;

    public ModelListAdapter(Context context, List<String> models) {
        mContext = context;
        mModels = models;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ModelListViewHolder viewHolder = new ModelListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_model_list, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ModelListViewHolder viewHolder = (ModelListViewHolder) holder;
        if (mModels.size() > 0) {
            viewHolder.tvContentModelListItem.setText(mModels.get(position));
            viewHolder.tvTimeModelListItem.setText(DateUtil.getCurrentTime_day());

            viewHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    toModelDetailActivity(position);

                }
            });
        }


    }

    private void toModelDetailActivity(int position) {
        String item = mModels.get(position);
        Intent intent = null;

        switch (item) {
            case "员工查询":
                intent = new Intent(mContext, EmployeeModelConfigureActivity.class);
                break;
            case "产品查询":
                intent = new Intent(mContext, ProductModelConfigureActivity.class);
                break;
            case "考勤查询":
                intent = new Intent(mContext, AttendanceActivity.class);
                break;
            case "招聘查询":
                intent = new Intent(mContext, PostConfigureActivity.class);
                break;
            case "员工培训":
                intent = new Intent(mContext, TrainActivity.class);
                break;
            case "绩效考核":
                intent = new Intent(mContext, CheckConfigureActivity.class);
                break;
            case "薪资查询":
                intent = new Intent(mContext, SalaryQueryConfigureActivity.class);
                break;
            case "订单查询":
                intent = new Intent(mContext, OrderConfigureActivity.class);
                break;
            case "销量查询":
                intent = new Intent(mContext, SalasConfigureActivity.class);
                break;
        }

        if (intent != null) {
            mContext.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    static class ModelListViewHolder extends RecyclerView.ViewHolder {


        private TextView tvContentModelListItem;
        private TextView tvTimeModelListItem;
        private LinearLayout mLinearLayout;


        public ModelListViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linear_model_item);
            tvContentModelListItem = (TextView) itemView.findViewById(R.id.tv_content_model_list_item);
            tvTimeModelListItem = (TextView) itemView.findViewById(R.id.tv_time_model_list_item);

        }
    }
}
