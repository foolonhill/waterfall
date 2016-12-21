package com.iwaimai.qa.waterfall.entity;

/**
 * 用于接受请求参数
 * Created by iWM on 2016/12/20.
 */
public class DbInfo {

    private String srcDb;

    private String destIp;

    private String destPort;

    private String destUser;

    private String destPassword;

    private String destDb;

    public String getSrcDb() {
        return srcDb;
    }

    public void setSrcDb(String srcDb) {
        this.srcDb = srcDb;
    }

    public String getDestIp() {
        return destIp;
    }

    public void setDestIp(String destIp) {
        this.destIp = destIp;
    }

    public String getDestPort() {
        return destPort;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort;
    }

    public String getDestUser() {
        return destUser;
    }

    public void setDestUser(String destUser) {
        this.destUser = destUser;
    }

    public String getDestPassword() {
        return destPassword;
    }

    public void setDestPassword(String destPassword) {
        this.destPassword = destPassword;
    }

    public String getDestDb() {
        return destDb;
    }

    public void setDestDb(String destDb) {
        this.destDb = destDb;
    }
}
