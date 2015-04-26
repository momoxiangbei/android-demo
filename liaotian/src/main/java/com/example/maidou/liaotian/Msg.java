package com.example.maidou.liaotian;

/**
 * Created by maidou on 2015/4/23.
 */
public class Msg {
    public static final int TYPE_RECERIVED = 0;
    public static final int TYPE_SENT = 1;

    private String content;

    private int type;

    public Msg(String content,int type){
        this.content = content;
        this.type =type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
