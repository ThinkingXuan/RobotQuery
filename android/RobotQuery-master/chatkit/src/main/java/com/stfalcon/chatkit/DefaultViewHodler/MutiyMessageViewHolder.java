package com.stfalcon.chatkit.DefaultViewHodler;

import android.view.View;

import com.stfalcon.chatkit.commons.chatinterface.DefaultMessageViewHolder;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.messages.MessagesListStyle;

/**
 * Created by youxuan on 2017/4/2 0002.
 */

public  class MutiyMessageViewHolder<MESSAGE extends IMessage>
        extends MessagesListAdapter.BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {


    public MutiyMessageViewHolder(View itemView) {
        super(itemView);

    }

    @Override
    public void onBind(MESSAGE message) {
//            bubble.setSelected(isSelected());
//
//            text.setText(message.getText());
//            time.setText(DateFormatter.format(message.getCreatedAt(), DateFormatter.Template.TIME));
//
//            if (userAvatar != null) {
//                boolean isAvatarExists = message.getUser().getAvatar() != null && !message.getUser().getAvatar().isEmpty();
//                userAvatar.setVisibility(isAvatarExists ? View.VISIBLE : View.GONE);
//                if (isAvatarExists && imageLoader != null) {
//                    imageLoader.loadImage(userAvatar, message.getUser().getAvatar());
//                }
//            }
    }

    @Override
    public void applyStyle(MessagesListStyle style) {
//            bubble.setPadding(style.getOutcomingDefaultBubblePaddingLeft(),
//                    style.getOutcomingDefaultBubblePaddingTop(),
//                    style.getOutcomingDefaultBubblePaddingRight(),
//                    style.getOutcomingDefaultBubblePaddingBottom());
//            bubble.setBackground(style.getOutcomingBubbleDrawable());
//
//            text.setTextColor(style.getOutcomingTextColor());
//            text.setTextSize(TypedValue.COMPLEX_UNIT_PX, style.getOutcomingTextSize());
//
//            time.setTextColor(style.getOutcomingTimeTextColor());
//            time.setTextSize(TypedValue.COMPLEX_UNIT_PX, style.getOutcomingTimeTextSize());
    }
}