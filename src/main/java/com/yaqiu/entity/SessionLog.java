package com.yaqiu.entity;

public class SessionLog {
    private String id;

    private String ip;

    private String deviceType;

    private String browserName;

    private String browserGroup;

    private String browserVersion;

    private String osName;

    private String osGroup;

    private String userId;

    private String createTime;

    private String province;

    private String city;

    private String isp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName == null ? null : browserName.trim();
    }

    public String getBrowserGroup() {
        return browserGroup;
    }

    public void setBrowserGroup(String browserGroup) {
        this.browserGroup = browserGroup == null ? null : browserGroup.trim();
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion == null ? null : browserVersion.trim();
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName == null ? null : osName.trim();
    }

    public String getOsGroup() {
        return osGroup;
    }

    public void setOsGroup(String osGroup) {
        this.osGroup = osGroup == null ? null : osGroup.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp == null ? null : isp.trim();
    }
}