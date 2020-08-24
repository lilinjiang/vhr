package com.jiang.vhr.model;

import java.util.Date;

/**
 * @author 李林江
 * 用于websoket的实体类
 */
public class ChatMsg {
    /**
     * 代表消息从哪来
     */
    private String from;
    /**
     * 代表消息到哪去
     */
    private String to;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息时间
     */
    private Date date;


    private String fromNickname;

    public String getFromNickname() {
        return fromNickname;
    }

    public void setFromNickname(String fromNickname) {
        this.fromNickname = fromNickname;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
