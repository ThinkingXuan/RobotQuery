package com.jialin.chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.njcit.chat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youxuan on 09/06/15.
 */
public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;
    private Context mContext;
    private List<Item> data;

    public ExpandableListAdapter(List<Item> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = null;
        Context context = parent.getContext();
        float dp = context.getResources().getDisplayMetrics().density;
        int subItemPaddingLeft = (int) (18 * dp);
        int subItemPaddingTopAndBottom = (int) (5 * dp);


        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_header, parent, false);
        ListHeaderViewHolder holder = new ListHeaderViewHolder(view);
        return holder;


    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case HEADER:
                final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
                itemController.refferalItem = item;
                itemController.header_title.setText(item.text);
                if (item.invisibleChildren == null) {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_up_grey_600_24dp);
                } else {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_down_grey_600_24dp);
                }
                itemController.itemExpandView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = data.indexOf(itemController.refferalItem);
                            while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_down_grey_600_24dp);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.ic_keyboard_arrow_up_grey_600_24dp);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            case CHILD:
//                TextView itemTextView = (TextView) holder.itemView;
//                itemTextView.setText(data.get(position).text);
                ((ListHeaderViewHolder) holder).header_title.setText(data.get(position).text);
                ((ListHeaderViewHolder) holder).header_title.setText(data.get(position).text);
                ((ListHeaderViewHolder) holder).btn_expand_toggle.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_subdirectory_arrow_left_grey_600_24dp));


                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView header_title;
        private ImageView btn_expand_toggle;
        private LinearLayout itemExpandView;

        private Item refferalItem;

        private ListHeaderViewHolder(View itemView) {
            super(itemView);
            itemExpandView = (LinearLayout) itemView.findViewById(R.id.item_expandView);
            header_title = (TextView) itemView.findViewById(R.id.header_title);
            btn_expand_toggle = (ImageView) itemView.findViewById(R.id.btn_expand_toggle);
        }
    }


    public static class Item {
        public int type;
        public String text;
        public List<Item> invisibleChildren;

        public Item() {
        }

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }
    }
}
