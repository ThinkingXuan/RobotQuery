package com.stfalcon.chatkit.commons.chatinterface;

/**
 * Created by youxuan on 2017/4/2 0002.
 */

/**
 * Interface definition for a callback to be invoked when selected messages count is changed.
 */
public interface SelectionListener {

    /**
     * Fires when selected items count is changed.
     *
     * @param count count of selected items.
     */
    void onSelectionChanged(int count);
}
