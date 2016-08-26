package com.cloudcof.domain;

import javax.persistence.*;

/**
 * Created by simon on 2016/8/26.
 */
@Entity
@Table(name = "cl_cof_app_update")
public class AppVersion {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "version_name")
    private String versionName;

    @Column(name = "app_url")
    private String appUrl;

    @Column(name = "upload_time")
    private Long uploadTime;

    @Column(name = "update_message")
    private String updateMessage;

    private Short remark;

    @Column(name = "version_code")
    private Integer versionCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public Short getRemark() {
        return remark;
    }

    public void setRemark(Short remark) {
        this.remark = remark;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }
}
