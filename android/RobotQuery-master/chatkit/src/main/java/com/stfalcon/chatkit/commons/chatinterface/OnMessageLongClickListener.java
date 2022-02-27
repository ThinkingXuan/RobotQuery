package com.stfalcon.chatkit.commons.chatinterface;

/**
 * Created by youxuan on 2017/4/2 0002.
 */

import com.stfalcon.chatkit.commons.models.IMessage;

/**
 * Interface definition for a callback to be invoked when message item is long clicked.
 */
public interface OnMessageLongClickListener<MESSAGE extends IMessage> {

    /**
     * Fires when message was long clicked.
     *
     * @param message clicked message.
     */
    void onMessageLongClick(MESSAGE message);
}