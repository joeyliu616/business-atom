package com.aoe.sms.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joey on 15-12-18.
 */
@Entity
@Table(name="t_sms",uniqueConstraints={
    @UniqueConstraint(
            columnNames = {"bizNo","firstParty"}
    )
})
public class SMS{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String bizNo;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String content;

    @Column
    private Boolean isReceived;

    //短信发送服务提供商.
    @Column(nullable = false)
    private String sp;

    //甲方. 短信发送的请求方
    @Column(nullable = false)
    private String firstParty;

    @Column
    private Boolean isSent;

    @Column
    @CreationTimestamp
    private Date createTime;

    @Column
    @UpdateTimestamp
    private Date updateTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean isReceived() {
        return isReceived;
    }

    public void setIsReceived(Boolean isReceived) {
        this.isReceived = isReceived;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public Boolean isSent() {
        return isSent;
    }

    public void setIsSent(Boolean isSent) {
        this.isSent = isSent;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }
}
