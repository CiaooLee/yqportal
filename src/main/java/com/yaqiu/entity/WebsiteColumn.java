package com.yaqiu.entity;

public class WebsiteColumn {
    private String id;

    private String name;

    private String identifier;

    private Byte sequenceNumber;

    private Boolean status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public Byte getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Byte sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}