package com.njcit.robotquery.view.chatUI.DefaultViewHodler;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njcit.robotquery.R;
import com.njcit.robotquery.view.CircleImageView;
import com.njcit.robotquery.view.chatUI.commons.chatinterface.DefaultMessageViewHolder;
import com.njcit.robotquery.view.chatUI.commons.models.IMessage;
import com.njcit.robotquery.view.chatUI.messages.MessagesListAdapter;
import com.njcit.robotquery.view.chatUI.messages.MessagesListStyle;
import com.njcit.robotquery.view.chatUI.utils.DateFormatter;


/**
 * Created by youxuan on 2017/4/2 0002.
 */

public  class OutcomingMessageViewHolder<MESSAGE extends IMessage>
        extends MessagesListAdapter.BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {

    protected ViewGroup bubble;
    protected TextView text;
    protected TextView time;
    protected CircleImageView userAvatar;

    public OutcomingMessageViewHolder(View itemView) {
        super(itemView);
        bubble = (ViewGroup) itemView.findViewById(R.id.bubble);
        text = (TextView) itemView.findViewById(R.id.messageText);
        time = (TextView) itemView.findViewById(R.id.messageTime);
        userAvatar = (CircleImageView) itemView.findViewById(R.id.messageUserAvatar);
    }

    @Override
    public void onBind(MESSAGE message) {
        bubble.setSelected(isSelected());

        text.setText(message.getText());
        time.setText(DateFormatter.format(message.getCreatedAt(), DateFormatter.Template.TIME));

        if (userAvatar != null) {
            boolean isAvatarExists = message.getUser().getAvatar() != null && !message.getUser().getAvatar().isEmpty();
            userAvatar.setVisibility(isAvatarExists ? View.VISIBLE : View.GONE);
            if (isAvatarExists && imageLoader != null) {
                imageLoader.loadImage(userAvatar, message.getUser().getAvatar());
            }
        }
    }

    @Override
    public void applyStyle(MessagesListStyle style) {
        bubble.setPadding(style.getOutcomingDefaultBubblePaddingLeft(),
                style.getOutcomingDefaultBubblePaddingTop(),
                style.getOutcomingDefaultBubblePaddingRight(),
                style.getOutcomingDefaultBubblePaddingBottom());
        bubble.setBackground(style.getOutcomingBubbleDrawable());

        text.setTextColor(style.getOutcomingTextColor());
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, style.getOutcomingTextSize());

        time.setTextColor(style.getOutcomingTimeTextColor());
        time.setTextSize(TypedValue.COMPLEX_UNIT_PX, style.getOutcomingTimeTextSize());
    }
}
