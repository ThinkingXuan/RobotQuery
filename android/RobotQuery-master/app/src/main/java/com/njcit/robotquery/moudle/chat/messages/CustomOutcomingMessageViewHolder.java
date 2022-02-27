package com.njcit.robotquery.moudle.chat.messages;

import android.view.View;

import com.njcit.robotquery.moudle.chat.fixtures.MessagesListFixtures;
import com.njcit.robotquery.view.chatUI.DefaultViewHodler.OutcomingMessageViewHolder;

public class CustomOutcomingMessageViewHolder
        extends OutcomingMessageViewHolder<MessagesListFixtures.Message> {

    public CustomOutcomingMessageViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(MessagesListFixtures.Message message) {
        super.onBind(message);

        time.setText(message.getStatus() + " " + time.getText());
    }
}
