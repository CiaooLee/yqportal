package com.yaqiu.entity;

public class OperationLog {
    private String id;

    private String sessionLogId;

    private Byte type;

    private String content;

    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSessionLogId() {
        return sessionLogId;
    }

    public void setSessionLogId(String sessionLogId) {
        this.sessionLogId = sessionLogId == null ? null : sessionLogId.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}