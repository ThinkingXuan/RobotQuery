package com.stfalcon.chatkit.DefaultViewHodler;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.stfalcon.chatkit.R;
import com.stfalcon.chatkit.bean.Employee;
import com.stfalcon.chatkit.commons.chatinterface.DefaultMessageViewHolder;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.messages.MessagesListStyle;
import com.stfalcon.chatkit.messages.fixtures.MessagesListFixtures;

/**
 * Created by youxuan on 2017/4/3 0003.
 */

public  class ImageMessageViewHolder<MESSAGE extends IMessage>
        extends MessagesListAdapter.BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {

    public static final String TAG = "ImageMessageViewHolder";
    private LinearLayout mLinearContent;
    public ImageMessageViewHolder(View itemView) {
        super(itemView);
        mLinearContent = (LinearLayout) itemView.findViewById(R.id.Linear_content);
    }

    @Override
    public void onBind(MESSAGE message) {

        MessagesListFixtures.Message msg = (MessagesListFixtures.Message) message;
        Log.d(TAG, "onBind: "+message.getView());
        mLinearContent.addView(message.getView());
    }

    @Override
    public void applyStyle(MessagesListStyle style) {

    }
}