package com.njcit.robotquery.adapter.subAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.bean.server.Products;

import java.util.List;

/**
 * Created by youxuan on 2017/5/16 0016.
 */

public class ProductDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Products.ProductBean> mDatas;

    public ProductDetailAdapter(Context context, List<Products.ProductBean> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_products_detail,parent,false);
        RecyclerView.ViewHolder viewHolder = new ProductDetailAdapter.ProductDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductDetailViewHolder viewHolder = (ProductDetailViewHolder) holder;
        if (viewHolder!=null){
            viewHolder.tvProductUsernameDetail.setText(mDatas.get(position).getProductName());
            viewHolder.tvProductTypeDetail.setText(mDatas.get(position).getTypeId());
            viewHolder.tvProductPriceDetail.setText(mDatas.get(position).getPrice());
            viewHolder.tvProductPerCountDetail.setText(mDatas.get(position).getPerCount());
            viewHolder.tvProductStorageCountDetail.setText(mDatas.get(position).getStorageCount());
            viewHolder.tvProductSuppler.setText(mDatas.get(position).getSupplierId());
            viewHolder.tvClickMore.setVisibility(View.GONE);
            viewHolder.tvDataCardCount.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ProductDetailViewHolder extends RecyclerView.ViewHolder{

        private TextView tvProductUsernameDetail;
        private TextView tvProductTypeDetail;
        private TextView tvProductPriceDetail;
        private TextView tvProductStorageCountDetail;
        private TextView tvProductSuppler;
        private LinearLayout tvClickMore;
        private TextView tvProductPerCountDetail;


        TextView tvDataCardCount;

        public ProductDetailViewHolder(View itemView) {
            super(itemView);

            tvProductUsernameDetail = (TextView) itemView.findViewById(R.id.tv_product_username_detail);
            tvProductTypeDetail = (TextView) itemView.findViewById(R.id.tv_product_type_detail);
            tvProductPriceDetail = (TextView) itemView.findViewById(R.id.tv_product_price_detail);
            tvProductPerCountDetail = (TextView) itemView.findViewById(R.id.tv_product_per_count_detail);
            tvProductStorageCountDetail = (TextView) itemView.findViewById(R.id.tv_product_storageCount_detail);
            tvProductSuppler = (TextView) itemView.findViewById(R.id.tv_product_suppler);
            tvClickMore = (LinearLayout) itemView.findViewById(R.id.tv_click_more);
            tvDataCardCount = (TextView) itemView.findViewById(R.id.tv_data_card_count);

        }
    }
}
