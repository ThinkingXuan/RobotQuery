package com.njcit.robotquery.view.chatUI.commons.chatinterface;

/**
 * Created by youxuan on 2017/4/2 0002.
 */

/**
 * Interface definition for a callback to be invoked when next part of messages need to be loaded.
 */
public interface OnLoadMoreListener {

    /**
     * Fires when user scrolled to the end of list.
     *
     * @param page            next page to download.
     * @param totalItemsCount current items count.
     */
    void onLoadMore(int page, int totalItemsCount);
}