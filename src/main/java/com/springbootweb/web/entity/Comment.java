package com.springbootweb.web.entity;

public class Comment {
    private String cid;
    private String username;
    private String topic;

    public Comment() {

    }

    public Comment(String cid, String username, String topic) {
        this.cid = cid;
        this.username = username;
        this.topic = topic;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid='" + cid + '\'' +
                ", username='" + username + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
