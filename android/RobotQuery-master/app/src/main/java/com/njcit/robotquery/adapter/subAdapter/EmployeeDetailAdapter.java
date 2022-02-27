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

import java.util.List;

/**
 * Created by youxuan on 2017/4/30 0030.
 */

public class EmployeeDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<EmployeeBean.EmployeeData> mDatas;

    public EmployeeDetailAdapter(Context context,List<EmployeeBean.EmployeeData> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_employee,parent,false);
        RecyclerView.ViewHolder viewHolder = new EmployeeDetailAdapter.EmployeeDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EmployeeDetailViewHolder viewHolder = (EmployeeDetailViewHolder) holder;
        if (viewHolder!=null){
            viewHolder.tvEmployeeUsername.setText(mDatas.get(position).getUserUsername());
            viewHolder.tvEmployeeGender.setText(mDatas.get(position).getUserGender());
            viewHolder.tvEmployeeAgeCardItem.setText(mDatas.get(position).getUserAge()+"");
            viewHolder.tvEmployeePosition.setText(mDatas.get(position).getUserPosition());
            viewHolder.tvEmployeeSalary.setText(mDatas.get(position).getUserSalary()+"");
            viewHolder.tvEmployeeDepartment.setText(mDatas.get(position).getUserDepartment());
            viewHolder.tvClickMore.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class EmployeeDetailViewHolder extends RecyclerView.ViewHolder{

        private TextView tvEmployeeUsername;
        private TextView tvEmployeeGender;
        private TextView tvEmployeeAgeCardItem;
        private TextView tvEmployeePosition;
        private TextView tvEmployeeSalary;
        private TextView tvEmployeeDepartment;
        private LinearLayout tvClickMore;
        TextView tvDataCardCount;

        public EmployeeDetailViewHolder(View itemView) {
            super(itemView);
            tvEmployeeUsername = (TextView) itemView.findViewById(R.id.tv_employee_username);
            tvEmployeeGender = (TextView)itemView.findViewById(R.id.tv_employee_gender);
            tvEmployeeAgeCardItem = (TextView) itemView.findViewById(R.id.tv_employee_age_detail);
            tvEmployeePosition = (TextView) itemView.findViewById(R.id.tv_employee_position_detail);
            tvEmployeeSalary = (TextView) itemView.findViewById(R.id.tv_employee_salary_detail);
            tvEmployeeDepartment = (TextView) itemView.findViewById(R.id.tv_employee_department_detail);
            tvClickMore = (LinearLayout) itemView.findViewById(R.id.tv_click_more);
            tvDataCardCount = (TextView) itemView.findViewById(R.id.tv_data_card_count);

        }
    }
}
