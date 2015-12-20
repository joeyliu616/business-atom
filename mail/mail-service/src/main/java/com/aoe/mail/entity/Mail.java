package com.aoe.mail.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by joey on 15-12-18.
 * 邮件发送记录. 后期替换成mongo
 */
@Entity
@Table(name="t_mail",uniqueConstraints={
    @UniqueConstraint(
            columnNames = {"bizNo","firstParty"}
    )
})
public class Mail {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String bizNo;

    @Column(nullable = false)
    private String senderAdderss;

    @Column(nullable = false)
    private String address;

    @Column
    private String title;

    @Column(nullable = false)
    private String content;

    //甲方. 短信发送的请求方
    @Column(nullable = false)
    private String firstParty;

    @Column(nullable = false)
    private boolean isSent;

    @Column
    @CreationTimestamp
    private Date createTime;

    @Column
    @UpdateTimestamp
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getSenderAdderss() {
        return senderAdderss;
    }

    public void setSenderAdderss(String senderAdderss) {
        this.senderAdderss = senderAdderss;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setIsSent(boolean isSent) {
        this.isSent = isSent;
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
}
