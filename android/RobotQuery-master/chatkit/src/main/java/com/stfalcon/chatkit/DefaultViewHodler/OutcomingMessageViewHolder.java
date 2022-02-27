package com.stfalcon.chatkit.DefaultViewHodler;

import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stfalcon.chatkit.R;
import com.stfalcon.chatkit.commons.chatinterface.DefaultMessageViewHolder;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.messages.MessagesListStyle;
import com.stfalcon.chatkit.utils.DateFormatter;

/**
 * Created by youxuan on 2017/4/2 0002.
 */

public  class OutcomingMessageViewHolder<MESSAGE extends IMessage>
        extends MessagesListAdapter.BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {

    protected ViewGroup bubble;
    protected TextView text;
    protected TextView time;
    protected ImageView userAvatar;

    public OutcomingMessageViewHolder(View itemView) {
        super(itemView);
        bubble = (ViewGroup) itemView.findViewById(R.id.bubble);
        text = (TextView) itemView.findViewById(R.id.messageText);
        time = (TextView) itemView.findViewById(R.id.messageTime);
        userAvatar = (ImageView) itemView.findViewById(R.id.messageUserAvatar);
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
