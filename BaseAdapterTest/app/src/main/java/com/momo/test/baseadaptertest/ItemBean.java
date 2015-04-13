package com.momo.test.baseadaptertest;

/**
 * Created by mmxb on 2015/4/13.
 */


/**
 * item数据封装
 */
public class ItemBean  {
    private int ItemImageResId;
    private String ItemTitle;
    private String ItemContent;

    public ItemBean(int itemImageResId, String itemTitle, String itemContent) {
        ItemImageResId = itemImageResId;
        ItemTitle = itemTitle;
        ItemContent = itemContent;
    }

    public void setItemImageResId(int itemImageResId) {
        ItemImageResId = itemImageResId;
    }

    public void setItemTitle(String itemTitle) {
        ItemTitle = itemTitle;
    }

    public void setItemContent(String itemContent) {
        ItemContent = itemContent;
    }

    public int getItemImageResId() {
        return ItemImageResId;
    }

    public String getItemTitle() {
        return ItemTitle;
    }

    public String getItemContent() {
        return ItemContent;
    }
}
