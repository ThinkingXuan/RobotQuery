package com.jialin.chat.adapter;


import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jialin.chat.Message;
import com.jialin.chat.util.DateUtil;
import com.jialin.chat.util.ImageUtils;
import com.jialin.chat.view.RecycleViewDivider;
import com.njcit.chat.R;


public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Message> data = null;
    public static final String TAG = "MessageAdapter";

    public MessageAdapter(Context context, List<Message> list) {
        super();
        this.context = context;
        this.data = list;
    }

    //定义两个类型，一个是发送方，一个是回复方
    public enum Type {
        RIGHT_MESSAGE,LEFT_MESSAGE,LEFT_EXPAND_MEUN;
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId: ");
        return position;
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return data != null ? data.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder: ");
        //加载Item View的时候根据不同的Type加载不同的布局
        if (viewType == Type.LEFT_MESSAGE.ordinal()) {

            return new LeftViewHolder(LayoutInflater.from(context).inflate(R.layout.msg_item_left, parent, false));
        } else if(viewType == Type.RIGHT_MESSAGE.ordinal()){
            return new RightViewHolder(LayoutInflater.from(context).inflate(R.layout.msg_item_right, parent, false));
        }else if(viewType == Type.LEFT_EXPAND_MEUN.ordinal()){
            return new LeftViewHolder_moudle(LayoutInflater.from(context).inflate(R.layout.module_recycler_item,parent,false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: ");
        Message message = data.get(position);

        if (holder instanceof LeftViewHolder) {
            BindViewLeftHolder((LeftViewHolder) holder, message, position);
        } else if (holder instanceof RightViewHolder) {
            BindViewRightHolder((RightViewHolder) holder, message, position);
        } else if (holder instanceof LeftViewHolder_moudle){
            BindViewLeft_moudle_Holder((LeftViewHolder_moudle)holder,message,position);
        }
    }


    private void BindViewLeftHolder(LeftViewHolder holder, Message message, int position) {

        Log.d(TAG, "BindViewLeftHolder: ");
        try {
            String dateString = DateFormat.format("yyyy-MM-dd h:mmaa", message.getTime()).toString();
            String[] t = dateString.split(" ");
            //viewHolder.sendTimeTextView.setText(t[1]);
            if (position == 0) {
                holder.sendDateTextView.setText(t[0]);
                holder.sendDateTextView.setVisibility(View.VISIBLE);
            } else {

                Message pmsg = data.get(position - 1);
                if (DateUtil.inSameDay(pmsg.getTime(), message.getTime())) {
                    //viewHolder.sendDateTextView.setVisibility(View.GONE);
                    holder.sendDateTextView.setText(DateUtil.friendlyTime(message.getTime()));
                } else {
                    holder.sendDateTextView.setText(t[0]);
                    //viewHolder.sendDateTextView.setVisibility(View.VISIBLE);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //设置个人头像
        // ImageUtils.loadChatUserImg(context, viewHolder.userAvatarImageView, message.getIsSend() ? Integer.parseInt(message.getFromUserAvatar()) : Integer.parseInt(message.getToUserAvatar()));

        //viewHolder.userNameTextView.setText(message.getToUserName());


        switch (message.getType()) {
            case 0://text
                holder.textTextView.setText(message.getContent());
                holder.textTextView.setVisibility(View.VISIBLE);
                // viewHolder.photoImageView.setVisibility(View.GONE);
                // viewHolder.faceImageView.setVisibility(View.GONE);


//                // viewHolder.failImageView.setVisibility(View.GONE);
//                viewHolder.sendingProgressBar.setVisibility(View.GONE);
//
//                LayoutParams sendTimeTextViewLayoutParams = (LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
//                sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.textTextView);
//                viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);


                break;
            case 1://photo
                holder.textTextView.setVisibility(View.GONE);
                // viewHolder.photoImageView.setVisibility(View.VISIBLE);
                //   viewHolder.faceImageView.setVisibility(View.GONE);

                //TODO set image
                int id = context.getResources().getIdentifier(message.getContent(), "drawable", context.getPackageName());
                // viewHolder.photoImageView.setImageResource(id);

//
//
//                    //viewHolder.failImageView.setVisibility(View.GONE);
//                    LayoutParams sendTimeTextViewLayoutParams = (LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
//                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.photoImageView);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);


                break;

            case 2://face
                // viewHolder.photoImageView.setVisibility(View.GONE);
                holder.textTextView.setVisibility(View.GONE);
                //  viewHolder.faceImageView.setVisibility(View.VISIBLE);
                int resId = context.getResources().getIdentifier(message.getContent(), "drawable", context.getPackageName());
                //  ImageUtils.loadImgResourceId(context, viewHolder.faceImageView, resId);
                //viewHolder.faceImageView.setImageResource(resId);


//                //viewHolder.failImageView.setVisibility(View.GONE);
//
//                LayoutParams sendTimeTextViewLayoutParams = (LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
//                sendTimeTextViewLayoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.faceImageView);
//                viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);
                break;
            default:
                holder.textTextView.setText(message.getContent());
                //  viewHolder.photoImageView.setVisibility(View.GONE);
                //  viewHolder.faceImageView.setVisibility(View.GONE);
                break;
        }


    }

    private void BindViewRightHolder(RightViewHolder holder, Message message, int position) {

        Log.d(TAG, "BindViewRightHolder: ");
        try {
            String dateString = DateFormat.format("yyyy-MM-dd h:mmaa", message.getTime()).toString();
            String[] t = dateString.split(" ");
            //viewHolder.sendTimeTextView.setText(t[1]);
            if (position == 0) {
                holder.sendDateTextView.setText(t[0]);
                holder.sendDateTextView.setVisibility(View.VISIBLE);
            } else {

                Message pmsg = data.get(position - 1);
                if (DateUtil.inSameDay(pmsg.getTime(), message.getTime())) {
                    //viewHolder.sendDateTextView.setVisibility(View.GONE);
                    holder.sendDateTextView.setText(DateUtil.friendlyTime(message.getTime()));
                } else {
                    holder.sendDateTextView.setText(t[0]);
                    //viewHolder.sendDateTextView.setVisibility(View.VISIBLE);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //设置个人头像
        // ImageUtils.loadChatUserImg(context, viewHolder.userAvatarImageView, message.getIsSend() ? Integer.parseInt(message.getFromUserAvatar()) : Integer.parseInt(message.getToUserAvatar()));

        //viewHolder.userNameTextView.setText(message.getToUserName());


        switch (message.getType()) {

            case 0://text
                holder.textTextView.setText(message.getContent());
                holder.textTextView.setVisibility(View.VISIBLE);

                holder.photoImageView.setVisibility(View.GONE);
                holder.faceImageView.setVisibility(View.GONE);


//                LayoutParams sendTimeTextViewLayoutParams = (LayoutParams) holder.sendTimeTextView.getLayoutParams();
//                sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.textTextView);
//                holder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                // LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
//                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.textTextView);
//                    if (message.getSendSucces() != null && message.getSendSucces() == false) {
//                        viewHolder.failImageView.setVisibility(View.VISIBLE);
//                        viewHolder.failImageView.setLayoutParams(layoutParams);
//                    } else {
//                        viewHolder.failImageView.setVisibility(View.GONE);
//                    }

                if (message.getState() != null && message.getState() == 0) {
                    holder.sendingProgressBar.setVisibility(View.VISIBLE);
                    // viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                } else {
                    holder.sendingProgressBar.setVisibility(View.GONE);
                }

                break;
            case 1://photo
                holder.textTextView.setVisibility(View.GONE);
                // viewHolder.photoImageView.setVisibility(View.VISIBLE);
                //   viewHolder.faceImageView.setVisibility(View.GONE);

                //TODO set image
                int id = context.getResources().getIdentifier(message.getContent(), "drawable", context.getPackageName());
                // viewHolder.photoImageView.setImageResource(id);


//                    LayoutParams sendTimeTextViewLayoutParams = (LayoutParams) holder.sendTimeTextView.getLayoutParams();
//                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.photoImageView);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                // LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                //   layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.photoImageView);
                if (message.getSendSucces() != null && message.getSendSucces() == false) {
                    //  viewHolder.failImageView.setVisibility(View.VISIBLE);
                    // viewHolder.failImageView.setLayoutParams(layoutParams);
                } else {
                    //viewHolder.failImageView.setVisibility(View.GONE);
                }

                if (message.getState() != null && message.getState() == 0) {
                    holder.sendingProgressBar.setVisibility(View.VISIBLE);
                    //    viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                } else {
                    holder.sendingProgressBar.setVisibility(View.GONE);
                }

                break;

            case 2://face
                Log.d(TAG, "BindViewRightHolder: facesdffffffffffffffffp");
                holder.photoImageView.setVisibility(View.GONE);
                holder.textTextView.setVisibility(View.GONE);
                holder.faceImageView.setVisibility(View.VISIBLE);
                int resId = context.getResources().getIdentifier(message.getContent(), "drawable", context.getPackageName());
                ImageUtils.loadImgResourceId(context, holder.faceImageView, resId);
                holder.faceImageView.setImageResource(resId);

//
//                    LayoutParams sendTimeTextViewLayoutParams = (LayoutParams) viewHolder.sendTimeTextView.getLayoutParams();
//                    sendTimeTextViewLayoutParams.addRule(RelativeLayout.LEFT_OF, R.id.faceImageView);
//                    viewHolder.sendTimeTextView.setLayoutParams(sendTimeTextViewLayoutParams);

                //  LayoutParams layoutParams = (LayoutParams) viewHolder.failImageView.getLayoutParams();
                //   layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.faceImageView);
//                    if (message.getSendSucces() != null && message.getSendSucces() == false) {
//                        viewHolder.failImageView.setVisibility(View.VISIBLE);
//                        viewHolder.failImageView.setLayoutParams(layoutParams);
//                    } else {
//                        viewHolder.failImageView.setVisibility(View.GONE);
//                    }

                if (message.getState() != null && message.getState() == 0) {
                    holder.sendingProgressBar.setVisibility(View.VISIBLE);
                    //viewHolder.sendingProgressBar.setLayoutParams(layoutParams);
                } else {
                    holder.sendingProgressBar.setVisibility(View.GONE);
                }

                break;
            default:
                holder.textTextView.setText(message.getContent());
                //  viewHolder.photoImageView.setVisibility(View.GONE);
                //  viewHolder.faceImageView.setVisibility(View.GONE);
                break;

        }
    }


    //绑定多级菜单的ViewHolder
    private void BindViewLeft_moudle_Holder(LeftViewHolder_moudle holder, Message message, int postion) {

        holder.recyclerModuleItem.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.recyclerModuleItem.addItemDecoration(new RecycleViewDivider(
                context, LinearLayoutManager.HORIZONTAL, 3, context.getResources().getColor(R.color.divider)));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        ExpandableListAdapter.Item fruits = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Fruits");
        fruits.invisibleChildren = new ArrayList<>();
        fruits.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Apple"));
        fruits.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Orange"));
        fruits.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Banana"));

        ExpandableListAdapter.Item cars = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Cars");
        cars.invisibleChildren = new ArrayList<>();
        cars.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Audi"));
        cars.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Aston Martin"));
        cars.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "BMW"));

        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Places");
        places.invisibleChildren = new ArrayList<>();
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Kerala"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Tamil Nadu"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Karnataka"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Maharashtra"));

        data.add(fruits);
        data.add(cars);
        data.add(places);
        holder.recyclerModuleItem.setAdapter(new ExpandableListAdapter(data,context));

    }
    @Override
    public int getItemViewType(int position) {
        boolean isSend = data.get(position).getIsSend();
        Log.d(TAG, "getItemViewType: "+isSend);
        if (!isSend){
            if (data.get(position).getType() == 3){
                return Type.LEFT_EXPAND_MEUN.ordinal();
            }
            return Type.LEFT_MESSAGE.ordinal();
        }else {
            return Type.RIGHT_MESSAGE.ordinal();
        }

      //  return !isSend ? Type.LEFT_MESSAGE.ordinal() : Type.RIGHT_MESSAGE.ordinal();
    }


    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }


    /**
     * 局部更新的方式
     *
     * @param listView
     * @param index
     * @param sendStatus
     */

    @Deprecated
    public void onUpDataItem(ListView listView, int index, int sendStatus) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();//可见区第一个Item脚标
        int lastVisiblePosition = listView.getLastVisiblePosition();//可见区最后一个Item脚标
        if (index - firstVisiblePosition >= 0 && index <= lastVisiblePosition) {
            // 更新目标view
            View view = listView.getChildAt(index - firstVisiblePosition);
            if (view == null)
                return;
            // 从view中取得holder
            LeftViewHolder holder = (LeftViewHolder) view.getTag();
            if (sendStatus == 3) {
                holder.sendingProgressBar.setVisibility(View.GONE);
                //  LayoutParams layoutParams = (LayoutParams) holder.failImageView.getLayoutParams();
                //   layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.faceImageView);
                // holder.failImageView.setVisibility(View.VISIBLE);
                //  holder.failImageView.setLayoutParams(layoutParams);
            } else {
                holder.sendingProgressBar.setVisibility(View.GONE);
                // holder.failImageView.setVisibility(View.GONE);
            }
        }
    }

    public static class RightViewHolder extends RecyclerView.ViewHolder {

        TextView sendDateTextView;
        ImageView userAvatarImageView;
        TextView textTextView;
        ImageView photoImageView;
        ImageView faceImageView;
        TextView sendTimeTextView;
        TextView userNameTextView;
        ImageView failImageView;
        ProgressBar sendingProgressBar;

        public RightViewHolder(View itemView) {
            super(itemView);

            sendDateTextView = (TextView) itemView.findViewById(R.id.sendDateTextView);
            userAvatarImageView = (ImageView) itemView.findViewById(R.id.userAvatarImageView);
            textTextView = (TextView) itemView.findViewById(R.id.textTextView);
            photoImageView = (ImageView) itemView.findViewById(R.id.photoImageView);
            faceImageView = (ImageView) itemView.findViewById(R.id.faceImageView);
            sendTimeTextView = (TextView) itemView.findViewById(R.id.sendTimeTextView);
            userNameTextView = (TextView) itemView.findViewById(R.id.userNameTextView);
            failImageView = (ImageView) itemView.findViewById(R.id.failImageView);
            sendingProgressBar = (ProgressBar) itemView.findViewById(R.id.sendingProgressBar);


        }
    }

    public static class LeftViewHolder extends RecyclerView.ViewHolder {

        TextView sendDateTextView;
        ImageView userAvatarImageView;
        TextView textTextView;
        ImageView photoImageView;
        ImageView faceImageView;
        TextView sendTimeTextView;
        TextView userNameTextView;
        ImageView failImageView;
        ProgressBar sendingProgressBar;


        public LeftViewHolder(View itemView) {
            super(itemView);
            sendDateTextView = (TextView) itemView.findViewById(R.id.sendDateTextView);
            userAvatarImageView = (ImageView) itemView.findViewById(R.id.userAvatarImageView);
            textTextView = (TextView) itemView.findViewById(R.id.textTextView);
            photoImageView = (ImageView) itemView.findViewById(R.id.photoImageView);
            faceImageView = (ImageView) itemView.findViewById(R.id.faceImageView);
            sendTimeTextView = (TextView) itemView.findViewById(R.id.sendTimeTextView);
            userNameTextView = (TextView) itemView.findViewById(R.id.userNameTextView);
            failImageView = (ImageView) itemView.findViewById(R.id.failImageView);
            sendingProgressBar = (ProgressBar) itemView.findViewById(R.id.sendingProgressBar);
        }
    }

    //加载初始化多级列表的ViewHoldler
    public static class LeftViewHolder_moudle extends RecyclerView.ViewHolder{

        private RecyclerView recyclerModuleItem;

        public LeftViewHolder_moudle(View itemView) {
            super(itemView);
            recyclerModuleItem = (RecyclerView) itemView.findViewById(R.id.recycler_module_item);
        }
    }


}


