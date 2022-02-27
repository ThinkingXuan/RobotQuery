package com.njcit.robotquery.adapter.subAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.bean.server.Orders;
import com.njcit.robotquery.bean.server.Products;

import java.util.List;

/**
 * Created by youxuan on 2017/5/16 0016.
 */

public class OrdersDetatilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Orders.OrderBean> mDatas;

    public OrdersDetatilAdapter(Context context, List<Orders.OrderBean> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_order_detail, parent, false);
        RecyclerView.ViewHolder viewHolder = new OrdersDetatilAdapter.OrdersDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OrdersDetailViewHolder viewHolder = (OrdersDetailViewHolder) holder;
        if (viewHolder != null) {
            viewHolder.tvOrderNumberDetail.setText(mDatas.get(position).getOrderId() + "");
            viewHolder.tvOrderPrice.setText(mDatas.get(position).getShipperPrice() + "元");
            viewHolder.tvOrderUser.setText(mDatas.get(position).getOwnerName() + "");
            viewHolder.tvOrderAddress.setText(mDatas.get(position).getOwnerAddress() + "");
            viewHolder.tvOrderTime.setText(mDatas.get(position).getOrderDate() + "");
            viewHolder.tvOrderReceiveTime.setText(mDatas.get(position).getGetOrderDate() + "");
            viewHolder.tvClickMore.setVisibility(View.GONE);
            viewHolder.tvDataCardCount.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class OrdersDetailViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOrderNumberDetail;
        private TextView tvOrderProductId;
        private TextView tvOrderPrice;
        private TextView tvOrderUser;
        private TextView tvOrderAddress;
        private TextView tvOrderTime;
        private TextView tvOrderReceiveTime;
        private LinearLayout tvClickMore;

        TextView tvDataCardCount;

        public OrdersDetailViewHolder(View itemView) {
            super(itemView);
            tvOrderNumberDetail = (TextView) itemView.findViewById(R.id.tv_order_number_detail);
            tvOrderProductId = (TextView) itemView.findViewById(R.id.tv_order_product_id);
            tvOrderPrice = (TextView) itemView.findViewById(R.id.tv_order_price);
            tvOrderUser = (TextView) itemView.findViewById(R.id.tv_order_user);
            tvOrderAddress = (TextView) itemView.findViewById(R.id.tv_order_address);
            tvOrderTime = (TextView) itemView.findViewById(R.id.tv_order_time);
            tvOrderReceiveTime = (TextView) itemView.findViewById(R.id.tv_order_receive_time);
            tvClickMore = (LinearLayout) itemView.findViewById(R.id.tv_click_more);
            tvDataCardCount = (TextView) itemView.findViewById(R.id.tv_data_card_count);

        }
    }
}
