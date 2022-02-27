package com.njcit.robotquery.moudle.chat.messages;

import android.view.View;

import com.njcit.robotquery.R;
import com.njcit.robotquery.moudle.chat.fixtures.MessagesListFixtures;
import com.njcit.robotquery.moudle.chat.models.DefaultUser;
import com.njcit.robotquery.view.chatUI.DefaultViewHodler.IncomingMessageViewHolder;


public class CustomIncomingMessageViewHolder
        extends IncomingMessageViewHolder<MessagesListFixtures.Message> {
    private View onlineView;

    public CustomIncomingMessageViewHolder(View itemView) {
        super(itemView);
      //  onlineView = itemView.findViewById(R.id.online);
    }

    @Override
    public void onBind(MessagesListFixtures.Message message) {
        super.onBind(message);

        boolean isOnline = ((DefaultUser) message.getUser()).isOnline();
//        if (isOnline) {
//            onlineView.setBackgroundResource(R.drawable.shape_bubble_online);
//        } else {
//            onlineView.setBackgroundResource(R.drawable.shape_bubble_offline);
//        }
    }
}
