package com.stfalcon.chatkit.DefaultViewHodler;

/**
 * Created by youxuan on 2017/4/2 0002.
 */

import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stfalcon.chatkit.R;
import com.stfalcon.chatkit.commons.MyLinkMovementMethod;
import com.stfalcon.chatkit.commons.chatinterface.DefaultMessageViewHolder;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.messages.MessagesListStyle;
import com.stfalcon.chatkit.utils.DateFormatter;

/**
 * Default view holder implementation for incoming message
 */
public class IncomingMessageViewHolder<MESSAGE extends IMessage>
        extends MessagesListAdapter.BaseMessageViewHolder<MESSAGE> implements DefaultMessageViewHolder {

    protected ViewGroup bubble;
    protected TextView text;
    protected TextView time;
    protected ImageView userAvatar;

    public IncomingMessageViewHolder(View itemView) {
        super(itemView);
        bubble = (ViewGroup) itemView.findViewById(R.id.bubble);
        text = (TextView) itemView.findViewById(R.id.messageText);
        time = (TextView) itemView.findViewById(R.id.messageTime);
        userAvatar = (ImageView) itemView.findViewById(R.id.messageUserAvatar);
    }

    @Override
    public void onBind(MESSAGE message) {
        bubble.setSelected(isSelected());
        // text.setText(message.getText());
        text.setText(Html.fromHtml(message.getText()));
        //text.setMovementMethod(LinkMovementMethod.getInstance());
        text.setOnTouchListener(MyLinkMovementMethod.getInstance());
        time.setText(DateFormatter.format(message.getCreatedAt(), DateFormatter.Template.TIME));

        boolean isAvatarExists = imageLoader != null && message.getUser().getAvatar() != null && !message.getUser().getAvatar().isEmpty();
        userAvatar.setVisibility(isAvatarExists ? View.VISIBLE : View.GONE);
        if (isAvatarExists) {
            imageLoader.loadImage(userAvatar, message.getUser().getAvatar());
        }
    }

    @Override
    public void applyStyle(MessagesListStyle style) {
        bubble.setPadding(style.getIncomingDefaultBubblePaddingLeft(),
                style.getIncomingDefaultBubblePaddingTop(),
                style.getIncomingDefaultBubblePaddingRight(),
                style.getIncomingDefaultBubblePaddingBottom());
        bubble.setBackground(style.getIncomingBubbleDrawable());

        text.setTextColor(style.getIncomingTextColor());
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, style.getIncomingTextSize());

        userAvatar.getLayoutParams().width = style.getIncomingAvatarWidth();
        userAvatar.getLayoutParams().height = style.getIncomingAvatarHeight();

        time.setTextColor(style.getIncomingTimeTextColor());
        time.setTextSize(TypedValue.COMPLEX_UNIT_PX, style.getIncomingTimeTextSize());
    }


}
